package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.data.page;
import com.example.demo.models.data.pageRepository;

@RestController
@RequestMapping(path = "/admin/pages", produces = "application/json")
@CrossOrigin("*")
public class AdminPagesController {
	
	@Autowired
	private pageRepository pageRepo;
	
	@GetMapping
	public Iterable<page> index(){
		List<page> pages = pageRepo.findAllByOrderBySortingAsc();
		return pages;
	}
	
	@PostMapping(path="/add",consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public page add(@RequestBody page page) {
		return pageRepo.save(page);
		
	}
	
	@GetMapping("/edit/{id}")
	public ResponseEntity<page> edit(@PathVariable int id){
		Optional<page> page = pageRepo.findById(id);
		
		return new ResponseEntity<>(page.get(),HttpStatus.OK);
		
	}

	@PutMapping("/edit")
	public page put(@RequestBody page page) {
		return pageRepo.save(page);
	}
	
	
	@DeleteMapping("/edit/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		try {
			pageRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
	
		}
	}
}
