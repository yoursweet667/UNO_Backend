package ru.yoursweet667.uno.service.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.yoursweet667.uno.service.game.GameService;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.event.Event;
import ru.yoursweet667.uno.service.model.event.TakeCardsEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class EventServiceImplTest {

    private final static String GAME_ID = "gameId";
    private static final String PLAYER_ID = "playerId";

    @Mock
    private GameService gameService;
    @Mock
    private EventHandlerRegistry eventHandlerRegistry;

    @InjectMocks
    private EventServiceImpl eventService;

    @BeforeEach
    private void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createEvent_validateAndProcessEvent() {

        //Given

        //When

        //Then
    }

    @Test
    void createEvent_gameNotFound_exception() {

        //Given
        TakeCardsEvent event = new TakeCardsEvent(123, null, null, null);
        Game game = new Game(GAME_ID, null, null,
                null, null, List.of(event));

        Mockito.when(gameService.getGame(GAME_ID)).thenReturn(Optional.empty());

        //When+Then
        Assertions.assertThatThrownBy(() -> eventService.createEvent(GAME_ID, event))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getEvents_gameExist_getNeededEvents() {

        //Given
        TakeCardsEvent event = new TakeCardsEvent(123, null, null, null);
        Game game = new Game(GAME_ID, null, null,
                null, null, List.of(event));

        Mockito.when(gameService.getGame(GAME_ID)).thenReturn(Optional.of(game));

        //When
        int fromEventId = 0;
        List<Event> events = eventService.getEvents(GAME_ID, PLAYER_ID, fromEventId);

        //Then
        assertThat(events).hasSize(game.getEvents().size());
        assertThat(events).contains(event);

    }

    @Test
    void getEvents_gameDoesNotExist_exception() {

        //Given
        Mockito.when(gameService.getGame(GAME_ID)).thenReturn(Optional.empty());

        //When
        int fromEventId = 0;

        //Then
        Assertions.assertThatThrownBy(() -> eventService.getEvents(GAME_ID, PLAYER_ID, fromEventId))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void getEvents_fromEventIdNotMoreEventSize_getNeededEvents() {

        //Given
        TakeCardsEvent firstEvent = new TakeCardsEvent(123, null, null, null);
        TakeCardsEvent secondEvent = new TakeCardsEvent(321, null, null, null);
        List<Event> eventsInGame = new ArrayList<>();
        eventsInGame.add(firstEvent);
        eventsInGame.add(secondEvent);
        Game game = new Game(GAME_ID, null, null,
                null, null, eventsInGame);

        Mockito.when(gameService.getGame(GAME_ID)).thenReturn(Optional.of(game));

        //When
        int fromEventId = 0;
        List<Event> events = eventService.getEvents(GAME_ID, PLAYER_ID, fromEventId);

        //Then
        assertThat(fromEventId).isLessThanOrEqualTo(events.size());
    }
}
