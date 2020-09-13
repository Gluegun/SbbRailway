package ru.tsystems.school.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tsystems.school.model.dto.ScheduleDtoRest;
import ru.tsystems.school.model.dto.StationDto;
import ru.tsystems.school.service.ScheduleService;
import ru.tsystems.school.service.StationService;

import java.util.List;

@RestController
@RequestMapping("/rest/")
@AllArgsConstructor
public class ScheduleControllerRest {

    private final ScheduleService scheduleService;
    private final StationService stationService;

    @GetMapping("schedule/{id}")
    public List<ScheduleDtoRest> getSchedules(@PathVariable("id") int stationId) {
        
        return scheduleService.findScheduleByStationId(stationId);
    }

    @GetMapping("stations")
    public List<StationDto> getStations() {
        return stationService.findAllStations();
    }
}

