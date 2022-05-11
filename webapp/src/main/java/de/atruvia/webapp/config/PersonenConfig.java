package de.atruvia.webapp.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.atruvia.webapp.repositories.PersonenRepository;
import de.atruvia.webapp.services.PersonenService;
import de.atruvia.webapp.services.impl.PersonenServiceImpl;
import de.atruvia.webapp.services.mapper.PersonMapper;

@Configuration
public class PersonenConfig {

	@Bean
	public PersonenService create(final PersonenRepository repo, final PersonMapper mapper, @Qualifier("antipathen") final List<String> antipathen) {
		return new PersonenServiceImpl(repo, mapper, antipathen);
	}
	
}
