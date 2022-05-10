package de.atruvia.webapp.demo;

import javax.inject.Named;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import de.atruvia.webapp.repositories.PersonenRepository;
import lombok.AllArgsConstructor;

@Named
@Scope(BeanDefinition.SCOPE_SINGLETON)
@AllArgsConstructor
public class Demo {
	
	
	private final PersonenRepository repo;
	
	
//	@PostConstruct
//	public void run() {
//		final PersonEntity person = PersonEntity
//				.builder()
//				.id(UUID.randomUUID().toString())
//				.vorname("John")
//				.nachname("Doe").build();
//		
//		repo.speichernOnly(person);
//	}
	
//	private final Translator translator;
//
//	@Autowired
//	public Demo(/* */final Translator translator) {
//		
//		this.translator = translator;
//		System.out.println(translator.translate("Ctor"));
//	}
//	
//	@PostConstruct
//	public void init() {
//		System.out.println(translator.translate("Init"));
//	}
//	
//	@PreDestroy
//	public void dispose() { 
//		System.out.println("Und Tschüss ");
//	}
	
}
