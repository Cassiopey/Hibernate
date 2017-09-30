package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MounthOfBirth {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserPage> getUsers() {
		return users;
	}

	public void setUsers(List<UserPage> users) {
		this.users = users;
	}

	private String name;
	
	@OneToMany(mappedBy="mounthOfBirth")
	private List<UserPage> users = new ArrayList<>();

}
