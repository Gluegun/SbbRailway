package ru.tsystems.school.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.tsystems.school.model.dto.StationDto;
import ru.tsystems.school.model.entity.Station;

@Mapper
public interface StationMapper {

    @Mapping(target = "trains", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    StationDto toDto(Station station);

    Station toEntity(StationDto stationDto);
}
