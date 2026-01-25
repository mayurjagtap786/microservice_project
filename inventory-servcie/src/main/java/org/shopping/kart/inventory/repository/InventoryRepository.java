package org.shopping.kart.inventory.repository;

import org.shopping.kart.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory , Long> {


    public Optional<Inventory> findByProductId(Long productId);

}
