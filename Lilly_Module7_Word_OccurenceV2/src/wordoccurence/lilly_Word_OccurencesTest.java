//Name: Trevor Lilly
//Date: 03/12/2022
//Program Name: lilly_Word_OccurencesTest.java
//Description: A JUnit program that tests how many instances of a word there might be.


package wordoccurence;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import wordoccurence.lilly_Word_Occurences.Word;


class lilly_Word_OccurencesTest {
	ArrayList<String> testWord = new ArrayList();
	ArrayList<Integer> testFreq = new ArrayList<Integer>();
	@SuppressWarnings("unchecked")
	
	@Test
	void testStartStage() throws FileNotFoundException {
		lilly_Word_Occurences test = new lilly_Word_Occurences();
		File file = new File("src/wordoccurence/play.txt");
		Scanner search = new Scanner(file);
		
		while (search.hasNext()) {
			String word = search.next();
			testWord.add(word.toLowerCase().replaceAll("\\p{Punct}", ""));
		}
		search.close();
		

		int frequency = Collections.frequency(testWord, "and");
		testFreq.add(frequency);
		// this test case should be a successful run
	//	assertEquals(565, frequency);
		// this commented out line forces a failure
		assertEquals(2, frequency);
	}
}
