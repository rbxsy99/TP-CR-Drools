package com.sample;

public class RessoMagnetica {
	int pirads; // 1 a 5
	int random_int;
	Utente utente;
	
	public RessoMagnetica(int pirads, Utente utente) {
		super();
		this.utente = utente;
		if(pirads == 0) {
			random_int = (int)Math.floor(Math.random()*((5-1)+1)+1);
			this.pirads = random_int;
		}else {
			this.pirads = pirads;
		}
		System.out.println("O utente " + utente.getNome() +" realizou uma Ressonância Magnética com pi-rads de " + this.pirads );
	}

	public int getPirads() {
		return pirads;
	}

	public void setPirads(int pirads) {
		this.pirads = pirads;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
}
