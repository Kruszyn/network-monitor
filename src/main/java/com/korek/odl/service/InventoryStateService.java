package com.korek.odl.service;

import com.korek.odl.model.Alert;
import com.korek.odl.model.InventoryState;
import com.korek.odl.model.json.NodeBody;
import com.korek.odl.model.json.NodeConnector;
import com.korek.odl.model.json.Nodes;
import com.korek.odl.repository.InventoryStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class InventoryStateService {

    @Autowired
    private InventoryStateRepository inventoryStateRepository;
    @Autowired
    private AlertService alertService;
    //TODO przenieść do pliku properties
    private static final String INVENTORY_NODES_URL = "http://185.243.54.14:8080/restconf/operational/opendaylight-inventory:nodes/";

    @Scheduled(cron = "0 0/1 * * * *")
    public void checkLinkStates() {
        List<InventoryState> inventoryStateList = getDataFromOdl();
        for(InventoryState iState : inventoryStateList){
            InventoryState lastInstance = inventoryStateRepository.findByName(iState.getName());
            if(lastInstance != null){
                lastInstance.setState(iState.getState());
                inventoryStateRepository.save(lastInstance);
            } else {
                inventoryStateRepository.save(iState);
            }
        }
    }

    private List<InventoryState> getDataFromOdl() {
        List<InventoryState> inventoryStateList = new LinkedList<>();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(
                new BasicAuthorizationInterceptor("admin", "admin"));
        Nodes nodes = restTemplate
                .getForObject(INVENTORY_NODES_URL, Nodes.class);

        for(NodeBody nodeBody : nodes.getNodes().getNodeBodies()) {
            for(NodeConnector nodeConnector : nodeBody.getNodeConnectorList()){
                InventoryState iState = new InventoryState();
                iState.setName(nodeConnector.getId());
                iState.setState(Boolean.parseBoolean(nodeConnector.getInventoryState().getLink()));
                iState.setType("LINK");
                if(iState.getState() && !iState.getName().endsWith("LOCAL"))addAlert(iState);
                inventoryStateList.add(iState);
            }
        }
        inventoryStateList.removeIf(o -> o.getName().trim().endsWith("LOCAL"));
        return inventoryStateList;
    }

    private void addAlert(InventoryState iState) {
        Alert alert = new Alert();
        alert.setInventoryName(iState.getName());
        alert.setTimestamp(new Timestamp(System.currentTimeMillis()));
        alert.setLevel("ERROR");
        alert.setMsg("Link down");
        alertService.addAlert(alert);
    }

    public List<InventoryState> findAll() {
        List<InventoryState> inventoryStateList = inventoryStateRepository.findAll();
        inventoryStateList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        return inventoryStateList;
    }
}
