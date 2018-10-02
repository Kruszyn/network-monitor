package com.korek.odl.repository;

import com.korek.odl.model.TrafficVolume;
import com.korek.odl.model.json.ChartData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrafficVolumeRepository extends JpaRepository<TrafficVolume,Long> {

    @Query(value = "SELECT SUM(bytesVolume) FROM traffic_volume WHERE port = ?1 AND type=?2", nativeQuery = true)
    Long bytesSumByIface(String port, String type);

    List<TrafficVolume> findAllByPort(String port);
}


