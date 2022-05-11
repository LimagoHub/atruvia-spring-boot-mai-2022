package de.atruvia.webapp.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Setter;

@Configuration
@Setter
@ConfigurationProperties(prefix = "demo")
@PropertySource(value = "classpath:demo.yml",  factory = YamlPropertySourceFactory.class)
public class DemoConfig {
	private String stadt;
	private String land;
	private String fluss;
	
	@Bean
	@Qualifier("stadtLandFluss")
	public List<String> getData() {
		return List.of(stadt, land, fluss);
	}
}
