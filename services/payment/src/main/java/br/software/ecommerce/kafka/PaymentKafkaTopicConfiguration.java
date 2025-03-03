package br.software.ecommerce.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class PaymentKafkaTopicConfiguration {

    @Bean
    public NewTopic paymentTopic() {
        return TopicBuilder.name("payment-topic").build();
    }
}
