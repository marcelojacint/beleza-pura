package com.marcelo.belezapura.validator;

import com.marcelo.belezapura.exception.RegistroDuplicadoException;
import com.marcelo.belezapura.model.Usuario;
import com.marcelo.belezapura.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsuarioValidator {
    private final UsuarioRepository repository;

    public void validar(Usuario usuario) {
        if (existeUsuarioCadastrado(usuario)) {
            throw new RegistroDuplicadoException("Usuário já cadastrado!");
        }
    }

    private boolean existeUsuarioCadastrado(Usuario usuario) {
        Optional<Usuario> usuarioEncontrado = repository.findByEmail(usuario.getEmail());

        if (usuario.getId() == null) {
            return usuarioEncontrado.isPresent();
        }
        return !usuario.getId().equals(usuarioEncontrado.get().getId()) && usuarioEncontrado.isPresent();
    }
}
