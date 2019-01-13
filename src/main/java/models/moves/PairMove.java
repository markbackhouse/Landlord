package models.moves;

import lombok.Getter;
import models.CardList;

@Getter
public class PairMove implements Move {
    CardList cards;

    public PairMove(CardList cards) {
        this.cards = cards;
    }

    @Override
    public boolean isValid() {
        return cards.size() == 2 &&
                (cards.containsJoker() || cards.get(0).hasEqualValue(cards.get(1)));
    }

    @Override
    public boolean beats(Move other) {
        if (cards.containsBothJokers()) { return true; }

        return isValid() &&
                getNonJokerValue() > ((PairMove) other).getNonJokerValue();
    }

    private int getNonJokerValue() {
        if (cards.containsJoker()) {
            return cards.getCards().stream()
                    .filter(card -> !card.isJoker())
                    .findFirst().get()
                    .getValue();
        }
        return cards.get(0).getValue();
    }
}
