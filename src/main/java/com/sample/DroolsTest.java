package com.sample;

import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


public class DroolsTest {
	public static KieSession kSession;
	public static Scanner in;
	public static int id_utentes = 0;
	public static ArrayList<Utente> utentes;
	public static ArrayList<Medico> medicos;
	
    public static final void main(String[] args) {
    	int op;
    	in = new Scanner(System.in);
    	utentes = new ArrayList<Utente>();
    	medicos = new ArrayList<Medico>();
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
    	    kSession = kContainer.newKieSession("ksession-rules");
    	    
           
            
            do {
            	System.out.println("Menu: \n 1- Adicionar novo médico");
            	System.out.println(" 2- Adicionar novo utente");
            	System.out.println(" 3- Adicionar pelo ficheiro .txt");
            	System.out.println(" 4- Sair do menu inicial");
            	op = in.nextInt();
            	switch(op) {
            		case 1 : addMedico();break;
            		case 2 : addUtente();break;
            		case 3 : try {
            					insertFile();
            				}catch(IOException e) {
            					e.printStackTrace();
            				}
            				break;
            		default: break;
            	}
            }while(op != 4);
            
            //kSession.insert();
            kSession.fireAllRules();
            kSession.dispose();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static void addUtente() throws ParseException { 
    	String nome,suspeitas_clinicas,data_consult,lesao,susp_bioq;
    	boolean lesao_focal,suspeitas_bioquimicas;
    	
    	System.out.println("Introduza o nome do utente:");
    	nome = in.next();
    	System.out.println("Introduza a data da consulta (dd/mm/yyyy)");
    	data_consult = in.next();
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	Date date = sdf.parse(data_consult);
    	Calendar data_consulta = Calendar.getInstance();
    	data_consulta.setTime(date);
    	System.out.println("Introduza as suspeitas clinicas (baixas/altas)");
    	do {
    		suspeitas_clinicas = in.next();
    	}while(!checkSuspeitas(suspeitas_clinicas));
    	System.out.println("O utente tem alguma lesão focal? (Sim/Nao)");
    	do {
    		lesao = in.next();
    	}while(!checkSimNao(lesao));
    	if(lesao.equalsIgnoreCase("sim")) {
    		lesao_focal = true;
    	}else {
    		lesao_focal = false;
    	}
    	System.out.println("O utente tem suspeitas bioquimicas? (Sim/Nao)");
    	do {
    		susp_bioq = in.next();
    	}while(!checkSimNao(susp_bioq));
    	if(susp_bioq.equalsIgnoreCase("sim")) {
    		suspeitas_bioquimicas = true;
    	}else {
    		suspeitas_bioquimicas = false;
    	}
    	if(new SimpleDateFormat("dd/MM/yyyy").parse(data_consult).after(new Date())) {
    		Utente u = new Utente(id_utentes,nome,data_consulta,null,suspeitas_clinicas,lesao_focal,suspeitas_bioquimicas);
        	id_utentes++;
        	kSession.insert(u);
        	utentes.add(u);
    	}
    }
    
    public static void addMedico() {
    	String nome;
    	System.out.println("Introduza o nome do medico:");
    	nome = in.next();
    	Medico m = new Medico(nome);
    	medicos.add(m);
    	kSession.insert(m);
    }
    
    public static void insertFile() throws IOException, ParseException{
    	String splitBy = ",";
        String line = "";
    	System.out.println("Formato do ficheiro:\nMedico:nome");
    	System.out.println("Utente:nome,dd/mm/yyyy,suspeitas clinicas(baixas/altas),lesão focal(sim/nao),suspeitas bioquimicas(sim/nao)");
    	String nome_fich;
    	System.out.println("Introduza o nome do ficheiro:");
    	nome_fich = in.next();
    	boolean lesao_focal,suspbioq;
    	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(nome_fich), StandardCharsets.UTF_8));
    	while ((line = br.readLine()) != null)   //returns a Boolean value
        {
    		String[] dados = line.split(splitBy);    // use comma as separator
    		if(dados.length==1) { //medico
    			Medico m = new Medico(dados[0]);
    	    	medicos.add(m);
    	    	kSession.insert(m);
    		}else if(dados.length == 5 && checkSuspeitas(dados[2]) && checkSimNao(dados[3]) && checkSimNao(dados[4])) {
    			if(dados[3].equalsIgnoreCase("sim")) {
    	    		lesao_focal = true;
    	    	}else {
    	    		lesao_focal = false;
    	    	}
    			if(dados[4].equalsIgnoreCase("sim")) {
    				suspbioq = true;
    	    	}else {
    	    		suspbioq = false;
    	    	}
    			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	    	Date date = sdf.parse(dados[1]);
    	    	Calendar data_consulta = Calendar.getInstance();
    	    	data_consulta.setTime(date);
    	    	//Verificar data da consulta
    	    	if (new SimpleDateFormat("dd/MM/yyyy").parse(dados[1]).after(new Date())) {
    	    		Utente u = new Utente(id_utentes,dados[0],data_consulta,null,dados[2],lesao_focal,suspbioq);
        			id_utentes++;
        	    	kSession.insert(u);
        	    	utentes.add(u);
    	    	}
    		}
        }
    	br.close();
    }
    
    public static boolean checkSuspeitas(String dados) {
    	if(dados.equalsIgnoreCase("baixas") || dados.equalsIgnoreCase("altas")) {
    		return true;
    	}
    	return false;
    }
    
    public static boolean checkSimNao(String dados) {
    	if(dados.equalsIgnoreCase("sim") || dados.equalsIgnoreCase("nao")) {
    		return true;
    	}
    	return false;
    }
}
