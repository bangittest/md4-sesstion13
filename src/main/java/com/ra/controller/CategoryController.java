package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/category")
    public String index(Model model){
        List<Category>categoryList=categoryService.findAll();
        model.addAttribute("list",categoryList);
        return "category/index";
    }
    @GetMapping("category-add")
    public String create(Model model){
        Category category=new Category();
        model.addAttribute("category",category);
        return "category/add";
    }

    @PostMapping("create-category")
    public String add(@ModelAttribute("category") Category category){
        categoryService.saveOfUpdate(category);
        return "redirect:/category";
    }
    @GetMapping("/category/edit/{id}")
    public String edit(@PathVariable("id") Integer id,Model model){
        Category category = categoryService.findById(id);
        model.addAttribute("category",category);
        return "category/edit";
    }
    @PostMapping("category-update")
    public String update(@ModelAttribute("category") Category category){
        categoryService.saveOfUpdate(category);
        return "redirect:/category";
    }

    @GetMapping("category/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        categoryService.delete(id);
        return "redirect:/category";
    }
}
