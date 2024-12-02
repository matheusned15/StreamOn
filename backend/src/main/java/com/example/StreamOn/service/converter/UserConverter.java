package com.example.StreamOn.service.converter;

import com.example.StreamOn.entities.User;
import com.example.StreamOn.entities.dto.UserDTO;

public class UserConverter {
    public UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        return dto;
    }
}

