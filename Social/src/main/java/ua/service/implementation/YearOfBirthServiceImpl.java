package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.YearOfBirth;
import ua.form.filter.YearOfBirthFilterForm;
import ua.repository.YearOfBirthRepository;
import ua.service.YearOfBirthService;
import ua.service.implementation.specification.YearOfBirthFilterAdapter;
@Service
@Transactional
public class YearOfBirthServiceImpl implements YearOfBirthService{

	@Autowired
	private YearOfBirthRepository yearOfBirthRepository;
	
	@Override
	public void save(String name) {
		YearOfBirth yearOfBirth = new YearOfBirth();
		yearOfBirth.setName(name);
		yearOfBirthRepository.save(yearOfBirth);
	}

	@Override
	public void save(YearOfBirth yearOfBirth) {
		yearOfBirthRepository.save(yearOfBirth);
		}

	@Override
	public void delete(int id) {
		yearOfBirthRepository.delete(id);
	}

	@Override
	public void deleteByName(String name) {
		yearOfBirthRepository.delete(name);
	}

	@Override
	public YearOfBirth findOne(int id) {
		return yearOfBirthRepository.findOne(id);
	}

	@Override
	public YearOfBirth findByName(String name) {
		return yearOfBirthRepository.findByName(name);
	}

	@Override
	public List<YearOfBirth> findAll() {
		return yearOfBirthRepository.findAll();
	}



	@Override
	public Page<YearOfBirth> findAll(Pageable pageable) {
		return yearOfBirthRepository.findAll(pageable);
	}

	@Override
	public Page<YearOfBirth> findAll(Pageable pageable, YearOfBirthFilterForm form) {
		return yearOfBirthRepository.findAll(new YearOfBirthFilterAdapter(form), pageable);
	}




}
