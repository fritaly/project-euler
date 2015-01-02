package com.github.fritaly.projecteuler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Problem "Poker hands".
 *
 * @author francois_ritaly
 */
public class Problem54 {

	private static enum Color {
		HEARTS, CLUBS, SPADES, DIAMONDS;

		public static Color getColor(char c) {
			switch (c) {
			case 'H':
				return HEARTS;
			case 'C':
				return CLUBS;
			case 'S':
				return SPADES;
			case 'D':
				return DIAMONDS;

			default:
				throw new IllegalArgumentException("Unsupported value: '" + c + "'");
			}
		}
	}

	private static enum Value {
		TWO('2'), THREE('3'), FOUR('4'), FIVE('5'), SIX('6'), SEVEN('7'), EIGHT('8'), NINE('9'), TEN('T'), JACK('J'), QUEEN('Q'), KING(
				'K'), ACE('A');

		final char id;

		private Value(char id) {
			this.id = id;
		}

		public static Value getValue(char c) {
			for (Value value : values()) {
				if (value.id == c) {
					return value;
				}
			}

			throw new IllegalArgumentException("Unsupported value: '" + c + "'");
		}
	}

	private static class Card implements Comparable<Card> {
		final Color color;

		final Value value;

		public Card(String id) {
			this.color = Color.getColor(id.charAt(id.length() - 1));
			this.value = Value.getValue(id.charAt(0));
		}

		@Override
		public int compareTo(Card other) {
			return this.value.compareTo(other.value);
		}

		@Override
		public String toString() {
			return String.format("%s%s", value.id, color.name().charAt(0));
		}
	}

	private static class Pair implements Comparable<Pair> {
		final Card c1, c2;

		public Pair(Card c1, Card c2) {
			if (c1.value != c2.value) {
				throw new IllegalArgumentException(String.format("The 2 given cards have different values: %s & %s", c1, c2));
			}

			this.c1 = c1;
			this.c2 = c2;
		}

		@Override
		public String toString() {
			return String.format("%s+%s", c1, c2);
		}

		Value getValue() {
			return c1.value;
		}

		@Override
		public int compareTo(Pair other) {
			return this.c1.value.compareTo(other.c1.value);
		}
	}

	private static class DoublePair implements Comparable<DoublePair> {
		final Pair p1, p2;

		public DoublePair(Pair p1, Pair p2) {
			this.p1 = p1;
			this.p2 = p2;
		}

		Pair getHighestPair() {
			return (p1.getValue().compareTo(p2.getValue()) > 0) ? p1 : p2;
		}

		Pair getLowestPair() {
			return (p1.getValue().compareTo(p2.getValue()) < 0) ? p1 : p2;
		}

		@Override
		public String toString() {
			return String.format("%s+%s", p1, p2);
		}

		@Override
		public int compareTo(DoublePair other) {
			final Pair highestPair1 = this.getHighestPair();
			final Pair highestPair2 = other.getHighestPair();

			if (highestPair1.compareTo(highestPair2) == 0) {
				return this.getLowestPair().compareTo(other.getLowestPair());
			}

			return highestPair1.compareTo(highestPair2);
		}
	}

	private static class ThreeOfKind implements Comparable<ThreeOfKind> {
		final Card c1, c2, c3;

		public ThreeOfKind(Card c1, Card c2, Card c3) {
			if ((c1.value != c2.value) || (c2.value != c3.value)) {
				throw new IllegalArgumentException(String.format("The 3 given cards have different values: %s, %s & %s", c1, c2,
						c3));
			}

			this.c1 = c1;
			this.c2 = c2;
			this.c3 = c3;
		}

		@Override
		public String toString() {
			return String.format("%s+%s+%s", c1, c2, c3);
		}

		@Override
		public int compareTo(ThreeOfKind other) {
			return this.c1.value.compareTo(other.c1.value);
		}
	}

	private static class FourOfKind implements Comparable<FourOfKind> {
		final Card c1, c2, c3, c4;

		public FourOfKind(Card c1, Card c2, Card c3, Card c4) {
			if ((c1.value != c2.value) || (c2.value != c3.value) || (c3.value != c4.value)) {
				throw new IllegalArgumentException(String.format("The 4 given cards have different values: %s, %s, %s & %s", c1, c2, c3, c4));
			}

			this.c1 = c1;
			this.c2 = c2;
			this.c3 = c3;
			this.c4 = c4;
		}

