package org.example.front.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.common.constant.PaginationConstant;
import org.example.params.ProductParam;
import org.example.pojo.Product;
import org.example.pojo.ProductType;
import org.example.service.ProductService;
import org.example.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/front/product")
public class ProductController {
    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductService productService;

    /*
    依据用户要求查找对应商品
     */
    @RequestMapping("/search")
    public String search(ProductParam productParam, Integer pageNum, Model model){
        if (ObjectUtils.isEmpty(pageNum)){
            pageNum= PaginationConstant.PAGE_NUM;
        }

        PageHelper.startPage(pageNum,PaginationConstant.PAGE_SIZE_FRONT);
        List<Product> products = productService.findByParams(productParam);
        PageInfo<Product> pageInfo = new PageInfo<Product>(products);
        model.addAttribute("pageInfo",pageInfo);

        return "main";
    }


    /*
    查找状态码为1，已启用的商品类型
     */
    @ModelAttribute("productTypes")
    public List<ProductType> loadProductTypes(){
        List<ProductType> productTypes = productTypeService.findEnable();
        return productTypes;
    }
}
