package it.lavori.gestione_ruoli.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utente {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codice;

    private String nome;
    private String cognome; 
    
    @Column(unique = true)
    private String email;    
    
    private String password;
    
    @OneToMany(mappedBy = "utente")
    private List<Ruolo> ruoli;

//	@ManyToMany
//	@JoinTable(name = "utente_ruolo", joinColumns = @JoinColumn(name = "codice_utente", referencedColumnName = "codice"), inverseJoinColumns = @JoinColumn(name = "nome_ruolo", referencedColumnName = "nome"))
//	private Set<Ruolo> ruoli = new HashSet<>();

//	@PrePersist
//	public void prePersist() {
//		if (codice == null) {
//			
//			
//		}
//	} 
}
