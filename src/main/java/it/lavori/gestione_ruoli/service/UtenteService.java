package it.lavori.gestione_ruoli.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import it.lavori.gestione_ruoli.model.Ruolo;
import it.lavori.gestione_ruoli.model.Utente;
import it.lavori.gestione_ruoli.repository.UtenteRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UtenteService {
	
	private final UtenteRepository utenteRepository;
	
	public UtenteService(UtenteRepository  utenteRepository ) {
		this.utenteRepository = utenteRepository;
	}
	
	public List<Utente> getAll() {
		return utenteRepository.findAll();
	}
	
	public Utente insert(Utente utente) {
		return utenteRepository.save(utente);		
	}
	
	public void delete(Long codice) {
		utenteRepository.deleteById(codice);;
	}
	
	public Utente update(Utente utente) {
		return utenteRepository.save(utente);
	}
	
	public Utente getById(Long codice) {
		return utenteRepository.getById(codice);
	}
	
	public List<Ruolo> getRuoliByCodice(Long codice) {
		Optional<Utente> utenteOpt = utenteRepository.findById(codice);
		List<Ruolo> lista = utenteOpt.isPresent() ? utenteOpt.get().getRuoli() : new ArrayList<Ruolo>();
		return lista;
	}	
	
	public Long getCount() {
	    return utenteRepository.count();
	}

	public Utente getByNome(String nome) {
		return utenteRepository.getByNome(nome);
	}

}
