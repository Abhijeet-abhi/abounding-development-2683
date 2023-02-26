package com.masai.dao;

import java.util.List;

import com.masai.dto.Complain;
import com.masai.dto.Engineer;
import com.masai.exception.SomeThingWrongException;

public interface HodDao {

	boolean loginHod(String username, String password) throws SomeThingWrongException;

	String registerEngineer(Engineer engineer) throws SomeThingWrongException;

	List<Engineer> getRegisterEngineer() throws SomeThingWrongException;

	String deleteEngineer(String engUsername) throws SomeThingWrongException;

	List<Complain> getListOfAllComplain() throws SomeThingWrongException;

	String assigningProblemToEngineer(int ticketNo, String engUsername) throws SomeThingWrongException;

}
