package it.lavori.gestione_ruoli.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utente implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codice;

    private String nome;
    private String cognome; 
    
    @Column(unique = true)
    private String email;    
    
    private String password;
    
	@ManyToMany(cascade = CascadeType.PERSIST , fetch = FetchType.EAGER)
	@JoinTable(name = "utente_ruolo", 
		joinColumns = @JoinColumn(name = "codice_utente", referencedColumnName = "codice"), 
		inverseJoinColumns = @JoinColumn(name = "nome_ruolo", referencedColumnName = "nome"))
	private List<Ruolo> ruoli = new ArrayList<>();
	
	public void addRuolo(Ruolo ruolo) {
		ruoli.add(ruolo);
		if(!ruolo.getUtenti().contains(this)) ruolo.addUtente(this);
	}
	
	public void removeRuolo(Ruolo ruolo) {
		ruoli.remove(ruolo);
		if(ruolo.getUtenti().contains(this)) ruolo.removeUtente(this);
	}

	@Override
	public String toString() {
		return "Utente [codice=" + codice + ", nome=" + nome + ", cognome=" + cognome + "]";
	}
}
