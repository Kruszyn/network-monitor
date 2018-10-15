package com.korek.odl.controller;

import com.korek.odl.service.AlertService;
import com.korek.odl.service.TrafficVolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class Switch {

    @Autowired
    private TrafficVolumeService trafficVolumeService;
    @Autowired
    private AlertService alertService;

    @GetMapping("/switch")
    public String switchView(@Param(value = "node") String node, Model model){
        model.addAttribute("last5Alerts", alertService.findLast5());
        model.addAttribute("nodeList", trafficVolumeService.findDistinctByNode());
        model.addAttribute("interfaces", trafficVolumeService.findDistinctByIfaceByNode(node));
        return "switch";
    }
}


