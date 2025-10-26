package com.example.myapi.service.impl;

import com.example.myapi.model.dto.UserRequestDTO;
import com.example.myapi.model.dto.UserResponseDTO;
import com.example.myapi.model.entity.User;
import com.example.myapi.repository.UserRepository;
import com.example.myapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private UserResponseDTO mapToResponse(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .puntos(user.getPuntos())
                .createdAt(user.getCreatedAt())
                .build();
    }

    private User mapToEntity(UserRequestDTO dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .puntos(dto.getPuntos() != null ? dto.getPuntos() : 0)
                .build();
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public UserResponseDTO getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return mapToResponse(user);
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new RuntimeException("El correo ya está registrado");
        }
        User user = mapToEntity(userRequest);
        return mapToResponse(userRepository.save(user));
    }

    @Override
    public UserResponseDTO updateUser(UUID id, UserRequestDTO userRequest) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        existing.setName(userRequest.getName());
        existing.setEmail(userRequest.getEmail());
        existing.setPassword(userRequest.getPassword());
        existing.setPuntos(userRequest.getPuntos());

        return mapToResponse(userRepository.save(existing));
    }

    @Override
    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        userRepository.deleteById(id);
    }

    // 🔥 Nuevo método: sumar puntos
    @Override
    public UserResponseDTO sumarPuntos(UUID id, Integer cantidad) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Asegurar que no quede en negativo
        int nuevosPuntos = user.getPuntos() + cantidad;
        if (nuevosPuntos < 0) nuevosPuntos = 0;

        user.setPuntos(nuevosPuntos);
        return mapToResponse(userRepository.save(user));
    }
}
