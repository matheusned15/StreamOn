package com.example.StreamOn.service;

import com.example.StreamOn.entities.Category;
import com.example.StreamOn.entities.Video;
import com.example.StreamOn.entities.dto.CategoryDTO;
import com.example.StreamOn.entities.dto.VideoDTO;
import com.example.StreamOn.repository.CategoryRepository;
import com.example.StreamOn.service.converter.CategoryConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    public CategoryService(CategoryRepository categoryRepository, CategoryConverter categoryConverter) {
        this.categoryRepository = categoryRepository;
        this.categoryConverter = categoryConverter;
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public CategoryDTO createCategory(CategoryDTO dto) {
        Category categoryConvert = convertDtoToUser(dto);
        categoryRepository.save(categoryConvert);
        return categoryConverter.convertToDto(categoryConvert);
    }

    private Category convertDtoToUser(CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return category;
    }

    public Category update(Long id, Category category) {
        return categoryRepository.findById(id).map(existingCategory -> {
            existingCategory.setName(category.getName());
            return categoryRepository.save(existingCategory);
        }).orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
