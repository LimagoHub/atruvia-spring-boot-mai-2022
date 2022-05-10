package de.atruvia.webapp.controllers.mapper;

import org.mapstruct.Mapper;

import de.atruvia.webapp.dtos.PersonDto;
import de.atruvia.webapp.services.models.Person;

@Mapper(componentModel = "spring")
public interface PersonDtoMapper {

	
	PersonDto convert(Person person);
	Person convert(PersonDto personDto);
	Iterable<PersonDto> convert(Iterable<Person> personen);
}
