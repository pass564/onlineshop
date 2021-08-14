package org.example.service;

import org.example.params.ProductParam;
import org.example.pojo.Product;

import java.util.List;

public interface ProductService {
    public void add(Product product);

    List<Product> findAll();

    Product findById(int id);

    void modify(Product product);

    void removeById(int id);

    List<Product> findByParams(ProductParam productParam);
}
