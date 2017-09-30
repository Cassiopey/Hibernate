package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.DayOfBirth;
import ua.form.filter.DayOfBirthFilterForm;
import ua.repository.DayOfBirthRepository;
import ua.service.DayOfBirthService;
import ua.service.implementation.specification.DayOfBirthFilterAdapter;
@Service
@Transactional
public class DayOfBirthServiceImpl implements DayOfBirthService {

	@Autowired
	private DayOfBirthRepository dayOfBirthRepository;

	@Override
	public void save(String name) {
		DayOfBirth dayOfBirth = new DayOfBirth();
		dayOfBirth.setName(name);
		dayOfBirthRepository.save(dayOfBirth);
	}

	@Override
	public void save(DayOfBirth dayOfBirth) {
		dayOfBirthRepository.save(dayOfBirth);
	}

	@Override
	public DayOfBirth findByName(String name) {
		return dayOfBirthRepository.findByName(name);
	}

	@Override
	public void deleteByName(String name) {
		dayOfBirthRepository.delete(name);
	}

	@Override
	public List<DayOfBirth> findAll() {
		return dayOfBirthRepository.findAll();
	}

	@Override
	public Page<DayOfBirth> findAll(Pageable pageable) {
		return dayOfBirthRepository.findAll(pageable);
	}

	@Override
	public DayOfBirth findOne(int id) {
		return dayOfBirthRepository.findOne(id);
	}

	@Override
	public void delete(int id) {
		dayOfBirthRepository.delete(id);
	}

	@Override
	public Page<DayOfBirth> findAll(Pageable pageable, DayOfBirthFilterForm form) {
		return dayOfBirthRepository.findAll(new DayOfBirthFilterAdapter(form), pageable);
	}
}
