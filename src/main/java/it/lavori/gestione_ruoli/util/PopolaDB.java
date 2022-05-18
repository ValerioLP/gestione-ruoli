package it.lavori.gestione_ruoli.util;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.lavori.gestione_ruoli.model.Ruoli;
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
//		IntStream.range(0, 10).forEach(i -> {
//			Utente u = new Utente();
//			u.setNome("Nome"+i);
//			u.setCognome("Cognome"+i);
//			u.setEmail("Email"+i);
//			u.setPassword("Password"+i);
//			utenteService.insert(u);
//		});
		
//		Ruolo r = new Ruolo();
//		r.setNomeRuolo(getRandomRuolo());
//		r.setDescrizione("Sono un Utente");
//		r.setUtente(utenteService.getById(1L));
//		ruoloService.insert(r);	
		
		List<Utente> utenti = utenteService.getAll();
		System.out.println(utenti);
	}
	
	private Ruoli getRandomRuolo() {
		Random random = new Random();
		int number = random.nextInt(3);
		Ruoli ruolo = null;
		
		switch(number) {
			case 0 : ruolo = Ruoli.SUPERADMIN;
			case 1 : ruolo = Ruoli.ADMIN;
			case 2 : ruolo = Ruoli.UTENTE;
		}
		return ruolo;
	}
}
