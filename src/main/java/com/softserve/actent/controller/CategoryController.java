package com.softserve.actent.controller;

import com.softserve.actent.constant.ExceptionMessages;
import com.softserve.actent.exceptions.ResourceNotFoundException;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.model.dto.CategoryDto;
import com.softserve.actent.model.dto.IdDto;
import com.softserve.actent.model.entity.Category;
import com.softserve.actent.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    private final CategoryService categoryService;

    private final ModelMapper modelMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public IdDto addCategory(@RequestBody CategoryDto categoryDto, Long id) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category parent = categoryService.get(id);
        category = categoryService.add(new Category(categoryDto.getName(), parent));
        return new IdDto(category.getId());
    }

    @GetMapping(value = "/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryService.getAll();
        return categories.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/categories/subcategories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDto> getSubCategoriesById(@PathVariable Long id) {
        Category parent = categoryService.get(id);
        List<Category> subcategories = categoryService.getSubcategories(parent);
        return subcategories.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/categories/subcategories")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDto> getSubCategoriesByName(@RequestParam String name) {
        Category parent = categoryService.getParentByName(name);
        if (parent == null) {
            throw new ResourceNotFoundException(ExceptionMessages.NO_SUBCATEGORIES_FOUND, ExceptionCode.NOT_FOUND);
        } else {
            List<Category> subcategories = categoryService.getSubcategories(parent);
            return subcategories.stream()
                    .map(category -> modelMapper.map(category, CategoryDto.class))
                    .collect(Collectors.toList());
        }
    }

    @GetMapping(value = "/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto getCategoryById(@PathVariable Long id) {
        Category category = categoryService.get(id);
        return modelMapper.map(category, CategoryDto.class);
    }

    @DeleteMapping(value = "/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategoryById(@PathVariable Long id) {
        Category category = categoryService.get(id);
        categoryService.delete(id);
    }

    @PutMapping(value = "/categories/{id}")
    public CategoryDto update(@PathVariable Long id, Long parentId, @RequestBody CategoryDto categoryDto) {
        Category parent = categoryService.get(parentId);
        Category newCategory = new Category(categoryDto.getName(), parent);
        categoryService.update(newCategory, id);
        return modelMapper.map(categoryDto, CategoryDto.class);
    }
}




