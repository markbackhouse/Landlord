package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private List<Card> listOfCards;

    public Deck() {
        listOfCards = construct();
    }

    private List<Card> construct() {
        List<Card> deck = new ArrayList<>();

        for (CardName name : CardName.values()) {
            if (name.isJoker()) {
                deck.add(Card.builder()
                        .name(name)
                        .build());
            } else {
                for (Suit suit : Suit.values()) {
                    deck.add(Card.builder()
                            .name(name)
                            .suit(suit)
                            .build());
                }
            }
        }
        return deck;
    }

    List<Card> dealCards(int numberOfCards) {
        this.shuffle();
        List<Card> cardsToDeal = new ArrayList<>(numberOfCards);

        for (int i = 0; i < numberOfCards; i += 1) {
            if (!this.isEmpty()) {
                Card cardToWithdraw = withdrawFirstCard();
                listOfCards.remove(0);
                cardsToDeal.add(cardToWithdraw);
            }
        }
        return cardsToDeal;
    }

    private Card withdrawFirstCard() {
        try {
            return listOfCards.get(0);
        } catch (AssertionError e) {
            System.out.println("Not enough cards in deck");
            return null;
        }
    }

    public void shuffle() {
        Collections.shuffle(listOfCards);
    }

    // For tests
    Card getCard(CardName name, Suit suit) {
        try {
            if (name.equals(CardName.JOKER_A) || name.equals(CardName.JOKER_B)) {
                return listOfCards.stream()
                        .filter(c -> c.name.equals(name))
                        .collect(Collectors.toList())
                        .get(0);
            } else {
                return listOfCards.stream()
                        .filter(c -> c.name.equals(name) && c.suit.equals(suit))
                        .collect(Collectors.toList())
                        .get(0);
            }
        } catch (AssertionError e) {
            System.out.printf("Card not in deck: {}", e);
            return null;
        }
    }

    public int size() {
        return listOfCards.size();
    }

    public boolean isEmpty() {
        return listOfCards.isEmpty();
    }
}
