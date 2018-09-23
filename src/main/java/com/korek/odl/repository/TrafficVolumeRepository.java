package com.korek.odl.repository;

import com.korek.odl.model.TrafficVolume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrafficVolumeRepository extends JpaRepository<TrafficVolume,Long> {
}
