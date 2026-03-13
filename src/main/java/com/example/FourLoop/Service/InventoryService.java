package com.example.FourLoop.Service;

import com.example.FourLoop.Model.Inventory;
import com.example.FourLoop.Repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public void saveInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    public Optional<Inventory> getInventoryById(Long id) {
        return inventoryRepository.findById(id);
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
}