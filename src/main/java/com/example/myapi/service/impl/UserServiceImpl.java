package com.example.myapi.service.impl;

import com.example.myapi.exception.ResourceNotFoundException;
import com.example.myapi.model.dto.UserDTO;
import com.example.myapi.model.entity.User;
import com.example.myapi.repository.UserRepository;
import com.example.myapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user = userRepository.save(user);
        return new UserDTO(user.getId(), user.getEmail(), user.getName());}

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return new UserDTO(user.getId(), user.getEmail(), user.getName());
    }
}