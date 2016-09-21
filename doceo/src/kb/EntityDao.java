package kb;

import java.util.ArrayList;

import model.Triple;

public interface EntityDao {

	ArrayList<Triple> getAllEntities(String entityName, String tblName);

	ArrayList<Triple> getEntityRelation(String entityName1, String tblName1,
			String relation, String entityName2, String tblName2);

	// void updateCountry(int id);
	// void deleteCountry(Entity country);
	// void addCountry(Entity country);

}
