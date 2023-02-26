package com.masai.dao;

import java.util.List;

import com.masai.dto.Employee;
import com.masai.dto.Complain;
import com.masai.exception.SomeThingWrongException;

public interface EmployeeDao {

	String registerEmployee(Employee employee) throws SomeThingWrongException;

	public boolean loginEmployee(String username, String password) throws SomeThingWrongException;

	int complainRegister(String type, String descComplain) throws SomeThingWrongException;

	List<Complain> getStatusOfComplain(int complainId) throws SomeThingWrongException;

	List<Complain> getComplainHistory() throws SomeThingWrongException;

	String changePassword(String username, String password, String newpassword) throws SomeThingWrongException;

}
