package ru.ykul.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ykul.exception.CourseNotificationJsonParseException;
import ru.ykul.model.MessageType;
import ru.ykul.model.dto.CourseNotificationDto;
import ru.ykul.model.projection.ScheduleProjection;
import ru.ykul.repository.jpa.ScheduleRepositoryJpa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CourseNotificationService {

    private final ScheduleRepositoryJpa repositoryJpa;
    private final ObjectMapper mapper;

    public List<CourseNotificationDto> getCourseStartNextWeek() {

        LocalDateTime intervalDateTime = LocalDateTime.now().plusWeeks(1);
        List<CourseNotificationDto> eventDtoList = new ArrayList<>();

        List<ScheduleProjection> projectionList = repositoryJpa
                .getScheduleForStudentsAndTeachers(intervalDateTime);

        for(ScheduleProjection projection : projectionList) {
            try {
                String message = mapper.writeValueAsString(projection);
                CourseNotificationDto eventDto = new CourseNotificationDto(MessageType.EMAIL, message);
                eventDtoList.add(eventDto);

            } catch (JsonProcessingException ex) {
                throw new CourseNotificationJsonParseException("ScheduleProjection serialization error: " +
                        ex.getMessage());
            }
        }
        return eventDtoList;
    }
}
