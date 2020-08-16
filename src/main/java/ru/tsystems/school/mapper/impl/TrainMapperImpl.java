package ru.tsystems.school.mapper.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.tsystems.school.dto.TrainDto;
import ru.tsystems.school.mapper.TrainMapper;
import ru.tsystems.school.model.Train;

@Component
@Transactional
public class TrainMapperImpl implements TrainMapper {

	
	@Override
	public TrainDto toDto(Train train) {

		TrainDto trainDto = new TrainDto();
		
		if(train != null) {
			trainDto.setId(train.getId());
			trainDto.setSeatsAmount(train.getSeatsAmount());
			trainDto.setTrainNumber(train.getTrainNumber());
		}

		return trainDto;
	}

	@Override
	public Train toEntity(TrainDto trainDto) {
		
		Train train = new Train();
		
		if (trainDto != null) {
			train.setId(trainDto.getId());
			train.setSeatsAmount(trainDto.getSeatsAmount());
			train.setTrainNumber(trainDto.getTrainNumber());
	    }
		return train;
	}
	
}
