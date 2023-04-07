package com.dh.apicatalog.controller;

import com.dh.apicatalog.client.MovieServiceClient;
import com.dh.apicatalog.client.SerieServiceClient;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.TimeUnit;


import static io.restassured.RestAssured.given;

class CatalogControllerTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8081";
    }

    @Test
    void completeCycle() throws InterruptedException {
        var responseId = given()
                .contentType(ContentType.JSON)
                .when()
                .body(new MovieServiceClient.MovieDTO(null, "Star Wars", "Ciencia Ficcion", "www.paramount.com"))
                .post("/api/v1/movies/save")
                .as(Map.class);

        given()
                .contentType(ContentType.JSON)
                .when()
                .body(new SerieServiceClient.SerieDTO(null, "Star Wars", "Ciencia Ficcion", null))
                .post("/api/v1/series/save")
                .as(Map.class);

        TimeUnit.SECONDS.sleep(10);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/catalog/online/Ciencia Ficcion")
                .then()
                .statusCode(200);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/catalog/offline/Ciencia Ficcion")
                .then()
                .statusCode(200);

    }
}