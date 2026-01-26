package org.shopping.kart.products.controller;

import org.shopping.kart.products.beans.Product;
import org.shopping.kart.products.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    @GetMapping("/")
    public List<Product> getAllProducts(){
       return productService.allProducts();
    }
}
