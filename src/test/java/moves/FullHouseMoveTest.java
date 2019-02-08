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

        Move fullHouseMove = new FullHouseMove(new CardList(cardList));

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

        Move fullHouseMoveA = new FullHouseMove(new CardList(cardListA));
        Move fullHouseMoveB = new FullHouseMove(new CardList(cardListB));

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

        Move fullHouseMoveA = new FullHouseMove(new CardList(cardListA));
        Move fullHouseMoveB = new FullHouseMove(new CardList(cardListB));

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

        Move fullHouseMoveA = new FullHouseMove(new CardList(cardListA));

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

        Move fullHouseMoveA = new FullHouseMove(new CardList(cardListA));

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

        Move fullHouseMoveA = new FullHouseMove(new CardList(cardListA));
        Move fullHouseMoveB = new FullHouseMove(new CardList(cardListB));

        assertFalse("Cannot get both 3 of a kind and a pair in this case with one joker", fullHouseMoveA.isValid());
        assertFalse("Cannot get both 3 of a kind and a pair in this case with two jokers", fullHouseMoveB.isValid());
    }

    @Test
    public void shouldBeatOtherHand() {
        List<Card> thisListNoJokers = Arrays.asList(
                Card.builder().name(TWO).suit(HEARTS).build(),
                Card.builder().name(TWO).suit(CLUBS).build(),
                Card.builder().name(TWO).suit(DIAMONDS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build(),
                Card.builder().name(FOUR).suit(SPADES).build()
        );

        List<Card> thisListOneJoker = Arrays.asList(
                Card.builder().name(TWO).suit(HEARTS).build(),
                Card.builder().name(TWO).suit(CLUBS).build(),
                Card.builder().name(JOKER_A).suit(DIAMONDS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build(),
                Card.builder().name(FOUR).suit(SPADES).build()
        );

        List<Card> thisListTwoJokers = Arrays.asList(
                Card.builder().name(TWO).suit(HEARTS).build(),
                Card.builder().name(JOKER_A).suit(CLUBS).build(),
                Card.builder().name(JOKER_B).suit(DIAMONDS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build(),
                Card.builder().name(FOUR).suit(SPADES).build()
        );

        List<Card> otherList = Arrays.asList(
                Card.builder().name(FIVE).suit(HEARTS).build(),
                Card.builder().name(FIVE).suit(SPADES).build(),
                Card.builder().name(FIVE).suit(DIAMONDS).build(),
                Card.builder().name(SEVEN).suit(CLUBS).build(),
                Card.builder().name(SEVEN).suit(HEARTS).build()
        );

        Move thisMoveNoJokers = new FullHouseMove(new CardList(thisListNoJokers));
        Move thisMoveOneJoker = new FullHouseMove(new CardList(thisListOneJoker));
        Move thisMoveTwoJokers = new FullHouseMove(new CardList(thisListTwoJokers));

        Move otherMove = new FullHouseMove(new CardList(otherList));

        assertTrue(thisMoveNoJokers.beats(otherMove));
        assertTrue(thisMoveOneJoker.beats(otherMove));
        assertTrue(thisMoveTwoJokers.beats(otherMove));
    }

    @Test
    public void shouldNotBeatOtherHand() {
        List<Card> thisListNoJokers = Arrays.asList(
                Card.builder().name(FOUR).suit(HEARTS).build(),
                Card.builder().name(FOUR).suit(CLUBS).build(),
                Card.builder().name(FOUR).suit(DIAMONDS).build(),
                Card.builder().name(KING).suit(HEARTS).build(),
                Card.builder().name(KING).suit(SPADES).build()
        );

        List<Card> thisListOneJoker = Arrays.asList(
                Card.builder().name(THREE).suit(HEARTS).build(),
                Card.builder().name(THREE).suit(CLUBS).build(),
                Card.builder().name(JOKER_A).suit(DIAMONDS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build(),
                Card.builder().name(FOUR).suit(SPADES).build()
        );

        List<Card> thisListTwoJokers = Arrays.asList(
                Card.builder().name(THREE).suit(HEARTS).build(),
                Card.builder().name(JOKER_A).suit(CLUBS).build(),
                Card.builder().name(JOKER_B).suit(DIAMONDS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build(),
                Card.builder().name(FOUR).suit(SPADES).build()
        );

        List<Card> otherList = Arrays.asList(
                Card.builder().name(FIVE).suit(HEARTS).build(),
                Card.builder().name(FIVE).suit(SPADES).build(),
                Card.builder().name(FIVE).suit(DIAMONDS).build(),
                Card.builder().name(SEVEN).suit(CLUBS).build(),
                Card.builder().name(SEVEN).suit(HEARTS).build()
        );

        Move thisMoveNoJokers = new FullHouseMove(new CardList(thisListNoJokers));
        Move thisMoveOneJoker = new FullHouseMove(new CardList(thisListOneJoker));
        Move thisMoveTwoJokers = new FullHouseMove(new CardList(thisListTwoJokers));

        Move otherMove = new FullHouseMove(new CardList(otherList));

        assertFalse(thisMoveNoJokers.beats(otherMove));
        assertFalse(thisMoveOneJoker.beats(otherMove));
        assertFalse(thisMoveTwoJokers.beats(otherMove));
    }
}
