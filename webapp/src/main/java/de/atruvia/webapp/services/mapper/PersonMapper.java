package de.atruvia.webapp.services.mapper;

import org.mapstruct.Mapper;

import de.atruvia.webapp.repositories.entities.PersonEntity;
import de.atruvia.webapp.services.models.Person;


@Mapper(componentModel = "spring")
public interface PersonMapper {
	
	Person convert(PersonEntity personEntity);
	PersonEntity convert(Person person);
	
	Iterable<Person> convert(Iterable<PersonEntity> entities);

}
