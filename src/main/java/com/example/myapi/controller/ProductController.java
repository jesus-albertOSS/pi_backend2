package com.example.myapi.controller;

import com.example.myapi.model.dto.CreateProductDTO;
import com.example.myapi.model.dto.ProductResponseDTO;
import com.example.myapi.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@Tag(
    name = "🎮 Productos",
    description = """
        Endpoints relacionados con la gestión de videojuegos dentro de **Game Connect**.
        
        Permite:
        - Listar todos los juegos disponibles.
        - Obtener detalles de un juego específico.
        - Crear nuevos productos (solo administradores).
        - Eliminar productos del catálogo.
        """
)
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 📋 Obtener todos los productos
    @Operation(
        summary = "Obtener todos los productos",
        description = "Devuelve la lista completa de videojuegos disponibles en la base de datos.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Lista obtenida correctamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponseDTO.class))
            )
        }
    )
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    // 🔍 Obtener producto por ID
    @Operation(
        summary = "Obtener un producto por su ID",
        description = "Busca un videojuego específico según su identificador único (UUID).",
        parameters = {
            @Parameter(name = "id", description = "Identificador único del producto", required = true)
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable UUID id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    // ➕ Crear producto
    @Operation(
        summary = "Crear un nuevo producto",
        description = "Registra un nuevo videojuego en la base de datos con sus datos principales.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Datos del nuevo videojuego",
            content = @Content(schema = @Schema(implementation = CreateProductDTO.class))
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "Producto creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o incompletos")
        }
    )
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody CreateProductDTO dto) {
        return ResponseEntity.ok(productService.create(dto));
    }

    // 🗑️ Eliminar producto
    @Operation(
        summary = "Eliminar un producto",
        description = "Elimina permanentemente un videojuego del catálogo.",
        parameters = {
            @Parameter(name = "id", description = "Identificador único del producto", required = true)
        },
        responses = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
        }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
