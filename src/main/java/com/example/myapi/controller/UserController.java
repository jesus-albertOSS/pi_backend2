package com.example.myapi.controller;

import com.example.myapi.model.dto.UserRequestDTO;
import com.example.myapi.model.dto.UserResponseDTO;
import com.example.myapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@Tag(
    name = "👤 Usuarios",
    description = """
        Endpoints para la **gestión de usuarios** dentro de *Game Connect*.
        
        Permite:
        - Listar y consultar usuarios registrados  
        - Crear y actualizar perfiles  
        - Eliminar usuarios  
        - Sumar puntos de recompensa (🎯 sistema de logros)
        """
)
public class UserController {

    @Autowired
    private UserService userService;

    // 📋 Obtener todos los usuarios
    @Operation(
        summary = "Listar todos los usuarios",
        description = "Devuelve una lista con todos los usuarios registrados en el sistema.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Lista obtenida correctamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserResponseDTO.class))
            )
        }
    )
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // 🔍 Obtener usuario por ID
    @Operation(
        summary = "Obtener un usuario por su ID",
        description = "Busca y devuelve un usuario específico a partir de su UUID.",
        parameters = {
            @Parameter(name = "id", description = "Identificador único del usuario", required = true)
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // ➕ Crear usuario
    @Operation(
        summary = "Registrar un nuevo usuario",
        description = "Crea un nuevo perfil de usuario dentro del sistema Game Connect.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Datos del nuevo usuario",
            content = @Content(schema = @Schema(implementation = UserRequestDTO.class))
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "Usuario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o incompletos")
        }
    )
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    // ♻️ Actualizar usuario
    @Operation(
        summary = "Actualizar datos de un usuario",
        description = "Modifica la información de un usuario existente mediante su UUID.",
        parameters = {
            @Parameter(name = "id", description = "Identificador del usuario a actualizar", required = true)
        },
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Datos actualizados del usuario",
            content = @Content(schema = @Schema(implementation = UserRequestDTO.class))
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
        }
    )
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable UUID id, @RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    // 🗑️ Eliminar usuario
    @Operation(
        summary = "Eliminar un usuario",
        description = "Elimina un usuario existente del sistema.",
        parameters = {
            @Parameter(name = "id", description = "UUID del usuario a eliminar", required = true)
        },
        responses = {
            @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
        }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // 🎯 Sumar puntos a un usuario
    @Operation(
        summary = "Sumar puntos a un usuario",
        description = "Permite incrementar los puntos de un usuario como parte del sistema de logros o recompensas.",
        parameters = {
            @Parameter(name = "id", description = "UUID del usuario", required = true),
            @Parameter(name = "cantidad", description = "Cantidad de puntos a sumar", required = true)
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Puntos sumados correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
        }
    )
    @PatchMapping("/{id}/puntos")
    public ResponseEntity<UserResponseDTO> sumarPuntos(
            @PathVariable UUID id,
            @RequestParam Integer cantidad
    ) {
        return ResponseEntity.ok(userService.sumarPuntos(id, cantidad));
    }
}
