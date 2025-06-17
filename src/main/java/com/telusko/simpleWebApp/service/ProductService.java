package com.telusko.simpleWebApp.service;

import com.telusko.simpleWebApp.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {
    List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(101,"Iphone" ,5000),
            new Product(102,"Camera",30000),
            new Product(103,"Android",20000)
            ));
    public List<Product> getProducts(){
        return products;
    }

    public Product getProduct(int prodId) {
        return products.stream().filter(p->p.getProdId() == prodId).findFirst().orElse(new Product(100,"No Item",0));
    }

    public void addProduct(Product prod){
        products.add(prod);
    }

    public void updateProduct(Product prod){
        int id = 0;
        for(int i=0;i<products.size();i++){
            if(products.get(i).getProdId() == prod.getProdId()){
                id = i;
            }
        }
        products.set(id,prod);
    }


    public void deleteProduct(int prodId) {
        int id = 0;
        for(int i=0;i<products.size();i++){
            if(products.get(i).getProdId() == prodId){
                id = i;
            }
        }
        products.remove(id);

    }
}
