package it.lavori.gestione_ruoli.util;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import it.lavori.gestione_ruoli.dto.UtenteDto;
import it.lavori.gestione_ruoli.kafka.JsonKafkaProducer;
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
	
	@Autowired
	private JsonKafkaProducer jsonKafkaProducer;

	@Override
	@Transactional
	public void run(String... args) throws CsvValidationException {
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
