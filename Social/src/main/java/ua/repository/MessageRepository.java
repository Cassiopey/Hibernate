package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>,JpaSpecificationExecutor<Message>{
	Message findByText(String text);

	default void delete(String text) {
		delete(findByText(text));
	}

	@Modifying
	@Query("DELETE FROM Message c WHERE c.text=:text")
	void deleteByText(@Param("text") String text);
	
	
	@Query("SELECT message FROM Message message WHERE message.reciverId.id =:id")
	List<Message> findByRecivers(@Param("id")int id);
}
