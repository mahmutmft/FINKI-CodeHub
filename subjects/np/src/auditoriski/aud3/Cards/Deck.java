package auditoriski.aud3.Cards;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Deck {
    private PlayingCard[] cards;
    private boolean[] isDealt;
    private int dealtTotal;

    public Deck() {
        cards = new PlayingCard[52];
        isDealt = new boolean[52];
        dealtTotal = 0;
        for (int i = 0; i < PlayingCardType.values().length; i++) {
            for (int j = 0; j < 13; j++) {
                cards[i * 13 + j] = new PlayingCard(j + 2, PlayingCardType.values()[i]);
            }
        }
    }

    public PlayingCard[] getCards() {
        return cards;
    }

    public void setCards(PlayingCard[] cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (PlayingCard card : cards) {
            stringBuilder.append(card.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck = (Deck) o;
        return Objects.deepEquals(cards, deck.cards);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(cards);
    }

    public boolean cardsLeft() {
        return (cards.length - dealtTotal) > 0;
    }

    public PlayingCard[] shuffle() {
        List<PlayingCard> playingCards = Arrays.asList(cards);
        Collections.shuffle(playingCards);
        return cards;
    }

    public PlayingCard dealCard() {
        if (!cardsLeft()) return null;

        int card = (int) (Math.random() * 52);

        if (!isDealt[card]) {
            isDealt[card] = true;
            dealtTotal++;
            return cards[card];
        }
        return dealCard();
    }
    public void dealCardSecondWay(){
        shuffle();
        for (PlayingCard card : cards)
            System.out.println(card);
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        System.out.println(deck);

        deck.shuffle();
        System.out.println(deck);

        PlayingCard cards;
        while ((cards = deck.dealCard()) != null){
            System.out.println(cards);
        }

        System.out.println();
        Deck deck2 = new Deck();
        System.out.println(deck2);

        deck2.dealCardSecondWay();

    }
}
