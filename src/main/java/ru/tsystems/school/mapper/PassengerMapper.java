package ru.tsystems.school.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.tsystems.school.dto.PassengerDto;
import ru.tsystems.school.model.Passenger;

@Mapper
public interface PassengerMapper {

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd.MM.yyyy")
    PassengerDto toDto(Passenger passenger);

    @InheritConfiguration
    Passenger toEntity(PassengerDto passengerDto);

}
