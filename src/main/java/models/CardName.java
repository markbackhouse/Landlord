package models;

import lombok.Getter;

@Getter
public enum CardName {
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13),
    ACE(14),
    TWO(15),
    JOKER_A(16),
    JOKER_B(16);

    private final int value;

    CardName(int value) {
        this.value = value;
    }

    boolean isJoker() {
        return value == 16;
    }
}
