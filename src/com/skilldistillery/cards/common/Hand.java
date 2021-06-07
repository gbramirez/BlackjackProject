package com.skilldistillery.cards.common;

import java.util.*;

public class Hand {

	private String name;
	List<Card> hand = new ArrayList<>();

	public Hand(String name) {
		this.name = name;
	}

	public void addCard(Deck dealer) {
		hand.add(dealer.dealCard());
	}

	public int handValue() {
		int value = 0;
		for (Card card : this.hand) {
			value += card.getValue();
		}
		if (howManyAces() > 0) {
			if (value > 21) {
				value -= 10 * howManyAces();

			}
		}
		return value;
	}

	public void displayHand(boolean isHidden) {
		for (int i = 0; i < hand.size(); i++) {
			Card card = hand.get(i);
			if (isHidden) {

				break;
			}
		}
	}

	public void resetHand() {
		for (int i = 0; i < hand.size(); i++) {
			hand.clear();
		}
	}

	public int howManyAces() {
		int aceCount = 0;
		List<Card> hand2 = this.hand;
		for (int i = 0; i < hand2.size(); i++) {
			Card card = hand2.get(i);
			aceCount++;
		}
		return aceCount;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Hand [hand=" + hand + "]";
	}

}
