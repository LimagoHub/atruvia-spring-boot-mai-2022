package de.atruvia.webapp.services;

import java.util.Optional;

import de.atruvia.webapp.services.PersonenServiceException;
import de.atruvia.webapp.services.models.Person;

public interface PersonenService {

	boolean speichern(Person person) throws PersonenServiceException;

	Optional<Person> findeNachId(String id) throws PersonenServiceException;

	Iterable<Person> findeAlle() throws PersonenServiceException;

	boolean delete(Person person) throws PersonenServiceException;

	boolean delete(String id) throws PersonenServiceException;

}