package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroEstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void remover (Long estadoId) {
        try {
            estadoRepository.deleteById(estadoId);
        }catch(DataIntegrityViolationException ex) {
            throw new EntidadeEmUsoException(String.format(
                    "O Estado com ID %d não pode ser removido, pois está em uso", estadoId));
        }catch(EmptyResultDataAccessException ex) {
            throw new EntidadeNaoEncontradaException(String.format(
                    "Não foi encontrado cadastro de Estado com ID %d", estadoId));
        }
    }
}
