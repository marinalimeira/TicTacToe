package com.game;


/**
 * Classe que implementa a interface de Player, que é responsável por gerenciar
 * as jogadas do jogador humano.
 * 
 * @author marina
 *
 */
public class Human extends Player {
	
	private final int topY = 10; 
	private final int topDownY = 101; //abaixo do top
	private final int bottomUpY = 201; //acima do bottom
	private final int bottomY = 300;
	private final int topX = 50; 	
	private final int topDownX = 141; //abaixo do top
	private final int bottomUpX = 241; //acima do bottom
	private final int bottomX = 340;
	
	public Human(String symbol) {
		super(symbol);
	}

	public Human() {
	}
	
	public String makePlay(int X, int Y) {
		if (Y>=topY && Y<topDownY){
			if (X>=topX && X<141){
				
				return ("1");
			} else if (X>=topDownX && X<bottomUpX){
				
				return ("2");
			} else if (X>=bottomUpX && X<bottomX){
				
				return ("3");
			}
		} else if (Y>=topDownY && Y<bottomUpY){
			if (X>=topX && X<141){
				
				return ("4");
			} else if (X>=topDownX && X<bottomUpX){
			
				return ("5");
			} else if (X>=bottomUpX && X<bottomX){
				
				return ("6");
			}
		} else if (Y>=bottomUpY && Y<bottomY){
			if (X>=topX && X<141){
				
				return ("7");
			} else if (X>=topDownX && X<bottomUpX){
				
				return ("8");
			} else if (X>=bottomUpX && X<bottomX){
				
				return ("9");
			}
		}
		return "";
	}
}