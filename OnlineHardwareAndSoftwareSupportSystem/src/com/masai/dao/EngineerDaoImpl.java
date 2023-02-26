package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.masai.dto.Complain;
import com.masai.dto.ComplainImpl;
import com.masai.dto.Engineer;
import com.masai.dto.EngineerImpl;
import com.masai.exception.SomeThingWrongException;
import com.masai.utility.DButil;

public class EngineerDaoImpl implements EngineerDao {
	Engineer eng = new EngineerImpl();

	@Override
	public boolean loginEngineer(String EngUserName, String EngPass) throws SomeThingWrongException {
		Connection conn = null;

		try {
			conn = DButil.provideConnection();
			PreparedStatement ps = conn
					.prepareStatement("select * from engineer where engUsername = ? AND engPassword = ?");

			ps.setString(1, EngUserName);
			ps.setString(2, EngPass);

			ResultSet x = ps.executeQuery();
			if (x.next()) {
				eng.setEngUserName(EngUserName);
				return true;
			}
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
		return false;
	}

	@Override
	public List<Complain> getListOfProblemAssignByHod() throws SomeThingWrongException {
		Connection conn = null;
		String engusername = eng.getEngUserName();
		List<Complain> lsComplain = new ArrayList<>();

		try {
			conn = DButil.provideConnection();
			String query = "Select * from complainBook where AssignEngineer=?";
			PreparedStatement ps = conn.prepareStatement(query);

			ps.setString(1, engusername);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int sno = rs.getInt("sno");
				int ticketNo = rs.getInt("ticketnumber");
				String type = rs.getString("type");
				String desc = rs.getString("description");
				String status = rs.getString("statusOfComplain");
				String assignEngineer = rs.getString("assignEngineer");
				String complainByEmployee = rs.getString("complainByEmployee");
				Complain comp = new ComplainImpl(sno, ticketNo, type, desc, status, assignEngineer, complainByEmployee);
				lsComplain.add(comp);
			}
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
		return lsComplain;
	}

	@Override
	public String changeStatusOfComplain(int ticketNum, String status) throws SomeThingWrongException {
		Connection conn = null;
		String message = "Unable to update staus for the ticket number " + ticketNum;
		try {
			conn = DButil.provideConnection();
			String query = "UPDATE complainBook SET StatusOfComplain = ? WHERE TicketNumber = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, status);
			ps.setInt(2, ticketNum);
			if (ps.executeUpdate() > 0) {
				System.out.println("Status Updated");
				String query2 = "select * from complainBook where ticketnumber=?";
				PreparedStatement ps2 = conn.prepareStatement(query2);
				ps2.setInt(1, ticketNum);
				ResultSet rs = ps2.executeQuery();
				message = "Sucessfully updated status to " + status + " for the ticket number " + ticketNum;
				if (rs.next() && status.equalsIgnoreCase("completed")) {
					String query3 = " insert into complainHistory(Ticketnumber, Type, Description, StatusOfComplain, AssignEngineer, ComplainByEmployee) values(?, ?, ?, ?, ?, ?)";
					PreparedStatement ps3 = conn.prepareStatement(query3);
					ps3.setInt(1, rs.getInt("TicketNumber"));
					ps3.setString(2, rs.getString("Type"));
					ps3.setString(3, rs.getString("description"));
					ps3.setString(4, rs.getString("statusOfComplain"));
					ps3.setString(5, rs.getString("AssignEngineer"));
					ps3.setString(6, rs.getString("ComplainByEmployee"));
					if (ps3.executeUpdate() > 0) {
						ComplainDaoImpl cm = new ComplainDaoImpl();
						cm.deleteTicketComplainBook(ticketNum);
						message = "Sucessfully updated status to " + status + " for the ticket number " + ticketNum;
					}
				}
			}
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
		return message;
	}

	@Override
	public List<Complain> getListOfProblemAttendByEngineer() throws SomeThingWrongException {
		Connection conn = null;
		String engusername = eng.getEngUserName();
		List<Complain> lsComp = new ArrayList<>();
		try {
			conn = DButil.provideConnection();
			String query = "SELECT c.Sno, c.TicketNumber, c.type, c.Description, c.StatusOfComplain, c.AssignEngineer, c.ComplainByEmployee\n"
					+ "FROM complainBook c\n" + "WHERE c.AssignEngineer = ?\n" + "UNION ALL\n"
					+ "SELECT ch.Sno, ch.TicketNumber, ch.type, ch.Description, ch.StatusOfComplain, ch.AssignEngineer, ch.ComplainByEmployee\n"
					+ "FROM complainHistory ch\n" + "WHERE ch.AssignEngineer = ?;";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, engusername);
			ps.setString(2, engusername);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int sNo = rs.getInt("sno");
				int tNum = rs.getInt("TicketNumber");
				String type = rs.getString("type");
				String cDesc = rs.getString("description");
				String cStatus = rs.getString("StatusOfComplain");
				String cAssignEngg = rs.getString("AssignEngineer");
				String cByEmployee = rs.getString("ComplainByEmployee");
				Complain comp = new ComplainImpl(sNo, tNum, type, cDesc, cStatus, cAssignEngg, cByEmployee);
				lsComp.add(comp);
			}
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
		return lsComp;
	}

	@Override
	public String changePassword(String username, String password, String newpassword) throws SomeThingWrongException {
		Connection conn = null;
		String message = "\n Something went wrong please try again \n";

		try {
			conn = DButil.provideConnection();
			PreparedStatement ps = conn
					.prepareStatement("update Engineer set EngPassword= ? where EngUserName= ? AND EngPassword= ? ");

			ps.setString(1, newpassword);
			ps.setString(2, username);
			ps.setString(3, password);

			if (ps.executeUpdate() > 0) {
				message = "Password changed Successfully!";
			}
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
		return message;
	}

}
