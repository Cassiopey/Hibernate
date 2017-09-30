package ua.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String text;
	
	@ManyToOne
	private UserPage writer;

	@ManyToOne
	private UserPage owner;
	
	public UserPage getWriter() {
		return writer;
	}

	public void setWriter(UserPage writer) {
		this.writer = writer;
	}

	public UserPage getOwner() {
		return owner;
	}

	public void setOwner(UserPage owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
