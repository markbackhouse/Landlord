package moves;

import models.CardList;

import java.util.Map;
import java.util.stream.Collectors;

public class ConsecutivePairsMove implements Move {
    private CardList cards;

    public ConsecutivePairsMove(CardList cards) {
        this.cards = cards;
    }

    @Override
    public boolean isValid() {
        if (cards.size() != 6) {
            return false;
        }

        cards.sort();
        Map<Integer, Long> valueFrequencies = new CardList(cards.getCards().stream()
                .filter(c -> !c.isJoker())
                .collect(Collectors.toList()))
                .getFrequencies();
        long numberOfPairs = valueFrequencies.values().stream()
                .filter(c -> c == 2)
                .count();

        if (cards.getJokerCount() == 1) {
            // Four of the remaining five cards must be in pairs for the joker to represent the fifth
            return numberOfPairs == 2L;
        } else if (cards.getJokerCount() == 2) {
            // As long as there is a pair in the remaining four cards, the jokers can represent the remainder
            return numberOfPairs != 0L;
        }
        return numberOfPairs == 3L;
    }

    @Override
    public boolean beats(Move o) {
        ConsecutivePairsMove other = (ConsecutivePairsMove) o;
        return isValid()
                && getHighestCardValue() > other.getHighestCardValue();
    }

    private int getHighestCardValue() {
        cards.sort();
        return 0;
    }
}
