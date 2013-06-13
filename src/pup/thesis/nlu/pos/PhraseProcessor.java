package pup.thesis.nlu.pos;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.security.auth.Subject;

import org.drools.runtime.StatefulKnowledgeSession;

import edu.stanford.nlp.dcoref.Dictionaries.Person;

import pup.thesis.knowledgebase.Variables;
import pup.thesis.knowledgebase.expert.Dietitian;
import pup.thesis.knowledgebase.expert.ExpertAnswer;
import pup.thesis.knowledgebase.expert.Food;
import pup.thesis.logging.App;
import pup.thesis.nlg.TextGenerator;
import pup.thesis.server.DietfixServer;
import pup.thesis.util.ClientData;

import simplenlg.features.Feature;
import simplenlg.features.Form;
import simplenlg.framework.CoordinatedPhraseElement;
import simplenlg.framework.NLGFactory;
import simplenlg.phrasespec.NPPhraseSpec;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.phrasespec.VPPhraseSpec;

public class PhraseProcessor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final TypedDep[] dependencies;
	private SPhraseSpec phr;
	private final HashMap<Integer,SPhraseSpec> specs = new HashMap<Integer,SPhraseSpec>();
	private final HashMap<Integer,NPPhraseSpec> npspecs = new HashMap<Integer,NPPhraseSpec>();
	private final HashMap<Integer,VPPhraseSpec> vpspecs = new HashMap<Integer,VPPhraseSpec>();
	private final HashMap<Integer,String> mappings;
	private final NLGFactory nfact;
	private final ClientData cdata;
	public PhraseProcessor(ClientData cdata,TypedDep[] td,HashMap<Integer,String> maps){
		nfact = DietfixServer.getTextGenerator().getNLGFactory();
		dependencies = td;
		phr = nfact.createClause();
		mappings = maps;
		this.cdata = cdata;
	}
	
	public TypedDep[] dependencies(){
		return dependencies;
	}
	public void addVerbxSubject(Word verb,Word subj){
		SPhraseSpec spe = nfact.createClause();
		spe.setSubject(nfact.createCoordinatedPhrase());
		spe.setVerb(nfact.createCoordinatedPhrase());
		spe.setObject(nfact.createCoordinatedPhrase());
		
		String word;
		if(mappings!=null && mappings.containsKey(subj.getIndex()) && subj.getTag().equals("NN")){
			word = parseVariable(mappings.get(subj.getIndex()));
		}
		else{
			word = subj.getLemma();
		}
		NPPhraseSpec np = nfact.createNounPhrase(word);
		VPPhraseSpec vp = nfact.createVerbPhrase(verb.getLemma());
		
		((CoordinatedPhraseElement) spe.getSubject()).addCoordinate(np);
		((CoordinatedPhraseElement) spe.getVerb()).addCoordinate(vp);
		
		vp.setFeature(Feature.PERSON, Person.YOU);
		
		//spe.setFeature(Feature.MODAL			,"should"  );
		spe.setFeature(Feature.FORM, Form.IMPERATIVE);
		npspecs.put(subj.getIndex(), np);
		vpspecs.put(verb.getIndex(), vp);
		specs.put(verb.getIndex(), spe);
	}
	private String parseVariable(String s){
		
		String[] s2 = s.split(":");
		Variables var = Variables.valueOf(s2[0]);
		if(var!=null||s2.length!=2){
			switch(var){
			case EXCERCISE:
				
				break;
			case FOOD:
				Dietitian dtn;
				try {
					dtn = new Dietitian(cdata);
				Food fd1 = dtn.getFood(s2[1]);
					return fd1.getDescription();
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException | SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				break;
			case USER_AGE:
				break;
			case USER_BMS:
				break;
			case USER_FIRSTNAME:
				break;
			case USER_FULLNAME:
				break;
			case USER_HEIGHT:
				break;
			case USER_WEIGHT:
				break;
			}
		}
		else{
			return "%INVALID%";
		}
		
		return null;
	}
	
	public void addNounDeterminer(Word nn,Word det){
		NPPhraseSpec np = npspecs.get(nn.getIndex());
		np.addPreModifier(det.getLemma());
	}
	
	public void addModifier(Word nn,Word mod){
		NPPhraseSpec np = npspecs.get(nn.getIndex());
		np.addModifier(mod.getLemma());		
	}
	
	public void setSpiObject(Word verb,Word obj){
		SPhraseSpec sp = specs.get(verb.getIndex());
		String word;
		if(mappings!=null && mappings.containsKey(obj.getIndex()) && obj.getTag().equals("NN")){
			word = parseVariable(mappings.get(obj.getIndex()));
		}
		else{
			word = obj.getLemma();
		}
		NPPhraseSpec np = nfact.createNounPhrase(word);
		((CoordinatedPhraseElement) sp.getVerb()).addPostModifier(np);
		
		npspecs.put(obj.getIndex(), np);
	}
	
	public void setSpdObject(Word verb,Word obj){
		SPhraseSpec sp = specs.get(verb.getIndex());
		String word;
		if(mappings!=null && mappings.containsKey(obj.getIndex()) && obj.getTag().equals("NN")){
			word = parseVariable(mappings.get(obj.getIndex()));
		}
		else{
			word = obj.getLemma();
		}
		NPPhraseSpec np = nfact.createNounPhrase(word);
		((CoordinatedPhraseElement) sp.getObject()).addCoordinate(np);
		
		npspecs.put(obj.getIndex(), np);
	}
	
	public List<SPhraseSpec> getSPhrases(){
		ArrayList<SPhraseSpec> sps = new ArrayList<SPhraseSpec>();
		for(SPhraseSpec ss : specs.values()){
			sps.add(ss);
		}
		return sps;
	}
	
	
	
}
