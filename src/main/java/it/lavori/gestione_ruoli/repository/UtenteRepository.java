package it.lavori.gestione_ruoli.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.lavori.gestione_ruoli.model.Ruolo;
import it.lavori.gestione_ruoli.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente,Long>{

	List<Ruolo> getRuoliByCodice(Long codice);

	Utente getByNome(String nome);
}
