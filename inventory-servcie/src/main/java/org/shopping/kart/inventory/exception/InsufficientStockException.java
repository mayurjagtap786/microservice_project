package org.shopping.kart.inventory.exception;

public class InsufficientStockException extends RuntimeException{

    public InsufficientStockException(Long productId){
        super(String.format("Insufficient Stock %d",productId));
    }
}
