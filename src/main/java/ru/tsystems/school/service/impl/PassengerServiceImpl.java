package ru.tsystems.school.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.school.dao.PassengerDao;
import ru.tsystems.school.dto.PassengerDto;
import ru.tsystems.school.mapper.PassengerMapper;
import ru.tsystems.school.model.Passenger;
import ru.tsystems.school.service.PassengerService;

import java.util.List;
import java.util.stream.Collectors;

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
    public void delete(int id) {
        passengerDao.deleteById(id);
    }

    @Override
    public List<PassengerDto> findAllDtoPassengers() {
        return passengerDao.findAll().stream().map(this::convertPassengerToDto).collect(Collectors.toList());
    }

    @Override
    public void save(PassengerDto passengerDto) {

        Passenger passenger = convertPassengerToEntity(passengerDto);
        passengerDao.save(passenger);

    }

    @Override
    public void createUser(PassengerDto passengerDto) {
        Passenger passenger = passengerMapper.toEntity(passengerDto);
        passengerDao.save(passenger);
    }

    @Override
    public PassengerDto findByUserName(String userName) {
        return passengerMapper.toDto(passengerDao.findByUserName(userName));
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
