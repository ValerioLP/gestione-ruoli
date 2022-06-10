package it.lavori.gestione_ruoli.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {

	private String userName;
	private String password;
}
