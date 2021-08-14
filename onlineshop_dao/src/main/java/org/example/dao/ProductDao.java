package org.example.dao;


import org.example.params.ProductParam;
import org.example.pojo.Product;

import java.util.List;

public interface ProductDao {
    public void insert(Product product);

    List<Product> selectAll();

    Product selectById(int id);

    void update(Product product);

    void deleteById(int id);

    List<Product> selectByParams(ProductParam productParam);
}
