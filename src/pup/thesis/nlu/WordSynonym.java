package pup.thesis.nlu;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.PointerType;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Word;
import pup.thesis.helper.JwnlHelper;
import pup.thesis.helper.MysqlHelper;

/**
 * Class that will determine whether
 * a word exist on the database. If the
 * word doesn't exist in the database, it will
 * find the synonyms of that word which could
 * possibly be on the database.
 * 
 * @since 05-20-2013
 * @author paulzed
 *
 */
public class WordSynonym {
	
	private JwnlHelper helper;
	private MysqlHelper sqlHelper;
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	public ArrayList<Synset> getRelatedWords(String input, String stringPos) {
		ArrayList<Synset> synonyms = new ArrayList<Synset>();
		helper = new JwnlHelper();
		IndexWord word;
		POS pos;
		
		try {
			pos = helper.getPOS(stringPos);
			PointerType type = identifyPointerType(pos);
			word = helper.getWord(pos, input);
			
			if(type == PointerType.HYPERNYM) {
				synonyms = helper.getHypernym(word);
			} 
			else if (type == PointerType.SIMILAR_TO) {
				synonyms = helper.getSynonyms(word);
			}
			
		} catch (JWNLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return synonyms;
	}
	
	private PointerType identifyPointerType(POS pos) {
		PointerType pointer = null;
		
		if(pos.equals(POS.VERB) || pos.equals(POS.NOUN)) {
			pointer =  PointerType.HYPERNYM;
		}
		else if(pos.equals(POS.ADJECTIVE)) {
			pointer = PointerType.SIMILAR_TO;
		}
		
		return pointer;
	}
	
	/**
	 * 
	 * @param sets
	 * @return
	 */
	public ArrayList<String> synset2String(ArrayList<Synset> sets) {
		ArrayList<String> synsets = new ArrayList<String>();
		
		Iterator i = sets.iterator();
		
		while(i.hasNext()) {
			Synset synset = (Synset)i.next();
			Word[] words = synset.getWords();
			
			for(int x = 0; x < words.length; x++) {
				synsets.add(words[x].getLemma());
			}
		}
		
		return synsets;
	}
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	public String getRelatedWordInDb(String input) {
		String relatedWord = "";
		
		ResultSet set;
		sqlHelper = new MysqlHelper();
		
		try {
			set = sqlHelper.executeQuery("");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return relatedWord;
	}
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	private boolean iterateInDb(ResultSet set) {
		
		//if none of the synonyms exist in the database
		return false;
	}
	
	/**
	 * 
	 * @param word
	 * @param synonym
	 * @return
	 */
	private boolean isRelated(String word, String synonym) {
		return (word.equalsIgnoreCase(synonym)) ? true : false;
	}
	
	
}
