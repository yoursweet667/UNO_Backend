package ru.yoursweet667.uno.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yoursweet667.uno.service.event.EventService;
import ru.yoursweet667.uno.service.model.event.Event;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("games/{gameId}/events")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createEvent(@PathVariable("gameId") String gameId, @RequestBody Event event){

        eventService.createEvent(gameId, event);
    }

    @GetMapping("games/{gameId}/events")
    public void getEvents(@PathVariable("gameId") String gameId, @RequestParam String playerId,
                          @RequestParam Integer fromEventId){

        eventService.getEvents(gameId, playerId, fromEventId);
    }
}