package it.lavori.gestione_ruoli.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {
	
	private final String type = "Bearer";
	private String token;
	private List<String> ruoli;

}
