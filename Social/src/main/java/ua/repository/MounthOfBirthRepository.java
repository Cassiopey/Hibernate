package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.MounthOfBirth;

public interface MounthOfBirthRepository extends JpaRepository<MounthOfBirth, Integer>,JpaSpecificationExecutor<MounthOfBirth>{
	
	MounthOfBirth findByName(String name);
	
	default void delete(String name){
		delete(findByName(name));
	}
	
	@Modifying
	@Query("DELETE FROM MounthOfBirth c WHERE c.name=:name")
	void deleteByName(@Param("name") String name);
}
