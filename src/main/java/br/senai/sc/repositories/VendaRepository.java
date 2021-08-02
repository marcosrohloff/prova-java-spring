package br.senai.sc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.senai.sc.models.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

}
