package pup.thesis.nlu;

import java.util.ArrayList;

import net.didion.jwnl.data.POS;

public class RelatedWord {
	
	private int rank = 0;
	private String label = "";
	private POS tag;
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public void setTag(POS tag) {
		this.tag = tag;
	}
	
	public POS getTag() {
		return tag;
	}
	
}
