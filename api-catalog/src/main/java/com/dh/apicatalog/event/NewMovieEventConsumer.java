package com.dh.apicatalog.event;

import com.dh.apicatalog.config.RabbitMQConfig;
import com.dh.apicatalog.service.CatalogService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NewMovieEventConsumer {

    @Autowired
    private CatalogService catalogService;

    //subscription to message queue

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NEW_MOVIE)
    public void listenNewMovieEvent(NewMovieEventConsumer.MessageMovie message) {
        log.info("There is a notification of a NEW MOVIE with genre : " + message.genre);
        //System.out.println("We have a notification of a new movie with genre : " + message.genre);
        catalogService.createMovie(message);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class MessageMovie {
        private String name;
        private String genre;
        private String urlStream;
    }
}
