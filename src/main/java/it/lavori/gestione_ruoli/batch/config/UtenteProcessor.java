package it.lavori.gestione_ruoli.batch.config;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import it.lavori.gestione_ruoli.model.Utente;

public class UtenteProcessor implements ItemProcessor<Utente, Utente> {

	@Override
	public Utente process(Utente utente) throws Exception {
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		utente.setPassword(bCrypt.encode("password"));
		return utente;
	}

}
