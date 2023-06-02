package com.algaworks.algafood.api;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
        Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);
        return cozinha.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {
        return cadastroCozinhaService.salvar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@RequestBody Cozinha cozinha,
                                             @PathVariable Long cozinhaId) {

        Optional<Cozinha> cozinhaEncontrada = cozinhaRepository.findById(cozinhaId);

        if(cozinhaEncontrada.isPresent()) {
            Cozinha cozinhaSalva = cozinhaEncontrada.get();
            BeanUtils.copyProperties(cozinha, cozinhaSalva, "id");
            cozinhaSalva = cozinhaRepository.save(cozinhaSalva);
            return ResponseEntity.ok(cozinhaSalva);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Void> remover(@PathVariable Long cozinhaId) {
//        Optional<Cozinha> cozinhaEncontrada = cozinhaRepository.findById(cozinhaId);
        try{
            cadastroCozinhaService.remover(cozinhaId);
            return ResponseEntity.noContent().build();

        } catch(EntidadeEmUsoException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        } catch(EntidadeNaoEncontradaException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
