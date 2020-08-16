package ru.tsystems.school.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.tsystems.school.dto.ScheduleDto;
import ru.tsystems.school.model.Schedule;

@Mapper
public interface ScheduleMapper {

    @Mapping(target = "train", ignore = true)
    @Mapping(target = "station", ignore = true)
    ScheduleDto toDto(Schedule schedule);

    Schedule toEntity(ScheduleDto scheduleDto);

}
