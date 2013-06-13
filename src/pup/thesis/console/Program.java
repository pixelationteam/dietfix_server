package pup.thesis.console;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import net.didion.jwnl.JWNL;
import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Pointer;
import net.didion.jwnl.data.PointerType;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Word;
import net.didion.jwnl.dictionary.Dictionary;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.LabeledWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.trees.Tree;
import pup.thesis.expert.Dietitian;
import pup.thesis.expert.Food;
import pup.thesis.expert.Nutrients;
import pup.thesis.helper.MysqlHelper;

import pup.thesis.util.ClientData;
import pup.thesis.util.mysql.DBQuerier;
import pup.thesis.helper.StanfordHelper;
import pup.thesis.logging.App;
import pup.thesis.querygeneration.FoodQG;
import pup.thesis.server.DietfixServer;

import simplenlg.aggregation.AggregationRule;
import simplenlg.aggregation.Aggregator;
import simplenlg.aggregation.BackwardConjunctionReductionRule;
import simplenlg.aggregation.ClauseCoordinationRule;
import simplenlg.aggregation.ForwardConjunctionReductionRule;
import simplenlg.features.Feature;
import simplenlg.features.InterrogativeType;
import simplenlg.features.Tense;
import simplenlg.framework.CoordinatedPhraseElement;
import simplenlg.framework.ElementCategory;
import simplenlg.framework.LexicalCategory;
import simplenlg.framework.NLGElement;
import simplenlg.framework.NLGFactory;
import simplenlg.framework.PhraseCategory;
import simplenlg.framework.WordElement;
import simplenlg.lexicon.Lexicon;
import simplenlg.lexicon.NIHDBLexicon;
import simplenlg.lexicon.XMLLexicon;
import simplenlg.phrasespec.NPPhraseSpec;
import simplenlg.phrasespec.PPPhraseSpec;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.phrasespec.VPPhraseSpec;
import simplenlg.realiser.english.Realiser;

