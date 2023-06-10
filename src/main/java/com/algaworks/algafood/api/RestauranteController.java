package com.algaworks.algafood.api;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeSemAtributosObrigatoriosException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restaurenteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

//    @GetMapping("/porNome")
//    public List<Restaurante> restaurantesPorCozinha(String nome, Long cozinhaId) {
//        return restaurenteRepository.consultarPorNome(nome, cozinhaId);
//    }

    @GetMapping("/por-nome-e-frete")
    public List<Restaurante> restaurantesPorNomeFrete(String nome, BigDecimal taxaFreteInicial,
                                                      BigDecimal taxaFreteFinal) {
        return restaurenteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
    }

    @GetMapping
    public List<Restaurante> listar() {
        return restaurenteRepository.findAll();
    }
    
    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
        Optional<Restaurante> restaurante = restaurenteRepository.findById(restauranteId);
        return restaurante.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        try{
            restaurante = cadastroRestauranteService.salvar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
        }catch(EntidadeNaoEncontradaException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> atualizar(@RequestBody Restaurante restaurante,
                                       @PathVariable Long restauranteId) {
        try{
            restaurante = cadastroRestauranteService.atualizar(restaurante, restauranteId);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
        }catch(EntidadeNaoEncontradaException | EntidadeSemAtributosObrigatoriosException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
