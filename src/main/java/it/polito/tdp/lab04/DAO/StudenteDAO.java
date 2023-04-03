package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public Studente getNomeECognomeDaMatricola(int matricola) {
		
		final String sql = "SELECT * "
				+ "FROM studente "
				+ "WHERE matricola = ?";
		
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			
			rs.next();
			Studente risultato = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS"));
			
			
			st.close();
			rs.close();
			conn.close();
		
			return risultato;
		
		}catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("Errore connessione al database");
			return null;
		}
	}
	
	public List<Corso> getCorsiACuiEIscrittoUnoStudente(Studente s){
		
		final String sql ="SELECT c.codins, c.crediti, c.nome, c.pd "
				+ "FROM corso c, iscrizione i "
				+ "WHERE c.codins = i.codins and i.matricola = ?";
		
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, s.getMatricola());
			ResultSet rs = st.executeQuery();
			
			List<Corso> l = new LinkedList<Corso>();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				l.add(c);
			}
			
			st.close();
			rs.close();
			conn.close();
		
			return l;
		
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			return null;
		}
	}

}
