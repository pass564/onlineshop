package org.example.service.impl;

import org.example.common.constant.ProductTypeConstant;
import org.example.common.exception.ProductTypeExistException;
import org.example.dao.ProductTypeDao;
import org.example.pojo.ProductType;
import org.example.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeDao productTypeDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<ProductType> findAll() {
        return productTypeDao.selectAll();
    }

    @Override
    public void add(String name) throws ProductTypeExistException {
        ProductType productType = productTypeDao.selectByName(name);
        if(null!=productType){
            throw new ProductTypeExistException("商品类型已存在");
        }
        productTypeDao.insert(name, ProductTypeConstant.Product_TYPE_ENABLE);
    }

    @Override
    public ProductType findById(int id) {
        return productTypeDao.selectById(id);
    }

    @Override
    public void modifyName(int id, String name) throws ProductTypeExistException {
        ProductType productType = productTypeDao.selectByName(name);
        if(null!=productType){
            throw new ProductTypeExistException("商品类型名称已存在");
        }
        productTypeDao.updateName(id,name);
    }

    @Override
    public void removeById(int id) {
        productTypeDao.deleteById(id);
    }

    @Override
    public void modifyStatus(int id) {
        ProductType productType = findById(id);
        int status = productType.getStatus();
        if(status==ProductTypeConstant.Product_TYPE_ENABLE){
            status=ProductTypeConstant.Product_TYPE_DISABLE;
        }else{
            status=ProductTypeConstant.Product_TYPE_ENABLE;
        }
        productTypeDao.updateStatus(id,status);
    }

    @Override
    public List<ProductType> findEnable() {
        return productTypeDao.selectByStatus(ProductTypeConstant.Product_TYPE_ENABLE);
    }
}
