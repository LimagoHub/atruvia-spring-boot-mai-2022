package de.atruvia.webapp.demo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

@Named
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class Demo {
	
	
	private final Translator translator;

	@Autowired
	public Demo(/* */final Translator translator) {
		
		this.translator = translator;
		System.out.println(translator.translate("Ctor"));
	}
	
	@PostConstruct
	public void init() {
		System.out.println(translator.translate("Init"));
	}
	
	@PreDestroy
	public void dispose() { 
		System.out.println("Und Tschüss ");
	}
	
}
