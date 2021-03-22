package ru.yoursweet667.uno.service.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import ru.yoursweet667.uno.service.event.processor.EventProcessor;
import ru.yoursweet667.uno.service.event.validator.EventValidator;
import ru.yoursweet667.uno.service.game.GameService;
import ru.yoursweet667.uno.service.model.*;
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
    @Mock
    private EventValidator eventValidator;
    @Mock
    private EventProcessor eventProcessor;
    @Mock
    private TakeCardsEvent takeCardsEvent;

    @InjectMocks
    private EventServiceImpl eventService;

    @BeforeEach
    private void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createEvent_validateAndProcessEvent() {

        //Given
        Game game = new Game(GAME_ID, null, null,
                null, null, null);
        TakeCardsEvent event = new TakeCardsEvent(123, null, null, null);

        Mockito.when(gameService.getGame(GAME_ID)).thenReturn(Optional.of(game));
        Mockito.when(eventHandlerRegistry.getValidator(event)).thenReturn(eventValidator);
        Mockito.when(eventHandlerRegistry.getProcessor(event)).thenReturn(eventProcessor);

        //When
        eventService.createEvent(GAME_ID, event);

        //Then
        Mockito.verify(eventValidator).validate(event, game);
        Mockito.verify(eventProcessor).process
                (Matchers.eq(event), Matchers.eq(game), Matchers.any());
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
        TakeCardsEvent event = new TakeCardsEvent(123, EventType.JOIN_GAME, null, null);
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
        TakeCardsEvent firstEvent = new TakeCardsEvent(123, EventType.JOIN_GAME, null, null);
        TakeCardsEvent secondEvent = new TakeCardsEvent(321, EventType.JOIN_GAME, null, null);
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

    @Test
    void getEvents_eventsContainOtherPlayersSensitiveData_returnedEventsHaveHiddenDetails() {

        //Given
        List<Card> cards = new ArrayList<>();

        Card card = new Card(CardType.ZERO, CardColour.RED, true);
        for (int i = 0; i < 2; i++ ) {
            cards.add(card);
        }

        Player player = new Player(PLAYER_ID, null, cards);

        TakeCardsEvent event = new TakeCardsEvent(123, EventType.TAKE_CARDS, cards, player);
        Game game = new Game(GAME_ID, null, null,
                null, null, List.of(event));

        Mockito.when(gameService.getGame(GAME_ID)).thenReturn(Optional.of(game));
        Mockito.when(takeCardsEvent.getPlayer()).thenReturn(player);

        //When
        int fromEventId = 0;
        List<Event> events = eventService.getEvents(GAME_ID, "somePlayerId", fromEventId);

        Event changedEvent = events.get(0);

        //Then
        assertThat(changedEvent).isInstanceOf(TakeCardsEvent.class);

        TakeCardsEvent takeCardsEvent = (TakeCardsEvent) changedEvent;

        boolean allCardsHidden = takeCardsEvent.getCards().stream()
                .noneMatch(Card::getVisible);

        assertThat(allCardsHidden).isTrue();
    }
}
