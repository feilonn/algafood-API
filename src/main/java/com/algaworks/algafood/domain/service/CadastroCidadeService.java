package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroCidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();

        Optional<Estado> estado = estadoRepository.findById(estadoId);

        if(estado.isEmpty()) {
            throw new EntidadeNaoEncontradaException(String.format(
                    "Não foi encontrado estado com o ID %d", estadoId));
        }

        cidade.setEstado(estado.get());

        return cidadeRepository.save(cidade);
    }

    public void remover(Long cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);
        } catch(EmptyResultDataAccessException ex) {
            throw new EntidadeNaoEncontradaException(String.format
                    ("Não foi encontrado cadastro de Cidade com o ID %d", cidadeId));
        }
    }

}