		@Override
		public String toString() {
			return String.format("%s+%s+%s+%s", c1, c2, c3, c4);
		}

		@Override
		public int compareTo(FourOfKind other) {
			return this.c1.value.compareTo(other.c1.value);
		}
	}

	private static class Straight implements Comparable<Straight> {
		final List<Card> cards;

		public Straight(List<Card> cards) {
			if (!consecutiveCards(cards)) {
				throw new IllegalArgumentException("The given cards aren't consecutive: " + cards);
			}

			this.cards = new ArrayList<>(cards);
		}

		Card getHighestCard() {
			return Collections.max(cards);
		}

		@Override
		public String toString() {
			return cards.toString();
		}

		@Override
		public int compareTo(Straight other) {
			return this.getHighestCard().compareTo(other.getHighestCard());
		}
	}

	private static class Flush implements Comparable<Flush> {
		final List<Card> cards;

		public Flush(List<Card> cards) {
			if (!sameColor(cards)) {
				throw new IllegalArgumentException(String.format("The given cards don't have the same color: " + cards));
			}

			this.cards = new ArrayList<>(cards);
		}

		Card getHighestCard() {
			return Collections.max(cards);
		}

		@Override
		public String toString() {
			return cards.toString();
		}

		@Override
		public int compareTo(Flush other) {
			return this.getHighestCard().compareTo(other.getHighestCard());
		}
	}

	private static class StraightFlush implements Comparable<StraightFlush> {
		final List<Card> cards;

		public StraightFlush(List<Card> cards) {
			if (!consecutiveCards(cards)) {
				throw new IllegalArgumentException("The given cards aren't consecutive: " + cards);
			}
			if (!sameColor(cards)) {
				throw new IllegalArgumentException(String.format("The given cards don't have the same color: " + cards));
			}

			this.cards = new ArrayList<>(cards);
		}

		Card getHighestCard() {
			return Collections.max(cards);
		}

		@Override
		public String toString() {
			return cards.toString();
		}

		@Override
		public int compareTo(StraightFlush other) {
			return this.getHighestCard().compareTo(other.getHighestCard());
		}
	}

	private static class RoyaleFlush implements Comparable<RoyaleFlush> {
		final List<Card> cards;

		public RoyaleFlush(List<Card> cards) {
			if (!consecutiveCards(cards)) {
				throw new IllegalArgumentException("The given cards aren't consecutive: " + cards);
			}
			if (!sameColor(cards)) {
				throw new IllegalArgumentException(String.format("The given cards don't have the same color: " + cards));
			}
			if (Collections.max(cards).value != Value.ACE) {
				throw new IllegalArgumentException(String.format("The given cards aren't a royale flush: " + cards));
			}

			this.cards = new ArrayList<>(cards);
		}

		Card getHighestCard() {
			return Collections.max(cards);
		}

		@Override
		public String toString() {
			return cards.toString();
		}

		@Override
		public int compareTo(RoyaleFlush other) {
			return this.getHighestCard().compareTo(other.getHighestCard());
		}
	}

	private static class FullHouse implements Comparable<FullHouse> {
		final ThreeOfKind threeOfKind;

		final Pair pair;

		public FullHouse(ThreeOfKind threeOfKind, Pair pair) {
			this.threeOfKind = threeOfKind;
			this.pair = pair;
		}

		@Override
		public String toString() {
			return String.format("%s+%s", threeOfKind, pair);
		}

		@Override
		public int compareTo(FullHouse other) {
			if (this.threeOfKind.compareTo(other.threeOfKind) == 0) {
				return this.pair.compareTo(other.pair);
			}

			return this.threeOfKind.compareTo(other.threeOfKind);
		}
	}

	private static boolean consecutiveCards(List<Card> cards) {
		final List<Card> list = new ArrayList<>(cards);

		Collections.sort(list, new Comparator<Card>() {
			@Override
			public int compare(Card c1, Card c2) {
				return -1 * c1.value.compareTo(c2.value);
			}
		});

		for (int i = 0; i < list.size() - 1; i++) {
			final Card c1 = list.get(i);
			final Card c2 = list.get(i + 1);

			if (c1.value.ordinal() != c2.value.ordinal() + 1) {
				return false;
			}
		}

		return true;
	}

