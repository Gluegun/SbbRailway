package ru.tsystems.school.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.tsystems.school.model.dto.ScheduleDto;
import ru.tsystems.school.model.entity.Schedule;

@Mapper
public interface ScheduleMapper {

    @Mapping(target = "train", ignore = true)
    @Mapping(target = "station", ignore = true)
    ScheduleDto toDto(Schedule schedule);

    Schedule toEntity(ScheduleDto scheduleDto);

}
