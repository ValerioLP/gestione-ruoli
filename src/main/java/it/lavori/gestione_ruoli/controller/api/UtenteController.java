package it.lavori.gestione_ruoli.controller.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.lavori.gestione_ruoli.model.Utente;
import it.lavori.gestione_ruoli.service.UtenteService;

@RestController
@RequestMapping("/api")
public class UtenteController {

	Logger logger = LoggerFactory.getLogger(UtenteController.class);

	@Autowired
	UtenteService utenteService;
	
	@GetMapping("/utenti")
	public List<Utente> getAll() {
		return utenteService.getAll();
	}
	
	@PostMapping("/insertUtente")
	public Utente insert(@RequestBody Utente utente) {
		return utenteService.insert(utente);
	}
	
	@DeleteMapping("/deleteUtente")
	public HttpStatus delete(@RequestParam Long codice) {
		utenteService.delete(codice);
		return HttpStatus.OK;
	}
	
	@PutMapping("/updateUtente")
	public Utente update(@RequestBody Utente utente) {
		return utenteService.update(utente);
	}
}
