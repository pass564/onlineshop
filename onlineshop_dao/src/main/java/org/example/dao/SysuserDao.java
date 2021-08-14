package org.example.dao;

import org.apache.ibatis.annotations.Param;
import org.example.pojo.Sysuser;

public interface SysuserDao {

    public Sysuser selectById(int id);

    public void insert(Sysuser sysuser);

    public void updateStatus(@Param("id") int id, @Param("isValid") int isValid);

    Sysuser selectByLoginNameAndPassword(@Param("loginName")String loginName, @Param("password")String password, @Param("isValid")int isValid);
}
