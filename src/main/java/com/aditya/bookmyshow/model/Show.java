package com.aditya.bookmyshow.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.NonFinal;

@Entity
@Table(name = "shows")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Show {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "movie_id")
	Movie movie;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "screen_id")
	Screen screen;
	
	@Column(name = "start_time", nullable = false)
	Date startTime;
	
	@JsonIgnore
	@OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
	List<ShowSeat> seatForShows;
	
	@JsonIgnore
	@OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
	List<Booking> bookings;

	String language ;

	String format ;


	@ElementCollection
	@CollectionTable(name = "show_category_price",
			joinColumns = @JoinColumn(name = "show_id"))
	@MapKeyColumn(name = "category")
	@Column(name = "price")
	Map<String, Integer> categoryToPrice ;


}
