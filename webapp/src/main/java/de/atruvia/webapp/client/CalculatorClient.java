package de.atruvia.webapp.client;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import de.atruvia.webapp.math.Calculator;

@Component
public class CalculatorClient {

	private final Calculator calculator;
	
	
	
	
	public CalculatorClient(@Qualifier("secure") final Calculator calculator) {
		this.calculator = calculator;
	}



	@PostConstruct
	public void run() {
		
		
		System.out.println(calculator.add(3, 4));

	}

}
