package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * 
 * @author marina
 *
 */
public class TicTacToe extends JPanel{

	private final int topY = 10; 
	private final int topDownY = 101; //abaixo do top
	private final int bottomUpY = 201; //acima do bottom
	private final int bottomY = 300;
	private final int topX = 50; 	
	private final int topDownX = 141; //abaixo do top
	private final int bottomUpX = 241; //acima do bottom
	private final int bottomX = 340;
	
	/* o que vai conter os valores para controle */
	private ArrayList<String> cardGame = new ArrayList<String>();
	
    private String[][] winningPlays = { { "1", "2", "3" }, { "4", "5", "6" },
			{ "7", "8", "9" }, { "1", "4", "7" }, { "2", "5", "8" },
			{ "3", "6", "9" }, { "1", "5", "9" }, { "3", "5", "7" } };
	
	private boolean endGame;

	private static TicTacToe instance = new TicTacToe();

	public static TicTacToe getInstance() {
		return instance;
	}

	public ArrayList<String> startGame() {
		for (int i = 1; i <= 9; i++) {
			String t = String.valueOf(i);
			cardGame.add(t);
		}

		/*for (int i = 1; i <= 9; i++) {
			// seta ele com espaços vazios, que serão substituidos por X ou O 
			realCardGame.add(" ");
		} */

		return cardGame;
	}

	/*public void showCardGame() {

		System.out.println(realCardGame.get(0) + "|" + realCardGame.get(1)
				+ "|" + realCardGame.get(2));
		System.out.println("------");
		System.out.println(realCardGame.get(3) + "|" + realCardGame.get(4)
				+ "|" + realCardGame.get(5));
		System.out.println("------");
		System.out.println(realCardGame.get(6) + "|" + realCardGame.get(7)
				+ "|" + realCardGame.get(8));

	}*/

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

	/*public void updateCardGame(int index, String symbol) {
		realCardGame.set(index, symbol);
	}*/
	
	public void paintComponent(Graphics g){
		g.setColor(Color.black);
		g.fillRect(topX,topDownY,290,8);
		g.fillRect(topX,bottomUpY,290,8);
		g.fillRect(topDownX,topY,8,290);
		g.fillRect(bottomUpX,topY,8,290);
	}

	public boolean isEndGame() {
		return endGame;
	}

	public void setEndGame(boolean endGame) {
		this.endGame = endGame;
	}
	//método para retornar a posição em que o X ou o O deve ser colocado
	public int[] setPlace(int X, int Y){
		int[] newPlace = new int[2];
		
		if (Y>=topY && Y<topDownY){
			newPlace[1]=20;
			if (X>=topX && X<topDownX){
				newPlace[0]=60;
			} else if (X>=topDownX && X<bottomUpX){
				newPlace[0]=161;				
			} else if (X>=bottomUpX && X<bottomX){
				newPlace[0]=261;
			}
		} else if (Y>=topDownY && Y<bottomUpY){
			newPlace[1]=121;
			if (X>=topX && X<topDownX){
				newPlace[0]=60;
			} else if (X>=topDownX && X<bottomUpX){
				newPlace[0]=161;	
			} else if (X>=bottomUpX && X<bottomX){
				newPlace[0]=261;
			}
		} else if (Y>=bottomUpY && Y<bottomY){
			newPlace[1]=221;
			if (X>=topX && X<141){
				newPlace[0]=60;
			} else if (X>=topDownX && X<bottomUpX){
				newPlace[0]=161;	
			} else if (X>=bottomUpX && X<bottomX){
				newPlace[0]=261;
			}
		}
		return newPlace;
	}
}
