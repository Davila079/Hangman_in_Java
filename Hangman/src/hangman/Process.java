package hangman;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Process extends JFrame {
	private JLabel labelLogo, labelTitle,labelHidedWord;
	private JTextField textFieldCharacter, textFielWord;
	private JButton buttonInsertChar, buttonGuessWord, buttonInsertWord, buttonX;
	private final int BASEX = 130, BASEY=60;
	String wordHidedDisp = new String();
	String wordS = new String();
	String hidedWordS = new String();
	String lettersS = new String();
	String wordCompleteIn = new String();
	StringBuilder sbHidedWord = new StringBuilder();
	int lives=6;
	boolean letterFound, LetterRepeated = false;
	boolean startAgainYoN = true;
	char[] alphabeth ={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z'};
	String alphabethS = new String(alphabeth);
	int[] lettersUsed = new int[27];
	int index = 0;
	
//constructor that receives the word form Hangman.java
	public Process (String p) throws Exception
	{
		wordS = p;
		hidedWordS = iniUnderscores(wordS);
		wordHidedDisp = spaceUnderscores(hidedWordS);
		sbHidedWord.append(hidedWordS);
		Arrays.fill(lettersUsed, 0);
//colors.
		
		Color colorBackround = new Color(80, 80, 80);
		Color colorButtons = new Color(100, 100, 100);
		Color colorBackround2 = new Color(150, 150, 150);
		Color colorText = new Color(255,255,255);
//frame
			JFrame frame = new JFrame("Hangman");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(700,700,650,550);

			JPanel mainPanel = new JPanel();
			mainPanel.setSize(800,800);
			frame.add(mainPanel);
			mainPanel.setLayout(new BorderLayout(0,0));
			
			GridBagConstraints c = new GridBagConstraints();
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(5,5,5,5);
//audios
			 File audioWin = new File("archivos/win.wav");
			 AudioInputStream audioStreamWin = AudioSystem.getAudioInputStream(audioWin);
			 Clip soundWin = AudioSystem.getClip();
			 soundWin.open(audioStreamWin);
			 
			 File audioLose = new File("archivos/lose.wav");
			 AudioInputStream audioStreamLose = AudioSystem.getAudioInputStream(audioLose);
			 Clip soundLose = AudioSystem.getClip();
			 soundLose.open(audioStreamLose);
			
			 File audioCorrect = new File("archivos/correct.wav");
			 AudioInputStream audioStreamCorrect = AudioSystem.getAudioInputStream(audioCorrect);
			 Clip soundCorrect = AudioSystem.getClip();
			 soundCorrect.open(audioStreamCorrect);
			
			 File audioIncorrect = new File("archivos/incorrect.wav");
			 AudioInputStream audioStreamIncorrect = AudioSystem.getAudioInputStream(audioIncorrect);
			 Clip sonidoIncorrect = AudioSystem.getClip();
			 sonidoIncorrect.open(audioStreamIncorrect);
			
			//Upper panel (labelLogo, Title).
			JPanel panelUp = new JPanel(new GridBagLayout());
			panelUp.setBackground(colorBackround);
			//labelLogo
			ImageIcon img =new ImageIcon("archivos/Logo_udlap1.png");
			labelLogo= new JLabel (img);
			c.gridx =1; c.gridy =0;
			panelUp.add(labelLogo,c);

	        labelTitle = new JLabel ("HANGMAN");
	        labelTitle.setFont(new Font("ARIAL",Font.BOLD,50));
	        labelTitle.setForeground(colorText);
	        c.gridx = 1; c.gridy = 1;
	        panelUp.add(labelTitle,c);

//panelMid (sub panel left, sub panel right)
	        JPanel panelCenterRight = new JPanel();
	        panelCenterRight.setLayout(new BorderLayout());
	        panelCenterRight.setBackground(colorBackround2);
	        
	        //Underscore Words.
	        labelHidedWord = new JLabel (wordHidedDisp);
	        labelHidedWord.setText(wordHidedDisp + "   ");
	        labelHidedWord.setFont(new Font("ARIAL",Font.BOLD,40));
	        panelCenterRight.add(labelHidedWord,BorderLayout.CENTER);

	        JPanel panelCenterLeft = new JPanel(){
				public void paintComponent(Graphics g) {
	        		super.paintComponent(g);
	        		
	        		switch (lives) {
	        		case 6: //Base
	        			//base
	        			g.fillRect(BASEX-90, BASEY+210, 200, 15);
	        			//vertical line
	        			g.fillRect(BASEX-50, BASEY-40, 10, 250);
	        			//horizontal line up
	        			g.fillRect(BASEX-50, BASEY-40, 100, 8);
	        			break;
	        		case 5:
	        			//base
	        			g.fillRect(BASEX-90, BASEY+210, 200, 15);
	        			//vertical line
	        			g.fillRect(BASEX-50, BASEY-40, 10, 250);
	        			//horizontal line up
	        			g.fillRect(BASEX-50, BASEY-40, 100, 8);
	        			//rope
	        			g.drawLine(BASEX+40, BASEY-40, BASEX+40, BASEY);
	        			break;
	        		case 4:
	        			//base
	        			g.fillRect(BASEX-90, BASEY+210, 200, 15);
	        			//vertical line
	        			g.fillRect(BASEX-50, BASEY-40, 10, 250);
	        			//horizontal line up
	        			g.fillRect(BASEX-50, BASEY-40, 100, 8);
	        			//rope
	        			g.drawLine(BASEX+40, BASEY-40, BASEX+40, BASEY);
	        			//head
	        			g.setColor(Color.white);
	        			g.fillOval(BASEX, BASEY, 80, 80);
	        			g.setColor(Color.black);
	        			break;
	        		case 3:
	        			//base
	        			g.fillRect(BASEX-90, BASEY+210, 200, 15);
	        			//vertical line
	        			g.fillRect(BASEX-50, BASEY-40, 10, 250);
	        			//horizontal line up
	        			g.fillRect(BASEX-50, BASEY-40, 100, 8);
	        			//rope
	        			g.drawLine(BASEX+40, BASEY-40, BASEX+40, BASEY);
	        			//head
	        			g.setColor(Color.white);
	        			g.fillOval(BASEX, BASEY, 80, 80);
	        			g.setColor(Color.black);
	        			//torso
	        			g.drawLine(BASEX+40, BASEY+80, BASEX+40, BASEY+125);
	        			break;
	        		case 2:
	        			//base
	        			g.fillRect(BASEX-90, BASEY+210, 200, 15);
	        			//vertical line up
	        			g.fillRect(BASEX-50, BASEY-40, 10, 250);
	        			//horizontal line up
	        			g.fillRect(BASEX-50, BASEY-40, 100, 8);
	        			//rope
	        			g.drawLine(BASEX+40, BASEY-40, BASEX+40, BASEY);
	        			//head
	        			g.setColor(Color.white);
	        			g.fillOval(BASEX, BASEY, 80, 80);
	        			g.setColor(Color.black);
	        			//torso
	        			g.drawLine(BASEX+40, BASEY+80, BASEX+40, BASEY+125);
	        			//arms
	        			g.drawLine(BASEX+40, BASEY+80, BASEX+20, BASEY+120);
	        			g.drawLine(BASEX+40, BASEY+80, BASEX+60, BASEY+120);
	        			break;
	        		case 1: 
	        			//base
	        			g.fillRect(BASEX-90, BASEY+210, 200, 15);
	        			//vertical line up
	        			g.fillRect(BASEX-50, BASEY-40, 10, 250);
	        			//horizontal line up
	        			g.fillRect(BASEX-50, BASEY-40, 100, 8);
	        			//rope
	        			g.drawLine(BASEX+40, BASEY-40, BASEX+40, BASEY);
	        			//head
	        			g.setColor(Color.white);
	        			g.fillOval(BASEX, BASEY, 80, 80);
	        			g.setColor(Color.black);
	        			//torso
	        			g.drawLine(BASEX+40, BASEY+80, BASEX+40, BASEY+125);
	        			//arms
	        			g.drawLine(BASEX+40, BASEY+80, BASEX+20, BASEY+120);
	        			g.drawLine(BASEX+40, BASEY+80, BASEX+60, BASEY+120);
	        			//legs
	        			g.drawLine(BASEX+40, BASEY+125, BASEX+30, BASEY+190);
	        			g.drawLine(BASEX+40, BASEY+125, BASEX+50, BASEY+190);
	        			break;
	        		case 0:
	        			//base
	        			g.fillRect(BASEX-90, BASEY+210, 200, 15);
	        			//vertical line up
	        			g.fillRect(BASEX-50, BASEY-40, 10, 250);
	        			//horizontal line up
	        			g.fillRect(BASEX-50, BASEY-40, 100, 8);
	        			//rope
	        			g.drawLine(BASEX+40, BASEY-40, BASEX+40, BASEY);
	        			//head
	        			g.setColor(Color.white);
	        			g.fillOval(BASEX, BASEY, 80, 80);
	        			g.setColor(Color.black);
	        			//torso
	        			g.drawLine(BASEX+40, BASEY+80, BASEX+40, BASEY+125);
	        			//arms
	        			g.drawLine(BASEX+40, BASEY+80, BASEX+20, BASEY+120);
	        			g.drawLine(BASEX+40, BASEY+80, BASEX+60, BASEY+120);
	        			//legs
	        			g.drawLine(BASEX+40, BASEY+125, BASEX+30, BASEY+190);
	        			g.drawLine(BASEX+40, BASEY+125, BASEX+50, BASEY+190);
	        			//draw eyes in X_X.
	        			g.setFont(new Font("ARIAL",Font.BOLD,25));
	        			g.drawString("X  X", BASEX+17, BASEY+42);
	        			g.drawString("__", BASEX+26, BASEY+50);
	        			
	        			break;
	        		case 10: //value 10 means the user win.
	        			g.fillRect(BASEX-90, BASEY+210, 200, 15);
	        			//vertical line up
	        			g.fillRect(BASEX-50, BASEY-40, 10, 250);
	        			//horizontal line up
	        			g.fillRect(BASEX-50, BASEY-40, 100, 8);
	        			//rope
	        			g.drawLine(BASEX+40, BASEY-40, BASEX+40, BASEY);
	        			//head
	        			g.setColor(Color.white);
	        			g.fillOval(BASEX+30, BASEY+20, 80, 80);
	        			g.setColor(Color.black);
	        			//torso
	        			g.drawLine(BASEX+70, BASEY+100, BASEX+70, BASEY+145);
	        			//arms
	        			g.drawLine(BASEX+70, BASEY+110, BASEX+20, BASEY+70);
	        			g.drawLine(BASEX+70, BASEY+110, BASEX+120, BASEY+70);
	        			//legs
	        			g.drawLine(BASEX+70, BASEY+145, BASEX+60, BASEY+210);
	        			g.drawLine(BASEX+70, BASEY+145, BASEX+80, BASEY+210);
	        			g.setFont(new Font("ARIAL",Font.BOLD,35));
	        			g.drawString("°  °", BASEX+47, BASEY+75);
	        			g.drawArc(BASEX+50, BASEY+60, 40, 25, 180, 180);
	        			g.setFont(new Font("ARIAL",Font.BOLD,60));
	        			g.drawString("YOU WON!", BASEX-150, BASEY);	        			
	        			break;
	        		}
	        	}
	        };
	        panelCenterLeft.setBackground(colorBackround2);
	        
	        JPanel panelMid = new JPanel ();
	        panelMid.setLayout(new BorderLayout(0,0));
	        panelMid.add(panelCenterRight,BorderLayout.EAST);
	        panelMid.add(panelCenterLeft,BorderLayout.CENTER);
	        JPanel panelDown = new JPanel (new GridBagLayout());
	        panelDown.setBackground(colorBackround);
	        
//panel Down (textfield text, butons).
	        //Insert Text
	        textFieldCharacter= new JTextField(3);
	        c.gridx = 0; c.gridy = 0;
	        panelDown.add(textFieldCharacter,c);
	        
	        //Jtext that receives complete word
	        textFielWord = new JTextField (10);
	        c.gridx = 4; c.gridy = 0;
	        panelDown.add(textFielWord,c);
	        textFielWord.setVisible(false);
	        
	        //button to insert char
	        buttonInsertChar = new JButton("Insert");
	        buttonInsertChar.setBackground(colorButtons);
			buttonInsertChar.setForeground(colorText);
	        c.gridx = 1; c.gridy = 0;
	        panelDown.add(buttonInsertChar,c);
	        
	        //receives the char.
	        buttonInsertChar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Compares the entered character and checks if the original word matches, if there is a match, it checks that the character is still a hyphen (letter not found) and replaces it with the letter.
					lettersS = textFieldCharacter.getText().toLowerCase();
					letterFound = false;
					LetterRepeated = false;
					for (int i = 0; i < wordS.length(); i++) {
			            if (wordS.charAt(i) == lettersS.charAt(0)) {
			            	letterFound = true;
			                if (sbHidedWord.charAt(i) == '_') {
			                    sbHidedWord.setCharAt(i, lettersS.charAt(0));
			                    soundCorrect.setFramePosition(0);
			                    soundCorrect.start();
			                }
			                else //If it matches, but the letter is no longer a underscore, it means that it has already been found, therefore it is a repeated letter.
			                	LetterRepeated = true;
			            }
			        }
					index = alphabethS.indexOf(lettersS); //extract the index of the letter in the array with alphabeth
					lettersUsed[index]++; //adds 1 when a letter is used.
					if(lettersUsed[index]==2) { //If a letter is entered twice, a warning occurs
						JOptionPane.showMessageDialog(null,"You repeated a letter, the next you will lose a life","Repeated Letter", JOptionPane.INFORMATION_MESSAGE);
					}
					if(lettersUsed[index]>=3) { //If a letter is entered 3 times you lose a life 
						JOptionPane.showMessageDialog(null, "You lose an attempt to repeat letters","Repeated Letter", JOptionPane.INFORMATION_MESSAGE);
						lives--;
						lettersUsed[index] = 1; //the counter of letters used is reset.
						letterFound = true; //If you enter a letter that is not there alone, 1 life is subtracted.
						sonidoIncorrect.setFramePosition(0);
						sonidoIncorrect.start();
					}
					if(!letterFound) {//if the LyricsFound value is false, one life is subtracted.
						lives--;
						sonidoIncorrect.setFramePosition(0);
						sonidoIncorrect.start();
					}
					System.out.println(lives);
			        // Update the text in the JTextfield with the updated script string.
			        hidedWordS = sbHidedWord.toString();
			        wordHidedDisp = spaceUnderscores(hidedWordS);
			        labelHidedWord.setText(wordHidedDisp + "   ");
			        textFieldCharacter.setText("");
			        //If the word is completed
			        if (hidedWordS.equals(wordS)) {
			        	lives=10;
			        	soundWin.setFramePosition(0);
			        	soundWin.start();
			        }
			        //Enter if the player wins or loses.
			        if(lives==0 || lives == 10) {
			        	if(lives==0) {
			        		soundLose.setFramePosition(0);
			        		soundLose.start();
			        	}
			        	panelCenterLeft.repaint();
						startAgainYoN = winLose(lives,wordS);
						if(!startAgainYoN)//If "no" is selected to play again, the frame is closed.
							frame.dispose();
						if(startAgainYoN) {//If "yes" is selected in play again, the values ​​are reset.
							wordS = JOptionPane.showInputDialog("Enter another word").toLowerCase();
							hidedWordS = wordS;
							hidedWordS = iniUnderscores(wordS);
							wordHidedDisp = spaceUnderscores(hidedWordS);
							sbHidedWord.setLength(0);
							sbHidedWord.append(hidedWordS);
					        labelHidedWord.setText(wordHidedDisp + "   ");
							lives = 6;
							Arrays.fill(lettersUsed, 0);
						}
					}
			        panelCenterLeft.repaint();
				}
	        });
	        //button to enter full word.
	        buttonInsertWord = new JButton ("Insert");
	        buttonInsertWord.setBackground(colorButtons);
			buttonInsertWord.setForeground(new Color(255, 255, 255));
	        c.gridx = 5; c.gridy = 0;
	        panelDown.add(buttonInsertWord,c);
	        buttonInsertWord.setVisible(false);
	        buttonInsertWord.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					wordCompleteIn = textFielWord.getText().toLowerCase();
					//If you enter the correct word you win.
					if(wordCompleteIn.equals(wordS)) {
						lives=10;
						soundWin.start();
						labelHidedWord.setText(wordCompleteIn + "   ");
					}
					//If you enter a wrong word you lose.
					else {
						lives=0;
						soundLose.setFramePosition(0);
						soundLose.start();
					}
					textFielWord.setText("");
					//Enter the if if you won or lost.
					if(lives==0 || lives == 10) {
						panelCenterLeft.repaint();
						startAgainYoN = winLose(lives,wordS);
						if(!startAgainYoN)//If "no" is selected to play again, the frame is closed.
							frame.dispose();
						if(startAgainYoN) {//If "yes" is selected in play again, the values ​​are reset.
							wordS = JOptionPane.showInputDialog("Introduce another word").toLowerCase();
							hidedWordS = wordS;
							hidedWordS = iniUnderscores(wordS);
							wordHidedDisp = spaceUnderscores(hidedWordS);
							sbHidedWord.setLength(0);
							sbHidedWord.append(hidedWordS);
					        labelHidedWord.setText(wordHidedDisp + "   ");
							lives = 6;
							Arrays.fill(lettersUsed, 0);
						}
					}
					panelCenterLeft.repaint();
				}
	        });
	        //button to guess full word
	        buttonGuessWord = new JButton ("I already know it!");
	        buttonGuessWord.setBackground(colorButtons);
			buttonGuessWord.setForeground(new Color(255, 255, 255));
	        c.gridx = 3; c.gridy = 0;
	        panelDown.add(buttonGuessWord,c);
	        buttonGuessWord.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		textFielWord.setVisible(true);
	        		buttonInsertWord.setVisible(true);
	        		buttonX.setVisible(true);
	        		}
	        	}
	        );
	        //x button to remove the textfield for the entire word along with its enter button.
	        buttonX = new JButton ("X");
	        buttonX.setVisible(false);
	        buttonX.setBackground(colorButtons);
			buttonX.setForeground(new Color(255, 255, 255));
	        c.gridx = 6; c.gridy = 0;
	        panelDown.add(buttonX,c);
	        buttonX.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textFielWord.setVisible(false);
					buttonInsertWord.setVisible(false);
					buttonX.setVisible(false);
				}
	        });	        
