package com.dh.serie.service;

import com.dh.serie.event.NewSerieEventProducer;
import com.dh.serie.model.Serie;
import com.dh.serie.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;
    @Autowired
    private NewSerieEventProducer newSerieEventProducer;


    public List<Serie> getAll() {
        return serieRepository.findAll();
    }

    public List<Serie> getSeriesBygGenre(String genre) {
        return serieRepository.findAllByGenre(genre);
    }

    public String create(Serie serie) {
        newSerieEventProducer.publishNewSerieEvent(serie);
        serieRepository.save(serie);
        return serie.getId();
    }
}
