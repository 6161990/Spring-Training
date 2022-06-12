package moviebuddy.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MovieFinderTest {
	final MovieBuddyFactory movieBuddyFactory = new MovieBuddyFactory();
	final MovieFinder movieFinder = movieBuddyFactory.movieFinder();

	@Test
	void NotEmpty_directedBy(){
		List<Movie> result = movieFinder.directedBy("Michael Bay");
		Assertions.assertEquals(3, result.size());
	}

	@Test
	void NotEmpty_releasedYearBy(){
		List<Movie> result = movieFinder.releasedYearBy(2015);
		Assertions.assertEquals(225,result.size());
	}
	
}