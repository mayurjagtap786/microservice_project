package org.shopping.kart.inventory.model;

import jakarta.persistence.*;

@Entity
public class Inventory{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long productId;
    private Integer availableQty;
    private Integer reservedQty;
    @Version
    private int version;

    public Inventory(Long productId, Integer availableQty, Integer reservedQty) {
        this.productId = productId;
        this.availableQty = availableQty;
        this.reservedQty = reservedQty;
    }

    public Integer getReservedQty() {
        return reservedQty;
    }

    public void setReservedQty(Integer reservedQty) {
        this.reservedQty = reservedQty;
    }

    public Integer getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(Integer availableQty) {
        this.availableQty = availableQty;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id="+ id +
                "productId=" + productId +
                ", availableQty=" + availableQty +
                ", reservedQty=" + reservedQty +
                '}';
    }
}
