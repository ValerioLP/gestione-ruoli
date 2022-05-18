package it.lavori.gestione_ruoli.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ruolo {

    @Id
	@Enumerated(EnumType.STRING)
	@Column(name = "nome")
	private Ruoli nome;

    private String descrizione;

}
