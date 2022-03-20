package com.example.demo.models.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface pageRepository extends JpaRepository<page, Integer>{
	
	Optional<page> findBySlug(String slug);	

	page findBySlugAndIdNot(String slug, int id);
	
	List<page> findAllByOrderBySortingAsc();
	
}