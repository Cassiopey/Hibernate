package ua.service.implementation;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Groupe;
import ua.entity.UserPage;
import ua.form.filter.GroupeFilterForm;
import ua.repository.GroupeRepository;
import ua.repository.UserPageRepository;
import ua.service.GroupeService;
import ua.service.implementation.specification.GroupeFilterAdapter;
@Service
@Transactional
public class GroupeServiceImpl implements GroupeService{

	@Autowired
	private GroupeRepository groupeRepository;
	
	@Autowired 
	private UserPageRepository userPageRepository;
	
	@Override
	public void save(String name, String description, int creatorId) {
		Groupe groupe = new Groupe();
		UserPage userPage = userPageRepository.findOne(creatorId);
		groupe.setName(name);
		groupe.setDescription(description);
		groupe.setCreator(userPage);
		groupeRepository.save(groupe);
	}

	@Override
	public Groupe findByName(String name) {
		return groupeRepository.findByName(name);
	}

	@Override
	public void delete(String name) {
		groupeRepository.delete(name);
	}

	@Override
	public List<Groupe> findAll() {
		return groupeRepository.findAll();
	}

	@Override
	public void delete(int id) {
		groupeRepository.delete(id);
	}


	@Override
	public Page<Groupe> findAll(Pageable pageable) {
		return groupeRepository.findAll(pageable);
	}

	@Override
	public Groupe findOne(int id) {
		return groupeRepository.findOne(id);
	}

	@Override
	public Page<Groupe> findAll(Pageable pageable, GroupeFilterForm form) {
		return groupeRepository.findAll(new GroupeFilterAdapter(form), pageable);
	}

	@Override
	public void save(Groupe groupe) {
		groupeRepository.save(groupe);
		
	}


	@Override
	public void saveGroupe(Groupe groupe, Principal principal) {
UserPage userPage = userPageRepository.findOne(Integer.valueOf(principal.getName()));
groupe.setCreator(userPage);
groupeRepository.save(groupe);
	}

	@Override
	public List<Groupe> findByCreators(Principal principal) {
int id = Integer.valueOf(principal.getName());
		return groupeRepository.findByCreator(id);
	}



}
