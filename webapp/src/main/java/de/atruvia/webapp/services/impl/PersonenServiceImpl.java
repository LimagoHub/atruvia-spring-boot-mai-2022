package de.atruvia.webapp.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.atruvia.webapp.repositories.PersonenRepository;
import de.atruvia.webapp.services.mapper.PersonMapper;
import de.atruvia.webapp.services.models.Person;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional(propagation = Propagation.REQUIRED, 
rollbackFor = PersonenServiceException.class, isolation = Isolation.READ_COMMITTED)
public class PersonenServiceImpl {
	
	private final PersonenRepository repo;
	private final PersonMapper mapper;
	
	public boolean speichern(final Person person) throws PersonenServiceException{
		
		try {
			if(person == null)
				throw new PersonenServiceException("Upps");
			
			final boolean result = repo.existsById(person.getId());
			
			repo.save(mapper.convert(person));
			
			return result;
		} catch (final RuntimeException e) {
			throw new PersonenServiceException("Fehler im Service", e);
		}
	}

}
