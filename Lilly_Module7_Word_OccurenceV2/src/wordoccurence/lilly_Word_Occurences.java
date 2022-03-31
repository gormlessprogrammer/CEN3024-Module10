//Name: Trevor Lilly
//Date: 03/01/2022
//Program Name: lilly_Word_Occurences.java
//Description: A text analyzer that reads a file and outputs statistics about the occurrences of words.

package wordoccurence;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class lilly_Word_Occurences extends Application {
	

	public static void main(String[] args) {
		launch(args);
	
	}
	
	public void start(Stage stage) throws Exception, FileNotFoundException {
		// Exactly what it says on the tin.
		stage.setTitle("The top 20 words of Macbeth!");
		TilePane t = new TilePane();
		// anybody's natural reaction tbh
		Button ok = new Button("Very interesting?");
		Label info = new Label("These are the top twenty words of Macbeth");
		// this is set to store, well - EVERY WORD of macbeth
		ArrayList<String> words = new ArrayList<String>();
		// this list is specifically for output later
		ArrayList<String> analyzedWords = new ArrayList<String>();
		// this list stores the frequency of each word
		ArrayList<Integer> analyzedFreq = new ArrayList<Integer>();
		// we're using a treeset to get rid of duplicates. this is in reverse order to help get our top 20 places
		Set<Word> set = new TreeSet<Word>(Comparator.reverseOrder());
		t.getChildren().add(info);
		
		// the very play itself
		File file = new File("src/wordoccurence/play.txt");
		
		Scanner input = new Scanner(file);
		
		// scans each and every word
		while (input.hasNext()) {
			String word = input.next();
			//i ran into a problem where we'd count MACBETH and macbeth as different words, especially words with commas
			// so this chunk of code is dedicated to making sure that everything is lowercase and has no punctuation
			words.add(word.toLowerCase().replaceAll("\\p{Punct}", ""));
		}
			input.close();
			
		for (int i = 0; i < words.size(); i++) {
			int frequency = Collections.frequency(words, words.get(i));
			Word a = (Word) new Word(frequency, words.get(i));
			set.add(a);
			}
		
		for (Word w:set) {
			// we're getting everything we have in frequency in a list
			analyzedFreq.add(w.freq);
			// and we're getting these words too
			analyzedWords.add(w.word);
		}
		
		StringBuilder result = new StringBuilder();
		Label w = new Label();
		w.setAlignment(Pos.CENTER);
		result.append("The most repeated twenty words in MacBeth: \n");
		result.append("---------------------------------------------\n");
		
		// this list iterates over the top twenty elements of the analyzedFreq/analyzedWords lists and gives a ranking
		for (int i = 0; i < 20; i++) {
			result.append("Frequency: " +analyzedFreq.get(i) + " Word: " + analyzedWords.get(i) + " - Ranking: " + (i+1));
			result.append(".\n");
		}
		
		w.setText(result.toString());
		Scene scene = new Scene(w, 800, 800);
		stage.setScene(scene);
		stage.show();
	}
	
	// the reason why this entire class exists is to help us get everything in order with our set function using the compare function
	// this helps us set up our lists later for presentation
		static class Word implements Comparable<Word> {
		int freq;
		String word;
		
	public Word(int freq, String word) {
		this.freq = freq;
		this.word = word;
	}
		
		public int compareTo(Word w) {
			if (freq > w.freq) {
			return 1;
		}else if(freq < w.freq) {
			return -1;
		}else {
			return 0;
		}
		
	}
	}
}

