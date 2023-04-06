package com.dh.apimovie;

import com.dh.apimovie.model.Movie;
import com.dh.apimovie.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class MovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(MovieRepository repository) {
		return (args) -> {
			if (!repository.findAll().isEmpty()) {
				return;
			}

			repository.save(new Movie(null, "Pelicula 1", "Terror", "www.netflix.com"));
			repository.save(new Movie(null, "Pelicula 2", "Comedia", "www.netflix.com"));
			repository.save(new Movie(null, "Pelicula 3", "Romance", "www.netflix.com"));
			repository.save(new Movie(null, "Pelicula 4", "Ficcion", "www.netflix.com"));
			repository.save(new Movie(null, "Pelicula 5", "Terror", "www.netflix.com"));

		};
	}

}
