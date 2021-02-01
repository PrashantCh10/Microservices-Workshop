package io.springms.resources;




import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.springms.model.CatalogItem;
import io.springms.model.Movie;
import io.springms.model.UserRating;

@RestController
@RequestMapping("/api")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/{id}")
	public List<CatalogItem> getCatalog(@PathVariable("id") String userId){	
		
		UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratings/users/"+userId, UserRating.class);
		
		return userRating.getUserRating().stream().map(rating -> { 
		Movie movie=restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
		return new CatalogItem(movie.getName(),"test",rating.getRate());
		})
				
	    .collect(Collectors.toList());
		
		
		
	}
}
