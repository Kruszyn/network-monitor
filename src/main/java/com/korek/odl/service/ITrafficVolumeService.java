package com.korek.odl.service;

import com.korek.odl.model.TrafficVolume;
import com.korek.odl.repository.TrafficVolumeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ITrafficVolumeService {

    List<TrafficVolume> getDataFromOdl();
    void saveTrafficDifference();
}
