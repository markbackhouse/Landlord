package moves;

import lombok.Getter;
import models.CardList;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
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

        Map<Integer, Long> valueFrequencies = new CardList(cards.getCards().stream()
                .filter(c -> !c.isJoker())
                .collect(Collectors.toList()))
                .getFrequencies();

        boolean containsPair = valueFrequencies.containsValue(2L);
        boolean containsThreeOfAKind = valueFrequencies.containsValue(3L);

        if (cards.getJokerCount() == 0) {
            return containsThreeOfAKind && containsPair;
        } else if (cards.getJokerCount() == 1) {
            boolean containsTwoPairs = valueFrequencies.values().stream()
                    .filter(c -> c == 2L)
                    .count() == 2;
            // Two pairs with a joker is a valid full house
            // One three of a kind, a single card and a joker is a valid full house
            return containsTwoPairs || containsThreeOfAKind;
        } else if (cards.getJokerCount() == 2) {
            // Two jokers can replace the missing values only in these cases
            return containsPair || containsThreeOfAKind;
        }
        return false;
    }

    @Override
    public boolean beats(Move o) {
        FullHouseMove other = (FullHouseMove) o;

        return isValid() &&
                getThreeOfAKindValue(cards.getFrequencies()) > other.getThreeOfAKindValue(other.cards.getFrequencies());
    }

    @Override
    public MoveType getType() {
        return MoveType.FULL_HOUSE;
    }

    private Integer getThreeOfAKindValue(Map<Integer, Long> frequencies) {
        Map<Integer, Long> valueFrequencies = new CardList(cards.getCards().stream()
                .filter(c -> !c.isJoker())
                .collect(Collectors.toList()))
                .getFrequencies();
        boolean containsThreeOfAKind = valueFrequencies.containsValue(3L);

        if (containsThreeOfAKind) {
            // Card list contains three of a kind, so don't need to worry about jokers being present
            return Optional.of(frequencies.keySet().stream()
                    .filter(cardValue -> frequencies.get(cardValue) == 3)
                    .findAny().get()
            ).orElse(0);
        }

        if (cards.getJokerCount() > 0) {
            // If there is one joker, and no three of a kind, then there must be two pairs - so joker represents the greatest of these.
            // If there are two jokers, and no three of a kind, then either both jokers can join to the single card (to make 3), or one
            // joker can join to the pair (to make 3).
            // In both cases, the three of a kind value is just the max. card value in the hand.
            return Collections.max(valueFrequencies.keySet());
        }
        return 0;
    }
}
