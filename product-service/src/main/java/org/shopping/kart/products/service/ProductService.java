package org.shopping.kart.products.service;

import org.shopping.kart.products.beans.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public List<Product> allProducts();
    public Optional<Product> getProductById(Integer id);
    public Product updateProduct(Integer id);
    public String deleteProductById(Integer id);

}
