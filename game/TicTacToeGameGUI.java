package game;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class TicTacToeGameGUI {

	private static JFrame frame;
	private static JPanel optionsPanel;
	private static JPanel newGamePanel;
	private static JPanel gamePanel;
	private static JButton newGame;
	private static JButton playerPlayer;
	private static JButton playerComputer;
	private static Player[] players = new Player[2];
	
	
	public static void main (String[] args){
		 startBaseComponents();
		 startNewGamePanel();
		 startGamePanel();		 
	}
	
	public static void startBaseComponents(){
		//inicia o frame
		frame = new JFrame("Tic Tac Toe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(400,400);
		
		//inicia o painel de opções
		optionsPanel = new JPanel();
		newGame = new JButton("New Game");
		newGame.addActionListener(new NewGameListener());
		optionsPanel.add(newGame);
		frame.getContentPane().add(BorderLayout.NORTH, optionsPanel);		
	}
	
	public static void startNewGamePanel(){
		//inicia o painel com as opções de um novo jogo
		newGamePanel = new JPanel();
		newGamePanel.setBackground(Color.cyan);
		//inicializando os botões
		playerPlayer = new JButton("Player x Player");
		playerPlayer.addActionListener(new PlayerPlayerListener());
		playerComputer = new JButton("Player x Computer");
		playerComputer.addActionListener(new PlayerComputerListener());
		
		//box que irá conter as opções
		//alinhar e organizar o box
		Box newGameBox = new Box (BoxLayout.Y_AXIS);
		newGameBox.add(new Label("Select a game type:"));
		newGameBox.add(playerPlayer);
		newGameBox.add(playerComputer);		
		
		newGamePanel.add(newGameBox);
		frame.getContentPane().add(BorderLayout.CENTER, newGamePanel);
	}
	
	public static void startGamePanel(){
		gamePanel = new JPanel();
		gamePanel.setBackground(Color.pink);
	}
	
	public static class NewGameListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			//newGame.setText("oi gente");
			
			Component c = frame.getContentPane().getComponentAt(90, 90);
			frame.getContentPane().remove(c);
			frame.getContentPane().add(BorderLayout.CENTER, gamePanel);
		}
	}
	
	public static class PlayerPlayerListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			players[0] = new Human("X");
			players[1] = new Human("O");	
		}
	}
	
	public static class PlayerComputerListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			players[0] = new Human("X");
			players[1] = new Computer("O");
		}
	}
}

/* if ( JOptionPane.showConfirmDialog(null, "Do you want to play again?") == 
                                                 JOptionPane.YES_OPTION )
    {
        application.removeAll();
        TTT.Restart();
        application.add(TTT);
        application.validate();
     }*/
