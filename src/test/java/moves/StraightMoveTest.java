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

public class StraightMoveTest {

    @Test
    public void shouldAllowValidMove() {
        List<Card> cardList = Arrays.asList(
                Card.builder().name(THREE).suit(HEARTS).build(),
                Card.builder().name(SIX).suit(HEARTS).build(),
                Card.builder().name(FIVE).suit(DIAMONDS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build(),
                Card.builder().name(SEVEN).suit(SPADES).build()
        );

        Move straightMove = new StraightMove(new CardList(cardList));

        assertTrue(straightMove.isValid());
    }

    @Test
    public void shouldAllowValidMoveWithJokers() {
        List<Card> cardListA = Arrays.asList(
                Card.builder().name(JACK).suit(HEARTS).build(),
                Card.builder().name(TEN).suit(HEARTS).build(),
                Card.builder().name(JOKER_A).suit(DIAMONDS).build(),
                Card.builder().name(QUEEN).suit(HEARTS).build(),
                Card.builder().name(ACE).suit(SPADES).build()
        );

        List<Card> cardListB = Arrays.asList(
                Card.builder().name(JACK).suit(HEARTS).build(),
                Card.builder().name(TEN).suit(HEARTS).build(),
                Card.builder().name(JOKER_A).suit(DIAMONDS).build(),
                Card.builder().name(JOKER_B).suit(HEARTS).build(),
                Card.builder().name(ACE).suit(SPADES).build()
        );

        List<Card> cardListC = Arrays.asList(
                Card.builder().name(JACK).suit(HEARTS).build(),
                Card.builder().name(JOKER_B).suit(HEARTS).build(),
                Card.builder().name(JOKER_A).suit(DIAMONDS).build(),
                Card.builder().name(QUEEN).suit(HEARTS).build(),
                Card.builder().name(ACE).suit(SPADES).build()
        );

        Move straightMoveA = new StraightMove(new CardList(cardListA));
        Move straightMoveB = new StraightMove(new CardList(cardListB));
        Move straightMoveC = new StraightMove(new CardList(cardListC));

        assertTrue("Straight with one card missing and Joker is valid", straightMoveA.isValid());
        assertTrue("Straight with two consecutive cards missing and two Jokers is valid", straightMoveB.isValid());
        assertTrue("Straight with two non-consecutive cards missing and two Jokers is valid", straightMoveC.isValid());
    }


    @Test
    public void shouldRejectShortStraight() {
        List<Card> cardList = Arrays.asList(
                Card.builder().name(THREE).suit(HEARTS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build(),
                Card.builder().name(FIVE).suit(DIAMONDS).build(),
                Card.builder().name(SIX).suit(HEARTS).build()
        );

        Move straightMove = new StraightMove(new CardList(cardList));

        assertFalse(straightMove.isValid());
    }

    @Test
    public void shouldRejectStraightWithDuplicates() {
        List<Card> cardList = Arrays.asList(
                Card.builder().name(THREE).suit(HEARTS).build(),
                Card.builder().name(FIVE).suit(CLUBS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build(),
                Card.builder().name(FIVE).suit(DIAMONDS).build(),
                Card.builder().name(SIX).suit(HEARTS).build()
        );

        Move straightMove = new StraightMove(new CardList(cardList));

        assertFalse(straightMove.isValid());
    }

    @Test
    public void shouldRejectStraightWithMissingValues() {
        List<Card> cardList = Arrays.asList(
                Card.builder().name(THREE).suit(HEARTS).build(),
                Card.builder().name(FOUR).suit(HEARTS).build(),
                Card.builder().name(FIVE).suit(DIAMONDS).build(),
                Card.builder().name(SIX).suit(HEARTS).build(),
                Card.builder().name(EIGHT).suit(HEARTS).build(),
                Card.builder().name(NINE).suit(HEARTS).build()
        );

        CardList cards = new CardList(cardList);
        Move straightMove = new StraightMove(cards);

        assertFalse(straightMove.isValid());
    }

    @Test
    public void shouldBeatOtherMoveWithMoreCards() {

    }

    @Test
    public void shouldBeatOtherMoveUsingJokers() {

    }

    @Test
    public void shouldNotBeatOtherMoveShortStraight() {

    }

    @Test
    public void shouldNotBeatOtherMoveHighCard() {

    }
}
