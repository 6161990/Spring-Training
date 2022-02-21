package moviebuddy.domain;

import java.net.URL;
import java.util.List;

public class JaxbMovieReader implements MovieReader{

	@Override
	public List<Movie> loadMovies() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static class MovieMetadata {
		
		public List<MovieData> movies;

		public List<MovieData> getMovies() {
			return movies;
		}

		public void setMovies(List<MovieData> movies) {
			this.movies = movies;
		}
		
	}
	
	public static class MovieData {
		private String title;
		private List<String> genres;
		private String language;
		private String country;
		private int releaseYear;
		private String director;
		private List<String> actors;
		private URL imdbLink;
		private String watchedDate;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public List<String> getGenres() {
			return genres;
		}

		public void setGenres(List<String> genres) {
			this.genres = genres;
		}

		public String getLanguage() {
			return language;
		}

		public void setLanguage(String language) {
			this.language = language;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public int getReleaseYear() {
			return releaseYear;
		}

		public void setReleaseYear(int releaseYear) {
			this.releaseYear = releaseYear;
		}

		public String getDirector() {
			return director;
		}

		public void setDirector(String director) {
			this.director = director;
		}

		public List<String> getActors() {
			return actors;
		}

		public void setActors(List<String> actors) {
			this.actors = actors;
		}

		public URL getImdbLink() {
			return imdbLink;
		}

		public void setImdbLink(URL imdbLink) {
			this.imdbLink = imdbLink;
		}

		public String getWatchedDate() {
			return watchedDate;
		}

		public void setWatchedDate(String watchedDate) {
			this.watchedDate = watchedDate;
		}
	}

}
