package com.example.myapi.service;

import com.example.myapi.model.dto.UserDTO;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(Long id);
}