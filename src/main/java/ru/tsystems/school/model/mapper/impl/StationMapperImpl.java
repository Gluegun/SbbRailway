package ru.tsystems.school.model.mapper.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.tsystems.school.model.dto.StationDto;
import ru.tsystems.school.model.mapper.StationMapper;
import ru.tsystems.school.model.entity.Station;

import javax.annotation.CheckForNull;

@Component
@Transactional
public class StationMapperImpl implements StationMapper {

    @Override
    @CheckForNull
    public StationDto toDto(Station station) {

        StationDto stationDto = new StationDto();

        stationDto.setId(station.getId());
        stationDto.setName(station.getName());

        return stationDto;
    }

    @Override
    @CheckForNull
    public Station toEntity(StationDto stationDto) {

        Station station = new Station();

        station.setId(stationDto.getId());
        station.setName(stationDto.getName());

        return station;
    }

}
