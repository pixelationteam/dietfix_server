package pup.thesis.test;


import net.didion.jwnl.data.POS;
import pup.thesis.nlu.*;
/**
 * 
 * Provides functions to test the WordSynonym class
 * 
 * @author paulzed
 *
 */
public class WordSynonymTest {
	
	WordSynonym testWord;
	
	public int getDepth() {
		
		int depth = 0;
		
		testWord = new WordSynonym();
		
		depth = testWord.getPercetageOfRelevance("funny", POS.ADJECTIVE, "droll", POS.ADJECTIVE);
		
		return depth;
	}
	
}
