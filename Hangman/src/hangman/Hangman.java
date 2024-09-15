package hangman;

import javax.swing.*;
import java.awt.*;

public class Hangman {

	@SuppressWarnings("unused")
	public static void main(String args[]) {
			String palabraS = JOptionPane.showInputDialog("Enter a word").toLowerCase();
			try {
				Process process = new Process (palabraS);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}