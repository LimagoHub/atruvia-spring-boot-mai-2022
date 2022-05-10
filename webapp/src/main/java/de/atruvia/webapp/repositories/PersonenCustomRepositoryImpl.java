package de.atruvia.webapp.repositories;

import javax.persistence.EntityManager;

import de.atruvia.webapp.repositories.entities.PersonEntity;

public class PersonenCustomRepositoryImpl implements PersonenCustomRepository{

	private EntityManager entityManager;
	
	

	@Override
	public void speichernOnly(final PersonEntity p) {
		entityManager.persist(p);
	}
}
