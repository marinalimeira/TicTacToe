package game;

import java.util.ArrayList;
/**
 * 
 * @author marina
 *
 */
public class TicTacToe {
 
	/*o que vai conter os valores para controle*/
	private ArrayList<String> cardGame = new ArrayList<String>(); 
	/*o que vai mostrar para o usuario*/
	private ArrayList<String> realCardGame = new ArrayList<String>(); 
	private String[][] winningPlays = { { "1", "2", "3" }, { "4", "5", "6" }, { "7", "8", "9" },
			{ "1", "4", "7" }, { "2", "5", "8" }, { "3", "6", "9" }, { "1", "5", "9" }, { "3", "5", "7" } };

	private static TicTacToe instance = new TicTacToe();

	public static TicTacToe getInstance() {
		return instance;
	}

	public ArrayList<String> startGame() {
		for (int i = 1; i <= 9; i++) {
			String t = String.valueOf(i);
			cardGame.add(t);
		}
		
		for (int i = 1; i <= 9; i++) {
			/*seta ele com espaços vazios*/
			realCardGame.add(" ");
		}
		
		return cardGame;
	}
	
	public void showCardGame(){
		
		System.out.println(realCardGame.get(0)+"|"+realCardGame.get(1)+"|"+realCardGame.get(2));
		System.out.println("------");
		System.out.println(realCardGame.get(3)+"|"+realCardGame.get(4)+"|"+realCardGame.get(5));
		System.out.println("------");
		System.out.println(realCardGame.get(6)+"|"+realCardGame.get(7)+"|"+realCardGame.get(8));
		
	}

	public boolean verifyPlay(int play) {
		if (cardGame.contains(play)) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<String> getCardGame() {
		return cardGame;
	}
	
	public ArrayList<String> getRealCardGame() {
		return cardGame;
	}

	public String[][] getWinningPlays() {
		return winningPlays;
	}

	public void updateCardGame(int index, String symbol){
		realCardGame.set(index, symbol);
	}
	/*
	 * primeiro, jogo player x player. instancia player 1 e 2, while nenhum
	 * ganhou: p1.makePlay(); seta o int ao local referente na matriz verifica
	 * se não ganhou
	 */

}