public class Program {

	
	
	
	public static void main(String...args) throws JWNLException{
		/*try {
			DBQuerier.start();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ClientData cdata = new ClientData();
		
		
		
		
		try {
			
			
			Dietitian dt = new Dietitian(cdata);
			FoodQG f = new FoodQG(cdata);
			
			List<Food> fd = dt.queryFood(f.generateQuery().fetch());
			

			App.log("HEHEHEHE:",fd.get(0).getNutrients().size()+":"+fd.get(0).getNutrient(Nutrients.LIPID)+":"+fd.get(0).getID()+":"+fd.get(0).getDescription());
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
         
         
         
         
         
        try {
			DBQuerier.stop();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
        
        testJWNL();
        //simpleNLG2();
        //testwnhelper();
 	    //testSimpleNLG();
	}
	
	public static void configureJWordNet() {
		// WARNING: This still does not work in Java 5!!!
		try {
			// initialize JWNL (this must be done before JWNL can be used)
			// See the JWordnet documentation for details on the properties file
			JWNL.initialize(new FileInputStream("resources/file_properties.xml"));
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}
	
	private static void testJWNL() throws JWNLException {
		configureJWordNet();
		Dictionary dictionary = Dictionary.getInstance();
		IndexWord word = dictionary.lookupIndexWord(POS.NOUN, "duck");
		System.out.println("Senses of the word 'duck':");
		Synset[] senses = word.getSenses();
		for (int i=0; i<senses.length; i++) {
		  Synset sense = senses[i];
		  System.out.println((i+1) + ". " + sense.getGloss());
		  Pointer[] holo = sense.getPointers(PointerType.PART_HOLONYM);
		  for (int j=0; j<holo.length; j++) {
		    Synset synset = (Synset) (holo[j].getTarget());
		    Word synsetWord = synset.getWord(0);
		    System.out.print("  -part-of-> " + synsetWord.getLemma());
		    System.out.println(" = " + synset.getGloss());
		  }
		}
		
		System.exit(1);
	}

	private static void simpleNLG2() {
		Lexicon lexicon = Lexicon.getDefaultLexicon();
         NLGFactory nlgFactory = new NLGFactory(lexicon);
         Realiser realiser = new Realiser(lexicon);
         
         
         
         
         SPhraseSpec p = nlgFactory.createClause();
         p.setSubject("Richard");
         p.setVerb("kiss");
         p.setObject("Bebang");
         p.addComplement("in the bathroom");
         
         
         SPhraseSpec p1 = nlgFactory.createClause();
         p1.setSubject("I");
         p1.setVerb("chase");
         p1.setObject("dog");
         
         p.setFeature(Feature.TENSE, Tense.FUTURE);
         
         //p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.HOW);
         //p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.HOW_MANY);
         //p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.WHO_SUBJECT);
         //p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.WHO_SUBJECT);
         
         App.log(realiser.realiseSentence(p));
         
         
         
         System.exit(1);
	}
	
	private static void test(){
		/*VPPhraseSpec verb = null;
        
        CoordinatedPhraseElement subj = nlgFactory.createCoordinatedPhrase(); 
        CoordinatedPhraseElement obj = nlgFactory.createCoordinatedPhrase(); 
        ArrayList<PPPhraseSpec> ppreps = new ArrayList<PPPhraseSpec>();
        for (Tree tree : parsetree.subTreeList()) {
       	 SFTag tag = SFTag.getEnum(tree.label().value());
       	 if(tag == SFTag.S){
       		 for (Tree tree2 : tree.getChildrenAsList()) {
					App.log(tag+":::"+tree2.label());
					SFTag childtag = SFTag.getEnum(tree2.value());
					ElementCategory ec = childtag.toElementCategory();

					App.log(tree2.yieldWords().toString());
					if(ec.equalTo(PhraseCategory.NOUN_PHRASE)){
						for(Tree nptree: tree2.getChildrenAsList()){
							ElementCategory npchildtag = SFTag.getEnum(nptree.value()).toElementCategory();
							if(npchildtag == LexicalCategory.NOUN||npchildtag == LexicalCategory.PRONOUN){
								App.log("SUbject is "+nptree.yieldWords().get(0));
								subj.addCoordinate(nptree.yieldWords().get(0));
							}
						}
					}
					else if(ec.equalTo(PhraseCategory.VERB_PHRASE)){
						String v = tree2.firstChild().yieldWords().get(0).toString()+" ";
						for(Tree nptree: tree2.getChildrenAsList()){
							ElementCategory npchildtag = SFTag.getEnum(nptree.value()).toElementCategory();
							if(npchildtag == PhraseCategory.NOUN_PHRASE){
								App.log("SUbject is "+toStringWord(nptree));
								subj.addCoordinate(nptree.yieldWords().get(0));
							}
							else if(npchildtag == PhraseCategory.VERB_PHRASE){
								SFTag childtag2 = SFTag.getEnum(nptree.value());
								ElementCategory ec2 = childtag2.toElementCategory();
								if(ec.equals(LexicalCategory.NOUN)){
									obj.addCoordinate(toStringWord(nptree));
								}
								else if(ec.equals(LexicalCategory.VERB)){
									v+= toStringWord(nptree);
								}
								else if(ec.equals(LexicalCategory.PREPOSITION)){
									PPPhraseSpec spp = nlgFactory.createPrepositionPhrase(toStringWord(nptree));
									ppreps.add(spp );
								}
								App.log("SUbject is "+toStringWord(nptree));
								verb = nlgFactory.createVerbPhrase(v);
							}
							else if(npchildtag == PhraseCategory.PREPOSITIONAL_PHRASE){
								App.log("SUbject is "+toStringWord(nptree));
								subj.addCoordinate(nptree.yieldWords().get(0));
								PPPhraseSpec spp = nlgFactory.createPrepositionPhrase(toStringWord(nptree));
								ppreps.add(spp );
							}
						}
					}
					else if(ec.equalTo(PhraseCategory.PREPOSITIONAL_PHRASE)){
						
					}
					else if(ec.equalTo(PhraseCategory.ADVERB_PHRASE)){
						
					}
					else if(ec.equalTo(PhraseCategory.ADJECTIVE_PHRASE)){
						
					}
					
				}
       		 p.setSubject(subj);
       		 p.setVerb(verb);
       		 p.setObject(obj);
       		 for (PPPhraseSpec prs3 : ppreps) {
					p.addComplement(prs3);
				}
       	 }
       	 else{
       		// App.log(tree.label()+":"+tree.pennString());
       	 }
			
		}
		 App.log("d");
        App.log(realiser.realiseSentence(p));
		*/
	}
	

	private static String toStringWord(Tree t){
		StringBuilder sb = new StringBuilder();
		
		for (HasWord tree : t.yieldHasWord()) {
			sb.append(tree.word()).append(" ");
		}
		
		return sb.toString();
	}
	private static void testwnhelper() {
		Connection con;
		
		
	}

	private static void testSimpleNLG() {
		Lexicon lexicon = Lexicon.getDefaultLexicon();
        NLGFactory nlgFactory = new NLGFactory(lexicon);
        Realiser realiser = new Realiser(lexicon);
	    VPPhraseSpec v = nlgFactory.createVerbPhrase("catch");
	    v.setFeature(Feature.TENSE, Tense.PAST);
		//App.log("", realiser.realise(v));

        SPhraseSpec s2 = nlgFactory.createClause("John", "sing", "a song");
        SPhraseSpec s3 = nlgFactory.createClause("John", "kick", "a song");
        //CoordinatedPhraseElement c = nlgFactory.createCoordinatedPhrase();
        // may revert to nlgFactory.createCoordinatedPhrase( ) ;
        Aggregator agr = new Aggregator();
        
        agr.initialise();
        agr.addRule(new ClauseCoordinationRule());
        List<NLGElement> enl = agr.realise(Arrays.asList(new NLGElement[]{s2,s3}));
        for (NLGElement nlgElement : enl) {
        	nlgElement.setFeature(Feature.TENSE, Tense.PAST);
            App.log("",realiser.realiseSentence(nlgElement));
			
		}
	}
}
