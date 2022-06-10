package it.lavori.gestione_ruoli.kafka;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import it.lavori.gestione_ruoli.controller.api.UtenteController;
import it.lavori.gestione_ruoli.dto.UtenteDto;
import it.lavori.gestione_ruoli.model.Utente;

@Service
public class JsonKafkaConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);
	
	@Autowired
	private UtenteController utenteController;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@KafkaListener(topics = "helloworld_json", groupId = "myGroup")
	public void consume(UtenteDto data) {
		LOGGER.info(String.format("Messaggio ricevuto -> %s", data.toString()));
		Utente utente = mapper.map(data, Utente.class);
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		utente.setPassword(bCrypt.encode(utente.getPassword()));
		utenteController.insert(utente);
	}
}
