package com.dh.apimovie.event;

import com.dh.apimovie.config.RabbitMQConfig;
import com.dh.apimovie.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewMovieEventProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //public void publishNewMovieEvent(NewMovieEventProducer.Message message) {
    public void publishNewMovieEvent(Movie movie) {
        //queue.publish(message)
        Message message = new Message();
        BeanUtils.copyProperties(movie, message);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.TOPIC_NEW_MOVIE, message);
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
