package org.example.backend.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.common.constant.PaginationConstant;
import org.example.common.util.ResponseResult;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/backend/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;
    /*
    查找状态码为1，已启用的商品类型
     */
    @ModelAttribute("productTypes")
    public List<ProductType> loadProductTypes() {
        List<ProductType> productTypes = productTypeService.findEnable();
        return productTypes;
    }
    /*
    查找所有商品
     */
    @RequestMapping("/findAll")
    public String findAll(Integer pageNum, Model model) {
        if (ObjectUtils.isEmpty(pageNum)) {
            pageNum = PaginationConstant.PAGE_NUM;
        }

        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);
        List<Product> products = productService.findAll();
        PageInfo<Product> pageInfo = new PageInfo<Product>(products);
        model.addAttribute("pageInfo", pageInfo);

        return "productManager";
    }
    /*
    添加新的商品
     */
    @RequestMapping("/add")
    public String add(Product product, Integer pageNum, HttpSession session, Model model) {
        productService.add(product);
        return "forward:findAll?pageNum=" + pageNum;
    }
    /*
       根据id查找已有商品
     */
    @RequestMapping("/findById")
    @ResponseBody
    public ResponseResult findById(int id) {
        Product product = productService.findById(id);
        return ResponseResult.success(product);
    }
    /*
       修改商品信息
    */
    @RequestMapping("/modify")
    public String modify(Product product, Integer pageNum, HttpSession session, Model model) {
        productService.modify(product);
        return "forward:findAll?pageNum=" + pageNum;
    }
    /*
        根据商品id来删除商品
     */
    @RequestMapping("/removeById")
    @ResponseBody
    public ResponseResult removeById(int id) {
        productService.removeById(id);
        return ResponseResult.success();
    }
}
