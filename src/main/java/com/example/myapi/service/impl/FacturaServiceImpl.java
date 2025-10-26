package com.example.myapi.service.impl;

import com.example.myapi.model.dto.FacturaRequestDTO;
import com.example.myapi.model.dto.FacturaResponseDTO;
import com.example.myapi.model.entity.Factura;
import com.example.myapi.model.entity.Product;
import com.example.myapi.model.entity.User;
import com.example.myapi.repository.FacturaRepository;
import com.example.myapi.repository.ProductRepository;
import com.example.myapi.repository.UserRepository;
import com.example.myapi.service.FacturaService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacturaServiceImpl implements FacturaService {

    private final FacturaRepository facturaRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public FacturaServiceImpl(FacturaRepository facturaRepository,
                              UserRepository userRepository,
                              ProductRepository productRepository) {
        this.facturaRepository = facturaRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public FacturaResponseDTO crearFactura(FacturaRequestDTO request) {
        Optional<User> userOpt = userRepository.findById(request.getUserId());
        Optional<Product> productOpt = productRepository.findById(request.getProductId());

        if (userOpt.isEmpty() || productOpt.isEmpty()) {
            throw new RuntimeException("Usuario o producto no encontrado");
        }

        User user = userOpt.get();
        Product product = productOpt.get();

        Factura factura = Factura.builder()
                .user(user)
                .product(product)
                .nombreJuego(product.getName())
                .precioTotal(product.getPrice())
                .build();

        // Sumar puntos al usuario (ejemplo: 1 punto por cada 10 unidades gastadas)
        int puntosGanados = product.getPrice().intValue() / 10;
        user.setPuntos(user.getPuntos() + puntosGanados);
        userRepository.save(user);

        Factura savedFactura = facturaRepository.save(factura);

        return mapToResponseDTO(savedFactura);
    }

    @Override
    public List<FacturaResponseDTO> obtenerFacturasPorUsuario(UUID userId) {
        List<Factura> facturas = facturaRepository.findByUserId(userId);
        return facturas.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FacturaResponseDTO> obtenerTodas() {
        return facturaRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // Método auxiliar para mapear entidad → DTO
    private FacturaResponseDTO mapToResponseDTO(Factura factura) {
        return FacturaResponseDTO.builder()
                .id(factura.getId())
                .nombreJuego(factura.getNombreJuego())
                .precioTotal(factura.getPrecioTotal())
                .fechaCompra(factura.getFechaCompra())
                .userId(factura.getUser().getId())
                .userName(factura.getUser().getName())
                .productId(factura.getProduct().getId())
                .build();
    }
}
