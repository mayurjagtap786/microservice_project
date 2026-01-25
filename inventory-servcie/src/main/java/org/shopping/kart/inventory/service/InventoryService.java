package org.shopping.kart.inventory.service;

import org.shopping.kart.inventory.dto.InventoryRequest;
import org.shopping.kart.inventory.exception.InsufficientStockException;
import org.shopping.kart.inventory.exception.InventoryNotFound;
import org.shopping.kart.inventory.model.Inventory;
import org.shopping.kart.inventory.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository repository;
    InventoryService(InventoryRepository inventoryRepository){
        this.repository = inventoryRepository;
    }

    public Inventory createInventory(InventoryRequest inventoryRequest){

        Inventory inventory = new Inventory(
                inventoryRequest.productId(),
                inventoryRequest.quantity(),
                0);

        return repository.save(inventory);
    }
    public Inventory getInventory(Long productId){
        return repository.findByProductId(productId).orElseThrow(() -> new InventoryNotFound(productId));
    }

    public boolean isStockAvailable(Long productId, Integer quantity){
        Inventory inventory = getInventory(productId);

        return inventory.getAvailableQty() >= quantity;
    }

    public void reservedStock(InventoryRequest request){
        Inventory inventory = getInventory(request.productId());
        if(inventory.getAvailableQty() < request.quantity()){
            throw new InsufficientStockException(request.productId());
        }
        inventory.setAvailableQty(request.quantity() - inventory.getAvailableQty());
    }

    public void releaseStock(InventoryRequest request){
        Inventory inventory = getInventory(request.productId());
        inventory.setReservedQty(inventory.getReservedQty() - request.quantity());
        inventory.setAvailableQty(inventory.getAvailableQty() + request.quantity());
        repository.save(inventory);
    }

    public void deductStock(InventoryRequest request){
        Inventory inventory = getInventory(request.productId());
            if(inventory.getReservedQty() < request.quantity()){
                throw new RuntimeException("Insufficient reserved stock");
            }
            inventory.setReservedQty(inventory.getReservedQty() - request.quantity());
            repository.save(inventory);
    }
}
