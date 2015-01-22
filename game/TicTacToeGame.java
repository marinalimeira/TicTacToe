package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TicTacToeGame {
	public static void main(String[] args) {

		String choice = "";
		System.out.println("Select game type.");
		System.out.println("1. Player x Player");
		System.out.println("2. Player x Computer");
		BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
		try {
			choice = is.readLine();
		} catch (IOException e) {
			System.out.println("Error " + e);
		}

		while (!choice.equals("1") && !choice.equals("2")) {
			System.out.println("You have to enter 1 or 2, please try again.");
			try {
				choice = is.readLine();
			} catch (IOException e) {
				System.out.println("Error " + e);
			}
		}

		TicTacToe.getInstance().startGame();
		Player p1 = new Human("X");
		Player p2 = null;

		if (choice.equals("1")) {

			p2 = new Human("O");
			while (!TicTacToe.getInstance().getCardGame().isEmpty()) {
				TicTacToe.getInstance().showCardGame();
				System.out.println("Player 1 turns.");
				p1.makePlay();

				if (p1.verifyWin()) {
					System.out.println("Player 1 wins!");
					break;
				}
				if (!TicTacToe.getInstance().getCardGame().isEmpty()) {
					TicTacToe.getInstance().showCardGame();
					System.out.println("Player 2 turns.");
					p2.makePlay();

					if (p2.verifyWin()) {
						System.out.println("Player 2 wins!");
						break;
					}
				}
			}
		} else if (choice.equals("2")) {

			p2 = new Computer("O");
			while (!TicTacToe.getInstance().getCardGame().isEmpty()) {
				TicTacToe.getInstance().showCardGame();
				System.out.println("Player 1 turns.");
				p1.makePlay();

				if (p1.verifyWin()) {
					System.out.println("Player 1 wins!");
					break;
				}
				if (!TicTacToe.getInstance().getCardGame().isEmpty()) {
					p2.makePlay();

					if (p2.verifyWin()) {
						System.out.println("Player 2 wins!");
						break;
					}
				}
			}
		}

		if (!p1.verifyWin() && !p2.verifyWin()) {
			System.out.println("Tie ):");
		}
		TicTacToe.getInstance().showCardGame();
	}
}
