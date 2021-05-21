package ru.yoursweet667.uno.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yoursweet667.uno.service.event.EventService;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

<<<<<<< HEAD
    @PostMapping("games/{gameId}/events")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createEvent(@PathVariable("gameId") String gameId, @RequestBody Event event){

        eventService.createEvent(gameId, event);
    }

    @PostMapping("games/{gameId}/events")
=======
    @GetMapping("games/{gameId}/events")
>>>>>>> caa2cae... создал каркас нового API, создал модели и процессоры для внешних команд
    public void getEvents(@PathVariable("gameId") String gameId, @RequestParam String playerId,
                          @RequestParam Integer fromEventId){

        eventService.getEvents(gameId, playerId, fromEventId);
    }
}