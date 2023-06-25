package com.navi.imbd.respository;

import com.navi.imbd.dto.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepo extends CrudRepository<Movie,Integer> {

}
