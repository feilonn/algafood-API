package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeSemAtributosObrigatoriosException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restaurenteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante) {

        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinhaEncontrada = cozinhaRepository.findById(cozinhaId)
                .orElseThrow( () ->  new EntidadeNaoEncontradaException(String.format(
                        "Não foi encontrado cozinha com o ID %d", cozinhaId)));

        restaurante.setCozinha(cozinhaEncontrada);

        return restaurenteRepository.save(restaurante);
    }

    public Restaurante atualizar(Restaurante restaurante, Long restauranteId) {
        try {
            Restaurante restauranteEncontrado = restaurenteRepository
                    .findById(restauranteId).orElse(null);

            if(restauranteEncontrado == null) {
                throw new EntidadeNaoEncontradaException(String.format(
                        "Não foi encontrado restaurante com o ID %d", restauranteId));
            }

            System.out.println(restauranteEncontrado.getId());

            BeanUtils.copyProperties(restaurante, restauranteEncontrado,
                    "id", "formasPagamento", "endereco", "dataCadastro", "dataAtualizacao");

            Long cozinhaId = restaurante.getCozinha().getId();
            Optional<Cozinha> cozinhaEncontrada = cozinhaRepository.findById(cozinhaId);

            if(cozinhaEncontrada.isEmpty()) {
                throw new EntidadeNaoEncontradaException(String.format(
                        "Não foi encontrado cozinha com o ID %d", cozinhaId));
            }

            restauranteEncontrado.setCozinha(cozinhaEncontrada.get());

            return restaurenteRepository.save(restauranteEncontrado);

        }catch(NullPointerException ex) {
            throw new EntidadeNaoEncontradaException("É preciso informar a cozinha.");
        }catch(DataIntegrityViolationException ex) {
            throw new EntidadeSemAtributosObrigatoriosException("É préciso informar os atributos do restaurante");
        }
    }
}
