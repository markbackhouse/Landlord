package moves;

import models.Card;
import models.CardList;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static models.CardName.*;
import static models.Suit.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FullHouseMoveTest {
    @Test
    public void shouldAcceptNormalFullHouse() {
        List<Card> cardList = Arrays.asList(
                Card.builder().name(TWO).suit(HEARTS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build(),
                Card.builder().name(TWO).suit(DIAMONDS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build()
        );

        CardList cards = new CardList(cardList);
        Move fullHouseMove = new FullHouseMove(cards);

        assertTrue(fullHouseMove.isValid());
    }

    @Test
    public void shouldAcceptCorrectHandWithSingleJoker() {
        List<Card> cardListA = Arrays.asList(
                Card.builder().name(TWO).suit(HEARTS).build(),
                Card.builder().name(JOKER_A).suit(HEARTS).build(),
                Card.builder().name(TWO).suit(DIAMONDS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build()
        );

        List<Card> cardListB = Arrays.asList(
                Card.builder().name(TWO).suit(HEARTS).build(),
                Card.builder().name(JOKER_A).suit(HEARTS).build(),
                Card.builder().name(FOUR).suit(DIAMONDS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build()
        );

        CardList cardsA = new CardList(cardListA);
        CardList cardsB = new CardList(cardListB);
        Move fullHouseMoveA = new FullHouseMove(cardsA);
        Move fullHouseMoveB = new FullHouseMove(cardsB);

        assertTrue(fullHouseMoveA.isValid());
        assertTrue(fullHouseMoveB.isValid());
    }

    @Test
    public void shouldAcceptCorrectHandWithTwoJokers() {
        List<Card> cardListA = Arrays.asList(
                Card.builder().name(TWO).suit(HEARTS).build(),
                Card.builder().name(JOKER_A).suit(HEARTS).build(),
                Card.builder().name(JOKER_B).suit(DIAMONDS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build()
        );

        List<Card> cardListB = Arrays.asList(
                Card.builder().name(JOKER_B).suit(HEARTS).build(),
                Card.builder().name(JOKER_A).suit(HEARTS).build(),
                Card.builder().name(FOUR).suit(DIAMONDS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build()
        );

        CardList cardsA = new CardList(cardListA);
        CardList cardsB = new CardList(cardListB);
        Move fullHouseMoveA = new FullHouseMove(cardsA);
        Move fullHouseMoveB = new FullHouseMove(cardsB);

        assertTrue(fullHouseMoveA.isValid());
        assertTrue(fullHouseMoveB.isValid());
    }

    @Test
    public void shouldRejectHandWithWrongNumberOfCards() {
        List<Card> cardListA = Arrays.asList(
                Card.builder().name(TWO).suit(HEARTS).build(),
                Card.builder().name(JOKER_A).suit(HEARTS).build(),
                Card.builder().name(TWO).suit(DIAMONDS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build()
        );

        CardList cardsA = new CardList(cardListA);
        Move fullHouseMoveA = new FullHouseMove(cardsA);

        assertFalse("Full house should have 5 cards", fullHouseMoveA.isValid());
    }

    @Test
    public void shouldRejectHandWithIncorrectCardPattern() {
        List<Card> cardListA = Arrays.asList(
                Card.builder().name(TWO).suit(HEARTS).build(),
                Card.builder().name(FIVE).suit(HEARTS).build(),
                Card.builder().name(TWO).suit(DIAMONDS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build()
        );

        CardList cardsA = new CardList(cardListA);
        Move fullHouseMoveA = new FullHouseMove(cardsA);

        assertFalse("Full house should have 3 of a kind and a pair", fullHouseMoveA.isValid());
    }

    @Test
    public void shouldRejectIncorrectUseOfJokers() {
        List<Card> cardListA = Arrays.asList(
                Card.builder().name(TWO).suit(HEARTS).build(),
                Card.builder().name(JOKER_A).suit(HEARTS).build(),
                Card.builder().name(FIVE).suit(DIAMONDS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build()
        );

        List<Card> cardListB = Arrays.asList(
                Card.builder().name(TWO).suit(HEARTS).build(),
                Card.builder().name(JOKER_A).suit(HEARTS).build(),
                Card.builder().name(JOKER_B).suit(DIAMONDS).build(),
                Card.builder().name(FIVE).suit(HEARTS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build()
        );

        CardList cardsA = new CardList(cardListA);
        CardList cardsB = new CardList(cardListB);
        Move fullHouseMoveA = new FullHouseMove(cardsA);
        Move fullHouseMoveB = new FullHouseMove(cardsB);

        assertFalse("Cannot get both 3 of a kind and a pair in this case with one joker", fullHouseMoveA.isValid());
        assertFalse("Cannot get both 3 of a kind and a pair in this case with two jokers", fullHouseMoveB.isValid());
    }
}