	private static boolean sameColor(List<Card> cards) {
		for (int i = 0; i < cards.size() - 1; i++) {
			if (cards.get(i).color != cards.get(i + 1).color) {
				return false;
			}
		}

		return true;
	}

	private static class Hand implements Comparable<Hand> {
		final List<Card> cards;

		public Hand(String... data) {
			final List<Card> list = new ArrayList<>();

			for (String card : data) {
				list.add(new Card(card));
			}

			this.cards = Collections.unmodifiableList(list);
		}

		public Hand(String data) {
			this(data.split(" "));
		}

		Object getBestCombination() {
			if (getRoyaleFlush() != null) {
				return getRoyaleFlush();
			}
			if (getStraightFlush() != null) {
				return getStraightFlush();
			}
			if (getFourOfKind() != null) {
				return getFourOfKind();
			}
			if (getFullHouse() != null) {
				return getFullHouse();
			}
			if (getFlush() != null) {
				return getFlush();
			}
			if (getStraight() != null) {
				return getStraight();
			}
			if (getThreeOfKind() != null) {
				return getThreeOfKind();
			}
			if (getDoublePair() != null) {
				return getDoublePair();
			}
			if (getPair() != null) {
				return getPair();
			}

			return getHighestCard();
		}

		Card getHighestCard() {
			return Collections.max(cards);
		}

		static Pair getBestPair(List<Card> cards) {
			Pair bestPair = null;

			for (int i = 0; i < cards.size() - 1; i++) {
				for (int j = i + 1; j < cards.size(); j++) {
					final Card c1 = cards.get(i);
					final Card c2 = cards.get(j);

					if (c1.value == c2.value) {
						final Pair pair = new Pair(c1, c2);

						if ((bestPair == null) || (bestPair.compareTo(pair) < 0)) {
							bestPair = pair;
						}
					}
				}
			}

			return bestPair;
		}

		Pair getPair() {
			return getBestPair(cards);
		}

		DoublePair getDoublePair() {
			DoublePair result = null;

			// Try to find 2 pairs
			final Pair pair1 = getPair();

			if (pair1 != null) {
				final List<Card> temp = new ArrayList<>(cards);
				temp.remove(pair1.c1);
				temp.remove(pair1.c2);

				final Pair pair2 = getBestPair(temp);

				return (pair2 != null) ? new DoublePair(pair1, pair2) : null;
			}

			return result;
		}

		FullHouse getFullHouse() {
			FullHouse result = null;

			final ThreeOfKind threeOfKind = getThreeOfKind();

			if (threeOfKind != null) {
				final List<Card> temp = new ArrayList<>(cards);
				temp.remove(threeOfKind.c1);
				temp.remove(threeOfKind.c2);
				temp.remove(threeOfKind.c3);

				final Pair pair = getBestPair(temp);

				return (pair != null) ? new FullHouse(threeOfKind, pair) : null;
			}

			return result;
		}

		ThreeOfKind getThreeOfKind() {
			ThreeOfKind result = null;

			for (int i = 0; i < cards.size() - 2; i++) {
				for (int j = i + 1; j < cards.size() - 1; j++) {
					final Card c1 = cards.get(i);
					final Card c2 = cards.get(j);

					if (c1.value == c2.value) {
						for (int k = j + 1; k < cards.size(); k++) {
							final Card c3 = cards.get(k);

							if (c2.value == c3.value) {
								return new ThreeOfKind(c1, c2, c3);
							}
						}
					}
				}
			}

			return result;
		}

		FourOfKind getFourOfKind() {
			final DoublePair doublePair = getDoublePair();

			return (doublePair != null) && (doublePair.p1.getValue() == doublePair.p2.getValue()) ? new FourOfKind(
					doublePair.p1.c1, doublePair.p1.c2, doublePair.p2.c1, doublePair.p2.c2) : null;
		}

		Straight getStraight() {
			if (consecutiveCards(cards)) {
				return new Straight(cards);
			}

			return null;
		}

		Flush getFlush() {
			return sameColor(cards) ? new Flush(cards) : null;
		}

		StraightFlush getStraightFlush() {
			return sameColor(cards) && consecutiveCards(cards) ? new StraightFlush(cards) : null;
		}

		RoyaleFlush getRoyaleFlush() {
			return sameColor(cards) && consecutiveCards(cards) && (getHighestCard().value == Value.ACE) ? new RoyaleFlush(cards)
					: null;
		}

