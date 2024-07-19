package com.aditya.bookmyshow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
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
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "show_seats")
@Data
@Builder
public class ShowSeat {

	public ShowSeat() {
		this.expiryTime = LocalDateTime.of(2023, 7, 18, 12, 30, 0);
	}

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

	@Column(name = "is_reserved", nullable = false)
	boolean isReserved = false ;

	@Column(name = "expiry_time")
	LocalDateTime expiryTime =  LocalDateTime.of(2023, 7, 18, 12, 30, 0);

	public ShowSeat(Long id, String seatNumber, Category category, Show show, Booking booking, int price, boolean isBooked, boolean isReserved, LocalDateTime expiryTime) {
		this.id = id;
		this.seatNumber = seatNumber;
		this.category = category;
		this.show = show;
		this.booking = booking;
		this.price = price;
		this.isBooked = isBooked;
		this.isReserved = isReserved;
		this.expiryTime = LocalDateTime.of(2023, 7, 18, 12, 30, 0);
	}
}
