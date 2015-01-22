package game;

/**
 * Classe para gerar números aleatórios entre 1 e 9 para fazer as jogadas, ainda
 * não implementado.
 * 
 * @author marina
 *
 */
public class Computer extends Player {

	public Computer(String symbol) {
		super(symbol);
	}

	public Computer() {
		super();
	}

	public void makePlay() {
		String playStr = null;
		Integer play;

		while (true) {
			play = (int) (Math.random() * 9)+1;
			playStr = play.toString();
			if (super.verifyPlay(playStr)) {
				break;
			}
		}

	}
}
