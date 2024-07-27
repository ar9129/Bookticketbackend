package com.aditya.bookmyshow.controller;

import com.aditya.bookmyshow.dto.ShowDTO;
import com.aditya.bookmyshow.model.*;
import com.aditya.bookmyshow.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins =  "http://192.168.1.122:3000")
//@CrossOrigin(origins =  "http://192.168.1.190:3000")
public class ShowController {

    private static final Logger log = LoggerFactory.getLogger(CreateController.class);


    @Autowired
    private  ShowRepository showRepository ;

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


    @PostMapping("/create-show")
    public Show createShow(@RequestBody ShowDTO showDTO) {
        Optional<Movie> movie1 = movieRepository.findById(showDTO.getMovieID());
        Optional<Screen> screen1 = screenRepository.findById(showDTO.getScreenID());
        Map< String, Integer> categoryToPrice = new HashMap<>();
        for (Map.Entry<Long, Integer> entry : showDTO.getCategoryToPrice().entrySet()) {
            Long categoryId = entry.getKey();
            String categoryName = categoryRepository.findById(categoryId).get().getCategoryName() ;
            Integer price = entry.getValue();

            categoryToPrice.put(categoryName, price) ;
        }

        log.info("categoryToPrice is {}" ,categoryToPrice);
        log.info(categoryToPrice.toString());

        Show show = Show.builder().movie(movie1.get()).screen(screen1.get()).startTime(showDTO.getStartTime()).language(showDTO.getLanguage()).format(showDTO.getFormat()).categoryToPrice(categoryToPrice).build();
        Show show1 = showRepository.save(show);

        List<ShowSeat> showSeatList = new ArrayList<ShowSeat>();
        for (Map.Entry<Long, Integer> entry : showDTO.getCategoryToPrice().entrySet()) {
            Long categoryId = entry.getKey();
            Integer price = entry.getValue();


            Optional<Category> category1 = categoryRepository.findById(categoryId);
            List<Seat> seatList = seatRepository.findAllByCategory(category1.get().getId());

            for (Seat s : seatList) {
                ShowSeat showSeat = ShowSeat.builder().seatNumber(s.getSeatNumber()).category(category1.get()).price(price).isBooked(false).show(show1).build();
                ShowSeat s2 = showSeatRepository.save(showSeat);
                showSeatList.add(s2);
            }

        }

        show1.setSeatForShows(showSeatList);
        return show1;


    }


    @GetMapping("/get-movies/{cityName}")
    List<Movie> findAllByCity(@PathVariable("cityName") String cityName) {
//        List<Movie>movieids = showRepository.findAllByCity(cityID) ;
//        List<Movie> movies = new ArrayList<>();
//        for (Long movieId : movieids){
//            Optional<Movie> movieOptional = movieRepository.findById(movieId);
//            movieOptional.ifPresent(movies::add);
//        }
        return showRepository.findAllMoviesByCity(cityName) ;
    }

    @GetMapping("/get-shows/{cityId}/{movieId}")
    List<Show> findAllByMovieAndCity(@PathVariable("cityId") Long cityId,
                                     @PathVariable("movieId") Long movieId){
        Date currentTime = new Date() ;
        log.info("date is", currentTime) ;
        return showRepository.findAllShowsByMovieAndCity(cityId,movieId,currentTime ) ;
    }


    @GetMapping("/get-shows/{cityId}/{movieName}/{language}/{format}")
    public List<Map<String, Object>> findAllShowsByMovieLanguageAndFormat1(
            @PathVariable("cityId") Long cityId,
            @PathVariable("movieName") String movieName,
            @PathVariable("language") String language,
            @PathVariable("format") String format) {

        Date currentTime = new Date();
        List<Object[]> results = showRepository.findAllShowsByMovieLanguageAndFormat(cityId, movieName, language, format, currentTime);
        List<Map<String, Object>> categorizedShows = new ArrayList<>();

        for (Object[] result : results) {
            Show show = (Show) result[0];
            String theatreName = (String) result[1];

            Map<String, Object> showMap = new HashMap<>();
            showMap.put("theatreName", theatreName);
            showMap.put("show", show);

            categorizedShows.add(showMap);
        }

        return categorizedShows;
    }
}



