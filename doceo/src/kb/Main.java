package kb;

import java.util.ArrayList;

import model.Triple;

public class Main {

	public static void main(String[] args) {

		// ArrayList<Triple> countryTripleList = new EntityDaoImpl()
		// .getAllEntities("country_txt", "country");
		//
		// new JenaTransformer().convertToJenaModel(countryTripleList);
		//
		// ArrayList<Triple> regionTripleList = new EntityDaoImpl()
		// .getAllEntities("region_txt", "region");
		//
		// new JenaTransformer().convertToJenaModel(regionTripleList);
		//
		ArrayList<Triple> entityRelationList = new EntityDaoImpl()
				.getEntityRelation("country_txt", "country", "region",
						"region_txt", "region");

		new JenaTransformer().convertToJenaModel("region", entityRelationList);
	}
}
