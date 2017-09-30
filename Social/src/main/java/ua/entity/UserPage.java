package ua.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class UserPage implements UserDetails{
	
	private static final long serialVersionUID = 1512198877402218090L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String login;
	
	private String email;
	
	private String password;
	
	private String name;
	
	private String surname;
	
	private String phone;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Country country;
	
	private String City;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Sex sex;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private DayOfBirth dayOfBirth;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private MounthOfBirth mounthOfBirth;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private YearOfBirth yearOfBirth;
	
	@OneToMany(mappedBy="senderId")
	private List<Message> sended = new ArrayList<>();
	
	@OneToMany(mappedBy="reciverId")
	private List<Message> reciver = new ArrayList<>();
	

	
	@Enumerated
	private Role role;
	
	@OneToMany(mappedBy="owner")
	private List<Post> posts = new ArrayList<>();
	
	@OneToMany(mappedBy="creator")
	private List<Groupe> groupes = new ArrayList<>();
	
	@OneToMany(mappedBy="writer")
	private List<Post> writedPosts = new ArrayList<>();
	
	@OneToMany(mappedBy="userPhoto")
	private List<Photo> userPhotos = new ArrayList<>();
	
	public void setPhone(String phone) {
		this.phone = phone;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<Message> getSended() {
		return sended;
	}

	public void setSended(List<Message> sended) {
		this.sended = sended;
	}

	public List<Message> getReciver() {
		return reciver;
	}

	public void setReciver(List<Message> reciver) {
		this.reciver = reciver;
	}

	public String getPhone() {
		return phone;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role.name()));
		return authorities;
	}

	@Override
	public String getUsername() {
		return String.valueOf(id);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
