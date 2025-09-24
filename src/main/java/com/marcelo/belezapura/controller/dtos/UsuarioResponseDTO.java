package com.marcelo.belezapura.controller.dtos;

import java.util.UUID;

public record UsuarioResponseDTO(
        UUID id,
        String nomeCompleto,
        String telefone,
        String email) {
}
