package com.marcelo.belezapura.controller.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDTO(
        @NotBlank(message = "Campo Obrigatório!")
        String nomeCompleto,

        @NotBlank(message = "Campo Obrigatório!")
        String telefone,

        @Email(message = "Insira Email válido!")
        @NotBlank(message = "Campo Obrigatório!")
        String email,

        @NotBlank(message = "Campo Obrigatório!")
        @Size(min = 6, message = "Senha deve conter no minimo 6 dígitos!")
        String senha
) {

}
