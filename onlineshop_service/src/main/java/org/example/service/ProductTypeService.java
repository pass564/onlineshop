package org.example.service;

import org.example.common.exception.ProductTypeExistException;
import org.example.pojo.ProductType;

import java.util.List;

public interface ProductTypeService {
    public List<ProductType> findAll();

    public void add(String name) throws ProductTypeExistException;

    ProductType findById(int id);

    void modifyName(int id, String name) throws ProductTypeExistException;

    void removeById(int id);

    void modifyStatus(int id);

    List<ProductType> findEnable();
}
