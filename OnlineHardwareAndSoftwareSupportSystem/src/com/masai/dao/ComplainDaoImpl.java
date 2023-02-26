package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.masai.exception.SomeThingWrongException;
import com.masai.utility.DButil;

public class ComplainDaoImpl implements ComplainDao {

	@Override
	public void deleteTicketComplainBook(int ticketNum) throws SomeThingWrongException {
		Connection conn = null;
		try {
			conn = DButil.provideConnection();
			String query = "Delete from complainBook where ticketNumber=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, ticketNum);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SomeThingWrongException();
		} finally {
			try {
				DButil.closeConnection(conn);
			} catch (SQLException e) {
				throw new SomeThingWrongException();
			}
		}
	}

}
