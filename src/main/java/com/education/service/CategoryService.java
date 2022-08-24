package com.education.service;

import com.education.model.Category;
import com.education.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public Category addCategory(Category category){
        return categoryRepository.save(category);
}

    public Category updateCategory(Category category) {
        Category categoryDB=categoryRepository.findById(category.getId()).get();
        if (category.getName()!=null && categoryDB.getName() != category.getName()) categoryDB.setName(category.getName());
        if (category.getDescription()!=null && categoryDB.getDescription() != category.getDescription()) categoryDB.setDescription(category.getDescription());

        return categoryRepository.save(categoryDB);
    }

    public String deletCategory(Long category_id) {
        Category categoryDB = categoryRepository.findById(category_id).get();
        if (categoryDB != null) {
            categoryRepository.deleteById(category_id);
            return "Category " + categoryDB.getName() + " Deleted ";
        } else {

            return "Category Id "+category_id +" Not found....";
        }
    }

    public List<Category> listAllCategory() {

        List<Category> all = categoryRepository.findAll();
        all.forEach(el-> el.getCourses().forEach(el1-> {
            el1.setCategory(null);
            el1.setParagraphs(null);
        }));
        return all;
    }
}
