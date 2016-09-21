package model;

import java.util.ArrayList;

public class Sentence {
	private String sentence;
	private ArrayList<Mention> mentions;

	public Sentence(String sentence) {
		this.setSentence(sentence);
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public ArrayList<Mention> getMentions() {
		return mentions;
	}

	public void setMentions(ArrayList<Mention> mentions) {
		this.mentions = mentions;
	}
	
	public String toString() {
		return "sentence:" + getSentence() + "--mentions:" + getMentions();
	}

}
