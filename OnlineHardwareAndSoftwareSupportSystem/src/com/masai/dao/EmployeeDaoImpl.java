package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.masai.dto.Complain;
import com.masai.dto.ComplainImpl;
import com.masai.dto.Employee;
import com.masai.dto.EmployeeImpl;
import com.masai.exception.SomeThingWrongException;
import com.masai.utility.DButil;

public class EmployeeDaoImpl implements EmployeeDao {
	Employee emp = new EmployeeImpl();

	@Override
	public String registerEmployee(Employee employee) throws SomeThingWrongException {
		Connection conn = null;
		String message = "Not Inserted";

		try {
			conn = DButil.provideConnection();
			PreparedStatement ps = conn
					.prepareStatement("insert into Employee(empName,empUserName,empPassword) values(?,?,?)");

			ps.setString(1, employee.getEmpName());
			ps.setString(2, employee.getEmpUserName());
			ps.setString(3, employee.getEmpPassword());

			int x = ps.executeUpdate();

			if (x > 0)
				message = "Employee Registration succesfully";

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
	public boolean loginEmployee(String username, String password) throws SomeThingWrongException {
		Connection conn = null;

		try {
			conn = DButil.provideConnection();
			PreparedStatement ps = conn
					.prepareStatement("select * from Employee where empUsername = ? AND empPassword = ?");

			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet x = ps.executeQuery();

			if (x.next()) {
				emp.setEmpUserName(username);
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
	public int complainRegister(String type, String descComplain) throws SomeThingWrongException {
		Connection conn = null;
		String empUsername = emp.getEmpUserName();
		Random rand = new Random();
		int num = rand.nextInt(10000);
		String message = "Unable to register the Complain";

		try {
			conn = DButil.provideConnection();
			String query = "Insert into complainBook(TicketNumber, Type, Description, ComplainByEmployee) values(?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, num);
			ps.setString(2, type);
			ps.setString(3, descComplain);
			ps.setString(4, empUsername);
			if (ps.executeUpdate() > 0) {
				message = "Complain Registered Succesfully";
				System.out.println(message);
			} else {
				message = "Unable to register the Complain";
				System.out.println(message);
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
		return num;
	}

	@Override
	public List<Complain> getStatusOfComplain(int complainId) throws SomeThingWrongException {
		Connection conn = null;
		String eUsername = emp.getEmpUserName();
		List<Complain> lsComp = new ArrayList<>();
		try {
			conn = DButil.provideConnection();
			String query = "SELECT c.Sno, c.TicketNumber,c.type, c.Description, c.StatusOfComplain, c.AssignEngineer, c.ComplainByEmployee\n"
					+ "FROM complainBook c\n" + "WHERE c.complainByEmployee = ? && c.ticketNumber=?\n" + "UNION ALL\n"
					+ "SELECT ch.Sno, ch.TicketNumber,ch.type, ch.Description, ch.StatusOfComplain, ch.AssignEngineer, ch.ComplainByEmployee\n"
					+ "FROM complainHistory ch\n" + "WHERE ch.complainByEmployee = ? && ch.ticketNumber=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, eUsername);
			ps.setInt(2, complainId);
			ps.setString(3, eUsername);
			ps.setInt(4, complainId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int tNum = rs.getInt("TicketNumber");
				String cType = rs.getString("type");
				String cDesc = rs.getString("description");
				String cStatus = rs.getString("StatusOfComplain");
				String cAssignEngg = rs.getString("AssignEngineer");
				Complain comp = new ComplainImpl(tNum, cType, cDesc, cStatus, cAssignEngg);
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
	public List<Complain> getComplainHistory() throws SomeThingWrongException {
		Connection conn = null;
		String username = emp.getEmpUserName();
		List<Complain> lsComp = new ArrayList<>();
		try {
			conn = DButil.provideConnection();
			String query = "SELECT c.Sno, c.TicketNumber,c.type, c.Description, c.StatusOfComplain, c.AssignEngineer, c.ComplainByEmployee FROM complainBook c WHERE c.ComplainByEmployee = ? "
					+ "UNION ALL "
					+ "SELECT ch.Sno, ch.TicketNumber,ch.type,  ch.Description, ch.StatusOfComplain, ch.AssignEngineer, ch.ComplainByEmployee "
					+ "FROM complainHistory ch WHERE ch.ComplainByEmployee = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, username);
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
		} catch (Exception e) {
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
		String message = "Password Not Updated";

		try {
			conn = DButil.provideConnection();
			PreparedStatement ps = conn
					.prepareStatement("update employee set emppassword= ? where empUserName= ? AND empPassword= ? ");

			ps.setString(1, newpassword);
			ps.setString(2, username);
			ps.setString(3, password);

			int x = ps.executeUpdate();

			if (x > 0) {

				message = "Your password change successfully !";
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