		@Override
		public int compareTo(Hand other) {
			final int c1 = Problem54.compareTo(this.getRoyaleFlush(), other.getRoyaleFlush());

			if (c1 != 0) {
				return c1;
			}

			final int c2 = Problem54.compareTo(this.getStraightFlush(), other.getStraightFlush());

			if (c2 != 0) {
				return c2;
			}

			final int c3 = Problem54.compareTo(this.getFourOfKind(), other.getFourOfKind());

			if (c3 != 0) {
				return c3;
			}

			final int c4 = Problem54.compareTo(this.getFullHouse(), other.getFullHouse());

			if (c4 != 0) {
				return c4;
			}

			final int c5 = Problem54.compareTo(this.getFlush(), other.getFlush());

			if (c5 != 0) {
				return c5;
			}

			final int c6 = Problem54.compareTo(this.getStraight(), other.getStraight());

			if (c6 != 0) {
				return c6;
			}

			final int c7 = Problem54.compareTo(this.getThreeOfKind(), other.getThreeOfKind());

			if (c7 != 0) {
				return c7;
			}

			final int c8 = Problem54.compareTo(this.getDoublePair(), other.getDoublePair());

			if (c8 != 0) {
				return c8;
			}

			final int c9 = Problem54.compareTo(this.getPair(), other.getPair());

			if (c9 != 0) {
				return c9;
			}

			return this.getHighestCard().compareTo(other.getHighestCard());
		}

		@Override
		public String toString() {
			return cards.toString();
		}
	}

	static <T extends Comparable<T>> int compareTo(T o1, T o2) {
		if (o1 != null) {
			if (o2 == null) {
				return +1;
			}

			return o1.compareTo(o2);
		} else {
			if (o2 != null) {
				return -1;
			}
		}

		return 0;
	}

	private static void test(String data1, String data2) {
		final Hand hand1 = new Hand(data1);
		final Hand hand2 = new Hand(data2);

		System.out.println(String.format("%s / %s -> %s", hand1.getBestCombination(), hand2.getBestCombination(),
				(hand1.compareTo(hand2) < 0) ? "Player 2" : "Player 1"));
	}

	private static void test(Hand hand) {
		System.out.println(hand + " -> " + hand.getBestCombination().getClass().getSimpleName() + " = "
				+ hand.getBestCombination());
	}

	public static void main(String[] args) throws IOException {
		final Scanner scanner = new Scanner(Problem54.class.getResourceAsStream("p054_poker.txt"));

		int count = 0;

//		test("5H 5C 6S 7S KD", "2C 3S 8S 8D TD");
//		test("5D 8C 9S JS AC", "2C 5C 7D 8S QH");
//		test("2D 9C AS AH AC", "3D 6D 7D TD QD");
//		test("4D 6S 9H QH QC", "3D 6D 7H QD QS");
//		test("2H 2D 4C 4D 4S", "3C 3D 3S 9S 9D");

//		test(new Hand("AS KS QS JS TS")); // Royale flush
//		test(new Hand("KS QS JS TS 9S")); // Straight flush
//		test(new Hand("KS KH KC KD AS")); // Four of a kind
//		test(new Hand("KS KH KC AD AS")); // Full house
//		test(new Hand("KS 2S 4S 6S 8S")); // Flush
//		test(new Hand("KS QH JC TD 9S")); // Straight
//		test(new Hand("KS KH KC TD AS")); // Four of a kind
//		test(new Hand("KS KH TC TD AS")); // Two pairs
//		test(new Hand("KS KH TC 8D 4S")); // One pair
//		test(new Hand("KS JH 6C AD 4S")); // Card alone

		while (scanner.hasNext()) {
			final String[] values = scanner.nextLine().split(" ");

			// System.out.println(Arrays.asList(values));

			final Hand player1 = new Hand(values[0], values[1], values[2], values[3], values[4]);
			final Hand player2 = new Hand(values[5], values[6], values[7], values[8], values[9]);

			if (player1.compareTo(player2) > 0) {
				System.out.println(player1 + " / " + player2);
				System.out.println("  " + player1.getBestCombination() + " > " + player2.getBestCombination());

				count++;
			} else {
				// System.out.println(player1 + " / " + player2);
				// System.out.println("  " + player1.getBestCombination() + " < " + player2.getBestCombination());
			}
		}

		scanner.close();

		System.out.println("Result: " + count);
	}
}