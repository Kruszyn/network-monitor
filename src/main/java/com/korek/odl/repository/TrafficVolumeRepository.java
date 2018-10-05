package com.korek.odl.repository;

import com.korek.odl.model.TrafficVolume;
import com.korek.odl.model.json.ChartData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrafficVolumeRepository extends JpaRepository<TrafficVolume,Long> {

    @Query(value = "SELECT SUM(bytes_volume) FROM traffic_volume WHERE iface = ?1 AND traffic_type=?2", nativeQuery = true)
    Long bytesSumByIface(String iface, String trafficType);
    @Query(value = "SELECT * FROM traffic_volume WHERE iface = ?1 AND traffic_type=?2", nativeQuery = true)
    List<TrafficVolume> findAllByIfaceByAndByTrafficType(String iface, String trafficType);
    @Query(value = "SELECT DISTINCT(node) FROM traffic_volume",nativeQuery = true)
    List<String> findDistinctByNode();
}


