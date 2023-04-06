package com.dh.apicatalog.event;

import com.dh.apicatalog.config.RabbitMQConfig;
import com.dh.apicatalog.service.CatalogService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewMovieEventConsumer {

    @Autowired
    private CatalogService catalogService;

    //subscription to message queue
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NEW_MOVIE)
    public void listenNewMovieEvent(NewMovieEventConsumer.Message message) {
        System.out.println("We have a notification of a new movie with genre : " + message.genre);
        catalogService.createMovie(message);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Message {
        private String name;
        private String genre;
        private String urlStream;
    }
}
