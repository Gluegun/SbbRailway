package ru.tsystems.school.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.tsystems.school.dto.StationDto;
import ru.tsystems.school.model.Station;

@Mapper
public interface StationMapper {

    @Mapping(target = "trains", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    StationDto toDto(Station station);

    Station toEntity(StationDto stationDto);
}
