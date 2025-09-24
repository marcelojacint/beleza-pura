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
    private String email;
    private String telefone;
    private Role role;
    private Instant createdAt;
    private Instant updatedAt;


}
