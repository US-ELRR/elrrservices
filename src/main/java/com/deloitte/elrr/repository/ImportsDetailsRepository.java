package com.deloitte.elrr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deloitte.elrr.entity.ImportsDetails;

@Repository
public interface ImportsDetailsRepository extends JpaRepository<ImportsDetails, Long>{
	
	@Query("SELECT i FROM ImportsDetails i WHERE importsId = :importId order by importBeginTime desc ")
    public List<ImportsDetails> findByImportId(@Param("importId") long importId);
}
