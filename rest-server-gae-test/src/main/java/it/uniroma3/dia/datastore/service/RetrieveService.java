package it.uniroma3.dia.datastore.service;

import it.uniroma3.dia.model.Note;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;

public class RetrieveService {
	
	public static Objectify objectifyInstance = OfyService.ofy(); 
	
	public static List<Note> retrieveFromAuthor(String author){
		return objectifyInstance.load().type(Note.class).filter("author", author).list();
	}

	public static List<Note> retrieveAll(int limit){
		return objectifyInstance.load().type(Note.class).limit(limit).list();
	}

	public static Note retrieve(Long key){
		return retrieve(Key.create(Note.class, key));
	}

	private static Note retrieve(Key<Note> key){
		return OfyService.ofy().load().key(key).now();
	}

}
