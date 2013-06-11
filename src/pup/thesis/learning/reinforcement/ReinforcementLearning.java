package pup.thesis.learning.reinforcement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.PointerType;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.relationship.Relationship;
import pup.thesis.helper.JwnlHelper;
import pup.thesis.helper.MysqlHelper;
import pup.thesis.nlu.RelatedWord;

public abstract class ReinforcementLearning {
	
	private JwnlHelper helper;
	private MysqlHelper sqlHelper;
	private Reward reward;
	
	/**
	 * 
	 * 
	 * @param words
	 * @return
	 */
	public boolean learn(ArrayList<RelatedWord> words) {
		
		Iterator<RelatedWord> i = words.iterator();
		RelatedWord rlTemp = new RelatedWord();
		int iTemp = 0;
		String sTemp = "";
		String aTemp = "";
		POS pTemp = null;
		sqlHelper = new MysqlHelper();
		
		//iterates the whole arraylist
		//RelatedWord with lowest depth will be learned
		while(i.hasNext()) {
			rlTemp = i.next();
			
			//gets the word with lowest rank/depth
			if(iTemp > rlTemp.getRank()) {
				iTemp = rlTemp.getRank();
				sTemp = rlTemp.getLabel();
				pTemp = rlTemp.getTag();
				aTemp = rlTemp.getAction();
			}
		}
		
		//learn the freaking word
		//still no idea about the action thingy here...
		String query = "INSERT INTO known_words VALUES(" +
				"null, '" + sTemp + "', '" + pTemp.toString() + "', '" + aTemp + "' )";
		
		return (sqlHelper.updateDb(query)) ? true : false;
	}
	
	/**
	 * 
	 * 
	 * @param word
	 * @return
	 */
	private int iterateDbToGetDepth(IndexWord word) {
		
		ArrayList<RelatedWord> words = new ArrayList<RelatedWord>();
		reward = new Reward();
		
		String query = "select * from known_words";
		sqlHelper = new MysqlHelper();
		PointerType type = null;
		Relationship rel = null;
		
		int depth = 0;
		int r = 0;
		
		ResultSet set = sqlHelper.executeQuery(query);
		
		try {
			while(set.next()) {
				String _word = set.getString(1);
				String _tag = set.getString(2);
				
				POS pos = helper.getPOS(_tag);
				
				IndexWord dbWord = helper.getWord(pos, _word);
				
				Synset[] set1 = word.getSenses();
				Synset[] set2 = dbWord.getSenses();
				
				rel = helper.getDepthOfRelationship(set1, set2, type);
				depth = rel.getDepth();
				
				r = reward.getReward(depth);
				RelatedWord rw = new RelatedWord();
				
				rw.setLabel(_word);
				rw.setTag(pos);
				rw.setRank(r);
				words.add(rw);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JWNLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	private int getDepthOfWord(RelatedWord word) throws JWNLException {
		
		helper = new JwnlHelper();
		IndexWord index;
		int depth = 0;
		
		String label = word.getLabel();
		POS labelTag = word.getTag();
		
		index = helper.getWord(labelTag, label);
		depth = iterateDbToGetDepth(index);
	
		return -1;
	}
	
	
}
