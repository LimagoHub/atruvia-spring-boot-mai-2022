package de.atruvia.webapp;

import org.springframework.boot.CommandLineRunner;

//@Component

public class MyRunner implements CommandLineRunner {

	@Override
	public void run(final String... args) throws Exception {
		System.out.println("Hallo");

	}

}
