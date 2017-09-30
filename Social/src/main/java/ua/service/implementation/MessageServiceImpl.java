package ua.service.implementation;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Message;
import ua.entity.UserPage;
import ua.form.filter.MessageFilterForm;
import ua.repository.MessageRepository;
import ua.repository.UserPageRepository;
import ua.service.MessageService;
import ua.service.implementation.specification.MessageFilterAdapter;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private UserPageRepository userPageRepository;

	@Override
	public void save(String text, int reciverId, int senderId) {

		Message message = new Message();
		UserPage userPage = userPageRepository.findOne(reciverId);
		message.setText(text);
		message.setReciverId(userPage);
		messageRepository.save(message);
	}

	@Override
	public Message findByText(String text) {
		return messageRepository.findByText(text);
	}

	@Override
	public void delete(String text) {
		messageRepository.delete(text);
	}

	@Override
	public List<Message> findAll() {
		return messageRepository.findAll();
	}

	@Override
	public void delete(int id) {
		messageRepository.delete(id);
	}

	public void save(Message message, Principal principal) {
		UserPage userPage = userPageRepository.findOne(Integer.valueOf(principal.getName()));
		message.setSenderId(userPage);
		messageRepository.save(message);
	}

	@Override
	public Page<Message> findAll(Pageable pageable) {
		return messageRepository.findAll(pageable);
	}

	@Override
	public Message findOne(int id) {
		return messageRepository.findOne(id);
	}

	@Override
	public Page<Message> findAll(Pageable pageable, MessageFilterForm filter) {
		return messageRepository.findAll(new MessageFilterAdapter(filter), pageable);
	}

	@Override
	public List<Message> findByReciver(Principal principal) {
		int id = Integer.valueOf(principal.getName());
		return messageRepository.findByRecivers(id);
	}

}
