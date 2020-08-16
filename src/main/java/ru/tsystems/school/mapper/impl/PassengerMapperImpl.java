package ru.tsystems.school.mapper.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.tsystems.school.dto.PassengerDto;
import ru.tsystems.school.mapper.PassengerMapper;
import ru.tsystems.school.model.Passenger;

@Component
@Transactional
public class PassengerMapperImpl implements PassengerMapper {

	@Override
	public PassengerDto toDto(Passenger passenger) {
		 
		PassengerDto passengerDto = new PassengerDto();
		if(passenger !=null) {
			passengerDto.setId(passenger.getId());
			passengerDto.setBirthDate(passenger.getBirthDate());
			passengerDto.setFirstName(passenger.getFirstName());
			passengerDto.setLastName(passenger.getLastName());
			passengerDto.setUsername(passenger.getUsername());
		}
		
		return passengerDto;
	}

	@Override
	public Passenger toEntity(PassengerDto passengerDto) {
		
		Passenger passenger =new Passenger();
		
		if (passengerDto != null) {
			passenger.setBirthDate(passengerDto.getBirthDate());
			passenger.setFirstName(passengerDto.getFirstName());
			passenger.setLastName(passengerDto.getLastName());
	    }
		return passenger;
	}

}
