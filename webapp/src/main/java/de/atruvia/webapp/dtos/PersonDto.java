package de.atruvia.webapp.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@XmlRootElement
public class PersonDto {
	
	@NotNull
	@Size(min=36, max=36)
	private String id;
	
	
	@Size(min=2, max=30)
	private String vorname;
	
	@NotNull
	@Size(min=2, max=30)
	private String nachname;

}
