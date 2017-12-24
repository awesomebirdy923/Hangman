import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

public class Hangman {
BufferedReader reader;
String fromIntToIndex;
List<String> listOfWords = new ArrayList<String>();
	public Hangman() {
		try {
			reader = new BufferedReader(new FileReader("src/dictionary.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		testIndexSearcher(reader);
	}
	
	private void testIndexSearcher(BufferedReader reader) {
		String in = JOptionPane.showInputDialog("Put a random number here:");
		int inVal = Integer.parseInt(in);
		for (int i = 0; i < inVal; i++) {
			try {
				listOfWords.add(reader.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(listOfWords.get(randomize(listOfWords)));
		
		}
	
	public int randomize(List<String> list) {
		return new Random().nextInt(list.size());
	}
	
	public static void main(String[] args) {
		new Hangman();
	}

}