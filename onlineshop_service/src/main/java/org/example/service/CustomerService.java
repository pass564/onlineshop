package org.example.service;


import org.example.common.exception.LoginErrorException;
import org.example.pojo.Customer;

public interface CustomerService {
    public Customer login(String loginName, String password) throws LoginErrorException;

}
