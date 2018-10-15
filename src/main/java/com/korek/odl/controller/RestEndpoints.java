package com.korek.odl.controller;

import com.korek.odl.model.json.ChartData;
import com.korek.odl.service.AlertService;
import com.korek.odl.service.TrafficVolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestEndpoints {

    @Autowired
    private TrafficVolumeService trafficVolumeService;
    @Autowired
    private AlertService alertService;

    @GetMapping("/chart")
    @ResponseBody
    public List<List<ChartData>> chartData(@RequestParam(name="iface") String iface){
        return trafficVolumeService.findAllForInterface(iface);
    }

    @GetMapping("/alertsCount")
    @ResponseBody
    public Integer alertsCount(){
        return alertService.findAlerts().length;
    }
}
