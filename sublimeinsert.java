		IntStream.range(0, 10).forEach(i -> {
			Utente u = new Utente();
			u.setNome("Nome"+i);
			u.setCognome("Cognome"+i);
			u.setEmail("Email"+i);
			u.setPassword("Password"+i);
			utenteService.insert(u);
		});
		
		Ruolo r = new Ruolo();
		r.setNomeRuolo(Ruoli.SUPERADMIN);
		r.setDescrizione("Sono un SuperAdmin");
		ruoloService.insert(r);
		r.setNomeRuolo(Ruoli.ADMIN);
		r.setDescrizione("Sono un Admin");
		ruoloService.insert(r);
		r.setNomeRuolo(Ruoli.UTENTE);
		r.setDescrizione("Sono un Utente");
		ruoloService.insert(r);

		delete from utente_ruolo;
		delete from utente;
		delete from ruolo