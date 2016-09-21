package pipeline;

import java.util.ArrayList;

public class ConcreteSentence {

	private String context;
	private ArrayList<ConcreteMention> mentions;

	private ArrayList<String> varliklar;
	private ArrayList<String> eylemler;

	public ConcreteSentence() {

		mentions = new ArrayList<ConcreteMention>();

	}

	// Varlik tip veya Eylem tip; varliklardan varlik listesi eylem lerden ise
	// cumlede gecen eylem listei olusturuluyor

	public void updatevarlikEylemList(ArrayList<ConcreteMention> entities) {
		for (ConcreteMention entity : entities) {
			separateVarlikEylem(entity);
		}
	}

	public void separateVarlikEylem(ConcreteMention entity) {
		String entitytype = entity.getType();
		if (entitytype.equalsIgnoreCase("varlik"))
			varliklar.add(entity.getName());
		else if (entitytype.equalsIgnoreCase("eylem"))
			eylemler.add(entity.getName());
	}

	// public void setParseTree(Tree parseTree) {
	// this.parseTree = parseTree;
	// }
	//
	// public Tree getParseTree() {
	// return parseTree;
	// }

	public ArrayList<ConcreteMention> getEntities() {
		return mentions;
	}

	public String getTextOfSenteence() {
		return getContex();
	}

	public void setEntities(ArrayList<ConcreteMention> entities) {
		this.mentions = entities;
	}

	public void setTextSenteence(String textSenteence) {
		this.setContent(textSenteence);
	}

	public void addEntity(ConcreteMention entity) {
		mentions.add(entity);

	}

	public String getContex() {
		return context;
	}

	public void setContent(String context) {
		this.context = context;
	}

	public ArrayList<String> getVarliklar() {
		return varliklar;
	}

	public void setVarliklar(ArrayList<String> varliklar) {
		this.varliklar = varliklar;
	}

	public ArrayList<String> getEylemler() {
		return eylemler;
	}

	public void setEylemler(ArrayList<String> eylemler) {
		this.eylemler = eylemler;
	}

}
