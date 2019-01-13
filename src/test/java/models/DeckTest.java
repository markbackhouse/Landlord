package models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DeckTest {
    @Test
    public void shouldHaveCorrectNumberOfCardsInDeck() {
        Deck deck = new Deck();

        assertFalse(deck.isEmpty());
        assertEquals(54, deck.size());
    }

    @Test
    public void shouldHaveCorrectCardValues() {
        Deck deck = new Deck();

        assertEquals(5, deck.getCard(CardName.FIVE, Suit.DIAMONDS).getValue());
        assertEquals(11, deck.getCard(CardName.JACK, Suit.CLUBS).getValue());
        assertEquals(16, deck.getCard(CardName.JOKER_B, null).getValue());
    }

}
