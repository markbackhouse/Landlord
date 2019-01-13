package models.moves;

import lombok.Getter;
import models.CardList;

import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class FullHouseMove implements Move {

    private CardList cards;

    public FullHouseMove(CardList cards) {
        this.cards = cards;
    }

    @Override
    public boolean isValid() {
        if (cards.size() != 5) {
            return false;
        }
        Map<Integer, Long> valueFrequencies;

        if (cards.getJokerCount() == 0) {
            valueFrequencies = cards.getFrequencies();
            return valueFrequencies.containsValue(3L) && valueFrequencies.containsValue(2L);
        } else if (cards.getJokerCount() == 1) {
            valueFrequencies = new CardList(cards.getCards().stream()
                    .filter(c -> !c.isJoker())
                    .collect(Collectors.toList()))
                    .getFrequencies();
            boolean containsTwoPairs = valueFrequencies.values().stream()
                    .filter(c -> c == 2L)
                    .count() == 2;
            boolean containsThreeOfAKind = valueFrequencies.containsValue(3L);

            if (containsTwoPairs || containsThreeOfAKind) {
                // Two pairs with a joker is a valid full house
                // One three of a kind, a single card and a joker is a valid full house
                return true;
            }
        } else if (cards.getJokerCount() == 2) {
            valueFrequencies = new CardList(cards.getCards().stream()
                    .filter(c -> !c.isJoker())
                    .collect(Collectors.toList()))
                    .getFrequencies();

            if (valueFrequencies.containsValue(2L) || valueFrequencies.containsValue(3L)) {
                // Two jokers can replace the missing values only in these cases
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean beats(Move o) {
        FullHouseMove other = (FullHouseMove) o;
        return isValid() &&
                getThreeOfAKindValue(cards.getFrequencies()) > other.getThreeOfAKindValue(other.cards.getFrequencies());
    }

    private Integer getThreeOfAKindValue(Map<Integer, Long> frequencies) {
        return frequencies.keySet().stream()
                .filter(cardValue -> frequencies.get(cardValue) == 3)
                .findAny().get();
    }
}
