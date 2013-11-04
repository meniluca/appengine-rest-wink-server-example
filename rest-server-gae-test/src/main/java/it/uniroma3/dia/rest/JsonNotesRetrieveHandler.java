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
import javax.ws.rs.core.MediaType;

@Path("/json/notes")
public class JsonNotesRetrieveHandler {

	private static final Logger log = Logger
			.getLogger(JsonNotesRetrieveHandler.class.getName());

	@GET
	@Produces("text/html")
	public String getMessage() {
		log.info("/rest/notes GET invoked");
		return "<h1>Notes Rest Service</h1>"
				+ "To visualize notes:<br>"
				+ "- /rest/json/notes/id?q=XXX with XXX a note's id<br>"
				+ "- /rest/json/notes/author?q=XXX with XXX a note's author<br>"
				+ "- /rest/json/notes/all to retrieve all notes<br>";
	}

	@GET
	@Path("/id")
	@Produces(MediaType.APPLICATION_JSON)
	public Note retrieveFromId(@QueryParam("q") String id) {
		log.info("/rest/notes/id GET invoked with parameters q: " + id);
		if (id == null) {
			log.warning("Invalid request: q parameter missing or empty");
			return null;
		}
		Note retrievedNote = RetrieveService.retrieve(new Long(id));
		if (retrievedNote == null) {
			log.warning("Note with id " + id + " doesn't exists");
			return null;
		}
		return retrievedNote;
	}

	@GET
	@Path("/author")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Note> retrieveFromAuthors(@QueryParam("q") String author) {

		if (author == null) {
			log.warning("Invalid request: q parameter missing or empty");
			return null;
		}

		List<Note> retrievedNotesList = RetrieveService
				.retrieveFromAuthor(author);

		if (retrievedNotesList == null) {
			log.warning("Notes with author " + author + " doesn't exist");
			return null;
		}

		return retrievedNotesList;

	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Note> retrieveAll() {
		return retrieveAll(null);
	}

	@GET
	@Path("/all/{limit}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Note> retrieveAll(@PathParam("limit") String limit) {

		log.info("/rest/notes/all GET invoked");

		int limitInteger;

		try {
			limitInteger = Integer.parseInt(limit);
		} catch (NumberFormatException e) {
			limitInteger = 100;
			log.info("limit is automatically set to 100");
		}

		log.info("log limit value: " + limitInteger);
		List<Note> retrievedNotesList = RetrieveService
				.retrieveAll(limitInteger);

		if (retrievedNotesList == null) {
			log.warning("There's not any note");
			return null;
		}
		return retrievedNotesList;

	}

}