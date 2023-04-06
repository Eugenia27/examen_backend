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

import java.util.ArrayList;
import java.util.List;

@Component
public class NewSerieEventConsumer {
    @Autowired
    private CatalogService catalogService;

    //subscription to message queue
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NEW_SERIE)
    public void listenNewSerieEvent(NewSerieEventConsumer.MessageSerie message) {
        System.out.println("We have a notification of a new serie with genre : " + message.genre);
        catalogService.createSerie(message);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class MessageSerie {

        private String name;
        private String genre;
        private List<Season> seasons = new ArrayList<>();

        @AllArgsConstructor
        @NoArgsConstructor
        @Setter
        @Getter
        public static class Season {

            private Integer seasonNumber;
            private List<Chapter> chapters = new ArrayList<>();

            @AllArgsConstructor
            @NoArgsConstructor
            @Setter
            @Getter
            public static class Chapter {

                private String name;
                private Integer number;
                private String urlStream;

            }
        }
    }
}
