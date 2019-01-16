package models;

import moves.Move;

import java.util.List;

public interface Hand {
    void drawFromDeck(Deck deck, int numberToDraw);

    Move playMove(List<Card> cardsToPlay, Move move, Move previousMove);

    Card withdraw(Card card);

    List<Card> withdraw(Card... cards);

    int size();

    boolean isEmpty();
}
