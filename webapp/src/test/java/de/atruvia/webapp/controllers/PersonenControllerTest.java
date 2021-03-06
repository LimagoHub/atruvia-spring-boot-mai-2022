package de.atruvia.webapp.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.atruvia.webapp.dtos.PersonDto;
import de.atruvia.webapp.services.PersonenService;
import de.atruvia.webapp.services.models.Person;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql({"create.sql", "insert.sql"})
@ExtendWith(SpringExtension.class)
public class PersonenControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	 @MockBean
	 private PersonenService serviceMock;
	
	@Test
	void test1() throws Exception{
		
		// Arrange
		final Optional<Person> optionalPerson = Optional.of(Person.builder().id("1").vorname("John").nachname("Doe").build());
		when(serviceMock.findeNachId(anyString())).thenReturn(optionalPerson);
		
		// Action
		final PersonDto person = restTemplate.getForObject("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDto.class);
		
		// Assertion
		assertEquals("John",person.getVorname());
	}
	@Test
	void test2() throws Exception{
		final String person = restTemplate.getForObject("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", String.class);
		System.out.println(person);
	}
	@Test
	void test3() throws Exception{
		final Optional<Person> optionalPerson = Optional.of(Person.builder().id("1").vorname("John").nachname("Doe").build());
		when(serviceMock.findeNachId(anyString())).thenReturn(optionalPerson);
		
		
		
		final ResponseEntity<PersonDto> personEntity = restTemplate.getForEntity("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDto.class);
		final PersonDto person =  personEntity.getBody();
		
		assertEquals("John",person.getVorname());
		assertEquals(HttpStatus.OK, personEntity.getStatusCode());
	}
	@Test
	void test4() throws Exception{
		
		final Optional<Person> optionalPerson = Optional.empty();
		when(serviceMock.findeNachId(anyString())).thenReturn(optionalPerson);
		
		
		final ResponseEntity<PersonDto> personEntity = restTemplate.getForEntity("/v1/personen/noSuchPerson", PersonDto.class);
		
		
		
		assertEquals(HttpStatus.NOT_FOUND, personEntity.getStatusCode());
	}
	
	@Test
	void test5() throws Exception{
		
		final HttpEntity entity = new HttpEntity( PersonDto.builder()
				.id("012345678901234567890123456789012345")
				.vorname("John")
				.nachname("Doe")
				.build());
		
		when(serviceMock.speichern(any())).thenReturn(false);
		
		
		final ResponseEntity<Void> personEntity = restTemplate.exchange("/v1/personen",HttpMethod.PUT, entity, Void.class);
		
		
		assertEquals(HttpStatus.CREATED ,personEntity.getStatusCode());
	    verify(serviceMock, times(1)).speichern(Person.builder().id("012345678901234567890123456789012345").vorname("John").nachname("Doe").build());

	}
	
	@Test
	void test6() throws Exception{
		
		final HttpEntity entity = new HttpEntity( PersonDto.builder()
				.id("012345678901234567890123456789012345")
				.vorname("John")
				.nachname("Doe")
				.build());
		
		when(serviceMock.speichern(any())).thenReturn(true);
		
		
		final ResponseEntity<Void> personEntity = restTemplate.exchange("/v1/personen",HttpMethod.PUT, entity, Void.class);
		
		
		assertEquals(HttpStatus.OK ,personEntity.getStatusCode());
	    verify(serviceMock, times(1)).speichern(Person.builder().id("012345678901234567890123456789012345").vorname("John").nachname("Doe").build());

	}
}
