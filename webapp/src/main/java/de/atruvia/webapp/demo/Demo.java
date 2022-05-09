package de.atruvia.webapp.demo;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Named
public class Demo {
	
	
	private final Translator translator;

	public Demo(/* */final Translator translator) {
		
		this.translator = translator;
		System.out.println(translator.translate("Ctor"));
	}
	
	@PostConstruct
	public void init() {
		System.out.println(translator.translate("Init"));
	}
	
}
