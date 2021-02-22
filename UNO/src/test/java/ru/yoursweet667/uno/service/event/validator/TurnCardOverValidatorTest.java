package ru.yoursweet667.uno.service.event.validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yoursweet667.uno.service.model.Game;
import ru.yoursweet667.uno.service.model.GameState;

public class TurnCardOverValidatorTest {

    private static final TurnCardOverValidator turnCardOver = new TurnCardOverValidator();

    @Test
    void validate_gameHasRequiredState_noException (){
        //Given
        Game game = new Game(null, null, GameState.INITIALISING,
                null, null,null);

        //When+Then
        Assertions.assertThatCode(() -> turnCardOver.validate(null, game))
                .doesNotThrowAnyException();
    }

    @Test
    void validate_gameHasNoRequiredState_exception (){
        //Given
        Game game = new Game(null, null, GameState.GAME_CREATED,
                null, null,null);

        //When+Then
        Assertions.assertThatThrownBy(() -> turnCardOver.validate(null, game))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
