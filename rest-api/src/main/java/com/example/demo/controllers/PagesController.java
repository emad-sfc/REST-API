package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.data.page;
import com.example.demo.models.data.pageRepository;

@RestController
@RequestMapping(path = "/pages", produces = "application/json")
@CrossOrigin(origins = "*")
public class PagesController {

	@Autowired
	private pageRepository pageRepo;

	@GetMapping("/all")
	public Iterable<page> pages() {

		List<page> pages = pageRepo.findAllByOrderBySortingAsc();
		return pages;

	}

	@GetMapping("/{slug}")
	public ResponseEntity<page> page(@PathVariable String slug) {

//		page page = pageRepo.findBySlug(slug);		
//		if(page==null) return null;
//		 return page;

		Optional<page> optPage = pageRepo.findBySlug(slug);

		if (optPage.isPresent()) {
			return new ResponseEntity<>(optPage.get(), HttpStatus.OK);
		}

		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

}
