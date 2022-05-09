package de.atruvia.webapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.atruvia.webapp.dtos.PersonDto;

@RestController
@RequestMapping("/demo")
public class DemoController {

	@GetMapping(path = "/gruss")
	public String getGruss() {
		return "Hallo Rest";
	}
	
	@GetMapping(path = "/person", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity< PersonDto> getPerson() {
//		PersonDto result = PersonDto
//				.builder()
//				.id(UUID.randomUUID().toString())
//				.vorname("John")
//				.nachname("Doe")
//				.build()	;
//		
//		return ResponseEntity.ok(result);
		
		return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
	}
	
}
