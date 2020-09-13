package ru.tsystems.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tsystems.school.dto.PassengerDto;
import ru.tsystems.school.service.PassengerService;

@Controller
@RequestMapping("/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;

    }

    @GetMapping("/{id}")
    public String findPassengerById(@PathVariable int id, Model model) {

        PassengerDto passengerDto = passengerService.findById(id);
        model.addAttribute("passenger", passengerDto);
        return "passenger";
    }

}
