package com.aditya.bookmyshow.repository;

import com.aditya.bookmyshow.model.Category;
import com.aditya.bookmyshow.model.Screen;
import com.aditya.bookmyshow.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long>{
       @Query("SELECT s FROM Seat s WHERE s.category.id = :categoryID")
       List<Seat>findAllByCategory(@Param("categoryID") Long categoryID) ;

}
