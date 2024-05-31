package ru.ykul.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ykul.model.Schedule;
import ru.ykul.model.dto.ScheduleDto;

@Mapper
public interface ScheduleMapperDto {

    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "groupName", source = "group.name")
    @Mapping(target = "teacherId", source = "teacher.id")
    @Mapping(target = "firstName", source = "teacher.firstName")
    @Mapping(target = "lastName", source = "teacher.lastName")
    @Mapping(target = "courseId", source = "course.id")
    @Mapping(target = "courseTitle", source = "course.title")
    ScheduleDto toDto(Schedule schedule);

    @InheritInverseConfiguration
    Schedule toSchedule(ScheduleDto scheduleDto);
}
