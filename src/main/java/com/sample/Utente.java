package com.sample;


import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utente {
	int id;
	String nome;
	Calendar data_consulta;
	Calendar data_proxconsulta;
	String suspeitas_clinicas;
	boolean lesao_focal;
	boolean suspeitas_bioquimicas;
	
	public Utente(int id, String nome, Calendar data_consulta, Calendar data_proxconsulta, String suspeitas_clinicas,
			boolean lesao_focal, boolean suspeitas_bioquimicas) {
		super();
		this.id = id;
		this.nome = nome;
		this.data_consulta = data_consulta;
		this.data_proxconsulta = data_proxconsulta;
		this.suspeitas_clinicas = suspeitas_clinicas;
		this.lesao_focal = lesao_focal;
		this.suspeitas_bioquimicas = suspeitas_bioquimicas;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getData_consulta() {
		return data_consulta.toString();
	}


	public void setData_consulta(Calendar data_consulta) {
		this.data_consulta = data_consulta;
	}


	public String getData_proxconsulta() {
		String strdate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (data_proxconsulta != null) {
			strdate = sdf.format(data_proxconsulta.getTime());
		}
		return strdate;
	}


	public void setData_proxconsulta(Calendar data_proxconsulta) {
		this.data_proxconsulta = data_proxconsulta;
	}
	
	public void setProxConsultaMeses(int nmeses) { 
		data_proxconsulta = data_consulta;
		data_proxconsulta.add(Calendar.MONTH, nmeses);
	}
	
	public void setProxConsultaAnual() {
		data_proxconsulta = data_consulta;
		data_proxconsulta.add(Calendar.YEAR, 1);
	}


	public String getSuspeitas_clinicas() {
		return suspeitas_clinicas;
	}


	public void setSuspeitas_clinicas(String suspeitas_clinicas) {
		this.suspeitas_clinicas = suspeitas_clinicas;
	}


	public boolean isLesao_focal() {
		return lesao_focal;
	}


	public void setLesao_focal(boolean lesao_focal) {
		this.lesao_focal = lesao_focal;
	}


	public boolean isSuspeitas_bioquimicas() {
		return suspeitas_bioquimicas;
	}


	public void setSuspeitas_bioquimicas(boolean suspeitas_bioquimicas) {
		this.suspeitas_bioquimicas = suspeitas_bioquimicas;
	}
	
	
}
