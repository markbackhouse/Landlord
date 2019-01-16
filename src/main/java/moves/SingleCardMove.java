package moves;

import lombok.Getter;
import models.CardList;

@Getter
public class SingleCardMove implements Move {

    CardList cards;

    public SingleCardMove(CardList cards) {
        this.cards = cards;
    }

    @Override
    public boolean isValid() {
        return cards.size() == 1;
    }

    @Override
    public boolean beats(Move other) {
        return isValid() &&
                cards.get(0).getValue() > ((SingleCardMove) other).getCards().get(0).getValue();
    }
}
