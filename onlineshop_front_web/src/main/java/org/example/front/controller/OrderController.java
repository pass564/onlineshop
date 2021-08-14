package org.example.front.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.common.constant.PaginationConstant;
import org.example.pojo.Customer;
import org.example.pojo.Order;
import org.example.service.CustomerService;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.commons.beanutils.PropertyUtils;

import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Controller
@RequestMapping("/front/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /*
    查找所有订单（购物车）,如果用户不存在，则返回主界面
     */
    @RequestMapping("/findAll")
    public String findAll(Integer pageNum, HttpSession session,Model model){
        Customer customer=(Customer)session.getAttribute("customer");
        if (customer == null){
            model.addAttribute("errorMsg", "用户不存在");
            return "main";
        }else {
            if (ObjectUtils.isEmpty(pageNum)) {
                pageNum = PaginationConstant.PAGE_NUM;
            }
            PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);
            List<Order> orders=orderService.findAll();
            PageInfo<Order> pageInfo = new PageInfo<Order>(orders);
            model.addAttribute("pageInfo", pageInfo);
            return "OrderManager";
        }
    }

    /*
    添加订单
     */
    @RequestMapping("/add")
    public String add(Order order, Integer pageNum, HttpSession session, Model model){
        Customer customer=(Customer)session.getAttribute("customer");
        if (customer == null){
            model.addAttribute("errorMsg", "用户不存在");
        }else {
            order.setCustomer(customer);
            orderService.add(order);
            model.addAttribute("successMsg", "添加成功");
        }
        return "forward:findAll?pageNum=" + pageNum;
    }
}
