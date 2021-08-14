package org.example.service;


import org.example.common.exception.SysuserNotExistException;
import org.example.pojo.Sysuser;

public interface SysuserService {
    public void add(Sysuser sysuser);

    public void modifyStatus(int id);

    Sysuser login(String loginName, String password) throws SysuserNotExistException;
}
