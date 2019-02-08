package moves;

import models.Card;
import models.CardList;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static models.CardName.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ConsecutivePairsMoveTest {
    @Test
    public void shouldAcceptValidMove() {
        List<Card> normalCardList = Arrays.asList(
                Card.builder().name(JACK).build(),
                Card.builder().name(JACK).build(),
                Card.builder().name(TEN).build(),
                Card.builder().name(TEN).build(),
                Card.builder().name(NINE).build(),
                Card.builder().name(NINE).build()
        );

        List<Card> cardListWithOneJoker = Arrays.asList(
                Card.builder().name(JACK).build(),
                Card.builder().name(JACK).build(),
                Card.builder().name(TEN).build(),
                Card.builder().name(TEN).build(),
                Card.builder().name(JOKER_A).build(),
                Card.builder().name(NINE).build()
        );

        List<Card> cardListWithBothJokersA = Arrays.asList(
                Card.builder().name(JACK).build(),
                Card.builder().name(JOKER_B).build(),
                Card.builder().name(TEN).build(),
                Card.builder().name(TEN).build(),
                Card.builder().name(JOKER_A).build(),
                Card.builder().name(NINE).build()
        );

        List<Card> cardListWithBothJokersB = Arrays.asList(
                Card.builder().name(JOKER_B).build(),
                Card.builder().name(JOKER_A).build(),
                Card.builder().name(TEN).build(),
                Card.builder().name(TEN).build(),
                Card.builder().name(NINE).build(),
                Card.builder().name(NINE).build()
        );

        List<Card> cardListWithBothJokersC = Arrays.asList(
                Card.builder().name(JOKER_B).build(),
                Card.builder().name(JOKER_A).build(),
                Card.builder().name(JACK).build(),
                Card.builder().name(JACK).build(),
                Card.builder().name(NINE).build(),
                Card.builder().name(NINE).build()
        );

        Move normalMove = new ConsecutivePairsMove(new CardList(normalCardList));
        Move moveWithJoker = new ConsecutivePairsMove(new CardList(cardListWithOneJoker));
        Move moveWithBothJokersA = new ConsecutivePairsMove(new CardList(cardListWithBothJokersA));
        Move moveWithBothJokersB = new ConsecutivePairsMove(new CardList(cardListWithBothJokersB));
        Move moveWithBothJokersC = new ConsecutivePairsMove(new CardList(cardListWithBothJokersC));

        assertTrue(normalMove.isValid());
        assertTrue(moveWithJoker.isValid());
        assertTrue(moveWithBothJokersA.isValid());
        assertTrue(moveWithBothJokersB.isValid());
        assertTrue(moveWithBothJokersC.isValid());
    }

    @Test
    public void shouldRejectInvalidMove() {
        List<Card> wrongNumberOfCards = Arrays.asList(
                Card.builder().name(JOKER_A).build(),
                Card.builder().name(TEN).build(),
                Card.builder().name(TEN).build(),
                Card.builder().name(NINE).build(),
                Card.builder().name(NINE).build()
        );

        List<Card> notEnoughPairs = Arrays.asList(
                Card.builder().name(EIGHT).build(),
                Card.builder().name(EIGHT).build(),
                Card.builder().name(SEVEN).build(),
                Card.builder().name(TEN).build(),
                Card.builder().name(NINE).build(),
                Card.builder().name(NINE).build()
        );

        List<Card> pairsNotConsecutive = Arrays.asList(
                Card.builder().name(QUEEN).build(),
                Card.builder().name(QUEEN).build(),
                Card.builder().name(TEN).build(),
                Card.builder().name(TEN).build(),
                Card.builder().name(NINE).build(),
                Card.builder().name(NINE).build()
        );

        Move wrongNumberOfCardsMove = new ConsecutivePairsMove(new CardList(wrongNumberOfCards));
        Move notEnoughPairsMove = new ConsecutivePairsMove(new CardList(notEnoughPairs));
        Move pairsNotConsecutiveMove = new ConsecutivePairsMove(new CardList(pairsNotConsecutive));

        assertFalse(wrongNumberOfCardsMove.isValid());
        assertFalse(notEnoughPairsMove.isValid());
        assertFalse(pairsNotConsecutiveMove.isValid());
    }

    @Test
    public void shouldBeatHand() {

    }
}
