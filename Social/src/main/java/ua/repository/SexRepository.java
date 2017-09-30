package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Sex;

public interface SexRepository extends JpaRepository<Sex,Integer>,JpaSpecificationExecutor<Sex>{
Sex findByName(String name);
	
	default void delete(String name){
		delete(findByName(name));
	}
	
	@Modifying
	@Query("DELETE FROM Sex s WHERE s.name=:name")
	void deleteByName(@Param("name") String name);
}
