package com.korek.odl.service;

import com.korek.odl.OdlApplication;
import com.korek.odl.model.json.Node;
import com.korek.odl.model.json.NodeConnector;
import com.korek.odl.model.json.Nodes;
import com.korek.odl.repository.TrafficVolumeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TrafficVolumeService implements ITrafficVolumeService{

    @Autowired
    private TrafficVolumeRepository trafficVolumeRepository;

    private static final Logger log = LoggerFactory.getLogger(OdlApplication.class);
    private static final String INVENTORY_NODES_URL = "http://185.243.54.14:8080/restconf/operational/opendaylight-inventory:nodes/node/openflow:3/";

    @Override
    @Scheduled(fixedDelay = 10000L)
    public void getDataFromOdl() {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(
                new BasicAuthorizationInterceptor("admin", "admin"));
        Nodes node = restTemplate
                .getForObject( INVENTORY_NODES_URL, Nodes.class);
        for(Node n : node.getNode()){
            System.out.println(n.getId());
            for(NodeConnector nodeConnector : n.getNodeConnectorList()){
                System.out.print(nodeConnector.getId());
                System.out.print(nodeConnector.getStatistics().getBytes().getReceived()
                        + " " + nodeConnector.getStatistics().getBytes().getTransmitted() );
                System.out.println();
            }
        }
    }
}
