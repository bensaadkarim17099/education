package com.education.controller;

import com.education.model.Category;
import com.education.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/category")
@RestController
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> listCategory(){
        return ResponseEntity.ok(categoryService.listAllCategory());
    }

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){

        return  ResponseEntity.ok(categoryService.addCategory(category));
    }

    @PutMapping("/update")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.updateCategory(category));
    }

    @DeleteMapping("/{category_id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long category_id){
        return ResponseEntity.ok(categoryService.deletCategory(category_id));
    }


}
