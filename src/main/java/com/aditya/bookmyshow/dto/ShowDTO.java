package com.aditya.bookmyshow.dto;

import lombok.Data;

import java.util.Date;
import java.util.Map;


@Data
public class ShowDTO {
    private Long movieID ;
    private Long screenID ;
    private Date startTime ;
    private Map<Long, Integer>categoryToPrice ;

}
