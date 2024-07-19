package com.aditya.bookmyshow.repository;

import com.aditya.bookmyshow.model.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long>{
    @Query("SELECT st from ShowSeat st WHERE st.show.id = :showId")
    List<ShowSeat> findByShowId(@PathVariable("showId") Long showId) ;



}
