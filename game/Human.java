package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author marina
 *
 */
public class Human extends Player {

	public Human(String symbol) {
		super(symbol);
	}

	public Human() {
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

			if (super.verifyPlay(input)) {
				break;
			} else {
				System.out.println("You can't do this move! Try again.");
			}
		}
	}
}