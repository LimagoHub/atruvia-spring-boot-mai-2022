package de.atruvia.webapp.controllers;


import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import de.atruvia.webapp.dtos.PersonDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/v1/personen")

public class PersonenController {

	
	
	@ApiResponse(responseCode = "200", description = "Person wurde gefunden")
	@ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
	@ApiResponse(responseCode = "400", description = "Falsches Format")
	@ApiResponse(responseCode = "500", description = "interner Serverfehler")
	@GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonDto> findPersonById(@PathVariable final String id) { // Nur Id als Parameter
		final PersonDto result = PersonDto
				.builder()
				.id(id)
				.vorname("John")
				.nachname("Doe")
				.build()	; 
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE) // Bei HTML -Maske oder andere Spalte als ID
	public ResponseEntity<Iterable< PersonDto>> findAll(
			@RequestParam(required = false, defaultValue = "") final String vorname,
			@RequestParam(required = false, defaultValue = "") final String name
			) {
		
		final Iterable<PersonDto> result = List.of(
				PersonDto
				.builder()
				.id("1")
				.vorname("John")
				.nachname("Doe")
				.build(),
				PersonDto
				.builder()
				.id("2")
				.vorname("John")
				.nachname("Rambo")
				.build(),
				PersonDto
				.builder()
				.id("3")
				.vorname("John")
				.nachname("Wayne")
				.build()	,
				PersonDto
				.builder()
				.id("1")
				.vorname("John")
				.nachname("McClain")
				.build()	
				);
		
		 
		return ResponseEntity.ok(result);
	}
	
	// Ersatz-Get wegen parameterobjekt (safe und idempotent)
	@PostMapping(path="/methods/to-upper", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonDto toUpper(@RequestBody final PersonDto person) { // Parameterobjekte
		person.setVorname(person.getVorname().toUpperCase());
		person.setNachname(person.getNachname().toUpperCase());
		return person;
	}
	
	// Put wenn die Abfrage idempotent ist
	@PutMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createOrUpdate(@Valid @RequestBody final PersonDto person) {
		
		
		// 201 create wenn neu (false)
		// 200 update (true)
		System.out.println(person + " wurde gespeichert");
		return ResponseEntity.ok().build(); // 201 create oder 200 ok
	}
	@DeleteMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@RequestBody final PersonDto person) {
		
		// 200 ok
		// 404 not found
		return delete(person.getId());
	}
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> delete(@PathVariable final String id) {
		// 200 ok
		// 404 not found
		System.out.println("Person mit der ID " + id + " wird geloescht");
		return ResponseEntity.ok().build(); // 201 create oder 200 ok
	}
	
	// Post wenn die Abfrage nicht idempotent ist
	@PostMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> saveOrUpdateNichtIdempotent(@RequestBody final PersonDto dto, final UriComponentsBuilder builder) {
		dto.setId(UUID.randomUUID().toString());
		
		final var uri = builder.path("/v1/personen/{id}").buildAndExpand(dto.getId());
		
		System.out.println("Person wurde gespeichert oder eingefuegt: " + dto);
		
		return ResponseEntity.created(uri.toUri()).build();
		//return ResponseEntity.ok().header(HttpHeaders.LOCATION, uri.toString()).build();
	}
	
	
}
