package it.lavori.gestione_ruoli.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.lavori.gestione_ruoli.model.Ruoli;
import it.lavori.gestione_ruoli.model.Ruolo;
import it.lavori.gestione_ruoli.model.Utente;
import it.lavori.gestione_ruoli.service.RuoloService;
import it.lavori.gestione_ruoli.service.UtenteService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PopolaDB implements CommandLineRunner {

	@Autowired
	UtenteService utenteService;
	
	@Autowired
	RuoloService ruoloService;

	@Override
	public void run(String... args) {
		Utente u = utenteService.getByNome("Nome1");
		Ruolo r = ruoloService.getByNome(Ruoli.ADMIN);
		u.addRuolo(r);
		utenteService.update(u);
	}
}
