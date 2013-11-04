package it.uniroma3.dia.rest;

import it.uniroma3.dia.datastore.service.RetrieveService;
import it.uniroma3.dia.model.Note;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.google.gson.Gson;

@Path("/notes")
public class NotesRetrieveHandler {
	
    private static final Logger log = Logger.getLogger(NotesRetrieveHandler.class.getName());  
	
	private boolean validFormat(String format){
		return format == null || ("html").equals(format) || ("plain").equals(format) || ("json").equals(format);
	}
	
	@GET
	@Produces({"text/html", "text/plain", "application/json"})
	public String getMessage() {
		log.info("/rest/notes GET invoked");
		return "<h1>Notes Rest Service</h1>"
				+ "To visualize notes:<br>"
				+ "- /rest/notes/id?q=XXX with XXX a note's id<br>"
				+ "- /rest/notes/author?q=XXX with XXX a note's author<br>"
				+ "- /rest/notes/all to retrieve all notes<br>";
	}
	
	@GET
	@Path("/id")
	@Produces({"text/html", "text/plain", "application/json"})
	public String retrieveFromId(@QueryParam("q") String id, @QueryParam("format") String format){
		
		log.info("/rest/notes/id GET invoked with parameters q: "+id+", format: "+format);
		if ( id == null || ! validFormat(format) ){
			log.warning("Invalid format of one of the parameters.");
			return "Invalid request: q parameter missing or empty, format parameter (optional) not valid (html, plain, json)";
		}
		
		Note retrievedNote = RetrieveService.retrieve(new Long(id));
		
		if ("json".equals(format)){
			return new Gson().toJson(retrievedNote);
		} else {
			if ( retrievedNote == null ) {
				return null;
			}
			return retrievedNote.toString();
		}
	}
	
	@GET
	@Path("/author")
	public String retrieveFromAuthors(@QueryParam("q") String author, @QueryParam("format") String format){
		
		if ( author == null || ! validFormat(format) ){
			return "Invalid request: q parameter missing or empty, format parameter (optional) not valid (html, plain, json)";
		}
		
		List<Note> retrievedNotesList = RetrieveService.retrieveFromAuthor(author);
		
		if ("json".equals(format)){
			return new Gson().toJson(retrievedNotesList);
		} else {
			if ( retrievedNotesList == null ) {
				return null;
			}
			return retrievedNotesList.toString();
		}
	}
	
	@GET
	@Path("/all")
	public String retrieveAll(@QueryParam("format") String format){
		return retrieveAll(null, format);
	}
	
	@GET
	@Path("/all/{limit}")
	public String retrieveAll(@PathParam("limit") String limit, @QueryParam("format") String format){
		
		log.info("/rest/notes/all GET invoked");
		if ( ! validFormat(format) ){
			log.warning("invalid format parameter");
			return "Format parameter (optional) not valid (html, plain, json)";
		}
		
		int limitInteger;
		
		try {
			limitInteger = Integer.parseInt(limit);
		} catch(NumberFormatException e){
			limitInteger = 100;
			log.info("limit is automatically set to 100");
		}
				
		log.info("log limit value: "+limitInteger);
		List<Note> retrievedNotesList = RetrieveService.retrieveAll(limitInteger);;
		
		if ("json".equals(format)){
			return new Gson().toJson(retrievedNotesList);
		} else {
			if ( retrievedNotesList == null ) {
				return null;
			}
			return retrievedNotesList.toString();
		}
	}
	
}