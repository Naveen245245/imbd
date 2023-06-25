package com.navi.imbd.respository;

import com.navi.imbd.dto.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepo extends CrudRepository<Review,Long> {

}
