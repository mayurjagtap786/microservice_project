package org.shopping.kart.inventory.controller;

import org.shopping.kart.inventory.exception.InsufficientStockException;
import org.shopping.kart.inventory.exception.InventoryNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> handleException(Exception ex){

        if(ex instanceof InventoryNotFound inventoryNotFound){
        Map<String,String> response = new HashMap<>();
        response.put("Error",inventoryNotFound.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        else if(ex instanceof InsufficientStockException insufficientStock){
            Map<String,String> reponse = new HashMap<>();
            reponse.put("Error",insufficientStock.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(reponse);
        }
        return null;
    }
}
