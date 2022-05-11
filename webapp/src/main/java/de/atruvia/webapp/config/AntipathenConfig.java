package de.atruvia.webapp.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AntipathenConfig {

	@Bean
	@Qualifier("antipathen")
	public List<String> getAntipathen() {
		return List.of("Attila", "Peter","Paul","Mary");
	}
	
	@Bean
	@Qualifier("fruits")
	public List<String> fruits() {
		return List.of("Strawberry", "Cherry","Banana","Raspberry");
	}
}
