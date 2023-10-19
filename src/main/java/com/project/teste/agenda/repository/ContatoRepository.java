package com.project.teste.agenda.repository;

import com.project.teste.agenda.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
    @Query("SELECT c FROM Contato c WHERE c.nome LIKE %:nome%")
    List<Contato> findByNomeContaining(@Param("nome") String nome);

    @Query("SELECT c FROM Contato c WHERE c.telefone LIKE %:telefone%")
    List<Contato> findByTelefone(@Param("telefone") String telefone);

    Optional<Contato> findContatoById(Long id);
}
