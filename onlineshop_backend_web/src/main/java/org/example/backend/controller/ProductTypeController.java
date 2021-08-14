package org.example.backend.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.common.constant.PaginationConstant;
import org.example.common.exception.ProductTypeExistException;
import org.example.common.util.ResponseResult;
import org.example.pojo.ProductType;
import org.example.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
@RequestMapping("/backend/productType")
public class ProductTypeController {
    @Autowired
    private ProductTypeService productTypeService;
    /*
    查询所有商品类型的信息，并展现出来
     */
    @RequestMapping("/findAll")
    public String findAll(Integer pageNum, Model model){
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum= PaginationConstant.PAGE_NUM;
        }
        //设置分页
        PageHelper.startPage(pageNum,PaginationConstant.PAGE_SIZE);
        //查找数据
        List<ProductType> productTypes = productTypeService.findAll();
        //将查找出的结果封装到PageInfo对象中
        PageInfo<ProductType> pageInfo=new PageInfo<ProductType>(productTypes);
        model.addAttribute("pageInfo",pageInfo);
        return "productTypeManager";
    }
    /*
    增加新的商品类型，状态码默认为1
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(String name){
        ResponseResult result = new ResponseResult();
        try {
            productTypeService.add(name);
            return ResponseResult.success("添加成功");
        } catch (ProductTypeExistException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }
    /*
    修改商品类型的名称
     */
    @RequestMapping("/modifyName")
    @ResponseBody
    public ResponseResult modifyName(int id,String name){
        try {
            productTypeService.modifyName(id,name);
            return ResponseResult.success("修改成功");
        } catch (ProductTypeExistException e) {
            //e.printStackTrace();
            return ResponseResult.fail(e.getMessage());
        }
    }
    /*
    根据id来删除某个商品类型
     */
    @RequestMapping("/removeById")
    @ResponseBody
    public ResponseResult removeById(int id){
        productTypeService.removeById(id);
        return ResponseResult.success();
    }
    /*
    修改商品的状态码，1表示启用，2表示禁用
     */
    @RequestMapping("/modifyStatus")
    @ResponseBody
    public ResponseResult modifyStatus(int id){
        productTypeService.modifyStatus(id);
        return ResponseResult.success();
    }
}
