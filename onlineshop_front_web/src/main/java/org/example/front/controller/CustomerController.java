package org.example.front.controller;

import org.example.common.constant.ResponseStatusConstant;
import org.example.common.exception.LoginErrorException;
import org.example.common.util.ResponseResult;
import org.example.pojo.Customer;
import org.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/front/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    /*
    用户登录
     */
    @RequestMapping("/loginByAccount")
    @ResponseBody
    public ResponseResult loginByAccount(String loginName, String password, HttpSession session) {
        ResponseResult result = new ResponseResult();
        try {
            Customer customer = customerService.login(loginName, password);
            session.setAttribute("customer", customer);
            result.setData(customer);
            result.setStatus(ResponseStatusConstant.RESPONSE_STATUS_SUCCESS);
        } catch (LoginErrorException e) {
            //e.printStackTrace();
            result.setStatus(ResponseStatusConstant.RESPONSE_STATUS_FAIL);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /*
    退出登录
     */
    @RequestMapping("/logout")
    @ResponseBody
    public ResponseResult logout(HttpSession session) {
        session.invalidate();
        return ResponseResult.success();
    }
}
