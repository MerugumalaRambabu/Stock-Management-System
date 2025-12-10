package com.merugumala.stock.service;

import com.merugumala.stock.model.StockItem;
import com.merugumala.stock.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    private final StockRepository repository;

    public StockService(StockRepository repository) {
        this.repository = repository;
    }

    public List<StockItem> findAll() { return repository.findAll(); }

    public List<StockItem> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public StockItem save(StockItem item) { return repository.save(item); }

    public Optional<StockItem> findById(Long id) { return repository.findById(id); }

    public void deleteById(Long id) { repository.deleteById(id); }
}
