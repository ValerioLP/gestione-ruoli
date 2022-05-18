package it.lavori.gestione_ruoli.util;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

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
	public void run(String... args) throws CsvValidationException {
//		Utente u = utenteService.getByNome("Nome1");
//		Ruolo r = ruoloService.getByNome(Ruoli.ADMIN);
//		u.addRuolo(r);
//		utenteService.update(u);
		popolaUtentiByFile("utenti.csv");
	}
	
	public void popolaUtentiByFile(String fileName) throws CsvValidationException {
		try (FileReader fr = new FileReader(fileName, StandardCharsets.UTF_8); CSVReader reader = new CSVReader(fr)) {
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				for (String s2 : nextLine) {
					String[] valori = s2.toLowerCase().split(";");
					Utente u = new Utente();
					u.setNome(valori[0]);
					u.setCognome(valori[1]);
					u.setEmail(valori[2]);
					u.setPassword(valori[3]);
					utenteService.insert(u);
				}
			}
		} catch (IOException e) {
			System.out.println("Errore durante l'apertura del file");
			e.printStackTrace();
		}
	}
}
