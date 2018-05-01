package br.com.valhala.agenda.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.valhala.agenda.modelo.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    @Query(value = "SELECT c FROM Contato c LEFT JOIN FETCH c.telefones WHERE c.id = :id")
    Contato findById(@Param("id") Long id);

}
