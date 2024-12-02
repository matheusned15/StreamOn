package com.example.StreamOn.service;


import com.example.StreamOn.entities.User;
import com.example.StreamOn.entities.dto.UserDTO;
import com.example.StreamOn.repository.UserRepository;
import com.example.StreamOn.service.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private final UserConverter userConverter;

    public UserService(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    public UserDTO createUser(UserDTO userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        User userConvert = convertDTOtoUser(userDto);
        userRepository.save(user);
        return userConverter.convertToDTO(userConvert);
    }

    private User convertDTOtoUser(UserDTO userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getPassword());
        return user;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public UserDTO updateUser(Long id, UserDTO userDto) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            return userConverter.convertToDTO(user);
        }
        throw new RuntimeException("User not found");
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll(); // Busca todos os usuários
        return users.stream() // Corrigi o nome da variável
                .map(userConverter::convertToDTO) // Converte cada entidade User para UserDTO
                .collect(Collectors.toList()); // Coleta os DTOs em uma lista
    }


}
