package it.polito.tdp.lab04.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	
	public Model() {
		this.corsoDAO = new CorsoDAO();
		this.studenteDAO = new StudenteDAO();
	}
	
	public List<Corso> getTuttiCorsi(){
		return this.corsoDAO.getTuttiICorsi();
	}
	
	public Studente getNomeECognome(int matricola) {
		return this.studenteDAO.getNomeECognomeDaMatricola(matricola);
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso c){
		return this.corsoDAO.getStudentiIscrittiAlCorso(c);
	}
	
	public List<Corso> getCorsiACuiEIscrittoUnoStudente(Studente s){
		return this.studenteDAO.getCorsiACuiEIscrittoUnoStudente(s);
	}
	
	public boolean inserisciIscrizione(Studente s, Corso c) {
		return this.corsoDAO.inscriviStudenteACorso(s, c);
	}
	
	

}
