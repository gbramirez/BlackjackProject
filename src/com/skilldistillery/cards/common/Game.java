package com.skilldistillery.cards.common;

import java.util.*;

public class Game {

	private Deck deck;
	private Player player;
	private double bet;
	private boolean isHidden = true;
	List<Hand> players = new ArrayList<>();

	Scanner keyboard = new Scanner(System.in);

	public Game() {
		this.deck = new Deck();
		Hand dealer = new Hand("Dealer");
		Hand player = new Hand("Player");
		this.player = new Player(player, 1000);
		players.add(player);
		players.add(dealer);
	}

	public void startGame(){

	firstDeal();

}

public void firstDeal() {
	isHidden = true;
	int playerChoice = -1;
	if (deck.checkDeckSize() < 10) {
		this.deck = new Deck();
	}
	
	menuScreen();
	while (playerChoice != 1 || playerChoice != 2) {
		try {
			playerChoice = keyboard.nextInt();
			
			if (playerChoice !=2) {
				bet = player.placeBet();
				
				for (Hand hand : players) {
					hand.resetHand();
					hand.addCard(deck);
					hand.addCard(deck);
				}
				
				displayCardTable();
				if (isHandOver()) {
					firstDeal();
				}
				else {
					askPlayer(players.get(0),players.get(1));
				}
			} else {
				System.out.println("Thank you for playing! Goodbye.");
				System.exit(0);
			}
		} catch (InputMismatchException e) {
			System.out.println("INVALID CHOICE");
			keyboard.nextLine();
		}
	}
}

	public void askPlayer(Hand player, Hand dealer) {
		System.out.println("1. Hit");
		System.out.println("2. Stay");

		int selection = keyboard.nextInt();

		switch (selection) {
		case 1:
			player.addCard(deck);
			displayCardTable();
			if (isHandOver()) {
				System.out.println("");
				firstDeal();
			} else {
				askPlayer(player, dealer);
			}
			break;

		case 2:
			isHidden = false;
			dealerHand();

		default:
			break;
		}
	}

	public void dealerHand() {

		while (players.get(1).handValue() < 17 && !(isHandOver())) {
			System.out.println("Dealer cards: HIDDEN");
			players.get(1).addCard(deck);
			displayCardTable();
		}
		if (players.get(0).handValue() == players.get(1).handValue()) {
			isHidden = false;
			displayCardTable();
			System.out.println("TIE.");
			player.setWallet(player.getWallet() + bet);
			firstDeal();
		}
		if (isHandOver()) {
			firstDeal();

		}
		if (players.get(0).handValue() > players.get(1).handValue() && players.get(1).handValue() < 22) {
			System.out.println("\nYou win!");
			if (isBlackjack(players.get(0))) {
				player.setWallet(player.getWallet() + (bet * (2 / 3)));
			}
			player.setWallet(player.getWallet() + (bet * 2));
		} else {
			isHidden = false;
			displayCardTable();
			System.out.println("\nDealer wins this round.");
		}
		firstDeal();
	}

	public boolean isHandOver() {
		if (players.get(0).getHand().size() == 5 && players.get(0).handValue() <= 21) {
			System.out.println("\nFive card rule: You win!");
			player.setWallet(player.getWallet() + (bet * 2));
			return true;
		}
		for (Hand hand : players) {
			if (isBlackjack(hand)) {
				System.out.println(hand.getName() + " has blackjack!");
				return false;
			} else if (isBust(hand)) {
				System.out.println(hand.getName() + " busted.");
				if (isBust(players.get(1))) {
					player.setWallet(player.getWallet() + (bet * 2));
				}
				return true;
			}
		}
		return false;
	}

	public boolean isBust(Hand hand) {
		return (hand.handValue() > 21) ? true : false;
	}

	public boolean isBlackjack(Hand hand) {
		return (hand.getHand().size() == 2 && hand.handValue() == 21) ? true : false;
	}

	public void displayCardTable() {
		System.out.println("Dealer's Hand is: HIDDEN");
		players.get(1).displayHand(isHidden);
		System.out.println();
		System.out.println("Player's Hand is: " + players.get(0).handValue() + "\tWager: $" + this.bet);
		players.get(0).displayHand(false);
		System.out.println("Wallet: $" + player.getWallet());
	}

	public void menuScreen() {
		System.out.println("");
		System.out.println("\nWelcome to the Blackjack table!");
		System.out.println("\nPlease make a selection");
		System.out.println("1. Play Blackjack");
		System.out.println("2. Exit");
		System.out.println("");
	}
}