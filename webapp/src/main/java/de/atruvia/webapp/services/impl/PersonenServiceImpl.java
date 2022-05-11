package de.atruvia.webapp.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.atruvia.webapp.repositories.PersonenRepository;
import de.atruvia.webapp.services.PersonenService;
import de.atruvia.webapp.services.PersonenServiceException;
import de.atruvia.webapp.services.mapper.PersonMapper;
import de.atruvia.webapp.services.models.Person;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional(propagation = Propagation.REQUIRED, 
rollbackFor = PersonenServiceException.class, isolation = Isolation.READ_COMMITTED)
public class PersonenServiceImpl implements PersonenService {
	
	private final PersonenRepository repo;
	private final PersonMapper mapper;
	
	@Override
	public boolean speichern(final Person person) throws PersonenServiceException{
		
		try {
			if(person == null)
				throw new PersonenServiceException("Upps");
			
			if(person.getVorname().equals("Attila"))
				throw new PersonenServiceException("Unsympath");
			
			final boolean result = repo.existsById(person.getId());
			
			repo.save(mapper.convert(person));
			
			return result;
		} catch (final RuntimeException e) {
			throw new PersonenServiceException("Fehler im Service", e);
		}
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
