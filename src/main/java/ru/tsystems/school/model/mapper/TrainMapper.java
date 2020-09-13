package ru.tsystems.school.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.tsystems.school.model.dto.TrainDto;
import ru.tsystems.school.model.entity.Train;

@Mapper
public interface TrainMapper {

    @Mapping(target = "stations", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    TrainDto toDto(Train train);

    Train toEntity(TrainDto trainDto);
}
