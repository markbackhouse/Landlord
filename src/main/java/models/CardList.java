package models;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Setter
@Getter
public class CardList {
    List<Card> cards;

    public CardList(List<Card> cards) {
        this.cards = cards;
    }

    public Card get(int index) {
        return cards.get(index);
    }

    public int size() {
        return cards.size();
    }

    public Map<Integer, Long> getFrequencies() {
        return cards.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));
    }

    public boolean containsBothJokers() {
        return cards.stream()
                .filter(Card::isJoker)
                .count() == 2;
    }

    public int getJokerCount() {
        return (int) cards.stream()
                .filter(Card::isJoker)
                .count();
    }

    public boolean containsJoker() {
        return cards.stream()
                .anyMatch(Card::isJoker);
    }

    public void sort() {
        Comparator<Card> comparator = new CardsComparer();
        Collections.sort(cards, comparator);
    }

    protected class CardsComparer implements Comparator<Card> {

        @Override
        public int compare(Card o1, Card o2) {
            return compare(o1.getValue(), o2.getValue());
        }

        int compare(int a, int b) {
            return a - b;
        }
    }
}
