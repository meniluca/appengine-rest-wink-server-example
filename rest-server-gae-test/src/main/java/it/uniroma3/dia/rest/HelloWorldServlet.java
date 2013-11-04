package it.uniroma3.dia.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class HelloWorldServlet {
	
	@GET
	public String getMessage() {
		return "<h1>Rest Service</h1>"
				+ "The following services are available:<br>"
				+ "- <a href=\"/rest/notes\">to display notes</a>"
				+ "- <a href=\"/rest/json/notes\">to display notes with json format</a>";
	}
}