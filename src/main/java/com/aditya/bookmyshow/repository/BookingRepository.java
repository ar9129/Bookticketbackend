package com.aditya.bookmyshow.repository;

import com.aditya.bookmyshow.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long>{

}
