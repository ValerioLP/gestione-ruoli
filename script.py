with open("utenti.csv", "w") as f:
	for i in range(1000):
		f.write("Mario"+str(i)+";Rossi"+str(i)+";mario"+str(i)+"@email.com;password"+str(i)+"\n")
	f.write("Mario1000;Rossi1000;mario1000@email.com;password1000")
	f.close()