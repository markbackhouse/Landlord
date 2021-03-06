package moves;

import lombok.Getter;
import models.Card;
import models.CardList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StraightMove implements Move {
    private Logger log = LoggerFactory.getLogger(StraightMove.class);
    private static final int MIN_STRAIGHT_LENGTH = 5;

    CardList cards;

    public StraightMove(CardList cards) {
        this.cards = cards;
    }

    @Override
    public boolean isValid() {
        return hasRequiredLength(MIN_STRAIGHT_LENGTH) && isConsecutive();
    }

    @Override
    public boolean beats(Move o) {
        StraightMove other = (StraightMove) o;
        return isValid()
                && hasRequiredLength(other.getCards().size())
                && getLowestCardValue() + cards.size() > other.getLowestCardValue() + other.cards.size(); // So that Joker values don't influence highest value
    }

    @Override
    public MoveType getType() {
        return MoveType.STRAIGHT;
    }

    private boolean hasRequiredLength(int minLength) {
        if (cards.size() < minLength) {
            log.warn("Straight is not long enough");
            return false;
        }
        return true;
    }

    private boolean isConsecutive() {
        cards.sort();

        int jokersThatCanBeUsed = cards.getJokerCount();
        int runningValue = getLowestCardValue();

        List<Card> cardsWithoutJokers = removeJokers();
        for (Card card : cardsWithoutJokers) {
            if (card.getValue() != runningValue) {
                boolean hasAJoker = jokersThatCanBeUsed > 0;
                boolean hasBothJokers = jokersThatCanBeUsed == 2;
                boolean oneCardMissing = card.getValue() == runningValue + 1;
                boolean twoConsecutiveCardsMissing = card.getValue() == runningValue + 2;

                if (hasAJoker && oneCardMissing) {
                    jokersThatCanBeUsed -= 1;
                    runningValue += 2;
                } else if (hasBothJokers && twoConsecutiveCardsMissing) {
                    jokersThatCanBeUsed -= 2;
                    runningValue += 3;
                } else {
                    log.warn("Straight does not contain consecutive values");
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

    int getLowestCardValue() {
        cards.sort();
        return cards.get(0).getValue();
    }
}
