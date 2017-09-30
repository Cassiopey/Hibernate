package ua.service;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Post;
import ua.form.filter.PostFilterForm;

public interface PostService {
	void save(Post post, Principal principal);
	
	void savePost(Post post);

	void save(String name, String text, int ownerId, int writerId);
	
	Post findByName(String name);
	
	void delete(String name);
	
	List<Post> findAll();

	void delete(int id);
	
	Page<Post> findAll(Pageable pageable);

	Post findOne(int id);
	
	Page<Post> findAll(Pageable pageable, PostFilterForm filter);
	
	List<Post> findByOwners(Principal principal);
	
	List<Post> findByWriter(Principal principal);

}
