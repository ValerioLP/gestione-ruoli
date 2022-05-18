package it.lavori.gestione_ruoli.model;


import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ruolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codice;
    
	@Enumerated(EnumType.STRING)
	@Column(name = "nome")
	private Ruoli nomeRuolo;

    private String descrizione;

    @Basic(optional = false)
    @Column(name = "updated_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
	private Date updated_at;	
	
	@ManyToOne
	@JoinColumn(name = "codice_utente")
	private Utente utente;

}
