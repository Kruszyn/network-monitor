package com.korek.odl.repository;

import com.korek.odl.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    Alert[] findAllByOrderByTimestamp();
    Alert[] findAllActiveByOrderByTimestamp();
    Alert[] findAllByLevel(String warn);
    @Query(value="SELECT * FROM alert WHERE level='ERROR' ORDER BY timestamp DESC LIMIT 5", nativeQuery = true)
    Alert[] findLast5();
}
