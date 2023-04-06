package com.dh.serie.event;

import com.dh.serie.config.RabbitMQConfig;
import com.dh.serie.model.Serie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewSerieEventProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //public void publishNewSerieEvent(NewSerieEventProducer.Message message) {
    public void publishNewSerieEvent(Serie serie) {
        //queue.publish(message)
        MessageSerie message = new MessageSerie();
        BeanUtils.copyProperties(serie, message);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.TOPIC_NEW_SERIE, message);
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
