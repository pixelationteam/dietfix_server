package pup.thesis.nlu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.PointerType;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Word;
import net.didion.jwnl.data.relationship.Relationship;
import pup.thesis.helper.JwnlHelper;
import pup.thesis.helper.MysqlHelper;

import com.mysql.jdbc.log.Log;

/**
 * Class that will determine whether
 * a word exist on the database. If the
 * word doesn't exist in the database, it will
 * find the synonyms of that word which could
 * possibly be on the database.
 * 
 * This class also utilizes Reinforcement Learning
 * 
 * 
 * @since 05-20-2013
 * @author paulzed
 *
 */
public class WordSynonym {
	
	private JwnlHelper helper;
	private MysqlHelper sqlHelper;
	private Log log;
	
	/**
	 * 
	 * 
	 * @param firstWord
	 * @param firstPOS
	 * @param secondWord
	 * @param secondPOS
	 * @return
	 */
	public int getPercetageOfRelevance(String firstWord, POS firstPOS, String secondWord, POS secondPOS) {
		
		helper = new JwnlHelper();
		IndexWord _firstWord, _secondWord;
		Relationship rel;
		int percentage = 0;
		Synset[] set1 = null, set2 = null;
		
		try {
			
			_firstWord = helper.getWord(firstPOS, firstWord);
			_secondWord = helper.getWord(secondPOS, secondWord);
			
			 set1 = _firstWord.getSenses();
			 set2 = _secondWord.getSenses();
			
			rel = helper.getDepthOfRelationship(set1, set2, PointerType.SIMILAR_TO);
			
			percentage = rel.getDepth();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return percentage;
	}
	
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
		
		Iterator<Synset> i = sets.iterator();
		
		while(i.hasNext()) {
			Synset synset = i.next();
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
			set = sqlHelper.executeQuery("SELECT * FROM known_words");                   
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return relatedWord;
	}
	
	/**
	 * 
	 * @param input
	 * @param word
	 * @param wordTag
	 * @throws SQLException
	 * @return
	 */
	private boolean iterateInDb(String word, POS wordTag, ResultSet set) throws SQLException {
		
		helper = new JwnlHelper();
		String dbWord = "";
		String dbPos = "";
		POS _dbPos;
		RelatedWord rlWord = new RelatedWord();
		
		while(set.next()) {
			
			dbWord = set.getString(1);
			dbPos = set.getString(2);
			_dbPos = helper.getPOS(dbPos);
			
			if(isRelated(word, wordTag, dbWord, _dbPos)) {
				rlWord.setLabel(word);
				rlWord.setTag(wordTag);
			}
			else {
				
			}
		}
		
		//if none of the synonyms exist in the database
		return false;
	}
	
	/**
	 * 
	 * @param word
	 * @param synonym
	 * @return
	 */
	private boolean isRelated(String word, POS wordTag, String synonym, POS synonymTag) {
		return (word.equalsIgnoreCase(synonym) && wordTag.equals(synonymTag)) ? true : false;
	}
	
	
}
