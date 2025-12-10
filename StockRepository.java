package com.merugumala.stock.repository;

import com.merugumala.stock.model.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<StockItem, Long> {
    List<StockItem> findByNameContainingIgnoreCase(String name);
}
