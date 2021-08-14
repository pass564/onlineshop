package org.example.service.impl;


import org.example.common.constant.CustomerConstant;
import org.example.common.exception.LoginErrorException;
import org.example.dao.CustomerDao;
import org.example.pojo.Customer;
import org.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Customer login(String loginName, String password) throws LoginErrorException {
        Customer customer = customerDao.selectByLoginNameAndPassword(loginName, password, CustomerConstant.CUSTOMER_VALID);
        if (customer == null) {
            throw new LoginErrorException("登陆失败，用户名或密码不正确");
        }
        return customer;
    }

}
