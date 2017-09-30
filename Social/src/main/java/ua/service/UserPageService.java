package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.UserPage;
import ua.form.UserPageForm;
import ua.form.filter.UserPageFilterForm;

public interface UserPageService {

	List<UserPage> findAll();

	void save(UserPageForm form);

	void save(String name, String surname, String phone, String password, String email, int countryId, int sexId, String city, int dayOfBirthId, int mounthOfBirthId, int yearOfBirthId,String login);

	UserPageForm findForForm(int id);

	void delete(int id);

	UserPage findOne(int id);

	Page<UserPage> findAll(Pageable pageable, UserPageFilterForm filter);

	UserPage findByLogin(String login);

	UserPage findById(int id);
}
