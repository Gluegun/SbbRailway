package ru.tsystems.school.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tsystems.school.dto.ScheduleDto;
import ru.tsystems.school.dto.StationDto;
import ru.tsystems.school.dto.TrainDto;
import ru.tsystems.school.model.Schedule;
import ru.tsystems.school.service.StationService;

import java.util.List;

@Controller
@RequestMapping("/stations")
@ControllerAdvice
@SessionAttributes({"stationDto", "schedule"})
@AllArgsConstructor
public class StationController {

    private final StationService stationService;

    @ModelAttribute("stationDto")
    public StationDto stationDto() {
        return new StationDto();
    }

    @ModelAttribute("schedule")
    public Schedule getSchedule() {
        return new Schedule();
    }

    @GetMapping
    public String getAllStations(Model model) {

        List<StationDto> stationsDto = stationService.findAllStations();
        model.addAttribute("stations", stationsDto);

        return "stationsList";
    }

    @GetMapping("/{id}")
    public String getTrainsForCurrentStation(Model model, @PathVariable int id) {

        StationDto stationDto = stationService.findStationById(id);

        List<ScheduleDto> allSchedulesForStation = stationService.findAllSchedulesForStation(id);

        model.addAttribute("station", stationDto);
        model.addAttribute("schedule", allSchedulesForStation);

        return "station";

    }

    @PostMapping("/add")
    public String saveStation(@ModelAttribute("stationDto") StationDto stationDto,
                              Model model) {

        model.addAttribute("stationDto", new StationDto());
        stationService.save(stationDto);

        return "redirect:/stations";
    }

    @GetMapping("/{id}/addTrain")
    public String addTrain(@RequestParam String departureTime,
                           @RequestParam String train, @ModelAttribute("StationDto") StationDto stationDto,
                           @PathVariable int id) {

        stationService.addTrainToStation(departureTime, train, stationDto, id);
        return "redirect:/stations";
    }

    @GetMapping("/add")
    public String addNewTrainPage() {
        return "addNewStation";
    }

    @GetMapping("/editStation/{id}")

    public String updateStation(@PathVariable int id, Model model) {

        StationDto stationDtoById = stationService.findStationById(id);
        List<TrainDto> resultTrainsForStation = stationService.potentialTrainsForStation(id);
        List<ScheduleDto> allSchedulesForStation = stationService.findAllSchedulesForStation(id);

        model.addAttribute("station", stationDtoById);
        model.addAttribute("allTrains", resultTrainsForStation);
        model.addAttribute("schedule", allSchedulesForStation);
        model.addAttribute("id", id);
        return "editStation";
    }

    @PostMapping("/updateStation")
    public String editStation(@ModelAttribute("stationDto") StationDto station) {
        stationService.update(station);
        return "redirect:/stations";
    }


    @GetMapping("deleteStation/{id}")
    public String deleteStation(@PathVariable int id) {
        stationService.deleteStationById(id);
        return "redirect:/stations";
    }

}
