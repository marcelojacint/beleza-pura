package com.marcelo.belezapura.service;

import com.marcelo.belezapura.exception.EntidadeNaoEncontradaException;
import com.marcelo.belezapura.model.Usuario;
import com.marcelo.belezapura.repository.UsuarioRepository;
import com.marcelo.belezapura.validator.UsuarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioValidator validator;

    public List<Usuario> listarTodos() {
        List<Usuario> listaUsuarios = repository.findAll();
        if (listaUsuarios.isEmpty()) {
            throw new EntidadeNaoEncontradaException("lista de Usuários vazia!");
        }
        return listaUsuarios;
    }

    public Optional<Usuario> buscarPorId(String id) {
        return repository.findById(UUID.fromString(id));
    }

    public Usuario salvar(Usuario usuario) {
        validator.validar(usuario);
        return repository.save(usuario);
    }

    public Usuario atualizar(String id, Usuario usuario) {
        Usuario usuarioExistente = repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("usuário não existe!"));
        usuario.setId(usuarioExistente.getId());
        usuarioExistente.setNomeCompleto(usuario.getNomeCompleto());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setTelefone(usuario.getTelefone());
        usuarioExistente.setSenha(usuario.getSenha());
        usuarioExistente.setRole(usuarioExistente.getRole());

        validator.validar(usuario);
        return repository.save(usuarioExistente);
    }

    public void remover(String id) {
        repository.findById(UUID.fromString(id))
                .ifPresentOrElse(repository::delete,
                        () -> {
                            throw new EntidadeNaoEncontradaException("usuário não encontrado!");
                        }
                );

    }
}
