package it.uniroma3.dia.model;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.google.gson.Gson;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index; 

@Entity
@Cache

@ToString(includeFieldNames=true, doNotUseGetters=true)
@EqualsAndHashCode
public class Note {
	
	public Note() { }
	
	public Note(Long id){
		this.id = id;
	}
	
	public Note(Long id, Date data, String content, String author,
			int priority, String star) {
		this.id = id;
		this.data = data;
		this.content = content;
		this.author = author;
		this.priority = priority;
		this.star = star;
	}

	@Id @Index
	private Long id;
	
	private Date data;
	
	private String content;

	@Index
	private String author;

	private int priority;

	private String star;
	
	public String toJson() {
		return (new Gson()).toJson(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}
	
}