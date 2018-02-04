import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Hangman implements KeyListener{
	static String randomWord;
BufferedReader reader;
String fromIntToIndex;
List<String> listOfWords = new ArrayList<String>();
int inVal;
Stack<String> stackOfRandomlyAssortedWords = new Stack<String>();

char guessedWord;

JFrame frame;
JPanel panel;
JLabel label;

String newWord = "";
static String underscores = "";

boolean completed;

String hiddenWord = "";

	public Hangman() {
		
		try {
			reader = new BufferedReader(new FileReader("src/dictionary.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		testIndexSearcher(reader);
		frame = new JFrame();
		panel = new JPanel();
		label = new JLabel(wordToSolve());
		panel.add(label);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(this);
		frame.setVisible(true);
		frame.pack();
		
//		frame.setResizable(false);
	}
	
	private void testIndexSearcher(BufferedReader reader) {
		
		String in = JOptionPane.showInputDialog("Put a random number here:");
		inVal = Integer.parseInt(in);
		for (int i = 0; i < inVal; i++) {
			try {
				listOfWords.add(reader.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		randomize();
		}
	
	public void randomize() {
		List<String> assortedWords = new ArrayList<String>();
		for (int i = 0; i < inVal; i++) {
			int randomIndex = new Random().nextInt(listOfWords.size());
			assortedWords.add(listOfWords.get(randomIndex));
			stackOfRandomlyAssortedWords.push(listOfWords.get(randomIndex));
//			System.out.println(stackOfRandomlyAssortedWords.peek());
			listOfWords.remove(randomIndex);
		}
	}
	
	public static void main(String[] args) {
		new Hangman();
		
	}

	private String wordToSolve() {
		 randomWord = stackOfRandomlyAssortedWords.pop();
		for (int i = 0; i < randomWord.length(); i++) {
			hiddenWord += "_";
			System.out.println(randomWord.length());
		}
		for (int i = 0; i < randomWord.length(); i++) {
			underscores+="_";
		}
		return hiddenWord;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		guessedWord = e.getKeyChar();
		String newString = "";
		
		for (int i = 0; i < randomWord.length(); i++) {
			if(guessedWord == randomWord.charAt(i)) {
				newString += randomWord.charAt(i);
			}else {
				newString += underscores.charAt(i);
				try {
					playSound("grunt.wav");
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		underscores = newString;
		label.setText(newString);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private synchronized void playSound(String fileName) throws UnsupportedAudioFileException, IOException {
		URL url = getClass().getResource(fileName);
		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream stream = AudioSystem.getAudioInputStream(url);
			clip.open(stream);
			clip.start();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}