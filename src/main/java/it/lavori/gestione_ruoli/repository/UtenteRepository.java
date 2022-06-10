package it.lavori.gestione_ruoli.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import it.lavori.gestione_ruoli.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente,Long>{

	Utente getByNome(String nome);
	
	public Optional<Utente> findByCodice(Integer codice);

	public Optional<Utente> findByUserName(String userName);

	public boolean existsByEmail(String email);

	public boolean existsByUserName(String userName);

}
