package org.shopping.kart.inventory.dto;

public record InventoryResponse(Long productId,Integer availableQty, Integer reservedQty) {
}
