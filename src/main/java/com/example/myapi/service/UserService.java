package com.example.myapi.service;

import com.example.myapi.model.dto.UserRequestDTO;
import com.example.myapi.model.dto.UserResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(UUID id);
    UserResponseDTO createUser(UserRequestDTO userRequest);
    UserResponseDTO updateUser(UUID id, UserRequestDTO userRequest);
    void deleteUser(UUID id);

    // 🔥 Nuevo método
    UserResponseDTO sumarPuntos(UUID id, Integer cantidad);
}
