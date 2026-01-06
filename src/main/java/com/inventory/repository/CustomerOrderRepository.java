package com.inventory.repository;

import com.inventory.model.CutomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepository extends JpaRepository<CutomerOrder,Long> {
}
