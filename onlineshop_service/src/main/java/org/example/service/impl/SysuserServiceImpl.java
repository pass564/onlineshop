package org.example.service.impl;

import org.example.common.constant.SysuserConstant;
import org.example.common.exception.SysuserNotExistException;
import org.example.dao.SysuserDao;
import org.example.pojo.Sysuser;
import org.example.service.SysuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SysuserServiceImpl implements SysuserService {
    @Autowired
    private SysuserDao sysuserDao;

    @Override
    public void add(Sysuser sysuser) {
        //默认为有效
        sysuser.setIsValid(SysuserConstant.SYSUSER_VALID);
        //创建时间为当前时间
        sysuser.setCreateDate(new Date());
        sysuserDao.insert(sysuser);
    }

    @Override
    public void modifyStatus(int id) {
        Sysuser sysuser = sysuserDao.selectById(id);
        int isValid = sysuser.getIsValid();
        if (isValid == SysuserConstant.SYSUSER_VALID) {
            isValid = SysuserConstant.SYSUSER_INVALID;
        } else {
            isValid = SysuserConstant.SYSUSER_VALID;
        }
        sysuserDao.updateStatus(id, isValid);
    }

    @Override
    public Sysuser login(String loginName, String password) throws SysuserNotExistException {
        Sysuser sysuser=sysuserDao.selectByLoginNameAndPassword(loginName, password, SysuserConstant.SYSUSER_VALID);
        if(sysuser!=null){
            return sysuser;
        }
        throw new SysuserNotExistException("登陆失败，用户名或密码不正确");
    }
}
