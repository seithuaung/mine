package com.mine.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
	
	
	@RequestMapping(value = "person", method = RequestMethod.GET)
	public ResponseEntity<Person> getHello(){
		Person person = new Person();
		person.setName("Mg Mg");
		person.setAge(12);
		return ResponseEntity.ok(person);
	}

}
