package com.dh.apicatalog.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "CatalogMovies")
public class Movie {

    @Id
    private String id;
    private String name;
    private String genre;
    private String urlStream;

}
