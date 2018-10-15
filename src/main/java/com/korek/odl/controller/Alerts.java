package com.korek.odl.controller;

import com.korek.odl.service.AlertService;
import com.korek.odl.service.TrafficVolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Alerts {

    @Autowired
    private TrafficVolumeService trafficVolumeService;
    @Autowired
    private AlertService alertService;

    @GetMapping("/alerts")
    public String alerts(Model model){
        model.addAttribute("alerts", alertService.findAll());
        model.addAttribute("nodeList", trafficVolumeService.findDistinctByNode());
        return "alerts";
    }
}
