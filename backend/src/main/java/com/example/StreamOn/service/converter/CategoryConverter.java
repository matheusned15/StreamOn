package com.example.StreamOn.service.converter;

import com.example.StreamOn.entities.Category;
import com.example.StreamOn.entities.dto.CategoryDTO;

public class CategoryConverter {
    public CategoryDTO convertToDto(Category category){
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }
}
