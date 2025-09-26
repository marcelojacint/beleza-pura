package com.marcelo.belezapura.controller;

import com.marcelo.belezapura.controller.utils.GeraUriCriacao;
import com.marcelo.belezapura.model.Servico;
import com.marcelo.belezapura.service.ServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/servico")
@RequiredArgsConstructor
public class ServicoController {

    private final ServicoService service;

    @GetMapping
    public ResponseEntity<List<Servico>> listar() {
        List<Servico> listaServicos = service.listar();
        return ResponseEntity.ok(listaServicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarPorId(@PathVariable String id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Servico> salvar(@RequestBody Servico servico) {
        Servico servicoSalvo = service.salvar(servico);
        URI uri = GeraUriCriacao.gerarUri(servicoSalvo.getId());
        return ResponseEntity.created(uri).body(servicoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(String id, Servico servico) {
        Servico servicoAtualizado = service.atualizar(id, servico);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(String id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

}
