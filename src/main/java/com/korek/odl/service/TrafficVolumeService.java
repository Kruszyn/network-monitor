package com.korek.odl.service;

import com.korek.odl.OdlApplication;
import com.korek.odl.model.TrafficVolume;
import com.korek.odl.model.json.ChartData;
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

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@Service
public class TrafficVolumeService{

    @Autowired
    private TrafficVolumeRepository trafficVolumeRepository;

    private static final Logger log = LoggerFactory.getLogger(OdlApplication.class);
    private static final String INVENTORY_NODES_URL = "http://185.243.54.14:8080/restconf/operational/opendaylight-inventory:nodes/";

    @Scheduled(fixedDelay = 100000L)
    public void saveTrafficDifference() {
        List<TrafficVolume> trafficVolumeList = getDataFromOdl();
        for(TrafficVolume t : trafficVolumeList){
            if(t.getType().equals("IN")){
                Long sumIn = trafficVolumeRepository.bytesSumByIface(t.getIface(), "IN");
                if(sumIn == null)sumIn = 0L;
                t.setBytesVolume(t.getBytesVolume()-sumIn);
            } else if (t.getType().equals("OUT")){
                Long sumOut = trafficVolumeRepository.bytesSumByIface(t.getIface(), "OUT");
                if(sumOut == null)sumOut = 0L;
                t.setBytesVolume(t.getBytesVolume()-sumOut);
            } else{
               log.error("Traffic volume with wrong type: " + t.getType());
            }
            trafficVolumeRepository.save(t);
        }
    }

    private List<TrafficVolume> getDataFromOdl() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        String localTime = LocalTime.now().format(dtf);

        List<TrafficVolume> trafficVolumeList = new LinkedList<>();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(
                new BasicAuthorizationInterceptor("admin", "admin"));
        Nodes nodes = restTemplate
                .getForObject( INVENTORY_NODES_URL, Nodes.class);

        for(NodeBody nodeBody : nodes.getNodes().getNodeBodies()) {
            for (NodeConnector nodeConnector : nodeBody.getNodeConnectorList()) {

                TrafficVolume trafficVolumeIn = new TrafficVolume();
                TrafficVolume trafficVolumeOut = new TrafficVolume();
                trafficVolumeIn.setNode(nodeBody.getId());
                trafficVolumeOut.setNode(nodeBody.getId());
                trafficVolumeIn.setIface(nodeConnector.getId());
                trafficVolumeOut.setIface(nodeConnector.getId());
                trafficVolumeIn.setBytesVolume(Long.parseLong(nodeConnector.getStatistics().getBytes().getReceived()));
                trafficVolumeOut.setBytesVolume(Long.parseLong(nodeConnector.getStatistics().getBytes().getTransmitted()));
                trafficVolumeIn.setType("IN");
                trafficVolumeOut.setType("OUT");
                trafficVolumeList.add(trafficVolumeIn);
                trafficVolumeList.add(trafficVolumeOut);
            }
        }
        return trafficVolumeList;
    }

    public List<ChartData> findAllForInterface(String iface) {
        List<ChartData> chartDataList = TrafficVolume.convertToChartData(trafficVolumeRepository.findAllByPort(iface));

        return chartDataList;
    }
}