package models;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CardTest {
    @Test
    public void shouldBeatInferiorCard() {
        Card goodCard = Card.builder()
                .name(CardName.ACE)
                .suit(Suit.HEARTS)
                .build();
        Card badCard = Card.builder()
                .name(CardName.THREE)
                .suit(Suit.CLUBS)
                .build();
        assertTrue(goodCard.beats(badCard));
    }
}
