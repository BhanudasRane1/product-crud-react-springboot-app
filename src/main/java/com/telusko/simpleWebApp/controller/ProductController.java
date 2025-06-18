package com.telusko.simpleWebApp.controller;

import com.telusko.simpleWebApp.model.Product;
import com.telusko.simpleWebApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
    @Autowired
    ProductService service;
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(service.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{prodId}")
    public ResponseEntity<Product> getProductById(@PathVariable  int prodId){
        Product product = service.getProduct(prodId);
        if(product != null){
            return new ResponseEntity<>(product,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/product")
    public ResponseEntity<?> addProducts(@RequestPart  Product product,@RequestPart MultipartFile imageFile){
        System.out.println(product);
        try{
            Product product1 = service.addProduct(product,imageFile);
            System.out.println(product1);
            return new ResponseEntity<>(product1,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProducts(@PathVariable int id, @RequestPart  Product product,@RequestPart MultipartFile imageFile){
        System.out.println(product);
        Product product1 = null;
        try{
            product1 = service.updateProduct(id,product,imageFile);
        }catch (Exception e){
            return new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);
        }

        if(product1!=null){
            return new ResponseEntity<>("Updated" , HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/product/{prodId}")
    public ResponseEntity<String> deleteProducts(@PathVariable  int prodId){
        Product product = service.getProduct(prodId);
        if(product!=null){
            service.deleteProduct(prodId);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Product Not Found",HttpStatus.NOT_FOUND);

        }

    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable("productId") int productId) {
        Product product = service.getProduct(productId);
        byte[] imageFile = product.getImageDate();
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<Product>> searchProducts(String keyword){
        System.out.println("Searching with " + keyword);
        List<Product> products = service.searchProducts(keyword);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }


}
