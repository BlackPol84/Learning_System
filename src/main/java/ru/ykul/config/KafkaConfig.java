package ru.ykul.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.template.default-topic}")
    private String notificationEventTopic;

    @Bean
    public NewTopic courseNotificationTopic() {
        return TopicBuilder
                .name(notificationEventTopic)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
