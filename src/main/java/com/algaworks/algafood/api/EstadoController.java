package com.algaworks.algafood.api;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    @GetMapping
    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {
        Optional<Estado> estadoEncontrado = estadoRepository.findById(estadoId);
        return estadoEncontrado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar(@RequestBody Estado estado) {
        return cadastroEstadoService.salvar(estado);
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado> atualizar(@RequestBody Estado estado,
                                            @PathVariable Long estadoId) {
        Optional<Estado> estadoEncontrado = estadoRepository.findById(estadoId);

        if(estadoEncontrado.isPresent()) {
            Estado estadoSalvo = estadoEncontrado.get();
            BeanUtils.copyProperties(estado, estadoSalvo, "id");
            estadoSalvo = cadastroEstadoService.salvar(estadoSalvo);

            return ResponseEntity.ok(estadoSalvo);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<?> remover(@PathVariable Long estadoId) {
        try {
            cadastroEstadoService.remover(estadoId);
            return ResponseEntity.noContent().build();

        } catch(EntidadeEmUsoException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());

        } catch(EntidadeNaoEncontradaException ex) {
            return ResponseEntity.notFound().build();
        }
    }

}
