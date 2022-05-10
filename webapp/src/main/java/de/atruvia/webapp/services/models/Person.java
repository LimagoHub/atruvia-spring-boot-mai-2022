package de.atruvia.webapp.services.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor




public class Person {
	
	@Setter(value = AccessLevel.PRIVATE)
	private String id;
	
	private String vorname;
	
	private String nachname;
}