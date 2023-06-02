package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestaurenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestaurenteRepository restaurenteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante) {
        System.out.println(restaurante);
        Long cozinhaId = restaurante.getCozinha().getId();
        Optional<Cozinha> cozinhaEncontrada = cozinhaRepository.findById(cozinhaId);

        if(cozinhaEncontrada.isEmpty()) {
            throw new EntidadeNaoEncontradaException(String.format(
                    "Não foi encontrado cozinha com o ID %d", cozinhaId));
        }

        restaurante.setCozinha(cozinhaEncontrada.get());

        return restaurenteRepository.save(restaurante);
    }
}
