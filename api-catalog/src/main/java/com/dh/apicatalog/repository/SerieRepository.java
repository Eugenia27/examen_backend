package com.dh.apicatalog.repository;

import com.dh.apicatalog.model.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieRepository extends MongoRepository<Serie, String> {

    List<Serie> findByGenre(String genre);
}
