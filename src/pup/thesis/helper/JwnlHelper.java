package pup.thesis.helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import net.didion.jwnl.JWNL;
import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.PointerType;
import net.didion.jwnl.data.PointerUtils;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.list.PointerTargetNode;
import net.didion.jwnl.data.list.PointerTargetNodeList;
import net.didion.jwnl.data.relationship.Relationship;
import net.didion.jwnl.data.relationship.RelationshipFinder;
import net.didion.jwnl.data.relationship.RelationshipList;
import net.didion.jwnl.dictionary.Dictionary;

import com.mysql.jdbc.log.Log;

public class JwnlHelper {
	
	private Dictionary wordnet;
	private Log log;
	
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
	 * 
	 * 
	 * @param first
	 * @param second
	 * @param type
	 * @return
	 * @throws JWNLException
	 */
	public Relationship getDepthOfRelationship(Synset[] first, Synset[] second, PointerType type) throws JWNLException {
		
		for(int x = 0; x < first.length; x++) {
			for(int y = 0; y < second.length; y++) {
				RelationshipList list = RelationshipFinder.getInstance().findRelationships(first[x], second[y], type);
				
				if(!list.isEmpty()) {
					return (Relationship) list.get(0);
				}
			}
		}
		
		return null;
	}
	
	/**
	 * This method will get all the synonyms of the given word.
	 * 
	 * @param sense
	 * @return ArrayList<ArrayList<Synset>>
	 */
	public ArrayList<Synset> getSynonyms(IndexWord word) {
		ArrayList<Synset> listSense = new ArrayList<Synset>();
		PointerTargetNodeList list;
		
		try {
			Synset[] senses = word.getSenses();
			if(senses.length > 0) {
				list = PointerUtils.getInstance().getSynonyms(senses[0]);
				
				Iterator i = list.iterator();
				
				while(i.hasNext()) {
					PointerTargetNode p = (PointerTargetNode)i.next();
					Synset s = p.getSynset();
					listSense.add(s);
				}
			}
			
		} catch (JWNLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listSense;
	}
	
	/**
	 * 
	 * @param word
	 * @return
	 */
	public ArrayList<Synset> getHypernym(IndexWord word) {
		ArrayList<Synset> listHypernym = new ArrayList<Synset>();
		PointerTargetNodeList list;
		
		try {
			Synset[] set = word.getSenses();
			list = PointerUtils.getInstance().getDirectHypernyms(set[0]);
			
			Iterator i = list.iterator();
			
			while(i.hasNext()) {
				PointerTargetNode node = (PointerTargetNode)i.next();
				Synset s = node.getSynset();
				listHypernym.add(s);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return listHypernym;
	}
	
	/**
	 * 
	 * @param word
	 * @return
	 */
	public ArrayList<Synset> getHyponym(IndexWord word) {
		ArrayList<Synset> listHyponym = new ArrayList<Synset>();
		PointerTargetNodeList list;
		
		try {
			Synset[] set = word.getSenses();
			list = PointerUtils.getInstance().getDirectHyponyms(set[0]);
			
			Iterator i = list.iterator();
			
			while(i.hasNext()) {
				PointerTargetNode node = (PointerTargetNode)i.next();
				Synset s = node.getSynset();
				listHyponym.add(s);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return listHyponym;
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
