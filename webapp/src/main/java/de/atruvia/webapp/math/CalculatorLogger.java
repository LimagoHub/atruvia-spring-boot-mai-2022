package de.atruvia.webapp.math;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("logger")
public class CalculatorLogger implements Calculator{
	
	private final Calculator calculator;

	public CalculatorLogger(@Qualifier("impl") Calculator calculator) {
		super();
		this.calculator = calculator;
	}

	public double add(double a, double b) {
		System.out.println("Add wurde gerufen");
		return calculator.add(a, b);
	}

	public double sub(double a, double b) {
		return calculator.sub(a, b);
	}
	
	

}
