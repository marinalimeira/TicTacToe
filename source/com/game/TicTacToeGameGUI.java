package com.game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

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

	private static JPanel scoresPanel; // painel dos scores
	private static JButton showScoresButton;
	// bot
	private static Player[] players = new Player[2];
	private static Label gameStatus;
	private static HashMap<String, Integer> scores;

	private static int playerTurns; // vez do player 1 = 0, ou 2 = 1

	public static void main(String[] args) {
		startBaseComponents();
		callNewGamePanel();
		System.out.println("Jogo inicializado.");
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
		showScoresButton = new JButton("Show Scores");
		showScoresButton.addActionListener(new ShowScoresListener());
		optionsPanel.add(newGame);
		optionsPanel.add(showScoresButton);

		// inicia o painel com as opções de um novo jogo
		newGamePanel = new JPanel();

		// inicializando os botões
		playerPlayer = new JButton("Player x Player");
		playerPlayer.addActionListener(new PlayerPlayerListener());
		// playerPlayer.setLocation(141, 201);
		playerComputer = new JButton("Player x Computer");
		playerComputer.addActionListener(new PlayerComputerListener());

		// box que irá conter as opções
		// alinhar e organizar o box
		// Box newGameBox = new Box(BoxLayout.Y_AXIS);
		newGamePanel.add(new Label("Select a game mode:                                   "));
		newGamePanel.add(playerPlayer);
		//newGamePanel.add(playerComputer);
		// newGamePanel.add(newGameBox);

		gamePanel = new TicTacToe();
		gamePanel.addMouseListener(new PlayListener());

		// inicializa o painel de um novo jogo
		gameStatusPanel = new JPanel();
		gameStatus = new Label("                             ");
		// colocando uma string com valor pequeno, o tamanho alocado pra esse
		// label será pequeno
		gameStatusPanel.add(gameStatus);
		System.out.println("Componentes iniciais prontos.");
	}

	// irá limpar toda a tela e inserir as opções e o painel de um novo jogo.
	private static void callNewGamePanel() {
		frame.getContentPane().removeAll();
		frame.repaint(); // clean
		frame.getContentPane().add(BorderLayout.NORTH, optionsPanel);
		frame.getContentPane().add(BorderLayout.CENTER, newGamePanel); // CHAMANDO
																		// GAMEPANEL
																		// TEMPORARIAMENTE
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
		// frame.getContentPane().repaint();
		System.out.println("Painel de um novo jogo iniciado.");

		TicTacToe.getInstance().startGame();
		TicTacToe.getInstance().setEndGame(false);
		playerTurns = 0;
		gameStatus.setText("Player 1 turns.");
		System.out.println("new game started");
	}

	public static void callScoresPanel() {
		startScoresPanel();
		frame.getContentPane().removeAll();
		frame.repaint(); // clean
		frame.add(BorderLayout.NORTH, optionsPanel);
		frame.add(BorderLayout.CENTER, scoresPanel);
		frame.getContentPane().invalidate();
		frame.getContentPane().validate();
		// frame.getContentPane().repaint();
		System.out.println("Painel de um novo jogo iniciado.");
	}
	
	public static void startScoresPanel() {
		scoresPanel = new JPanel();
		JTextArea scoresTArea = new JTextArea();
		scoresTArea.setFont(new Font("Verdana", Font.BOLD, 12));
		scoresTArea.setEditable(false);
		
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(
					"TicTacToe.ser"));
			scores = (HashMap<String, Integer>) is.readObject();
			scoresTArea.setText(scores.toString());
			System.out.println("Lido o arquivo com as pontuações!");
			is.close();

		} catch (FileNotFoundException io) {
			scoresTArea.setText("Não existe nenhuma pontuação arquivada! ):");
			io.printStackTrace();
		} catch(Exception ex){
			ex.printStackTrace();
		}
		scoresPanel.add(scoresTArea);
	}

	public static void playGame(int x, int y) {
		if (!TicTacToe.getInstance().getCardGame().isEmpty()) {

			if (players[playerTurns].verifyPlay(players[playerTurns].makePlay(
					x, y))) {
				int[] newPlace = TicTacToe.getInstance().setPlace(x, y);
				if (players[playerTurns].getSymbol().equals("X")) {
					frame.getContentPane().add(
							new DrawX(newPlace[0], newPlace[1]));
				} else if (players[playerTurns].getSymbol().equals("O")) {
					frame.getContentPane().add(
							new DrawO(newPlace[0], newPlace[1]));
				}

				frame.repaint();
				frame.getContentPane().invalidate();
				frame.getContentPane().validate();

				if (players[playerTurns].verifyWin()) {
					gameStatus
							.setText("Player " + (playerTurns + 1) + " wins!");
					getPlayerName(playerTurns);
					TicTacToe.getInstance().setEndGame(true);
				} else if (TicTacToe.getInstance().getCardGame().isEmpty()) {
					gameStatus.setText("Tie ):");
					TicTacToe.getInstance().setEndGame(true);
				} else {
					if (playerTurns == 0) {
						playerTurns = 1;
					} else if (playerTurns == 1) {
						playerTurns = 0;
					}
					gameStatus.setText("Player " + (playerTurns + 1)
							+ " turns.");
				}
			}
		} else {
			gameStatus.setText("Tie ):");
			TicTacToe.getInstance().setEndGame(true);
		}
	}

	public static void getPlayerName(int playerTurns) {

		String playerName = JOptionPane.showInputDialog("Player "
				+ (playerTurns + 1) + " congratulations for your win!"
				+ " What's your name?");

		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(
					"TicTacToe.ser"));
			scores = (HashMap<String, Integer>) is.readObject();
			System.out.println("Lido o arquivo com as pontuações!");
			is.close();

		} catch (FileNotFoundException io) {
			System.out.println("Não foi possível encontrar o arquivo! Criando um novo...");
			try {
				ObjectOutputStream os = new ObjectOutputStream(
						new FileOutputStream("TicTacToe.ser"));

				scores = new HashMap<String, Integer>();
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} // close catch is

		if (scores.containsKey(playerName)) {
			scores.put(playerName, scores.get(playerName) + 1);
			System.out.println("Score de " + playerName + " atualizado!");
		} else {
			scores.put(playerName, 1);
		}

		try {
			ObjectOutputStream os = new ObjectOutputStream(
					new FileOutputStream("TicTacToe.ser"));

			os.writeObject(scores);
			System.out.println("Nova lista de pontuações salva!");
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static class NewGameListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			callNewGamePanel();
		}
	}

	private static class ShowScoresListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			callScoresPanel();
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
			if (!TicTacToe.getInstance().isEndGame()) {
				playGame(e.getX(), e.getY());
			}
		}
	}

	private static class DrawX extends JPanel {
		private int x;
		private int y;

		public DrawX(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public void paintComponent(Graphics g) {
			Image X = new ImageIcon("../../X.png").getImage();
			g.drawImage(X, x, y, this);
		}
	}

	private static class DrawO extends JPanel {
		private int x;
		private int y;

		public DrawO(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public void paintComponent(Graphics g) {
			Image O = new ImageIcon("../../O.png").getImage();
			g.drawImage(O, x, y, this);
		}
	}
}