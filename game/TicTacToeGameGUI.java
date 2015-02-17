package game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class TicTacToeGameGUI {

	private static JFrame frame; // janela principal
	private static JPanel optionsPanel; // painel com as opções
	private static JPanel newGamePanel; // painel com as opções para um novo
										// jogo
	private static JPanel gamePanel; // painel com o jogo
	private static JPanel gameStatusPanel; // painel com o status do jogo
	private static JButton newGame; // botão para iniciar um novo jogo
	private static JButton playerPlayer; // botão para iniciar um jogo pvp
	private static JButton playerComputer; // botão para iniciar um jogo contra
											// bot
	private static Player[] players = new Player[2];
	private static Label gameStatus;

	private static int playerTurns; // vez do player 1 = 0, ou 2 = 1

	public static void main(String[] args) {
		startBaseComponents();
		callNewGamePanel(); 
		System.out.println("All done!");
	}

	private static void startBaseComponents() {
		// inicia o frame
		frame = new JFrame("Tic Tac Toe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(400, 400);

		// inicia o painel de opções
		optionsPanel = new JPanel();
		newGame = new JButton("New Game");
		newGame.addActionListener(new NewGameListener());
		optionsPanel.add(newGame);

		// inicia o painel com as opções de um novo jogo
		newGamePanel = new JPanel();
		newGamePanel.setBackground(Color.cyan);
		
		// inicializando os botões
		playerPlayer = new JButton("Player x Player");
		playerPlayer.addActionListener(new PlayerPlayerListener());
		//playerPlayer.setLocation(141, 201);
		playerComputer = new JButton("Player x Computer");
		playerComputer.addActionListener(new PlayerComputerListener());

		// box que irá conter as opções
		// alinhar e organizar o box
		//Box newGameBox = new Box(BoxLayout.Y_AXIS);
		newGamePanel.add(new Label("Select a game mode:                                   "));
		newGamePanel.add(playerPlayer);
		newGamePanel.add(playerComputer);
		//newGamePanel.add(newGameBox);
		System.out.println("newGamePanel started!");
		
		gamePanel = new TicTacToe();
		System.out.println("gamePanel started!");
		gamePanel.addMouseListener(new PlayListener());

		// inicializa o painel de um novo jogo
		gameStatusPanel = new JPanel();
		gameStatus = new Label("                             ");
		// colocando uma string com valor pequeno, o tamanho alocado pra esse
		// label será pequeno
		gameStatusPanel.add(gameStatus);
		System.out.println("gameStatusPanel started!");
	}

	// irá limpar toda a tela e inserir as opções e o painel de um novo jogo.
	private static void callNewGamePanel() {
		frame.getContentPane().removeAll();
		frame.repaint(); // clean
		frame.getContentPane().add(BorderLayout.NORTH, optionsPanel);
		frame.getContentPane().add(BorderLayout.CENTER, newGamePanel); //CHAMANDO GAMEPANEL TEMPORARIAMENTE 
		System.out.println("newGamePanel done");
	}

	// chama gamePanel
	public static void callGamePanel() {
		frame.getContentPane().removeAll();
		frame.repaint(); // clean
		frame.add(BorderLayout.NORTH, optionsPanel);
		frame.add(BorderLayout.CENTER, gamePanel);
		frame.add(BorderLayout.SOUTH, gameStatusPanel);
		frame.getContentPane().invalidate();
		frame.getContentPane().validate();
		//frame.getContentPane().repaint();
		System.out.println("gamePanel done");
		
		TicTacToe.getInstance().startGame();
		TicTacToe.getInstance().setEndGame(false);
		playerTurns = 0;
		gameStatus.setText("Player 1 turns.");
		System.out.println("new game started");
	}

	public static void playGame(int x, int y) {
		if (!TicTacToe.getInstance().getCardGame().isEmpty()){
			
			if (players[playerTurns].verifyPlay(players[playerTurns].makePlay(x, y))){
				int [] newPlace = TicTacToe.getInstance().setPlace(x, y); 
				if (players[playerTurns].getSymbol().equals("X")){
					frame.getContentPane().add(new DrawX(newPlace[0], newPlace[1]));
				} else if (players[playerTurns].getSymbol().equals("O")){
					frame.getContentPane().add(new DrawO(newPlace[0], newPlace[1]));
				}
				
				frame.repaint();
				frame.getContentPane().invalidate();
				frame.getContentPane().validate();
				
				if (players[playerTurns].verifyWin()) {
					gameStatus.setText("Player "+(playerTurns+1)+" wins!");	
					TicTacToe.getInstance().setEndGame(true);
				} else {					
					if (playerTurns==0){
						playerTurns=1;
					} else if (playerTurns==1){
						playerTurns=0;
					}
					gameStatus.setText("Player "+(playerTurns+1)+" turns.");
				}	
			} 
		} else {
			gameStatus.setText("Tie ):");
			TicTacToe.getInstance().setEndGame(true);
		}
	}

	private static class NewGameListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			callNewGamePanel();
		}
	}

	private static class PlayerPlayerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			players[0] = new Human("X");
			players[1] = new Human("O");
			callGamePanel();
		}
	}

	private static class PlayerComputerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			players[0] = new Human("X");
			players[1] = new Computer("O");
			callGamePanel();			
		}
	}

	private static class PlayListener implements MouseListener {
		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseClicked(MouseEvent e) {
			if (!TicTacToe.getInstance().isEndGame()){
				playGame(e.getX(), e.getY());
			}
		}
	}
	
	private static class DrawX extends JPanel{
		private int x;
		private int y;
		
		public DrawX(int x, int y){
			this.x=x;
			this.y=y;
		}
		public void paintComponent(Graphics g){
			Image X = new ImageIcon("X.png").getImage();
			g.drawImage(X, x, y, this);
		}
	}
	
	private static class DrawO extends JPanel{
		private int x;
		private int y;
		
		public DrawO(int x, int y){
			this.x=x;
			this.y=y;
		}
		public void paintComponent(Graphics g){
			Image O = new ImageIcon("O.png").getImage();
			g.drawImage(O, x, y, this);
		}
	}
}
/*
 * gameStatus.setText("oasdnajsdi"); try { Thread.sleep(1000); } catch
 * (InterruptedException e1) { // TODO Auto-generated catch block
 * e1.printStackTrace(); } gameStatus.setText("ioioioioioioioioioi"); fazer algo
 * tipo, computer turns, computer is thinking, or dont?
 */
/*	if (players[playerTurns].verifyWin()) {
			gameStatus.setText("Player " + playerTurns + " wins!");
			if (JOptionPane.showConfirmDialog(null,
					"Do you want to play again?") == JOptionPane.YES_OPTION) {
				callNewGamePanel();
			}
		} else if (TicTacToe.getInstance().getCardGame().isEmpty()) {
			gameStatus.setText("Tie! ):");
			if (JOptionPane.showConfirmDialog(null,
					"Do you want to play again?") == JOptionPane.YES_OPTION) {
				callNewGamePanel();
			}
		}
*/
/*
*/