package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.service.CategoryService;
import com.ra.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @RequestMapping("/product")
    public String index(Model model){
        List<Product>productList=productService.findAll();
        model.addAttribute("productList",productList);

        return "product/index";
    }
    @GetMapping("product-add")
    public String create(Model model){
        Product product=new Product();
        List<Category> categoryList=categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("product",product);
        return "product/add";
    }
    @PostMapping("create-product")
    public String add(@ModelAttribute("product")Product product){
        productService.saveOfUpdate(product);
        return "redirect:/product";
    }

    @GetMapping("product/edit/{id}")
    public String edit(@PathVariable("id") Integer id,Model model){
        Product product=productService.findById(id);
        List<Category>categoryList=categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("product",product);
        return "product/edit";
    }

    @PostMapping("update-product")
    public String update(@ModelAttribute("product") Product product){
        productService.saveOfUpdate(product);
        return "redirect:/product";
    }
    @GetMapping("product/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        productService.delete(id);
        return "redirect:/product";
    }
}
