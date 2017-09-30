package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.DayOfBirth;

public interface DayOfBirthRepository extends JpaRepository<DayOfBirth, Integer>,JpaSpecificationExecutor<DayOfBirth>{
DayOfBirth findByName(String name);
	
	default void delete(String name){
		delete(findByName(name));
	}
	
	@Modifying
	@Query("DELETE FROM DayOfBirth c WHERE c.name=:name")
	void deleteByName(@Param("name") String name);
}
