package br.senai.sc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.senai.sc.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
