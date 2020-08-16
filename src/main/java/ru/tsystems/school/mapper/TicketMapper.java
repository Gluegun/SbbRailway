package ru.tsystems.school.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ru.tsystems.school.dto.TicketDto;
import ru.tsystems.school.model.Ticket;

@Mapper
public interface TicketMapper {

	@Mapping(target = "train", ignore = true)
    @Mapping(target = "passenger", ignore = true)
	TicketDto toDto(Ticket ticket);

	Ticket toEntity(TicketDto ticketDto);
}
