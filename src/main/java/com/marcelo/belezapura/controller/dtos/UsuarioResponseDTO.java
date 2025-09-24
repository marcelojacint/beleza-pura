package com.marcelo.belezapura.controller.dtos;

import com.marcelo.belezapura.model.enums.Role;

import java.util.UUID;

public record UsuarioResponseDTO(
        UUID id,
        String nomeCompleto,
        String telefone,
        String email,
        Role role
        ) {
}
