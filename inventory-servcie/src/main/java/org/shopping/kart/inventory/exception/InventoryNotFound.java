package org.shopping.kart.inventory.exception;

public class InventoryNotFound extends RuntimeException{

    public InventoryNotFound(Long productId){
        super(String.format("Inventory not found for %d",productId));
    }
}
