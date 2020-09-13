package ru.tsystems.school.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.tsystems.school.model.dto.ScheduleDto;
import ru.tsystems.school.model.dto.StationDto;
import ru.tsystems.school.model.dto.TrainDto;
import ru.tsystems.school.service.exceptions.NotUniqueNameException;
import ru.tsystems.school.model.entity.Schedule;
import ru.tsystems.school.service.ScheduleService;
import ru.tsystems.school.service.StationService;

import java.util.List;

@Controller
@RequestMapping("/stations")
@ControllerAdvice
@SessionAttributes({"stationDto", "schedule"})
@AllArgsConstructor
public class StationController {

    private final StationService stationService;
    private final ScheduleService scheduleService;
    private static final String STATION = "station";
    private static final String REDIRECT_STATIONS = "redirect:/stations";

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
        model.addAttribute(STATION, stationDto);
        model.addAttribute("schedule", allSchedulesForStation);

        return STATION;

    }

    @PostMapping("/add")
    public String saveStation(@ModelAttribute("stationDto") StationDto stationDto,
                              Model model, NotUniqueNameException ex, SessionStatus sessionStatus) {

        model.addAttribute("stationDto", new StationDto());
        model.addAttribute("msg", ex);
        sessionStatus.setComplete();
        stationService.save(stationDto);

        return REDIRECT_STATIONS;
    }

    @GetMapping("/{id}/addTrain")
    public String addTrain(@RequestParam String departureTime,
                           @RequestParam String arrivalTime,
                           @RequestParam String train, @ModelAttribute("StationDto") StationDto stationDto,
                           @PathVariable int id) {

        stationService.addTrainToStation(departureTime, arrivalTime, train, stationDto, id);
        return REDIRECT_STATIONS;
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

        model.addAttribute(STATION, stationDtoById);
        model.addAttribute("allTrains", resultTrainsForStation);
        model.addAttribute("schedule", allSchedulesForStation);
        model.addAttribute("id", id);
        return "editStation";
    }

    @PostMapping("/updateStation")
    public String editStation(@ModelAttribute("stationDto") StationDto station, SessionStatus sessionStatus) {

        stationService.update(station);
        sessionStatus.setComplete();
        return REDIRECT_STATIONS;
    }


    @GetMapping("deleteStation/{id}")
    public String deleteStation(@PathVariable int id) {
        stationService.deleteStationById(id);
        return REDIRECT_STATIONS;
    }

    @GetMapping("/{trainId}/delete/{stationId}")
    public String deleteTrainFromStationSchedule(@PathVariable int stationId,
                                                 @PathVariable int trainId) {

        scheduleService.deleteTrainFromSchedule(trainId, stationId);
        return REDIRECT_STATIONS + "/{stationId}";

    }

}
