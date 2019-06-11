package models;

import moves.Move;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LandlordHand implements Hand {
    private Logger log = LoggerFactory.getLogger(LandlordHand.class);
    List<Card> hand;

    public LandlordHand() {
        hand = new ArrayList<>();
    }

    @Override
    public void drawFromDeck(Deck deck, int numberToDraw) {
        List<Card> cardsToDeal = deck.dealCards(numberToDraw);
        if (hand.isEmpty()) {
            hand = cardsToDeal;
        } else {
            hand.addAll(cardsToDeal);
        }
    }

    @Override
    public Move playMove(List<Card> cardsToPlay, Move move, Move previousMove) {
        if (move.isValid() && move.beats(previousMove)) {
            return move;
        } else {
            log.warn("Move invalid or does not beat previous move.");
            return null;
        }
    }

    @Override
    public Card withdraw(Card card) {
        Card cardToWithdraw = hand.get(findCardIndex(card));
        hand.remove(card);
        return cardToWithdraw;
    }

    @Override
    public List<Card> withdraw(Card... cardsToWithDraw) {
        List<Card> cardsList = new ArrayList<>();
        for (Card card : cardsToWithDraw) {
            if (hand.contains(card)) {
                cardsList.add(card);
            } else {
                throw new AssertionError("Card not in hand.");
            }
        }
        return cardsList;
    }

    @Override
    public int size() {
        return hand.size();
    }

    @Override
    public boolean isEmpty() {
        return hand.isEmpty();
    }

    int findCardIndex(Card cardToFind) {
        try {
            return hand.indexOf(cardToFind);
        } catch (AssertionError e) {
            log.warn("Card not in hand", e);
            return -1;
        }
    }

    // For tests
    List<Card> getHandList() {
        return hand;
    }
}
