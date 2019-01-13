package models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LandlordHandTest {
    @Test
    public void shouldWithdrawCardFromHand() {
        Deck deck = new Deck();
        LandlordHand hand = new LandlordHand();

        hand.drawFromDeck(deck, 12);
        Card cardToWithdraw = hand.getHandList().get(0);
        hand.withdraw(cardToWithdraw);

        assertEquals(11, hand.size());
        assertTrue(!hand.getHandList().contains(cardToWithdraw));
    }

    @Test
    public void shouldRemoveCardsFromDeckForDifferentHands() {
        Deck deck = new Deck();

        LandlordHand handA = new LandlordHand();
        LandlordHand handB = new LandlordHand();
        LandlordHand handC = new LandlordHand();
        LandlordHand handD = new LandlordHand();

        handA.drawFromDeck(deck, 12);
        handB.drawFromDeck(deck, 12);
        handC.drawFromDeck(deck, 12);
        handD.drawFromDeck(deck, 12);

        handA.drawFromDeck(deck, 6);

        assertEquals(18, handA.size());
        assertEquals(0, deck.size());
    }
}