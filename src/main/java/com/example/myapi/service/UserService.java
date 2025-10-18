package com.example.myapi.service;

import com.example.myapi.model.dto.UserRequestDTO;
import com.example.myapi.model.dto.UserResponseDTO;
import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userRequest);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(UUID id);
    void deleteUser(UUID id);
}
