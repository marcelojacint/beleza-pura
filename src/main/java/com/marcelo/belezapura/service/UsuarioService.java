package com.marcelo.belezapura.service;

import com.marcelo.belezapura.model.Usuario;
import com.marcelo.belezapura.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;

    public List<Usuario> listaTodos() {
        List<Usuario> listaUsuarios = repository.findAll();
        if (listaUsuarios.isEmpty()) {
            throw new RuntimeException("lista de Usuários vazia!");
        }
        return listaUsuarios;
    }

    public Optional<Usuario> buscaPorId(String id) {
        return repository.findById(UUID.fromString(id));
    }

    public Usuario criaNovo(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario atualiza(String id, Usuario usuario) {
        Usuario usuarioExistente = repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("usuário não existe!"));
        usuario.setId(usuarioExistente.getId());

        return repository.save(usuario);
    }

    public void remove(String id) {
        repository.findById(UUID.fromString(id))
                .ifPresentOrElse(repository::delete,
                        () -> {
                            throw new RuntimeException("usuário não encontrado!");
                        }
                );

    }
}
