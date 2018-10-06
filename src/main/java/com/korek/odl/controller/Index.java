package com.korek.odl.controller;

import com.korek.odl.model.InventoryState;
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

    @GetMapping(value = {"/","/index"})
    public String index(Model model){
        model.addAttribute("nodeList", trafficVolumeService.findDistinctByNode());
        model.addAttribute("inventoryList", inventoryStateService.findAll());
        return "/index";
    }

}
