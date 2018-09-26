package com.korek.odl.service;

import com.korek.odl.OdlApplication;
import com.korek.odl.model.TrafficVolume;
import com.korek.odl.model.json.NodeBody;
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

import java.util.LinkedList;
import java.util.List;

@Service
public class TrafficVolumeService implements ITrafficVolumeService{

    @Autowired
    private TrafficVolumeRepository trafficVolumeRepository;

    private static final Logger log = LoggerFactory.getLogger(OdlApplication.class);
    private static final String INVENTORY_NODES_URL = "http://185.243.54.14:8080/restconf/operational/opendaylight-inventory:nodes/";

    @Override
    @Scheduled(fixedDelay = 10000L)
    public void saveTrafficDifference() {
        List<TrafficVolume> trafficVolumeList = getDataFromOdl();
        for(TrafficVolume t : trafficVolumeList){
            Long sumOut = trafficVolumeRepository.bytesOutSum(t.getPort());
            Long sumIn = trafficVolumeRepository.bytesInSum(t.getPort());
            if(sumOut == null)sumOut = 0L;
            if(sumIn == null)sumIn = 0L;
            t.setBytesOut(t.getBytesOut() - sumOut);
            t.setBytesIn(t.getBytesIn() - sumIn);
            trafficVolumeRepository.save(t);
        }
    }


    @Override
    public List<TrafficVolume> getDataFromOdl() {
        List<TrafficVolume> trafficVolumeList = new LinkedList<>();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(
                new BasicAuthorizationInterceptor("admin", "admin"));
        Nodes nodes = restTemplate
                .getForObject( INVENTORY_NODES_URL, Nodes.class);

        for(NodeBody nodeBody : nodes.getNodes().getNodeBodies()) {
            System.out.println(nodeBody.getId() + "  ||");
            for (NodeConnector nodeConnector : nodeBody.getNodeConnectorList()) {
                System.out.print(nodeConnector.getId() + " %%");
                System.out.print(nodeConnector.getStatistics().getBytes().getReceived() + "||" + nodeConnector.getStatistics().getBytes().getTransmitted());
                TrafficVolume trafficVolume = new TrafficVolume();
                trafficVolume.setNode(nodeBody.getId());
                trafficVolume.setPort(nodeConnector.getId());
                trafficVolume.setBytesIn(Long.parseLong(nodeConnector.getStatistics().getBytes().getReceived()));
                trafficVolume.setBytesOut(Long.parseLong(nodeConnector.getStatistics().getBytes().getTransmitted()));
                trafficVolumeList.add(trafficVolume);
            }
        }
        return trafficVolumeList;
    }

}