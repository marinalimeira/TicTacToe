package game;

import java.util.ArrayList;

/**
 * 
 * @author marina
 *
 */
public abstract class Player {

	private ArrayList<String> plays;

	private String symbol;

	public Player(String symbol) {
		this.symbol = symbol;
        plays = new ArrayList<String>();
	}

	public Player() {
        this.symbol = "?";
        plays = new ArrayList<String>();
	}

	public abstract void makePlay();

	/*
	 * Verifica se não foi feita ainda essa jogada pelo player e ela esta
	 * disponivel no jogo
	 */
	public boolean verifyPlay(String play) {
		if (!getPlays().contains(play)
				&& TicTacToe.getInstance().getCardGame().contains(play)) {
			getPlays().add(play);
			TicTacToe.getInstance().getCardGame().remove(play);
			TicTacToe.getInstance().updateCardGame(Integer.parseInt(play) - 1,
					getSymbol());
			return true;
		} else {
			return false;
		}

	}

	public boolean verifyWin() {
		int goodPlays = 0; // quantidade de plays que correspondem as
							// combinações de vitoria
		boolean win = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 3; j++) {
				for (String play : plays) {
					/*
					 * percorre as plays e ve se elas correspondem a alguma
					 * combinacao
					 */

					if (play.equals(TicTacToe.getInstance().getWinningPlays()[i][j])) {
						goodPlays++;

						if (goodPlays == 3) { // se for igual a 3 é porque ele
												// conseguiu
							win = true;
							break;
						}
					}
				}
			}
			goodPlays = 0;// reseta
		}
		return win;
	}

	public ArrayList<String> getPlays() {
		return plays;
	}

	public void setPlays(ArrayList<String> plays) {
		this.plays = plays;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}
