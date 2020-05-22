package org.formacio.setmana2.repositori;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.formacio.setmana2.domini.Alumne;
import org.formacio.setmana2.domini.Curs;
import org.formacio.setmana2.domini.Matricula;
import org.springframework.stereotype.Repository;

/**
 * Modifica aquesta classe per tal que sigui un component Spring que realitza les 
 * operacions de persistencia tal com indiquen les firmes dels metodes
 */
@Repository
public class RepositoriEscola {

	@PersistenceContext
	private EntityManager em;
	
	public Curs carregaCurs(String nom) {
		return em.find(Curs.class, nom);
	}
	
	
	public Matricula apunta (String alumne, String curs) throws EdatIncorrecteException {
	    Alumne alumnes = em.find(Alumne.class, alumne);	
	    Curs curso = this.carregaCurs(curs);
	    
	    if (alumnes == null || curso == null) {
	    	return null;
	    }
	 
	    Matricula matriculas = new Matricula();
	    
	    if (alumnes.getEdat() >= curso.getEdatMinima()) {
	    	matriculas.setAlumne(alumnes);
	    	matriculas.setCurs(curso);
	    	em.persist(matriculas);
	    	} else {
	    		throw new EdatIncorrecteException();
	    	}
	    return matriculas;
	}
	
}
