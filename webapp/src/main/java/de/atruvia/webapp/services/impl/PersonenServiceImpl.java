package de.atruvia.webapp.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.atruvia.webapp.repositories.PersonenRepository;
import de.atruvia.webapp.services.PersonenService;
import de.atruvia.webapp.services.PersonenServiceException;
import de.atruvia.webapp.services.mapper.PersonMapper;
import de.atruvia.webapp.services.models.Person;


//@Service
@Transactional(propagation = Propagation.REQUIRED, 
rollbackFor = PersonenServiceException.class, isolation = Isolation.READ_COMMITTED)
public class PersonenServiceImpl implements PersonenService {
	
	private final PersonenRepository repo;
	private final PersonMapper mapper;
	
	
	private final List<String> antipathen;
	
	
	
	public PersonenServiceImpl(final PersonenRepository repo, final PersonMapper mapper, final List<String> antipathen) {
		this.repo = repo;
		this.mapper = mapper;
		this.antipathen = antipathen;
	}

	@Override
	public boolean speichern(final Person person) throws PersonenServiceException{
		
		try {
			checkPerson(person);
			
			final boolean result = repo.existsById(person.getId());
			
			repo.save(mapper.convert(person));
			
			return result;
		} catch (final RuntimeException e) {
			throw new PersonenServiceException("Fehler im Service", e);
		}
	}

	private void checkPerson(final Person person) throws PersonenServiceException {
		validatePerson(person);
		
		businessCheck(person);
	}

	private void businessCheck(final Person person) throws PersonenServiceException {
		if(antipathen.contains(person.getVorname()))
			throw new PersonenServiceException("Unsympath");
	}

	private void validatePerson(final Person person) throws PersonenServiceException {
		if(person == null)
			throw new PersonenServiceException("Upps");
	}
	
	@Override
	public Optional<Person> findeNachId(final String id)  throws PersonenServiceException {
		try {
			return repo.findById(id).map(mapper::convert);
		} catch (final RuntimeException e) {
			throw new PersonenServiceException("Fehler im Service", e);
		}
	}

	@Override
	public Iterable<Person> findeAlle()  throws PersonenServiceException {
		try {
			return mapper.convert(repo.findAll());
		} catch (final RuntimeException e) {
			throw new PersonenServiceException("Fehler im Service", e);
		}
	}
	
	@Override
	public boolean delete(final Person person)  throws PersonenServiceException {
		return delete(person.getId());
	}

	@Override
	public boolean delete(final String id)  throws PersonenServiceException {
		try {
			if(repo.existsById(id)) {
				repo.deleteById(id);
				return true;
			}
			
			
			
			return false;
			
		} catch (final RuntimeException e) {
			throw new PersonenServiceException("Fehler im Service", e);
		}
	}


}
