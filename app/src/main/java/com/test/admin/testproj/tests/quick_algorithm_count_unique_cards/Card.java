package com.test.admin.testproj.tests.quick_algorithm_count_unique_cards;


import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Task:
 * We have a 3rd party library class Card, that we cannot modify.
 public final class Card {
 public byte[] code;
 public String name;
 public String address;
 }
 Please, create an utility method
 public static int countUniqueCards(Collection<Card> cards)
 that counts unique cards. We assume two cards to be not unique, if they have both equal code and name (address may be different).
 For example, if we have a collection of
 {code={23, -45, 18}, name="aaa", address="any1"},
 {code={10, 97}, name="bbb", address="any2"},
 {code={23, -45, 18}, name="aaa", address="any3"},
 {code={13, -40}, name="ccc", address="any4"},
 {code={14, 55}, name="ddd", address="any5"},
 {code={23, -45, 18}, name="aaa", address="any6"},
 {code={10, 97}, name="bbb", address="any7"}
 then countUniqueCards must return 4.
 The algorithm must be more effective than a full scan O(N*N). For example, it must be ok to have an input cards collection of size 100,000.
 */
public final class Card {
    public byte[] code;
    public String name;
    public String address;

    public static class CardWrapper implements Comparable<CardWrapper> {
        public final Card card;

        public CardWrapper(Card card) {
            this.card = card;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            CardWrapper cardWrapper = (CardWrapper) o;
            return Arrays.equals(card.code, cardWrapper.card.code) && card.name.equals(cardWrapper.card.name);
        }

        @Override
        public int hashCode() {
            int result = Arrays.hashCode(card.code);
            result = 31 * result + card.name.hashCode();
            return result;
        }

        @Override
        public int compareTo(CardWrapper other) {
            if (Arrays.equals(card.code, other.card.code) && card.name.equals(other.card.name)) {
                return 0;
            }
            if (card.name.compareTo(other.card.name) != 0) {
                return card.name.compareTo(other.card.name);
            }
            if (card.code.length != other.card.code.length) {
                return card.code.length - other.card.code.length;
            }
            for (int i = 0; i < card.code.length; i++) {
                if (card.code[i] != other.card.code[i]) {
                    return card.code[i] - other.card.code[i];
                }
            }
            return -1;
        }
    }

    public static int countUniqueCards_1(Collection<Card> cards) {
        HashSet<CardWrapper> cardWrappers = new HashSet<>();
        for (Card card : cards) {
            cardWrappers.add(new CardWrapper(card));
        }
        return cardWrappers.size();
    }

    public static int countUniqueCards_2(Collection<Card> cards) {
        TreeSet<CardWrapper> cardWrappers = new TreeSet<>();
        for (Card card : cards) {
            cardWrappers.add(new CardWrapper(card));
        }
        return cardWrappers.size();
    }
}
