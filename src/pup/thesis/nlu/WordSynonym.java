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
import pup.thesis.learning.reinforcement.ReinforcementLearning;

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
public class WordSynonym extends ReinforcementLearning {
	
	private JwnlHelper helper;
	private MysqlHelper sqlHelper;
	private Log log;
	
	/**
	 * 
	 * 
	 * @param word
	 * @return
	 * @throws SQLException
	 */
	public boolean isWordExistInDb(ArrayList<RelatedWord> word) throws SQLException {
		
		ArrayList<RelatedWord> kWords = new ArrayList<RelatedWord>();
		
		kWords = iterateInDb();
		
		Iterator<RelatedWord> i2 = kWords.iterator();
		Iterator<RelatedWord> i = word.iterator();
		
		while(i.hasNext()) {
			while(i2.hasNext()) {
				RelatedWord kWord = i2.next();
				RelatedWord _word = i.next();
				
				if(isSame(_word.getLabel(), _word.getTag(), kWord.getLabel(), kWord.getTag())) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean learnWord() {
		RelatedWord word = new RelatedWord();	
			
		return false;
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
	 * @param set
	 * @throws SQLException
	 * @return
	 */
	private ArrayList<RelatedWord> iterateInDb() throws SQLException {
		
		ArrayList<RelatedWord> listRelated = new ArrayList<RelatedWord>();
		helper = new JwnlHelper();
		sqlHelper = new MysqlHelper();
		RelatedWord rlWord = new RelatedWord();
		
		ResultSet set = sqlHelper.executeQuery("SELECT * FROM known_words");
		
		while(set.next()) {
			rlWord.setLabel(set.getString(1));
			rlWord.setTag(helper.getPOS(set.getString(2)));
			rlWord.setAction(set.getString(3));
			listRelated.add(rlWord);
		}
		

		return listRelated;
	}
	
	
	/**
	 * 
	 * @param word
	 * @return
	 * @throws SQLException
	 */
	public boolean updateKnownWords(RelatedWord word) throws SQLException {
		
		sqlHelper = new MysqlHelper();
		
		String query = "INSERT INTO known_words VALUES(" +
				"null, " + word.getLabel() + ", " + word.getTag() + ", " ;
		
		return (sqlHelper.updateDb(query)) ? true : false;
	}
	
	/**
	 * 
	 * @param word
	 * @param synonym
	 * @return
	 */
	private boolean isSame(String word, POS wordTag, String synonym, POS synonymTag) {
		return (word.equalsIgnoreCase(synonym) && wordTag.equals(synonymTag)) ? true : false;
	}
	
}
