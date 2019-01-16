package moves;

import lombok.Getter;
import models.Card;
import models.CardList;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StraightMove implements Move {
    private static final int MIN_STRAIGHT_LENGTH = 5;

    CardList cards;

    public StraightMove(CardList cards) {
        this.cards = cards;
    }

    @Override
    public boolean isValid() {
        return cards.size() >= MIN_STRAIGHT_LENGTH && cardListIsConsecutive();
    }

    private boolean cardListIsConsecutive() {
        cards.sort();

        int jokersThatCanBeUsed = cards.getJokerCount();
        int runningValue = getLowestCardValue();

        List<Card> cardsWithoutJokers = removeJokers();
        for (Card card : cardsWithoutJokers) {
            if (card.getValue() != runningValue) {
                if (jokersThatCanBeUsed > 0) {
                    jokersThatCanBeUsed -= 1;
                } else {
                    return false;
                }
            } else {
                runningValue += 1;
            }
        }
        return true;
    }

    private List<Card> removeJokers() {
        return cards.getCards().stream()
                .filter(c -> !c.isJoker())
                .collect(Collectors.toList());
    }

    @Override
    public boolean beats(Move o) {
        StraightMove other = (StraightMove) o;
        return isValid()
                && cards.size() >= other.getCards().size()
                && getLowestCardValue() + cards.size() > other.getLowestCardValue() + other.cards.size(); // So that Joker values don't influence highest value
    }

    int getLowestCardValue() {
        cards.sort();
        return cards.get(0).getValue();
    }
}
