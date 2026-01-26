package org.shopping.kart.products.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.shopping.kart.products.enums.ProductCategory;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Product {

    private Integer id;
    private String name;
    private ProductCategory category;
    private Double price;
    private String shortDescription;
    private String brand;


}
