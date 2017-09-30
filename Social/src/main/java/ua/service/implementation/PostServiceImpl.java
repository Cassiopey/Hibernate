package ua.service.implementation;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Post;
import ua.entity.UserPage;
import ua.form.filter.PostFilterForm;
import ua.repository.PostRepository;
import ua.repository.UserPageRepository;
import ua.service.PostService;
import ua.service.implementation.specification.PostFilterAdapter;
@Service
@Transactional
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserPageRepository userPageRepository;



	@Override
	public Post findByName(String name) {
		return postRepository.findByName(name);
	}

	@Override
	public void delete(String name) {
		postRepository.delete(name);
	}

	@Override
	public List<Post> findAll() {
		return postRepository.findAll();
	}

	@Override
	public void delete(int id) {
		postRepository.delete(id);
	}
	@Override
	public void savePost(Post post){
		postRepository.save(post);
	}

	@Override
	public void save(Post post, Principal principal) {
		UserPage userPage = userPageRepository.findOneDayInited(Integer.valueOf(principal.getName()));
		post.setWriter(userPage);
		postRepository.save(post);
	}

	@Override
	public Page<Post> findAll(Pageable pageable) {
		return postRepository.findAll(pageable);
	}

	@Override
	public Post findOne(int id) {
		return postRepository.findOne(id);
	}

	@Override
	public void save(String name, String text, int ownerId, int writerId) {
		Post post = new Post();
		UserPage userowner = userPageRepository.findOne(ownerId);
		UserPage userwriter = userPageRepository.findOne(writerId);
		post.setName(name);
		post.setText(text);
		post.setOwner(userowner);
		post.setWriter(userwriter);
	}
	
	@Override
	public Page<Post> findAll(Pageable pageable, PostFilterForm filter) {
		return postRepository.findAll(new PostFilterAdapter(filter), pageable);
	}

	@Override
	public List<Post> findByOwners(Principal principal) {
		try{
		int id = Integer.valueOf(principal.getName());
		return postRepository.findByOwners(id);}
		catch(NullPointerException e){}
		return null;
	
	}

	@Override
	public List<Post> findByWriter(Principal principal) {
		int id = Integer.valueOf(principal.getName());
		return postRepository.findByWriters(id);
	}
}
