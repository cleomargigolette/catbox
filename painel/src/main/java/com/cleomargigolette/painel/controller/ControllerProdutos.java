package com.cleomargigolette.painel.controller;

import com.cleomargigolette.painel.domain.Produto;
import com.cleomargigolette.painel.repository.RepositoryProdutos;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/painel")
public class ControllerProdutos {

    @Autowired
    private RepositoryProdutos repositoryProdutos;

    @PostMapping
    public Produto adionar(@Valid @RequestBody Produto produto) {
        return repositoryProdutos.save(produto);
    }

    @GetMapping
    public List<Produto> listarProdutos() {
        return repositoryProdutos.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable Long id) {
        Produto produto = repositoryProdutos.getOne(id);

        if (produto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id,
                                             @Valid @RequestBody Produto produto) {
        Produto existente = repositoryProdutos.getOne(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(produto, existente, "id");

        existente = repositoryProdutos.save(existente);

        return ResponseEntity.ok(existente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        Produto produto = repositoryProdutos.getOne(id);

        if (produto == null) {
            return ResponseEntity.notFound().build();
        }

        repositoryProdutos.delete(produto);

        return ResponseEntity.noContent().build();
    }

}
