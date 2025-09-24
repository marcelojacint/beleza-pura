package com.marcelo.belezapura.controller;


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

    @GetMapping
    public ResponseEntity<List<Usuario>> listaTodos() {
        List<Usuario> listaUsuarios = service.listaTodos();
        return ResponseEntity.ok(listaUsuarios);
    }

    @GetMapping("/{id}")
    public Usuario buscaPorId(@PathVariable String id) {
        return service.buscaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()).getBody();
    }

    @PostMapping
    public ResponseEntity<Usuario> criaNovo(@RequestBody Usuario usuario) {
        Usuario usuarioNovo = service.criaNovo(usuario);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("id")
                .buildAndExpand(usuario.getId())
                .toUri();
        return ResponseEntity.created(uri).body(usuarioNovo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualiza(@PathVariable String id, @RequestBody Usuario usuario) {
        service.atualiza(id, usuario);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable String id) {
         service.remove(id);
         return ResponseEntity.noContent().build();
    }

}
