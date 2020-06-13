package org.traccar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;


public class UserStatsToMySql {
	public static void upload(String [][] usersStats) throws Exception {

		Connection con = null;
		Datbase_con db = new Datbase_con();
		String insertNewPLAYERSQL = "INSERT INTO usersStats "
				+ " (version,  captureTime, activeUsers, activeDevices, requests, messagesReceived, messagesStored, mailSent, smsSent, geocoderRequests, geolocationRequests) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		try {

			con = db.getConnection(); //get Connection

			PreparedStatement stmt = con.prepareStatement(insertNewPLAYERSQL);
			stmt.setString(1, usersStats[0][1]);
			stmt.setString(2, usersStats[1][1]);
			stmt.setString(3, usersStats[2][1]);
			stmt.setString(4, usersStats[3][1]);
			stmt.setString(5, usersStats[4][1]);
			stmt.setString(6, usersStats[5][1]);
			stmt.setString(7, usersStats[6][1]);
			stmt.setString(8, usersStats[7][1]);
			stmt.setString(9, usersStats[8][1]);
			stmt.setString(10, usersStats[9][1]);
			stmt.setString(11, usersStats[10][1]);


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

	}// End of register
}
