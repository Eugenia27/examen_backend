package com.dh.apicatalog.controller;

import com.dh.apicatalog.client.MovieServiceClient;
import com.dh.apicatalog.client.SerieServiceClient;
import com.dh.apicatalog.controller.dto.OfflineCatalogDTO;
import com.dh.apicatalog.controller.dto.OnlineCatalogDTO;
import com.dh.apicatalog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(name = "/api/v1/catalog")
public class CatalogController {

    @Autowired
    public MovieServiceClient movieServiceClient;

    @Autowired
    public SerieServiceClient serieServiceClient;

    @Autowired
    public CatalogService catalogService;


    // Modo online
    @GetMapping("/online/{genre}")
    public ResponseEntity<OnlineCatalogDTO> getByGenreOnline(@PathVariable String genre, HttpServletResponse response) {
       return ResponseEntity.ok(catalogService.getCatalogByGenreOnline(genre));
    }

    // Modo offline
    @GetMapping("/offline/{genre}")
    public ResponseEntity<OfflineCatalogDTO> getByGenreOffline(@PathVariable String genre, HttpServletResponse response) {
        return ResponseEntity.ok(catalogService.getCatalogByGenreOffline(genre));
    }
}
