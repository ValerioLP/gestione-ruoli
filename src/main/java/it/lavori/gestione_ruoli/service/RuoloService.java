package it.lavori.gestione_ruoli.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.lavori.gestione_ruoli.model.Ruoli;
import it.lavori.gestione_ruoli.model.Ruolo;
import it.lavori.gestione_ruoli.repository.RuoloRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RuoloService {

	private final RuoloRepository ruoloRepository;
	
	public RuoloService(RuoloRepository ruoloRepository ) {
		this.ruoloRepository = ruoloRepository;
	}
	
	public List<Ruolo> getAll() {
		return ruoloRepository.findAll();
	}
	
	public Ruolo insert(Ruolo ruolo) {
		return ruoloRepository.save(ruolo);		
	}
	
	public void delete(String nome) {
		ruoloRepository.deleteById(nome);;
	}
	
	public Ruolo update(Ruolo ruolo) {
		return ruoloRepository.save(ruolo);
	}
	
	public Ruolo getByNome(Ruoli nome) {
		return ruoloRepository.getByNome(nome);
	}	
}
