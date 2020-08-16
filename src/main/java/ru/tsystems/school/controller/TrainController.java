package ru.tsystems.school.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tsystems.school.dto.PassengerDto;
import ru.tsystems.school.dto.ScheduleDto;
import ru.tsystems.school.dto.StationDto;
import ru.tsystems.school.dto.TrainDto;
import ru.tsystems.school.service.ScheduleService;
import ru.tsystems.school.service.TicketService;
import ru.tsystems.school.service.TrainService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/trains")
@ControllerAdvice
@SessionAttributes({"trainDto", "trains", "stationDto"})
@AllArgsConstructor
public class TrainController {

    private final TrainService trainService;
    private final TicketService ticketService;
    private final ScheduleService scheduleService;

    @ModelAttribute("trainDto")
    public TrainDto getTrainDto() {
        return new TrainDto();
    }

    @ModelAttribute("trainsDto")
    public List<TrainDto> getTrainsDto() {
        return new ArrayList<>();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAllTrains(Model model) {

        List<TrainDto> trainsDto = trainService.findAllDtoTrains();
        model.addAttribute("trains", trainsDto);
        return "trainsList";
    }

    @GetMapping("/{id}")
    public String findTrainById(@PathVariable int id, Model model) {

        List<PassengerDto> passengersDto = trainService.findAllPassengers(id);
        TrainDto trainDtoById = trainService.findTrainById(id);
        List<ScheduleDto> allSchedulesForTrain = scheduleService.findSchedulesDtoByTrainId(id);

        model.addAttribute("train", trainDtoById);
        model.addAttribute("passengers", passengersDto);
        model.addAttribute("schedules", allSchedulesForTrain);

        return "train";
    }

    @PostMapping("/add")
    public String saveTrain(@ModelAttribute("trainDto") TrainDto train,
                            Model model) {

        model.addAttribute("trainDto", new TrainDto());
        trainService.save(train);

        return "redirect:/trains";
    }

    @GetMapping("/{id}/addStation")
    public String addStation(@RequestParam String departureTime, @RequestParam String station,
                             @ModelAttribute("TrainDto") TrainDto trainDto, @PathVariable int id) {

        trainService.addStationToTrain(departureTime, station, trainDto, id);

        return "redirect:/trains";
    }

    @GetMapping("/buy/{trainId}")
    public String buyTicket(@PathVariable int trainId,
                            @RequestParam String fromStation) {

        ticketService.buyTicket(trainId, fromStation);

        return "redirect:/";
    }


    @GetMapping("/add")
    public String addNewTrainPage() {
        return "addNewTrain";
    }

    @GetMapping("/edit/{id}")
    public String updateTrain(@PathVariable int id, Model model) {

        TrainDto trainDtoById = trainService.findTrainById(id);
        List<StationDto> resultStationDtoForTrain = trainService.potentialStationsForTrain(id);

        model.addAttribute("train", trainDtoById);
        model.addAttribute("allStations", resultStationDtoForTrain);
        model.addAttribute("id", id);
        return "editTrain";
    }

    @PostMapping("/update")
    public String editTrain(@ModelAttribute("trainDto") TrainDto train) {
        trainService.update(train);
        return "redirect:/trains";
    }

    @GetMapping("delete/{id}")
    public String deleteTrain(@PathVariable int id) {
        trainService.deleteById(id);
        return "redirect:/trains";
    }
}


