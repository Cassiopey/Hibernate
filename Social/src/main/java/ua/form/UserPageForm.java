package ua.form;

import org.springframework.web.multipart.MultipartFile;

import ua.entity.Country;
import ua.entity.DayOfBirth;
import ua.entity.MounthOfBirth;
import ua.entity.Sex;
import ua.entity.YearOfBirth;

public class UserPageForm {
	private int id;

	private String login;

	private String email;

	private String password;

	private String name;

	private String surname;

	private String phone;

	private Country country;

	private String City;

	private Sex sex;

	private String path;

	private int version;

	private MultipartFile file;

	public String getPath() {
		return path;
	}

	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	private DayOfBirth dayOfBirth;

	private MounthOfBirth mounthOfBirth;

	private YearOfBirth yearOfBirth;

	public DayOfBirth getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(DayOfBirth dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public MounthOfBirth getMounthOfBirth() {
		return mounthOfBirth;
	}

	public void setMounthOfBirth(MounthOfBirth mounthOfBirth) {
		this.mounthOfBirth = mounthOfBirth;
	}

	public YearOfBirth getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(YearOfBirth yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

}
