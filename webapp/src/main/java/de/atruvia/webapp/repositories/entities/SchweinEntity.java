package de.atruvia.webapp.repositories.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tbl_schweine")
public class SchweinEntity {

	@Id
	@Column(length = 36, nullable = false )
	private String id;
	@Column(length = 30, nullable = true )
	private String name;
	
	@Column(nullable = false )
	private int gewicht;
	

}
