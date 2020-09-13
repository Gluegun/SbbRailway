package ru.tsystems.school.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.school.dao.PassengerDao;
import ru.tsystems.school.dto.PassengerDto;
import ru.tsystems.school.exceptions.NoSuchEntityException;
import ru.tsystems.school.mapper.PassengerMapper;
import ru.tsystems.school.model.Passenger;
import ru.tsystems.school.service.PassengerService;

@Service
@Transactional
@AllArgsConstructor
public class PassengerServiceImpl implements PassengerService {


    private final PassengerDao passengerDao;
    private final PassengerMapper passengerMapper;


    @Override
    public PassengerDto findById(int id) {
        return passengerMapper.toDto(passengerDao.findById(id));
    }

    @Override
    public PassengerDto findByUserName(String userName) {

        Passenger passenger = passengerDao.findByUserName(userName);
        if (passenger == null) {
            throw new NoSuchEntityException("user not found");
        }
        return convertPassengerToDto(passenger);

    }

    private PassengerDto convertPassengerToDto(Passenger passenger) {
        return passengerMapper.toDto(passenger);
    }

    private Passenger convertPassengerToEntity(PassengerDto passengerDto) {
        return passengerMapper.toEntity(passengerDto);
    }

    @Override
    public PassengerDto getAuthorizedPassenger() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        return findByUserName(userDetail.getUsername());
    }

}
