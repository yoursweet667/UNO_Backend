package ru.yoursweet667.uno.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yoursweet667.uno.service.model.Player;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.game.GameService;
import ru.yoursweet667.uno.web.model.Events;
import ru.yoursweet667.uno.web.model.GameResponse;
import ru.yoursweet667.uno.web.model.PlayerResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("games")
    public GameResponse createGame() {
        Game game = gameService.createGame();
        List<PlayerResponse> players = game.getPlayers().values().stream()
                .map(GameController::convertToPlayerResponse)
                .collect(Collectors.toList());
        //todo: make join URL
        return new GameResponse(game.getGameId(), players, "gameId");
    }

    @PostMapping("games/{gameId}/players")
    public PlayerResponse addPlayerToGame(@RequestParam("playerName") String playerName,
                                          @PathVariable("gameId") String gameId) {
        Player player = gameService.addPlayerToGame(gameId, playerName);
        return convertToPlayerResponse(player);
    }

    @DeleteMapping("games/{gameId}/players/{playerId}")
    public void deletePlayer(@PathVariable("playerId") String playerId, @PathVariable("gameId") String gameId) {
        gameService.removePlayerFromGame(gameId, playerId);
    }

    @GetMapping("games/{gameId}/events")
    public Events getEvents(@PathVariable("gameId") String gameId) {

        return new Events(List.of());
    }


    static PlayerResponse convertToPlayerResponse(Player player) {
        String playerId = player.getPlayerId();
        String playerName = player.getName();
        return new PlayerResponse(playerId, playerName, true);
    }
}