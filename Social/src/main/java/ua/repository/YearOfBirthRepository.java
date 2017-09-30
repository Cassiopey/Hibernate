package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.YearOfBirth;

public interface YearOfBirthRepository extends JpaRepository<YearOfBirth, Integer>,JpaSpecificationExecutor<YearOfBirth> {
	YearOfBirth findByName(String name);

	default void delete(String name) {
		delete(findByName(name));
	}

	@Modifying
	@Query("DELETE FROM YearOfBirth y WHERE y.name=:name")
	void deleteByName(@Param("name") String name);
}
