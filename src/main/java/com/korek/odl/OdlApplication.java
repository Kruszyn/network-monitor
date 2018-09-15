package com.korek.odl;

import com.korek.odl.model.json.Node;
import com.korek.odl.model.json.NodeConnector;
import com.korek.odl.model.json.Nodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class OdlApplication {
    private static final Logger log = LoggerFactory.getLogger(OdlApplication.class);
    private static final String INVENTORY_NODES_URL = "http://185.243.54.14:8080/restconf/operational/opendaylight-inventory:nodes/node/openflow:3/";

    public static void main(String[] args) {
        SpringApplication.run(OdlApplication.class, args);
    }

    @Scheduled(fixedDelay = 10000L)
    public void askController(){
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
