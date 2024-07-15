package com.aditya.bookmyshow.repository;

import com.aditya.bookmyshow.model.Movie;
import com.aditya.bookmyshow.model.Seat;
import com.aditya.bookmyshow.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
//    @Query("SELECT DISTINCT s.movie " +
//            "FROM Show s " +
//            "JOIN Screen scr ON s.screen = scr.id " +
//            "JOIN Theatre t ON scr.theatre = t.id " +
//            "WHERE t.city.id = :city_id")
//    List<Movie> findAllMoviesByCity(@Param("city_id") Long cityId);

    @Query("SELECT DISTINCT s.movie FROM Show s JOIN s.screen scr JOIN scr.theatre t WHERE t.city.name= :cityName")
    List<Movie> findAllMoviesByCity(@Param("cityName") String cityName);


    @Query("SELECT s FROM Show s JOIN s.screen scr JOIN scr.theatre t WHERE t.city.id = :cityId AND s.movie.id = :movieId AND s.startTime > :currentTime ORDER BY t.name")
    List<Show> findAllShowsByMovieAndCity(@Param("cityId") Long cityId, @Param("movieId") Long movieId, @Param("currentTime") Date currentTime);

    @Query("SELECT s, t.name FROM Show s JOIN s.screen sc JOIN sc.theatre t WHERE t.city.id = :cityId AND s.movie.name = :movieName AND s.language = :language AND s.format = :format AND s.startTime > :currentTime ORDER BY t.name")
    List<Object[]>  findAllShowsByMovieLanguageAndFormat (@Param("cityId") Long cityId, @Param("movieName") String movieName
    ,@Param("language") String language, @Param("format") String format, @Param("currentTime") Date currentTime) ;



}
