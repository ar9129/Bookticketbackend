package com.aditya.bookmyshow.dto;

import com.aditya.bookmyshow.model.Category;
import com.aditya.bookmyshow.model.Seat;
import com.aditya.bookmyshow.model.Theatre;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public class ScreenDTO {
    private String name ;
    private Long theatreID ;
    private Map<String, List<String>> categoryTOSeatNumber ;
}
