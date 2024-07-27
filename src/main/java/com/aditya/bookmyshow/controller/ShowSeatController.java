package com.aditya.bookmyshow.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.aditya.bookmyshow.model.ShowSeat;
import com.aditya.bookmyshow.repository.ShowSeatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins =  "http://192.168.1.1222:3000")
//@CrossOrigin(origins =  "http://192.168.1.190:3000")
public class ShowSeatController {

    private static final Logger log = LoggerFactory.getLogger(ShowSeatController.class);

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @GetMapping("/get-seatLayout/{showId}")
    public List<ShowSeat> findAllSeatsByShow(@PathVariable Long showId) {
        return showSeatRepository.findByShowId(showId);
    }

     @PostMapping("/reserve-seat/{showSeatId}")
      public Boolean reserveseat(@PathVariable Long showSeatId) {
         Optional<ShowSeat> showSeat = showSeatRepository.findById(showSeatId);
         if (showSeat.isPresent()) {
             ShowSeat showSeat1 = showSeat.get();

             if (!showSeat1.isReserved()) {
                 showSeat1.setReserved(true);
                 LocalDateTime now = LocalDateTime.now();
                 LocalDateTime expiryTime = now.plus(Duration.ofMinutes(2));

                 showSeat1.setExpiryTime(expiryTime);
                 showSeatRepository.save(showSeat1);
                 return true;
             }
         }
         log.info("seat is already reserved");
         return false;
    }


     @Scheduled(fixedRate = 60000)
    public void relaeseExpiredReservations(){
        LocalDateTime now = LocalDateTime.now();

        List<ShowSeat> showSeats = showSeatRepository.findAll().stream().filter(
                showSeat -> showSeat.isReserved() && showSeat.getExpiryTime().isBefore(now)).collect(Collectors.toList());

         if (!showSeats.isEmpty()) {
            for (ShowSeat showseat : showSeats) {
                log.info("Releasing seat: {}", showseat.getId());
                showseat.setReserved(false);
                showseat.setExpiryTime(null);
                showSeatRepository.save(showseat);
                log.info("Seat released: {}", showseat.getId());
            }
        }
    }
}


//     @PostMapping("/relaese-seat/{showSeatId}")
//     public  void  releaseSeat(@PathVariable Long showSeatId){
//        Optional<ShowSeat>showSeat = showSeatRepository.findById(showSeatId) ;
//        if(showSeat.isPresent()){
//            ShowSeat showSeat1 = showSeat.get() ;
//
//            if(showSeat1.isReserved()){
//                showSeat1.setReserved(false);
//                showSeat1.setExpiryTime(null);
//            }
//            else {
//                return;
//            }
//        }
//     }