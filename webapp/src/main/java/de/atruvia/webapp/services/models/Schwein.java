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
public class Schwein {

	@Setter(AccessLevel.PRIVATE)
	private String id;
	
	@Setter(AccessLevel.PRIVATE)
	private String name;
	
	@Setter(AccessLevel.PRIVATE)
	private int gewicht;
	
	public void taufen(final String name) {
		setName(name);
	}
	public void fuettern() {
		setGewicht(getGewicht() + 1);
	}
}
