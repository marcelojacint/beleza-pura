package com.marcelo.belezapura.controller;


import com.marcelo.belezapura.controller.dtos.ErroResposta;
import com.marcelo.belezapura.controller.dtos.UsuarioRequestDTO;
import com.marcelo.belezapura.controller.dtos.UsuarioResponseDTO;
import com.marcelo.belezapura.controller.mappers.UsuarioMapper;
import com.marcelo.belezapura.exception.RegistroDuplicadoException;
import com.marcelo.belezapura.model.Usuario;
import com.marcelo.belezapura.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService service;
    private final UsuarioMapper mapper;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        List<Usuario> listaUsuarios = service.listarTodos();
        List<UsuarioResponseDTO> listaUsuariosResponses = listaUsuarios
                .stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(listaUsuariosResponses);
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO buscarPorId(@PathVariable String id) {
        return service.buscarPorId(id)
                .map(usuario -> ResponseEntity.ok(mapper.toResponse(usuario)))
                .orElse(ResponseEntity.notFound().build()).getBody();
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@Valid @RequestBody UsuarioRequestDTO dto) {
        try {
            Usuario usuario = mapper.toEntity(dto);
            Usuario usuarioNovo = service.salvar(usuario);
            UsuarioResponseDTO usuarioResponseDTO = mapper.toResponse(usuarioNovo);
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("id")
                    .buildAndExpand(usuario.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(usuarioResponseDTO);
        } catch (RegistroDuplicadoException e) {
            ErroResposta erroResposta = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroResposta.status()).body(erroResposta);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable String id, @RequestBody UsuarioRequestDTO dto) {
        try {
            Usuario usuario = mapper.toEntity(dto);
            service.atualizar(id, usuario);
            return ResponseEntity.noContent().build();
        } catch (RegistroDuplicadoException e) {
            ErroResposta erroResposta = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroResposta.status()).body(erroResposta);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable String id) {
         service.remover(id);
         return ResponseEntity.noContent().build();
    }

}
