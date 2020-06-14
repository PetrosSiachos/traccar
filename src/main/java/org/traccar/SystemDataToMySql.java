package org.traccar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * A class that uploads System's data 
 * to MySql database.
 * @author siachos_petros (siachospetros@gmail.com)
 */

public class SystemDataToMySql {
	public static void upload(String [] systemdatainfo) throws Exception {

		Connection con = null;
		Datbase_con db = new Datbase_con();
		String insertNewPLAYERSQL = "INSERT INTO systemdatinfo "
				+ " (Operating_system's_name,  Operating_system's_version, Operating_system's_architecture, Java_runtime's_name, Java_runtime's_vendor, Java_runtime's_version, Memory_limit's_heap, Memory_limit's_non_heap, Character_encoding) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

		try {

			con = db.getConnection(); //get Connection

			PreparedStatement stmt = con.prepareStatement(insertNewPLAYERSQL);
			stmt.setString(1, systemdatainfo[0]);
			stmt.setString(2, systemdatainfo[1]);
			stmt.setString(3, systemdatainfo[2]);
			stmt.setString(4, systemdatainfo[3]);
			stmt.setString(5, systemdatainfo[4]);
			stmt.setString(6, systemdatainfo[5]);
			stmt.setString(7, systemdatainfo[6]);
			stmt.setString(8, systemdatainfo[7]);
			stmt.setString(9, systemdatainfo[8]);
			


			stmt.executeUpdate();

			stmt.close();
			db.close(); //close connection
		} catch (SQLIntegrityConstraintViolationException  e) {

			throw new Exception(e.getMessage());


		} catch (SQLException e) {

			throw new Exception(e.getMessage());

		} catch (Exception e) {

			throw new Exception(e.getMessage());

		} finally {

			if(con != null) // if connection is still open, then close.
				con.close();

		}

	}// End of upload
}
