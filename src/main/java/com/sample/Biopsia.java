package com.sample;

public class Biopsia {
	Utente utente;
	boolean resultado; //Resultado : Positivo (true) | Negativo (false)
	boolean ultrassom; 
	boolean guiada_ressomag; //Biopsia guiada por Ressonância magnética
	String risco; //Baixo / Medio / Alto
	int random_int;
	
	public Biopsia(boolean resultado, Utente utente) { 
		this.resultado = resultado;
		this.utente = utente;
	}
	
	public Biopsia(Utente utente, boolean ultrassom) {
		super();
		this.utente = utente;
		this.ultrassom = ultrassom;
		random_int = (int)Math.floor(Math.random()*((2-1)+1)+1);
		if(random_int == 1) { //Resultado:Negativo
			this.resultado = false;
		}else {
			this.resultado = true;
		}
		random_int = (int)Math.floor(Math.random()*((3-1)+1)+1);
		if(random_int == 1) { //Baixo Risco
			this.risco = "baixo";
		}else if(random_int == 2) { //Médio Risco
			this.risco = "medio";
		}else if(random_int == 3) { //Alto Risco
			this.risco = "alto";
		}
		System.out.println("O utente " + utente.getNome() +" realizou uma Biopsia "+(ultrassom ? " guiada por Ultrassom " : "") + " com resultado: " 
								+ (resultado ? "Positivo" : "Negativo") +" e Risco: " + risco);
	}
	
	

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public boolean isResultado() {
		return resultado;
	}

	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

	public boolean isUltrassom() {
		return ultrassom;
	}

	public void setUltrassom(boolean ultrassom) {
		this.ultrassom = ultrassom;
	}

	public String getRisco() {
		return risco;
	}

	public void setRisco(String risco) {
		this.risco = risco;
	}

	public boolean isGuiada_ressomag() {
		return guiada_ressomag;
	}

	public void setGuiada_ressomag(boolean guiada_ressomag) {
		this.guiada_ressomag = guiada_ressomag;
	}
	
}
