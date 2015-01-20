package game;

import java.util.ArrayList;

/**
 * The class Computer will generate random numbers between one and nine to make
 * it's plays.
 * 
 * @author marina
 *
 */
public class Computer implements Player {

	private ArrayList<Integer> plays;

	public void makePlay() {

	}

	public boolean verifyWin() {

		return true;
	}

	public ArrayList<Integer> getPlays() {
		return plays;
	}

	public void setPlays(ArrayList<Integer> plays) {
		this.plays = plays;
	}

	/*
	 * método para jogada será gerar um int random que ainda não foi selecionado
	 */
}
