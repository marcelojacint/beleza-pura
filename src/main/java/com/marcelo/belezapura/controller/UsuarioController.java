package com.marcelo.belezapura.controller;


import com.marcelo.belezapura.controller.dtos.UsuarioRequestDTO;
import com.marcelo.belezapura.controller.dtos.UsuarioResponseDTO;
import com.marcelo.belezapura.controller.mappers.UsuarioMapper;
import com.marcelo.belezapura.model.Usuario;
import com.marcelo.belezapura.service.UsuarioService;
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
    public ResponseEntity<List<UsuarioResponseDTO>> listaTodos() {
        List<Usuario> listaUsuarios = service.listaTodos();
        List<UsuarioResponseDTO> listaUsuariosResponses = listaUsuarios.stream().map(mapper::toResponse).toList();
        return ResponseEntity.ok(listaUsuariosResponses);
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO buscaPorId(@PathVariable String id) {
        return service.buscaPorId(id)
                .map(usuario -> ResponseEntity.ok(mapper.toResponse(usuario)))
                .orElse(ResponseEntity.notFound().build()).getBody();
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criaNovo(@RequestBody UsuarioRequestDTO dto) {
        Usuario usuario = mapper.toEntity(dto);
        Usuario usuarioNovo = service.criaNovo(usuario);
        UsuarioResponseDTO usuarioResponseDTO = mapper.toResponse(usuarioNovo);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("id")
                .buildAndExpand(usuario.getId())
                .toUri();
        return ResponseEntity.created(uri).body(usuarioResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualiza(@PathVariable String id, @RequestBody UsuarioRequestDTO dto) {
        Usuario usuario = mapper.toEntity(dto);
        service.atualiza(id, usuario);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable String id) {
         service.remove(id);
         return ResponseEntity.noContent().build();
    }

}
