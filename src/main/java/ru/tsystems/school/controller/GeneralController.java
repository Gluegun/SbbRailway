package ru.tsystems.school.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tsystems.school.dto.PassengerDto;
import ru.tsystems.school.dto.StationDto;
import ru.tsystems.school.dto.TicketDto;
import ru.tsystems.school.dto.TrainDto;
import ru.tsystems.school.service.PassengerService;
import ru.tsystems.school.service.StationService;
import ru.tsystems.school.service.TicketService;
import ru.tsystems.school.service.TrainService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class GeneralController {

    private final StationService stationService;
    private final TrainService trainService;
    private final TicketService ticketService;
    private final PassengerService passengerService;

    @GetMapping("account")
    public String showAccountInfo(Model model) {

        List<TicketDto> tickets = ticketService.getTicketsForAuthorizedUser();
        PassengerDto user = passengerService.getAuthorizedPassenger();

        List<StationDto> departureStations = new ArrayList<>();

        for (TicketDto ticket : tickets) {
            departureStations.add(ticketService.getStationDeparture(ticket.getTrain().getId(), ticket.getDepartureTime()));
        }

        model.addAttribute("user", user);
        model.addAttribute("tickets", tickets);
        model.addAttribute("depStations", departureStations);
        return "account";
    }

    @GetMapping
    public String homePage(Model model) {

        List<StationDto> allDtoStations = stationService.findAllStations();
        model.addAttribute("stations", allDtoStations);
        return "home";
    }

    @GetMapping("/buy_ticket")
    public String buyTicket(Model model) {

        List<StationDto> allDtoStations = stationService.findAllStations();
        model.addAttribute("stations", allDtoStations);
        return "buy_ticket";

    }

    @GetMapping("/suitableTrainList")
    public String getSuitableTrainList(@RequestParam String fromStation,
                                       @RequestParam String toStation,
                                       @RequestParam String fromTime,
                                       @RequestParam String toTime,
                                       Model model) {

        StationDto from = stationService.findByStationName(fromStation);
        StationDto to = stationService.findByStationName(toStation);
        List<TrainDto> trains = stationService.findSuitableTrains(from, to, fromTime, toTime);
        Map<Integer, Boolean> ticketsPassenger = ticketService.ticketsForPassengers(fromStation,
                toStation, fromTime, toTime);

        model.addAttribute("ticketsPassenger", ticketsPassenger);
        model.addAttribute("trains", trains);
        model.addAttribute("fromStation", fromStation);

        return "trainsList";
    }

    @GetMapping("deleteTicket/{id}")
    public String deleteTicket(@PathVariable int id) {

        TicketDto ticket = ticketService.findById(id);
        TrainDto train = trainService.findTrainById(ticket.getTrain().getId());
        train.setSeatsAmount(train.getSeatsAmount() + 1);
        trainService.update(train);
        ticketService.deleteById(id);
        return "redirect:/account";

    }
}
