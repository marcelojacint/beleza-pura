package com.marcelo.belezapura.model;

import com.marcelo.belezapura.model.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "/usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nomeCompleto;
    private String telefone;
    private String email;
    private String senha;

    @Enumerated(EnumType.STRING)
    private Role role = Role.CLIENTE;

    private Instant createdAt;
    private Instant updatedAt;


}
