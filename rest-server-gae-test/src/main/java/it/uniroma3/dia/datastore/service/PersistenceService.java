package it.uniroma3.dia.datastore.service;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;

import it.uniroma3.dia.model.Note;

public class PersistenceService {
	
	public static Objectify objectifyInstance = OfyService.ofy(); 
	
	public static Key<Note> persist(Note note){
		//Validare la nota
		return objectifyInstance.save().entity(note).now();
	}

	public static void persistAsync(Note note){
		//Validare la nota
		objectifyInstance.save().entity(note);
	}	
	
}
