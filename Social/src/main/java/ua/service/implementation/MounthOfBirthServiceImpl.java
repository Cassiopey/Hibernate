package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.MounthOfBirth;
import ua.form.filter.MounthOfBirthFilterForm;
import ua.repository.MounthOfBirthRepository;
import ua.service.MounthOfBirthService;
import ua.service.implementation.specification.MounthOfBirthFilterAdapter;

@Service
@Transactional
public class MounthOfBirthServiceImpl implements MounthOfBirthService {

	@Autowired
	private MounthOfBirthRepository mounthOfBirthRepository;

	@Override
	public void save(MounthOfBirth mounthOfBirth) {
		mounthOfBirthRepository.save(mounthOfBirth);
	}

	@Override
	public void save(String name) {
		MounthOfBirth mounthOfBirth = new MounthOfBirth();
		mounthOfBirth.setName(name);
		mounthOfBirthRepository.save(mounthOfBirth);
	}

	@Override
	public void deleteByName(String name) {
		mounthOfBirthRepository.delete(name);
	}

	@Override
	public void delete(int id) {
		mounthOfBirthRepository.delete(id);

	}

	@Override
	public MounthOfBirth findByName(String name) {
		return mounthOfBirthRepository.findByName(name);
	}

	@Override
	public MounthOfBirth findOne(int id) {
		return mounthOfBirthRepository.findOne(id);
	}

	@Override
	public List<MounthOfBirth> findAll() {
		return mounthOfBirthRepository.findAll();
	}

	@Override
	public Page<MounthOfBirth> findAll(Pageable pageable) {
		return mounthOfBirthRepository.findAll(pageable);
	}

	@Override
	public Page<MounthOfBirth> findAll(Pageable pageable,
			MounthOfBirthFilterForm form) {
		return mounthOfBirthRepository.findAll(new MounthOfBirthFilterAdapter(form), pageable);
	}

}
