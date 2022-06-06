package it.lavori.gestione_ruoli.kafka;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.lavori.gestione_ruoli.dto.UtenteDto;

@RestController
@RequestMapping("api/v1/kafka")
public class JsonMessageController {

	private JsonKafkaProducer kafkaProducer;

	public JsonMessageController(JsonKafkaProducer kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}
	
	@PostMapping("/publish")
	public ResponseEntity<String> publish(@RequestBody UtenteDto utente) {
		kafkaProducer.sendMessage(utente);
		return ResponseEntity.ok("Messaggio inviato al topic");
	}
}
