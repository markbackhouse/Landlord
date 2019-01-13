package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    CardName name;
    Suit suit;

    public boolean isJoker() {
        return name.equals(CardName.JOKER_A) || name.equals(CardName.JOKER_B);
    }

    public boolean beats(Card other) {
        return getValue() > other.getValue();
    }

    public int getValue() {
        return name.getValue();
    }

    public boolean hasEqualValue(Card other) {
        return getValue() == other.getValue() ||
                this.isJoker() || other.isJoker();
    }
}
