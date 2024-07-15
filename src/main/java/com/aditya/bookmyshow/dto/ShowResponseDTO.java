package com.aditya.bookmyshow.dto;

import com.aditya.bookmyshow.model.Screen;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class ShowResponseDTO {
    private  Long id ;
    private Map<String, Integer> categoryToPrice ;
    private Date startTime ;
    Screen screen ;
}
