package com.korek.odl.service;

import com.korek.odl.repository.TrafficVolumeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface ITrafficVolumeService {

    void getDataFromOdl();
}
