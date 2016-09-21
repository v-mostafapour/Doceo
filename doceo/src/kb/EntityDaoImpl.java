package kb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Triple;

public class EntityDaoImpl implements EntityDao {
	private Connection connection;
	private Statement statement;

	@Override
	public ArrayList<Triple> getAllEntities(String entityName, String tblName) {
		ArrayList<Triple> tripleList = new ArrayList<Triple>();
		String query = "SELECT " + entityName + " FROM `gtddb`.`" + tblName
				+ "`";
		ResultSet rs = null;
		try {
			Class.forName(DBUtils.JDBC_DRIVER);

			connection = DriverManager.getConnection(DBUtils.DB_URL,
					DBUtils.USER, DBUtils.PASS);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next()) {
				String name = rs.getString(entityName);
				tripleList.add(new Triple(name.trim(), "rdf:type", "dbo:"
						+ tblName + ""));

			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return tripleList;
	}

	@Override
	public ArrayList<Triple> getEntityRelation(String entityName1,
			String tblName1, String relation, String entityName2,
			String tblName2) {

		// SELECT country_txt, region_txt FROM gtddb.country JOIN gtddb.region
		// ON country.region = region.region;

		ArrayList<Triple> tripleList = new ArrayList<Triple>();
		String query = "SELECT " + entityName1 + "," + entityName2
				+ " FROM `gtddb`.`" + tblName1 + "` JOIN `gtddb`.`" + tblName2
				+ "` ON " + tblName1 + "." + relation + "=" + tblName2 + "."
				+ relation + "";
		ResultSet rs = null;
		try {
			Class.forName(DBUtils.JDBC_DRIVER);

			connection = DriverManager.getConnection(DBUtils.DB_URL,
					DBUtils.USER, DBUtils.PASS);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next()) {
				String name = rs.getString(entityName1);
				String name2 = rs.getString(entityName2);
				tripleList.add(new Triple(name.trim(), relation, name2.trim()));

			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return tripleList;
	}
}
