package com.telusko.simpleWebApp.controller;

import com.telusko.simpleWebApp.model.Product;
import com.telusko.simpleWebApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService service;
    @GetMapping("/products")
    public List<Product> getProducts(){
        return service.getProducts();
    }

    @GetMapping("/products/{prodId}")
    public Product getProductById(@PathVariable  int prodId){
        return service.getProduct(prodId);
    }
    @PostMapping("/products")
    public void addProducts(@RequestBody  Product prod){
        System.out.println(prod);
        service.addProduct(prod);
    }

    @PutMapping("/products")
    public void updateProducts(@RequestBody Product prod){
        System.out.println(prod);
        service.updateProduct(prod);
    }

    @DeleteMapping("/products/{prodId}")
    public void deleteProducts(@PathVariable  int prodId){
        service.deleteProduct(prodId);
    }
}
