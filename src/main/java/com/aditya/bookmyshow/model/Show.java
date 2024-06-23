package com.aditya.bookmyshow.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
	
	@OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
	List<ShowSeat> seatForShows;
	
	@OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
	List<Booking> bookings;
	
}
