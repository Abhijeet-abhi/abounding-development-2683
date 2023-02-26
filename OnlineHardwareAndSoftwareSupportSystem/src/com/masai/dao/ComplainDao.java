package com.masai.dao;

import com.masai.exception.SomeThingWrongException;

public interface ComplainDao {

	void deleteTicketComplainBook(int ticketNum) throws SomeThingWrongException;

}
