package pup.thesis.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Synset;
import pup.thesis.helper.JwnlHelper;
import pup.thesis.nlu.CoreParser;
import pup.thesis.nlu.WordSynonym;
import edu.stanford.nlp.trees.Tree;

/**
 * Servlet implementation class DietfixController
 */
@WebServlet("/DietfixController")
public class DietfixController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private WordSynonym synonym;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DietfixController() {
        super();
        // TODO Auto-generated constructor stub
        
        
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		CoreParser parser = new CoreParser();
		synonym = new WordSynonym();
		JwnlHelper help = new JwnlHelper();
		IndexWord word;
		
		try {
			word = help.getWord(POS.ADJECTIVE, "pretty");
			ArrayList<Synset> sets = help.getSynonyms(word);
			//ArrayList<String> synonyms = synonym.synset2String(sets);
			request.setAttribute("word", sets);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		String input = (String)request.getParameter("input");
		
		ArrayList<String> result = parser.getTokens(input);
		ArrayList<String> result2 = parser.getTags(input);
		ArrayList<String> result3 = parser.getLemma(input);
		Tree parseTree = parser.getParseTree(input);
		
		
		request.setAttribute("tokens", result);
		request.setAttribute("tags", result2);
		request.setAttribute("lemma", result3);
		request.setAttribute("tree", parseTree);
		
		request.setAttribute("path", getServletContext().getRealPath("/"));
		
		
		
		//ArrayList<ArrayList<String>> synonyms = new ArrayList<ArrayList<String>>();
		//synonyms = getSynonyms(result3, result2);
		//request.setAttribute("synonyms", synonyms);
		RequestDispatcher view = request.getRequestDispatcher("result.jsp");
		
		view.forward(request, response);
	}
	
	private ArrayList<ArrayList<String>> getSynonyms(ArrayList<String> lemma, ArrayList<String> posTags) {
		ArrayList<ArrayList<String>> synonyms = new ArrayList<ArrayList<String>>();
		ArrayList<Synset> synsets = new ArrayList<Synset>();
		
		for(int i = 0; i < lemma.size(); i++) {
			try {
				synsets = synonym.getSynonyms(lemma.get(i), posTags.get(i));
				if(!synsets.isEmpty()) {
					synonyms.add(synonym.synset2String(synsets));
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return synonyms;
	}
	
	protected String processToNLU(String input) {
		String query = "";
		
		return query;
	}
}
