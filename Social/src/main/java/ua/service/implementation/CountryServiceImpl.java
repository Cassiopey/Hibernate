package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Country;
import ua.form.filter.CountryFilterForm;
import ua.repository.CountryRepository;
import ua.service.CountryService;
import ua.service.implementation.specification.CountryFilterAdapter;
@Service
@Transactional
public class CountryServiceImpl implements CountryService{
	@Autowired
	private CountryRepository countryRepository;
	
	@Override
	public void save(String name) {
		Country country = new Country();
		country.setName(name);
		countryRepository.save(country);
	}

	@Override
	public Country findByName(String name) {
		return countryRepository.findByName(name);
	}

	@Override
	public void delete(String name) {
		countryRepository.delete(name);
	}

	@Override
	public List<Country> findAll() {
		return countryRepository.findAll();
	}

	@Override
	public void delete(int id) {
		countryRepository.delete(id);
	}

	@Override
	public void save(Country country) {
			countryRepository.save(country);
	}

	@Override
	public Page<Country> findAll(Pageable pageable) {
		return countryRepository.findAll(pageable);
	}

	@Override
	public Country findOne(int id) {
		return countryRepository.findOne(id);
	}

	@Override
	public Page<Country> findAll(Pageable pageable, CountryFilterForm form) {
		return countryRepository.findAll(new CountryFilterAdapter(form), pageable);
	}

}
