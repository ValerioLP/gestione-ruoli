		IntStream.range(0, 10).forEach(i -> {
			Utente u = new Utente();
			u.setNome("Nome"+i);
			u.setCognome("Cognome"+i);
			u.setEmail("Email"+i);
			u.setPassword("Password"+i);
			utenteService.insert(u);
		});

		^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
		
		popolaUtentiByFile("utenti.csv");
		
		Ruolo r = new Ruolo();
		r.setNome(Ruoli.SUPERADMIN);
		r.setDescrizione("Sono un SuperAdmin");
		ruoloService.insert(r);
		r.setNome(Ruoli.ADMIN);
		r.setDescrizione("Sono un Admin");
		ruoloService.insert(r);
		r.setNome(Ruoli.UTENTE);
		r.setDescrizione("Sono un Utente");
		ruoloService.insert(r);
		
		Utente u = utenteService.getByNome("valerio");
		Ruolo admin = new Ruolo();
		admin = ruoloService.getByNome(Ruoli.ADMIN);
		u.addRuolo(admin);
		utenteService.update(u);
		
		Utente valerio = utenteService.getByNome("valerio");
		Ruolo utente = new Ruolo();
		utente = ruoloService.getByNome(Ruoli.UTENTE);
		valerio.addRuolo(utente);
		utenteService.update(valerio);

**************************************************************************

		delete from utente_ruolo;
		delete from utente;
		delete from ruolo

***************************************************************************
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
.\bin\windows\kafka-server-start.bat .\config\server.properties

