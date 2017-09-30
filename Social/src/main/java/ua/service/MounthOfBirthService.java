package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.MounthOfBirth;
import ua.form.filter.MounthOfBirthFilterForm;

public interface MounthOfBirthService {

	void save (MounthOfBirth mounthOfBirth);
	
	void save (String name);
	
	void deleteByName(String name);
	
	void delete(int id);
	
	MounthOfBirth findByName(String name);
	
	MounthOfBirth findOne(int id);
	
	List<MounthOfBirth> findAll();
	
	Page<MounthOfBirth> findAll(Pageable pageable);

	Page<MounthOfBirth> findAll(Pageable pageable, MounthOfBirthFilterForm form);
}
