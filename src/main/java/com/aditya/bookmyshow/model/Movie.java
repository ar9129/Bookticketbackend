package com.aditya.bookmyshow.model;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "movies")
@Data
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	String name;
	
	String description;
	
	int rating;
	
	int duration;

	@ElementCollection
	@CollectionTable(name = "movie_language_format",
			joinColumns = @JoinColumn(name = "movie_id"))
	@MapKeyColumn(name = "language")
	@Column(name = "formats")
	Map<String, List<String>> languageFormat;

	List<String> genre ;



	String url ;


}
