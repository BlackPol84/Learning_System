package ru.ykul.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.ykul.model.dto.CourseNotificationDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseNotificationSender {

    private final CourseNotificationService service;
    private final KafkaTemplate<String, CourseNotificationDto> template;

    public void sendNotificationToKafka() {

        List<CourseNotificationDto> eventDtoList = service
                .getCourseStartNextWeek();

        for(CourseNotificationDto eventDto : eventDtoList) {
            template.sendDefault(eventDto);
        }
    }
}
