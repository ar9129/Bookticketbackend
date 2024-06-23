package com.aditya.bookmyshow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "cities")
@Builder
@Data
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@Column(nullable = false)
	String name;
	
	String state;
	
	String country ;

	@JsonIgnore
	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
	List<Theatre> Theatres;

	public City() {
	}

	public City(Long id, String name, String state, String country, List<Theatre> theatres) {
		this.id = id;
		this.name = name;
		this.state = state;
		this.country = country;
		Theatres = theatres;
	}
}
