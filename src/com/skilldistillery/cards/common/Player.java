package com.skilldistillery.cards.common;

import java.util.*;

public class Player {

	private Hand hand;
	private double wallet;
	Scanner keyboard = new Scanner(System.in);

	public Player(Hand hand, double wallet) {
		this.hand = hand;
		this.wallet = wallet;

	}

	public Hand getHand() {
		return hand;
	}

	public double placeBet() {
		double bet = -1;
		while (bet < 0) {
			try {
				System.out.println("Player money: " + getWallet());
				System.out.print("Please enter an amount to wager: ");
				bet = keyboard.nextDouble();
				if (wallet <= 0) {
					System.out.println("You are out of money. Thanks for playing! Goodbye.");
					System.exit(0);
				}
				while (bet > wallet) {
					System.out.println("You do not have enough funds to place this bet.");
					System.out.print("Please enter an amount to wager: ");
					bet = keyboard.nextDouble();
				}
				wallet -= bet;
				return bet;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a number.");
				keyboard.nextLine();

			}
		}
		return bet;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}

	public double getWallet() {
		return wallet;
	}

	public void setWallet(double wallet) {
		this.wallet = wallet;
	}

}
