package com.marcelo.belezapura.service;

import com.marcelo.belezapura.exception.EntidadeNaoEncontradaException;
import com.marcelo.belezapura.model.Servico;
import com.marcelo.belezapura.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServicoService {
    private final ServicoRepository repository;

    public Servico salvar(Servico servico) {
        return repository.save(servico);
    }

    public List<Servico> listar() {
        List<Servico> listaServicos = repository.findAll();

        if (listaServicos.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Lista de serviços Vazia");
        }
        return listaServicos;
    }

    public Optional<Servico> buscarPorId(String id) {
        return buscarServico(id);
    }

    public Servico atualizar(String id, Servico servico) {
        Servico servicoEncontrado = buscarServico(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Serviço não encontrado!"));

        servicoEncontrado.setNomeServico(servico.getNomeServico());
        servicoEncontrado.setPreco(servico.getPreco());

        return servicoEncontrado;
    }

    public void remover(String id) {
        buscarServico(id).ifPresentOrElse(repository::delete, () -> {
            throw new EntidadeNaoEncontradaException("serviço não encontrado");
        });
    }

    private Optional<Servico> buscarServico(String id) {
        return repository.findById(UUID.fromString(id));
    }
}
