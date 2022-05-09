package de.applikation;

import de.client.CalculatorClient;
import de.math.Calculator;
import de.math.CalculatorImpl;
import de.math.CalculatorLogger;
import de.math.CalculatorSecure;

public class Main {

	public static void main(String[] args) {
		Calculator calculator = new CalculatorImpl();
		calculator = new CalculatorLogger(calculator);
		calculator = new CalculatorSecure(calculator);
		CalculatorClient client = new CalculatorClient(calculator);
		client.run();

	}

}
