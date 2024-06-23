package com.aditya.bookmyshow.controller;

import com.aditya.bookmyshow.dto.ShowDTO;
import com.aditya.bookmyshow.model.*;
import com.aditya.bookmyshow.repository.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class ShowController {

    @Autowired
    private MovieRepository movieRepository ;

    @Autowired
    private ScreenRepository screenRepository ;

    @Autowired
    private ShowSeatRepository showSeatRepository ;

    @Autowired
    private SeatRepository seatRepository ;

    @Autowired
    private CategoryRepository categoryRepository ;

    @Autowired
    private  ShowRepository showRepository ;

    @PostMapping("/create-show")
    public Show createShow(@RequestBody ShowDTO showDTO){
        Optional<Movie>movie1 = movieRepository.findById(showDTO.getMovieID()) ;
        Optional<Screen>screen1 = screenRepository.findById(showDTO.getScreenID()) ;
        Show show = Show.builder().movie(movie1.get()).screen(screen1.get()).startTime(showDTO.getStartTime()).build();
        Show show1 = showRepository.save(show) ;

        List<ShowSeat> showSeatList = new ArrayList<ShowSeat>();
        for (Map.Entry<Long, Integer> entry : showDTO.getCategoryToPrice().entrySet()) {
            Long categoryID = entry.getKey();
            Integer price = entry.getValue();


            Optional<Category> category1 = categoryRepository.findById(categoryID);
            List<Seat> seatList = seatRepository.findAllByCategory(categoryID);

            for (Seat s : seatList) {
                ShowSeat showSeat = ShowSeat.builder().seatNumber(s.getSeatNumber()).category(category1.get()).price(price).isBooked(false).show(show1).build();
                ShowSeat s2 = showSeatRepository.save(showSeat);
                showSeatList.add(s2);
            }

        }

        show1.setSeatForShows(showSeatList);
        return show1 ;


    }
}
