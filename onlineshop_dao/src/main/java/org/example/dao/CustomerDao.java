package org.example.dao;


import org.apache.ibatis.annotations.Param;
import org.example.pojo.Customer;

public interface CustomerDao {

    public Customer selectByLoginNameAndPassword(@Param("loginName")String loginName, @Param("password")String password, @Param("isValid")Integer isValid);

}
