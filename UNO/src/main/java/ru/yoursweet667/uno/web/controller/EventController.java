package ru.yoursweet667.uno.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yoursweet667.uno.service.event.EventService;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("games/{gameId}/events")
    public void getEvents(@PathVariable("gameId") String gameId, @RequestParam String playerId,
                          @RequestParam Integer fromEventId){

        eventService.getEvents(gameId, playerId, fromEventId);
    }
}