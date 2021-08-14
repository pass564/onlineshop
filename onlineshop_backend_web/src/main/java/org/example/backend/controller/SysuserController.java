package org.example.backend.controller;

import org.example.common.constant.ResponseStatusConstant;

import org.example.common.exception.SysuserNotExistException;
import org.example.common.util.ResponseResult;
import org.example.pojo.Sysuser;
import org.example.service.SysuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/backend/sysuser")
public class SysuserController {

    @Autowired
    private SysuserService sysuserService;

    /*
    登录管理员用户
     */
    @RequestMapping("/login")
    public ResponseResult login(String loginName, String password, HttpSession session, Model model){
        ResponseResult responseResult = new ResponseResult();
        try {
            Sysuser sysuser=sysuserService.login(loginName,password);
            session.setAttribute("sysuser",sysuser);
            responseResult.setStatus(ResponseStatusConstant.RESPONSE_STATUS_SUCCESS);
            responseResult.setData(sysuser);
            return responseResult;
        } catch (SysuserNotExistException e) {
            model.addAttribute("errorMsg",e.getMessage());
            responseResult.setStatus(ResponseStatusConstant.RESPONSE_STATUS_FAIL);
            return responseResult;
            //e.printStackTrace();
        }

    }

    /*
    添加管理员用户
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(Sysuser sysuser){
        sysuserService.add(sysuser);
        return ResponseResult.success();
    }

    /*
    修改管理员用户权限
     */
    @RequestMapping("/modifyStatus")
    @ResponseBody
    public ResponseResult modifyStatus(int id){
        sysuserService.modifyStatus(id);
        return ResponseResult.success();
    }
}
