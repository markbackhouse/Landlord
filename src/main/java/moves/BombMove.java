package moves;

import models.CardList;

import java.util.Map;
import java.util.stream.Collectors;

public class BombMove implements Move {
    private CardList cards;

    public BombMove(CardList cards) {
        this.cards = cards;
    }

    @Override
    public boolean isValid() {
        if (isJokerBomb()) {
            return true;
        }

        Map<Integer, Long> valueFrequencies = new CardList(cards.getCards().stream()
                .filter(c -> !c.isJoker())
                .collect(Collectors.toList()))
                .getFrequencies();

        return cards.size() == 4 && (
                valueFrequencies.containsValue(4)
                        || valueFrequencies.containsValue(3) && cards.getJokerCount() == 1
                        || valueFrequencies.containsValue(2) && cards.getJokerCount() == 2
        );
    }

    @Override
    public boolean beats(Move other) {
        return true;
    }

    @Override
    public MoveType getType() {
        return MoveType.BOMB;
    }

    private boolean isJokerBomb() {
        return cards.size() == 2
                && cards.containsBothJokers();
    }
}
