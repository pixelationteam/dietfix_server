package pup.thesis.helper;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import net.didion.jwnl.JWNL;
import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.PointerUtils;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.list.PointerTargetNode;
import net.didion.jwnl.data.list.PointerTargetNodeList;
import net.didion.jwnl.dictionary.Dictionary;

public class JwnlHelper {
	
	private Dictionary wordnet;
	
	/**
	 * This constructor will instantiate the
	 * location of the wordnet in the project
	 * using the 'file_properties.xml' file
	 * 
	 */
	public JwnlHelper() {
		try {
			InputStream xml = getClass().getResourceAsStream("/file_properties.xml");
			JWNL.initialize(xml);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		wordnet = Dictionary.getInstance();
	}
	
	public boolean isInitialized() {
		return JWNL.isInitialized();
	}
	
	/**
	 * This method will get all the synonyms of the given word.
	 * 
	 * @param sense
	 * @return ArrayList<ArrayList<Synset>>
	 */
	public ArrayList<Synset> getSynonyms(IndexWord word) {
		ArrayList<Synset> listSense = new ArrayList<Synset>();
		try {
			Synset[] senses = word.getSenses();
			
			if(senses.length > 0) {
			
			}
			
		} catch (JWNLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listSense;
	}
	
	/**
	 * This will find the appropriate word based on
	 * the part of speech tag and the actual word
	 * 
	 * @param pos
	 * @param word
	 * @return IndexWord
	 * @throws JWNLException
	 */
	public IndexWord getWord(POS pos, String word) throws JWNLException {
		IndexWord indexWord = wordnet.getIndexWord(pos, word);
		return indexWord;
	}
	
	/**
	 * Converts the POS that comes from Stanford Parser
	 * to appropriate POS in JWNL
	 * 
	 * @param pos
	 * @return POS
	 */
	public POS getPOS(String pos) {
		
		if (pos.contains("VB")) {
			return POS.VERB;
		} else if (pos.contains("JJ")) {
			return POS.ADJECTIVE;
		} else if (pos.contains("RB")) {
			return POS.ADVERB;
		} else if (pos.contains("NN")) {
			return POS.NOUN;
		}
		//oooops! that's an alien pos. XD
		return null;
	}
}
