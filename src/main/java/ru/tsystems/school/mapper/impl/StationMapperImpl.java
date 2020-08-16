package ru.tsystems.school.mapper.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.tsystems.school.dto.StationDto;
import ru.tsystems.school.mapper.StationMapper;
import ru.tsystems.school.model.Station;

@Component
@Transactional
public class StationMapperImpl implements StationMapper {

	@Override
	public StationDto toDto(Station station) {
		StationDto stationDto = new StationDto();
		
		if(station != null) {
			stationDto.setId(station.getId());
			stationDto.setName(station.getName());
		}
		return stationDto;
	}

	@Override
	public Station toEntity(StationDto stationDto) {

		Station station = new Station();
		
		if(stationDto != null) 
			station.setId(stationDto.getId());
			station.setName(stationDto.getName());
		
		return station;
	}

}
