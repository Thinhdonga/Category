package org.example.category1.controller;

import org.example.category1.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoryController {
    private List<Category>  categoryList = new ArrayList<>();

    public CategoryController() {
        Category c1 = new Category();
        Category c2 = new Category();
        categoryList.add(c1);
        categoryList.add(c2);
    }

    @GetMapping("/category")
    @ResponseBody
    public List<Category> getCategory() {
        return categoryList;
    }

    @GetMapping
    public ResponseEntity<Category> getCategoryById(@PathVariable String categoryId) {
        for (Category category : categoryList) {
            if (category.getCategoryId().equals(categoryId)) {
                return ResponseEntity.status(200).body(category);
            }
        }
        return ResponseEntity.status(404).body(null);  // Trả về lỗi 404 nếu không tìm thấy

    }
    @DeleteMapping("users/{id}")
    @ResponseBody
    public List<Category> deleteUser(@PathVariable("id") String userId) {
        for (Category category : categoryList) {
            if (category.getCategoryId().equals(userId)) {
                categoryList.remove(category);
                break;
            }
        }
        return categoryList;
    }
    @PostMapping("/user")
    public ResponseEntity<Category> createUser(@RequestBody Category newcategory) {
        categoryList.add(newcategory);
        return ResponseEntity.status(201).body(newcategory);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<Category> updateUser(@PathVariable("id") String userId, @RequestBody Category updateUser) {
        for (Category category : categoryList) {
            if (category.getCategoryId().equals(userId)) {
                category.setCategoryName(updateUser.getCategoryName());


                return ResponseEntity.status(200).body(category);// Trả về status 200 OK và user đã được cập nhật
            }
        }


        return ResponseEntity.status(404).body(null);
    }


}
