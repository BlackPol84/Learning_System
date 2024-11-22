package ru.ykul.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseNotificationJob {

    private final CourseNotificationSender sender;

    @Scheduled(fixedRateString = "${app.schedule.frequency}")
    public void sendCourseNotification() {
        sender.sendNotificationToKafka();
    }
}
