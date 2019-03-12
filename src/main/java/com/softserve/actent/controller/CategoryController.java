package com.softserve.actent.controller;

import com.softserve.actent.constant.ExceptionMessages;
import com.softserve.actent.constant.NumberConstants;
import com.softserve.actent.constant.StringConstants;
import com.softserve.actent.exceptions.ResourceNotFoundException;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.model.dto.category.CategoryDto;
import com.softserve.actent.model.dto.IdDto;
import com.softserve.actent.model.dto.category.ShowCategoryDto;
import com.softserve.actent.model.dto.category.CreateCategoryDto;
import com.softserve.actent.model.entity.Category;
import com.softserve.actent.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
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
    public IdDto addCategory(@RequestBody @Validated CreateCategoryDto createCategoryDto) {
        Category category = modelMapper.map(createCategoryDto, Category.class);
        Category parent = categoryService.getParent(createCategoryDto.getParentId());
        category = categoryService.add(new Category(createCategoryDto.getName(), parent));
        return new IdDto(category.getId());
    }

    @GetMapping(value = "/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<ShowCategoryDto> getCategories() {
        List<Category> categories = categoryService.getAll();
        return categories.stream()
                .map(category -> modelMapper.map(category, ShowCategoryDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/categories/subcategories/{parentId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ShowCategoryDto> getSubCategoriesById(@PathVariable @NotNull @Min(value = 1, message = "should be more than 0") Long parentId) {
        Category parent = categoryService.getParent(parentId);
        List<Category> subcategories = categoryService.getSubcategories(parent);
        return subcategories.stream()
                .map(category -> modelMapper.map(category, ShowCategoryDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/categories/subcategories")
    @ResponseStatus(HttpStatus.OK)
    public List<ShowCategoryDto> getSubCategoriesByName(@RequestParam @Size(min = NumberConstants.MIN_VALUE_FOR_CATEGORY_NAME, max = NumberConstants.MAX_VALUE_FOR_CATEGORY_NAME, message = StringConstants.CATEGORY_NO_LONGER_THAN_THIRTY_SYMBOLS) String parentCategoryName) {
        Category parent = categoryService.getParentByName(parentCategoryName);
        if (parent == null) {
            throw new ResourceNotFoundException(ExceptionMessages.NO_SUBCATEGORIES_FOUND, ExceptionCode.NOT_FOUND);
        } else {
            List<Category> subcategories = categoryService.getSubcategories(parent);
            return subcategories.stream()
                    .map(category -> modelMapper.map(category, ShowCategoryDto.class))
                    .collect(Collectors.toList());
        }
    }

    @GetMapping(value = "/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto getCategoryById(@PathVariable @NotNull @Min(value = 1, message = "should be more than 0") Long id) {
        Category category = categoryService.get(id);
        return modelMapper.map(category, CategoryDto.class);
    }

    @DeleteMapping(value = "/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategoryById(@PathVariable @NotNull @Min(value = 1, message = "should be more than 0") Long id) {
        Category category = categoryService.get(id);
        categoryService.delete(id);
    }

    @PutMapping(value = "/categories/{id}")
    public CategoryDto update(@PathVariable @NotNull @Min(value = 1, message = "should be more than 0") Long id, @RequestBody @Validated CreateCategoryDto createCategoryDto) {
        Category parent = categoryService.getParent(createCategoryDto.getParentId());
        Category newCategory = new Category(createCategoryDto.getName(), parent);
        categoryService.update(newCategory, id);
        return modelMapper.map(createCategoryDto, CategoryDto.class);
    }
}




