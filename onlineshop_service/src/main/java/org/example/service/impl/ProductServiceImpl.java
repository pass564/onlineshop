package org.example.service.impl;

import org.example.dao.ProductDao;
import org.example.params.ProductParam;
import org.example.pojo.Product;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public void add(Product product){
        productDao.insert(product);
    }

    @Override
    public List<Product> findAll() {
        return productDao.selectAll();
    }

    @Override
    public Product findById(int id) {
        return productDao.selectById(id);
    }

    @Override
    public void modify(Product product){
        productDao.update(product);
    }

    @Override
    public void removeById(int id) {
        productDao.deleteById(id);
    }

    @Override
    public List<Product> findByParams(ProductParam productParam) {
        return productDao.selectByParams(productParam);
    }

}
