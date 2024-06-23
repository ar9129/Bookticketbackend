package com.aditya.bookmyshow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "theatres")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Theatre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@Column(nullable = false)
	String name ;

	@ManyToOne
	@JoinColumn(name = "city_id")
	City city ;

	@JsonIgnore
    @OneToMany(mappedBy = "theatre" , cascade = CascadeType.ALL)
	List<Screen>screens ;
	
		
	
	
}
