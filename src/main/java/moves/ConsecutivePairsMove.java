package moves;

import models.CardList;

import java.util.List;
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

        long numberOfPairsIgnoringJokers = valueFrequencies.values().stream()
                .filter(c -> c == 2)
                .count();

        return containsThreePairs(numberOfPairsIgnoringJokers) && pairsAreConsecutive(valueFrequencies);
    }

    private boolean pairsAreConsecutive(Map<Integer, Long> valueFrequencies) {
        List<Integer> cardValues = valueFrequencies.keySet().stream()
                .sorted()
                .collect(Collectors.toList());

        int jokerCount = cards.getJokerCount();

        int runningCardValue = cardValues.get(0);
        for (Integer cardValue : cardValues) {
            boolean onePairMissing = cardValue == runningCardValue + 1;

            if (cardValue == runningCardValue) {
                runningCardValue += 1;
            } else if (jokerCount == 2 && onePairMissing) {
                jokerCount = 0;
                runningCardValue += 2;
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean beats(Move o) {
        ConsecutivePairsMove other = (ConsecutivePairsMove) o;
        return isValid()
                && getHighestCardValue() > other.getHighestCardValue();
    }

    private boolean containsThreePairs(long numberOfPairsIgnoringJokers) {
        if (cards.getJokerCount() == 1) {
            // Four of the remaining five cards must be in pairs for the joker to represent the fifth
            return numberOfPairsIgnoringJokers == 2L;
        } else if (cards.getJokerCount() == 2) {
            // As long as there is a pair in the remaining four cards, the jokers can represent the remainder
            return numberOfPairsIgnoringJokers != 0L;
        }
        return numberOfPairsIgnoringJokers == 3L;
    }

    private int getHighestCardValue() {
        cards.sort();
        return 0;
    }
}
