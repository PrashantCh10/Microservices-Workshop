package io.springms.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.springms.model.Rating;
import io.springms.model.UserRating;

@RestController
@RequestMapping("/ratings")
public class RatingResource {

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId,4);
	}
	
	@RequestMapping("/users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId){
		
		List<Rating> ratings = Arrays.asList(
				new Rating("Trans",4),
				new Rating("Spi",5)
				);
		UserRating userRating=new UserRating();
		userRating.setUserRating(ratings);
		return userRating;
	}
}
