package de.atruvia.webapp.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.atruvia.webapp.repositories.PersonenRepository;
import de.atruvia.webapp.services.PersonenServiceException;
import de.atruvia.webapp.services.mapper.PersonMapper;

@ExtendWith(MockitoExtension.class)
public class PersonenServiceImplTest {

	@InjectMocks
	private PersonenServiceImpl objectUnderTest;
	
	@Mock
	private PersonenRepository repoMock;
	
	@Mock
	private PersonMapper mapperMock;
	
	
	@Test
	void speichern_nullParameter_throwsPersonenServiceException() {
		final PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(null));
		assertEquals("Upps", ex.getMessage());
	}
}
