package pup.thesis.helper;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.Morphology;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.PTBTokenizer.PTBTokenizerFactory;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;

/**
 * 
 * This class will serve as a helper to encapsulate
 * the complexity behind getting tags, tokens, parse tree, etc.
 * The class utilizes the Stanford parser to get tags, tokens, parse tree, etc.
 * 
 * @since 05-17-2013
 * @author paulzed
 *
 */

public class StanfordHelper {
	
	/**
	 * 
	 * @param input
	 * @param lparser
	 * @return
	 */
	public static Tree getParseTree(String input, LexicalizedParser lparser) {
		
		String sent = input;
		
		TokenizerFactory<CoreLabel> tf = PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
		
		List<CoreLabel> list = tf.getTokenizer(new StringReader(input)).tokenize();
		
		Tree parseTree = lparser.apply(list);
		
		return parseTree;
	}	
	
	/**
	 * 
	 * @param input
	 * @param lparser
	 * @return
	 */
	public static ArrayList<String> getTokens(String input, LexicalizedParser lparser) {
		ArrayList<String> tokens = new ArrayList<String>();
		
		Tree parseTree = getParseTree(input, lparser);
		
		for(CoreLabel label : parseTree.taggedLabeledYield()) {
			tokens.add(label.word());
		}
		
		return tokens;
	}
	
	/**
	 * 
	 * @param input
	 * @param lparser
	 * @return
	 */
	public static ArrayList<String> getTags(String input, LexicalizedParser lparser) {
		ArrayList<String> tags = new ArrayList<String>();		
		
		Tree parseTree = getParseTree(input, lparser);
		
		for(CoreLabel label : parseTree.taggedLabeledYield()) {
			tags.add(label.tag());
		}
		
		return tags;
	}
	
	public static ArrayList<String> getLemmas(ArrayList<String> word, ArrayList<String> tag) {
		ArrayList<String> lemmas = new ArrayList<String>();
		
		Morphology morp = new Morphology();
		
		for(int i = 0; i < word.size(); i++) {
			lemmas.add(morp.lemma(word.get(i), tag.get(i)));
		}
		
		return lemmas;
	}
	
}
