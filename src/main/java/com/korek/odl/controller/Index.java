package com.korek.odl.controller;

import com.korek.odl.model.InventoryState;
import com.korek.odl.service.AlertService;
import com.korek.odl.service.InventoryStateService;
import com.korek.odl.service.TrafficVolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.sort;

@Controller
public class Index {

    @Autowired
    private TrafficVolumeService trafficVolumeService;
    @Autowired
    private InventoryStateService inventoryStateService;
    @Autowired
    private AlertService alertService;

    @GetMapping(value = {"/","/index"})
    public String index(Model model){
        List<String> nodeList = trafficVolumeService.findDistinctByNode();
        model.addAttribute("nodeList", nodeList);
        model.addAttribute("nodeCount", nodeList.size());

        model.addAttribute("last5Alerts", alertService.findLast5());
        model.addAttribute("alertsCount", alertService.findAlerts().length);
        model.addAttribute("warningCount", alertService.findWarnings().length);

        model.addAttribute("inventoryList", inventoryStateService.findAll());

        return "index";
    }

}
