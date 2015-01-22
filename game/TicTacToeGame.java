package game;

public class TicTacToeGame {
	public static void main(String[] args) {
		TicTacToe.getInstance().startGame();
		Player p1 = new Human("X");
		Player p2 = new Human("O");
		
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
		if (!p1.verifyWin() && !p2.verifyWin()) {
			System.out.println("Tie ):");
		}
		TicTacToe.getInstance().showCardGame();
	}
}