//add panels to the main panel.
		    mainPanel.add(panelUp, BorderLayout.NORTH);
		    mainPanel.add(panelMid, BorderLayout.CENTER);
		    mainPanel.add(panelDown, BorderLayout.SOUTH);
			
//frame configuration.
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
	//receives a string and returns one of the same size with underscores.
	String iniUnderscores(String wordS) {
		StringBuilder sbPalabra= new StringBuilder();
		String wordHided = new String();
		for (int i = 0; i < wordS.length(); i++) {
		    if (wordS.charAt(i) == ' ') {
		        sbPalabra.append(" ");
		    } else {
		        sbPalabra.append("_");
		    }
		}
		wordHided = sbPalabra.toString();
		return wordHided;
	}
	//receives a string and returns a string with spaces between characters.
	String spaceUnderscores(String wordUnderscores) {
		StringBuilder result = new StringBuilder();
		String underscoresSpaces = new String();
		//loop to add spaces.
		for (int i = 0; i < wordUnderscores.length(); i++) {
			result.append(wordUnderscores.charAt(i)).append(' ');
		}
		underscoresSpaces = result.toString().trim();
		return underscoresSpaces;
	}
	//Enter if you lose or win and return if the game is repeated or not.
	boolean winLose (int lives,String word) {
		String[] options = {"Yes","No"};
		int winLoseAnswer;
		if(lives == 0) {//If you lose, a panel is displayed and asks if you want to play again.
			winLoseAnswer = JOptionPane.showInternalOptionDialog(null,"You lost!\n the word was "+ word +"\nDo you want to play again?","You lost",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, options[1]);
			if (winLoseAnswer == JOptionPane.YES_OPTION) {
				return true;
	        } else if (winLoseAnswer == JOptionPane.NO_OPTION) {
	        	return false;
	        }
		}
		if(lives == 10) {//If you win, a panel appears and asks if you want to play again.
			winLoseAnswer = JOptionPane.showInternalOptionDialog(null, "You won!!!\nDo you want to play again?", "You won!!!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			if (winLoseAnswer == JOptionPane.YES_OPTION) {
				return true;
	        } else if (winLoseAnswer == JOptionPane.NO_OPTION) {
	        	return false;
	        }
		}
		return false;
	}
}