package it.lavori.gestione_ruoli.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.lavori.gestione_ruoli.model.Utente;
import it.lavori.gestione_ruoli.repository.UtenteRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UtenteRepository utenteRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<Utente> user = utenteRepository.findByUserName(userName);

		if (user.isPresent()) {
			return UserDetailsImpl.build(user.get());
		} else {
			throw new UsernameNotFoundException("User Not Found with username: " + userName);
		}
	}
}
