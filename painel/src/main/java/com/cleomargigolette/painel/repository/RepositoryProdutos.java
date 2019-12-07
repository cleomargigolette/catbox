package com.cleomargigolette.painel.repository;

import com.cleomargigolette.painel.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryProdutos extends JpaRepository<Produto, Long> {
}
