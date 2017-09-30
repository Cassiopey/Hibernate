package ua.form.filter;

import java.util.ArrayList;
import java.util.List;

public class UserPageFilterForm {
private String name="";

private String surname="";

private String email="";

private String password="";

private String phone="";

private String City="";

	public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getSurname() {
	return surname;
}

public void setSurname(String surname) {
	this.surname = surname;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String getCity() {
	return City;
}

public void setCity(String city) {
	City = city;
}

	private List<Integer> countryIds = new ArrayList<>();

	private List<Integer> dayOfBirthIds = new ArrayList<>();
	
	private List<Integer> mounthOfBirthIds = new ArrayList<>();
	
	private List<Integer> yearOfBirthIds = new ArrayList<>();
	
	private List<Integer> sexIds = new ArrayList<>();




	public List<Integer> getCountryIds() {
		return countryIds;
	}

	public void setCountryIds(List<Integer> countryIds) {
		this.countryIds = countryIds;
	}

	public List<Integer> getDayOfBirthIds() {
		return dayOfBirthIds;
	}

	public void setDayOfBirthIds(List<Integer> dayOfBirthIds) {
		this.dayOfBirthIds = dayOfBirthIds;
	}

	public List<Integer> getMounthOfBirthIds() {
		return mounthOfBirthIds;
	}

	public void setMounthOfBirthIds(List<Integer> mounthOfBirthIds) {
		this.mounthOfBirthIds = mounthOfBirthIds;
	}

	public List<Integer> getYearOfBirthIds() {
		return yearOfBirthIds;
	}

	public void setYearOfBirthIds(List<Integer> yearOfBirthIds) {
		this.yearOfBirthIds = yearOfBirthIds;
	}



	public List<Integer> getSexIds() {
		return sexIds;
	}

	public void setSexIds(List<Integer> sexIds) {
		this.sexIds = sexIds;
	}


}