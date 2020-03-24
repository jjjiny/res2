package ssm.service;

import entity.Product;

import java.util.List;

public interface ProductService {
     List<Product> findAllProduct();

     void save(Product product);
}
