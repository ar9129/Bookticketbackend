package com.aditya.bookmyshow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "show_seats")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ShowSeat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	
	String seatNumber;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	Category category;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "show_id")
	Show show;

	@ManyToOne
	@JoinColumn(name = "booking_id")
	Booking booking;
	
	int price;
	
	@Column(name = "is_booked")
	boolean isBooked;
}
