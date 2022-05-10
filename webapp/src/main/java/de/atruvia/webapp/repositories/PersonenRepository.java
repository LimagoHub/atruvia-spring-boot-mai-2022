package de.atruvia.webapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.atruvia.webapp.repositories.entities.PersonEntity;
import de.atruvia.webapp.repositories.entities.TinyPerson;

public interface PersonenRepository extends CrudRepository<PersonEntity, String>, PersonenCustomRepository{

	Iterable<PersonEntity> findByVorname(String vorname);
	
	@Query("select p.id, p.nachname from PersonEntity p where p.nachname like :nachname")
	Iterable<Object[]> peter(String nachname);
	
	@Query("select new de.atruvia.webapp.repositories.entities.TinyPerson(p.id, p.nachname) from PersonEntity p")
	Iterable<TinyPerson> franz();
	
	List<PersonEntity> findAllAsList();
}
