package com.dh.apicatalog.client;

import com.dh.apicatalog.config.LoadBalancerConfiguration;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "api-movie")
@LoadBalancerClient(value="api-movie", configuration= LoadBalancerConfiguration.class)
public interface MovieServiceClient {

    @GetMapping("/api/v1/movies/{genre}")
    public List<MovieDTO> getMovieByGenre(@PathVariable String genre);

    @Getter
    @Setter
    class MovieDTO {
        private Long id;
        private String name;
        private String genre;
        private String urlStream;
    }
}
