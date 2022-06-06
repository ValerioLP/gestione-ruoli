package it.lavori.gestione_ruoli.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import it.lavori.gestione_ruoli.dto.UtenteDto;

@Service
public class JsonKafkaProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);

	private KafkaTemplate<String, UtenteDto> kafkaTemplate;

	public JsonKafkaProducer(KafkaTemplate<String, UtenteDto> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(UtenteDto utente) {
		LOGGER.info(String.format("Messaggio inviato -> %s", utente.toString()));
		
		Message<UtenteDto> message = MessageBuilder.withPayload(utente)
				.setHeader(KafkaHeaders.TOPIC, "helloworld_json")
				.build();
		kafkaTemplate.send(message);
	}
}
