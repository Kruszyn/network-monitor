package com.korek.odl.repository;

import com.korek.odl.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    Alert[] findAllByOrderByTimestamp();
    Alert[] findAllActiveByOrderByTimestamp();
}
