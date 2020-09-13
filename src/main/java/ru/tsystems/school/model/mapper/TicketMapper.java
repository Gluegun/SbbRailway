package ru.tsystems.school.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ru.tsystems.school.model.dto.TicketDto;
import ru.tsystems.school.model.entity.Ticket;

@Mapper
public interface TicketMapper {

	@Mapping(target = "train", ignore = true)
    @Mapping(target = "passenger", ignore = true)
	TicketDto toDto(Ticket ticket);

	Ticket toEntity(TicketDto ticketDto);
}
