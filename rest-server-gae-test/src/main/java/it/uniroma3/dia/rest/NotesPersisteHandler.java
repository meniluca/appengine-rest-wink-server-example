package it.uniroma3.dia.rest;

import java.util.Date;

import it.uniroma3.dia.datastore.service.PersistenceService;
import it.uniroma3.dia.model.Note;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;


@Path("/add")
public class NotesPersisteHandler {
	
	@GET
	public Response getMessage() {
		return Response.ok("GET non implemented").build();
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response risponde(JSONObject requestJSON){
		
		Note newNote = null;
		try {
			
			newNote = new Note(
					requestJSON.getLong("id"),
					new Date(System.currentTimeMillis()),
					requestJSON.getString("content"),
					requestJSON.getString("author"),
					Integer.parseInt(requestJSON.getString("priority")),
					requestJSON.getString("star")
					);
			
			PersistenceService.persist(newNote);
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.status(201).entity(newNote.toString()).build();
	}
	
}