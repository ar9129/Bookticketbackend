package com.aditya.bookmyshow.service;

import com.aditya.bookmyshow.model.*;
import com.aditya.bookmyshow.repository.*;

import java.util.Date;

public class AdminServiceImpl implements  CreateServive{

    private ScreenRepository screenRepository ;
    private MovieRepository movieRepository ;
    private SeatRepository seatRepository ;
    private ShowRepository showRepository ;
    private CityRepository cityRepository ;


    void adminMethod(Long id, String cityName, String state, String country , String screenName , String theatreName, String seatNumber , String showName, String categoryName, Date startTime, int Duration, String movieName ){

        //List<Theatre> theatreList = new ArrayList<>((Collection) theatre) ;
        City city= City.builder().name(cityName).state(state).country(country).build() ;
        Theatre theatre = Theatre.builder().name(theatreName).city(city).build() ;
        Screen screen = Screen.builder().name(screenName).theatre(theatre).build() ;
        Category category = Category.builder().categoryName(categoryName).build();
        Seat seat = Seat.builder().category(category).screen(screen).seatNumber(seatNumber).build() ;



    }

}
