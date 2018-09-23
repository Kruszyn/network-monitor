package com.korek.odl.repository;

import com.korek.odl.model.TrafficVolume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrafficVolumeRepository extends JpaRepository<TrafficVolume,Long> {

    @Query(value = "SELECT SUM(bytes_in) FROM traffic_volume WHERE port = ?1", nativeQuery = true)
    Long bytesInSum(String port);
    @Query(value = "SELECT SUM(bytes_out) FROM traffic_volume WHERE port = ?1", nativeQuery = true)
    Long bytesOutSum(String port);
}


