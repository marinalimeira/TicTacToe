package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * 
 * @author marina
 *
 */
public class Human implements Player {

	private ArrayList<String> plays = new ArrayList<String>();
	private String symbol = "";

	public Human(String symbol){
		this.symbol = symbol;
	}
	
	public Human(){
		
	}
	
	public void makePlay() {
		String input = null;
		while (true) {
			BufferedReader is = new BufferedReader(new InputStreamReader(
					System.in));
			try {
				input = is.readLine();
			} catch (IOException e) {
				System.out.println("Error " + e);
			}

			/*
			 * se não foi feita ainda essa jogada pelo player e ela esta
			 * disponivel no jogo
			 */
			if (!plays.contains(input)
					&& TicTacToe.getInstance().getCardGame().contains(input)) {
				plays.add(input);
				TicTacToe.getInstance().getCardGame().remove(input);
				TicTacToe.getInstance().updateCardGame(Integer.parseInt(input)-1, symbol);
				
				break;
			} else {
				System.out.println("You can't do this move! Try again.");
			}
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
						
						if (goodPlays == 3) { // se for igual a 3 é porque ele conseguiu
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
	
	public void setSymbol(String symbol){
		this.symbol = symbol;
	}
}