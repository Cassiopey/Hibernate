package ua.service.implementation;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Country;
import ua.entity.DayOfBirth;
import ua.entity.MounthOfBirth;
import ua.entity.Role;
import ua.entity.Sex;
import ua.entity.UserPage;
import ua.entity.YearOfBirth;
import ua.form.UserPageForm;
import ua.form.filter.UserPageFilterForm;
import ua.repository.CountryRepository;
import ua.repository.DayOfBirthRepository;
import ua.repository.MounthOfBirthRepository;
import ua.repository.SexRepository;
import ua.repository.UserPageRepository;
import ua.repository.YearOfBirthRepository;
import ua.service.UserPageService;
import ua.service.implementation.specification.UserPageFilterAdapter;

@Service("userDetailsService")
@Transactional
public class UserPageServiceImpl implements UserPageService,UserDetailsService{
	@Resource
	private UserPageRepository userPageRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private SexRepository sexRepository;
	
	@Autowired
	private DayOfBirthRepository dayOfBirthRepository;
	
	@Autowired
	private MounthOfBirthRepository mounthOfBirthRepository;
	
	@Autowired
	private YearOfBirthRepository yearOfBirthRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public List<UserPage> findAll() {
		return userPageRepository.findAll();
	}

	@Override
	public void delete(int id) {
		userPageRepository.delete(id);
	}

	@Override
	public void save(String name, String surname, String phone,
			String password, String email, int countryId, int sexId, String city, int dayOfBirthId, int mounthOfBirthId, int yearOfBirthId, String login) {
		UserPage userPage = new UserPage();
		Country country = countryRepository.findOne(countryId);
		Sex sex = sexRepository.findOne(sexId);
		DayOfBirth dayOfBirth = dayOfBirthRepository.findOne(dayOfBirthId);
		MounthOfBirth mounthOfBirth = mounthOfBirthRepository.findOne(mounthOfBirthId);
		YearOfBirth yearOfBirth = yearOfBirthRepository.findOne(yearOfBirthId);
		userPage.setLogin(login);
		userPage.setEmail(email);
		userPage.setName(name);
		userPage.setSurname(surname);
		userPage.setPhone(phone);
		userPage.setPassword(password);
		userPage.setEmail(email);
		userPage.setCity(city);
		userPage.setCountry(country);
		userPage.setSex(sex);
		userPage.setDayOfBirth(dayOfBirth);
		userPage.setMounthOfBirth(mounthOfBirth);
		userPage.setYearOfBirth(yearOfBirth);
		userPageRepository.save(userPage);
	}

	@Override
	public void save(UserPageForm form) {
		
		UserPage userPage = new UserPage();
		userPage.setLogin(form.getLogin());
		userPage.setRole(Role.ROLE_USER);
		userPage.setEmail(form.getEmail());
		userPage.setName(form.getName());
		userPage.setSurname(form.getSurname());
		userPage.setPhone(form.getPhone());
		userPage.setPassword(encoder.encode(form.getPassword()));
		userPage.setEmail(form.getEmail());
		userPage.setCity(form.getCity());
		userPage.setCountry(form.getCountry());
		userPage.setSex(form.getSex());
		userPage.setDayOfBirth(form.getDayOfBirth());
		userPage.setMounthOfBirth(form.getMounthOfBirth());
		userPage.setYearOfBirth(form.getYearOfBirth());
		userPageRepository.save(userPage);
	}

	@Override
	public UserPageForm findForForm(int id) {
		UserPage userPage = userPageRepository.findOneCountryInited(id);
		userPage = userPageRepository.findOneSexInited(id);
		userPage = userPageRepository.findOneDayInited(id);
		userPage = userPageRepository.findOneMounthInited(id);
		userPage = userPageRepository.findOneYearInited(id);
		UserPageForm form = new UserPageForm();
		form.setId(userPage.getId());
		form.setLogin(userPage.getLogin());
		form.setName(userPage.getName());
		form.setEmail(userPage.getEmail());
		form.setSurname(userPage.getSurname());
		form.setPhone(userPage.getPhone());
		form.setPassword(userPage.getPassword());
		form.setCity(userPage.getCity());
		form.setCountry(userPage.getCountry());
		form.setSex(userPage.getSex());
		form.setDayOfBirth(userPage.getDayOfBirth());
		form.setMounthOfBirth(userPage.getMounthOfBirth());
		form.setYearOfBirth(userPage.getYearOfBirth());
		return form;
	}

	@Override
	public UserPage findOne(int id) {
		return userPageRepository.findOne(id);
	}

	@Override
	public Page<UserPage> findAll(Pageable pageable, UserPageFilterForm filter) {
		return userPageRepository.findAll(new UserPageFilterAdapter(filter), pageable);
	}
	
	@Override
	public UserPage findByLogin(String login) {
		return userPageRepository.findByLogin(login);
	}

	@Override
	public UserPage findById(int id) {
		return userPageRepository.findOne(id);
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		return userPageRepository.findByLogin(login);
	}
	
	@PostConstruct
	public void saveAdmin(){
		UserPage user = userPageRepository.findOne(1);
		if(user==null){
			user = new UserPage();
			user.setRole(Role.ROLE_ADMIN);
			user.setPassword(encoder.encode("admin"));
			user.setLogin("admin");
			user.setId(1);
			userPageRepository.save(user);
		}
	}
}
