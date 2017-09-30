package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Sex;
import ua.form.filter.SexFilterForm;
import ua.repository.SexRepository;
import ua.service.SexService;
import ua.service.implementation.specification.SexFilterAdapter;
@Service
@Transactional
public class SexServiceImpl implements SexService{
	
	@Autowired
	private SexRepository sexRepository;
	
	@Override
	public void save(String name) {
		Sex sex = new Sex();
		sex.setName(name);
		sexRepository.save(sex);
	}

	@Override
	public Sex findByName(String name) {
		return sexRepository.findByName(name);
	}

	@Override
	public void delete(String name) {
		sexRepository.delete(name);
	}

	@Override
	public List<Sex> findAll() {
		return sexRepository.findAll();
	}

	@Override
	public void delete(int id) {
		sexRepository.delete(id);
	}

	@Override
	public Page<Sex> findAll(Pageable pageable) {
		return sexRepository.findAll(pageable);
	}

	@Override
	public Sex findOne(int id) {
		return sexRepository.findOne(id);
	}

	@Override
	public void save(Sex sex) {
	sexRepository.save(sex);
		
	}

	@Override
	public Page<Sex> findAll(Pageable pageable, SexFilterForm form) {
		return sexRepository.findAll(new SexFilterAdapter(form), pageable);
	}

}
