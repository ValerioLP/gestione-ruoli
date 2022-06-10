package it.lavori.gestione_ruoli.util;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import it.lavori.gestione_ruoli.kafka.JsonKafkaProducer;
import it.lavori.gestione_ruoli.model.Ruoli;
import it.lavori.gestione_ruoli.model.Ruolo;
import it.lavori.gestione_ruoli.model.Utente;
import it.lavori.gestione_ruoli.repository.RuoloRepository;
import it.lavori.gestione_ruoli.repository.UtenteRepository;
import it.lavori.gestione_ruoli.service.RuoloService;
import it.lavori.gestione_ruoli.service.UtenteService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PopolaDB implements CommandLineRunner {

//	@Autowired
//	UtenteService utenteService;
//
//	@Autowired
//	RuoloService ruoloService;
	
	@Autowired
	UtenteRepository utenteRepository;

	@Autowired
	RuoloRepository ruoloRepository;
	
//	@Autowired
//	private JsonKafkaProducer jsonKafkaProducer;

	@Override
	@Transactional
	public void run(String... args) throws CsvValidationException {
//		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
//		
//		Ruolo role_admin = new Ruolo();
//		role_admin.setId(Ruoli.ADMIN);
//		
//		Utente user_admin = new Utente();
//		user_admin.setNome("Valerio");
//		user_admin.setCognome("Mezzoprete");
//		user_admin.setUserName("ValerioLP");
//		user_admin.setPassword(bCrypt.encode("password"));
//		user_admin.setEmail("valerio@email");
//		user_admin.addRuolo(role_admin);
//		user_admin.setActive(true);
//
//		utenteRepository.save(user_admin);
//		
//		Ruolo role_user = new Ruolo();
//		role_user.setId(Ruoli.UTENTE);
//		
//		Utente user_uimple = new Utente();
//		user_uimple.setUserName("user");
//		user_uimple.setPassword(bCrypt.encode("user"));
//		user_uimple.setEmail("admin@domain.com");
//		user_uimple.addRuolo(role_user);
//		user_uimple.setActive(true);
//
//		utenteRepository.save(user_uimple);
		
		
		
//		popolaUtentiByFile("utenti.csv");//		
//		Ruolo r = new Ruolo();
//		r.setNome(Ruoli.SUPERADMIN);
//		r.setDescrizione("Sono un SuperAdmin");
//		ruoloService.insert(r);
//		r.setNome(Ruoli.ADMIN);
//		r.setDescrizione("Sono un Admin");
//		ruoloService.insert(r);
//		r.setNome(Ruoli.UTENTE);
//		r.setDescrizione("Sono un Utente");
//		ruoloService.insert(r);
//		
//		Utente u = utenteService.getByNome("valerio");
//		Ruolo admin = new Ruolo();
//		admin = ruoloService.getByNome(Ruoli.ADMIN);
//		u.addRuolo(admin);
//		utenteService.update(u);
//		
//		Utente valerio = utenteService.getByNome("valerio");
//		Ruolo utente = new Ruolo();
//		utente = ruoloService.getByNome(Ruoli.UTENTE);
//		valerio.addRuolo(utente);
//		utenteService.update(valerio);

//		log.info(ruoloService.getAll().toString());
//		log.info(utenteService.getAll().toString());
//		log.info(ruoloService.findById(Ruoli.ADMIN).toString());
//		log.info(utenteService.getRuoliByCodice(109L).toString());
//		log.info(ruoloService.findUtentiById(Ruoli.ADMIN).toString());

//		utenteService.delete(101L);
	}

	public void popolaUtentiByFile(String fileName) throws CsvValidationException {
		try (FileReader fr = new FileReader(fileName, StandardCharsets.UTF_8); CSVReader reader = new CSVReader(fr)) {
			log.info("Sono all'interno di popola utenti by file");
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				for (String s2 : nextLine) {
					String[] valori = s2.toLowerCase().split(";");
					Utente u = new Utente();
					u.setNome(valori[0]);
					u.setCognome(valori[1]);
					u.setEmail(valori[2]);
					u.setPassword(valori[3]);
//					utenteService.insert(u);
				}
			}
			log.info("Ho terminato l'inserimento dei record da file");
		} catch (IOException e) {
			log.error("Errore durante l'apertura del file : --> " + e.getMessage());
		}
	}
}
