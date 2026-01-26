package org.shopping.kart.products.service.impl;

import org.shopping.kart.products.beans.Product;
import org.shopping.kart.products.enums.ProductCategory;
import org.shopping.kart.products.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    static List<Product> products;
    static {
         products = List.of(
                new Product(101,"iPhone 17 Pro", ProductCategory.ELECTRONICS,117000.00,"Apple","This i Apple Product"),
                new Product(102,"iPhone 17 Pro", ProductCategory.ELECTRONICS,117000.00,"Apple","This i Apple Product"));
    }
    @Override
    public List<Product> allProducts() {
        return products;
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Product updateProduct(Integer id) {
        return null;
    }

    @Override
    public String deleteProductById(Integer id) {
        return "";
    }
}
