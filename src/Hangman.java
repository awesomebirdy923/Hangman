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

public class Hangman {
BufferedReader reader;
String fromIntToIndex;
List<String> listOfWords = new ArrayList<String>();
int inVal;
Stack<String> stackOfRandomlyAssortedWords = new Stack<String>();

JFrame frame;
JPanel panel;
JLabel label;

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
		String randomWord = stackOfRandomlyAssortedWords.pop();
		for (int i = 0; i < randomWord.length(); i++) {
			hiddenWord += "_";randomWord.length();
			System.out.println(randomWord.length());
		}
		return hiddenWord;
	}
	
}
