package com.marcelo.belezapura.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "servicos")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nomeServico;
    private BigDecimal preco;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;
}
