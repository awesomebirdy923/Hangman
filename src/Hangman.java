import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Hangman implements KeyListener{
	String randomWord;
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
			hiddenWord += "_";randomWord.length();
			System.out.println(randomWord.length());
		}
		
		return hiddenWord;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		guessedWord = e.getKeyChar();
		System.out.println("Hi.");
		
		for(int i = 0; i < randomWord.length(); i++) {
		if(randomWord.contains("" + guessedWord)) {
				char c = randomWord.charAt(i);
				if(c == guessedWord) {
					hiddenWord+=guessedWord;
					label.setText(hiddenWord);
//					System.out.println(hiddenWord);
				}else {
					hiddenWord += c;
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
