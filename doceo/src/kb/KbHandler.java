package kb;

import java.util.ArrayList;

import model.Triple;

public class KbHandler {

	public ArrayList<Triple> getTriples() {
		ArrayList<Triple> tripleList = new ArrayList<Triple>();

		tripleList.add(new Triple("Islamic State", "isA", "Terror Group"));
		tripleList.add(new Triple("Syria", "isA", "Country"));

		return tripleList;
	}

}
