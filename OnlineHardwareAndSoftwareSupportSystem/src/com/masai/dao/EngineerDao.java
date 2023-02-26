package com.masai.dao;

import java.util.List;
import com.masai.dto.Complain;
import com.masai.exception.SomeThingWrongException;

public interface EngineerDao {

	public boolean loginEngineer(String EngUserName, String EngPass) throws SomeThingWrongException;

	List<Complain> getListOfProblemAssignByHod() throws SomeThingWrongException;

	String changeStatusOfComplain(int ticketNum, String status) throws SomeThingWrongException;

	List<Complain> getListOfProblemAttendByEngineer() throws SomeThingWrongException;

	String changePassword(String username, String password, String newpassword) throws SomeThingWrongException;

}
