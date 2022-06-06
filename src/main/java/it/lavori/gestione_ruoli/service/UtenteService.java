package it.lavori.gestione_ruoli.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.lavori.gestione_ruoli.dto.UtenteDto;
import it.lavori.gestione_ruoli.model.Ruolo;
import it.lavori.gestione_ruoli.model.Utente;
import it.lavori.gestione_ruoli.repository.UtenteRepository;

@Service
public class UtenteService {

	private final UtenteRepository utenteRepository;

	public UtenteService(UtenteRepository utenteRepository) {
		this.utenteRepository = utenteRepository;
	}

	@Autowired
	private DozerBeanMapper mapper;

	public List<UtenteDto> findAll() {
		return utenteRepository.findAll().stream().map(entity -> mapper.map(entity, UtenteDto.class))
				.collect(Collectors.toList());
	}
	
	public UtenteDto getByCodice(Long codice) {
		return mapper.map(utenteRepository.getById(codice), UtenteDto.class);
	}
	
	public UtenteDto insert(Utente utente) {
		return mapper.map(utenteRepository.save(utente), UtenteDto.class);
	}

	public void delete(Long codice) {
		boolean exists = utenteRepository.existsById(codice);
		if(!exists) {
			throw new IllegalStateException("Non esiste un utente con il codice " + codice);
		}
		utenteRepository.deleteById(codice);
	}

	@Transactional
	public UtenteDto update(Utente utente) {
		boolean exists = utenteRepository.existsById(utente.getCodice());
		if(!exists) {
			throw new IllegalStateException("L'utente " + utente.toString() + " non esiste");
		}
		return mapper.map(utenteRepository.save(utente), UtenteDto.class);
	}

	public List<Ruolo> getRuoliByCodice(Long codice) {
		Optional<Utente> utenteOpt = utenteRepository.findById(codice);
		return utenteOpt.isPresent() ? utenteOpt.get().getRuoli() : new ArrayList<Ruolo>();
	}

	public Long getCount() {
		return utenteRepository.count();
	}

	public UtenteDto getByNome(String nome) {
		return mapper.map(utenteRepository.getByNome(nome), UtenteDto.class);
	}
}
