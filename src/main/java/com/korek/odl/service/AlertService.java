package com.korek.odl.service;

import com.korek.odl.model.Alert;
import com.korek.odl.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    public Alert[] findAlerts(){
        return alertRepository.findAllByLevel("ERROR");
    }

    public void addAlert(Alert alert){
        alertRepository.save(alert);
    }

    public void findActiveAlerts(){
        alertRepository.findAllActiveByOrderByTimestamp();
    }

    public Integer countAllActive() {
        return alertRepository.findAllActiveByOrderByTimestamp().length;
    }

    public Alert[] findWarnings(){
        return alertRepository.findAllByLevel("WARN");
    }

    public Alert[] findLast5() {
        return alertRepository.findLast5();
    }

}
