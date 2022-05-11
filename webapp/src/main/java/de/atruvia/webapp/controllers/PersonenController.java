package de.atruvia.webapp.controllers;


import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
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

import de.atruvia.webapp.controllers.mapper.PersonDtoMapper;
import de.atruvia.webapp.dtos.PersonDto;
import de.atruvia.webapp.services.PersonenService;
import de.atruvia.webapp.services.PersonenServiceException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/v1/personen")
@AllArgsConstructor
public class PersonenController {

	private final PersonenService personenService;
	private final PersonDtoMapper mapper;
	
	@ApiResponse(responseCode = "200", description = "Person wurde gefunden")
	@ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
	@ApiResponse(responseCode = "400", description = "Falsches Format")
	@ApiResponse(responseCode = "500", description = "interner Serverfehler")
	@GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonDto> findPersonById(@PathVariable final String id) throws PersonenServiceException{ // Nur Id als Parameter
		
		return ResponseEntity.of(personenService.findeNachId(id).map(mapper::convert));
	}
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE) // Bei HTML -Maske oder andere Spalte als ID
	public ResponseEntity<Iterable< PersonDto>> findAll(
			@RequestParam(required = false, defaultValue = "") final String vorname,
			@RequestParam(required = false, defaultValue = "") final String name
			)  throws PersonenServiceException{
		
		
		 
		return ResponseEntity.ok(mapper.convert(personenService.findeAlle()));
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
	public ResponseEntity<Void> createOrUpdate(@Valid @RequestBody final PersonDto person)  throws PersonenServiceException{
		
		
		if(personenService.speichern(mapper.convert(person)))
			return ResponseEntity.ok().build(); 
		return ResponseEntity.status(HttpStatus.CREATED).build(); 
	}
	@DeleteMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@RequestBody final PersonDto person)  throws PersonenServiceException{
		return delete(person.getId());
	}
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> delete(@PathVariable final String id)  throws PersonenServiceException{
		if(personenService.delete(id))
			return ResponseEntity.ok().build(); 
		return ResponseEntity.notFound().build(); 
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
