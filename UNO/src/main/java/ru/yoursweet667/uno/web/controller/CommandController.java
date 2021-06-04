package ru.yoursweet667.uno.web.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yoursweet667.uno.service.command.model.CommandType;
import ru.yoursweet667.uno.service.model.Card;

@RestController
public class CommandController {

    @PostMapping("games/{gameId}/commands")
    public void sendCommand(@PathVariable("gameId") String gameId) {

    }
}
