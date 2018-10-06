package com.korek.odl.repository;

import com.korek.odl.model.InventoryState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryStateRepository extends JpaRepository<InventoryState, Long> {
    InventoryState findByName(String name);
}
