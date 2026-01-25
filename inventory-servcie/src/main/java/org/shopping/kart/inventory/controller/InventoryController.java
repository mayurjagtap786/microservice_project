package org.shopping.kart.inventory.controller;

import org.shopping.kart.inventory.dto.InventoryRequest;
import org.shopping.kart.inventory.dto.InventoryResponse;
import org.shopping.kart.inventory.model.Inventory;
import org.shopping.kart.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/add")
    public ResponseEntity<String> addInventory(InventoryRequest inventoryRequest){
        Inventory inventory = inventoryService.createInventory(inventoryRequest);
        return ResponseEntity.ok("Stock added successfully");
    }

    @GetMapping("/{productId}")
    public Inventory getInventory(@PathVariable Long productId){
        return inventoryService.getInventory(productId);
    }

    @PostMapping("/reserve")
    public ResponseEntity<Object> reserveInventory(InventoryRequest inventoryRequest){

        inventoryService.reservedStock(inventoryRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Stock Reserved");
    }

    @PostMapping("/release")
    public ResponseEntity<String> release(InventoryRequest inventoryRequest) {
        inventoryService.releaseStock(inventoryRequest);
        return ResponseEntity.ok("Stock released");
    }


    @PostMapping("/deduct")
    public ResponseEntity<String> deduct(InventoryRequest inventoryRequest) {
        inventoryService.deductStock(inventoryRequest);
        return ResponseEntity.ok("Stock deducted");
    }
}
