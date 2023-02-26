package com.masai.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.masai.dao.EmployeeDao;
import com.masai.dao.EmployeeDaoImpl;
import com.masai.dto.Complain;
import com.masai.dto.Employee;
import com.masai.dto.EmployeeImpl;
import com.masai.exception.SomeThingWrongException;

public class EmployeeUi {
	Scanner sc = new Scanner(System.in);
	EmployeeDao emp = new EmployeeDaoImpl();

	public void registerEmployeeUi() throws SomeThingWrongException {
		System.out.println("Enter your name: ");
		String name = sc.next();
		System.out.println("Enter your username: ");
		String username = sc.next();
		System.out.println("Enter password: ");
		String password = sc.next();
		Employee employee = new EmployeeImpl(name, username, password);
		String msg = emp.registerEmployee(employee);
		System.out.println("\033[32m"); // green
		System.out.println(msg);
		System.out.println("\033[0m"); // black
		System.out.println("Please choose one option from below: ");
		System.out.println("\033[31m"); // red
		System.out.println("1. Back");
		System.out.println("0. Mainmenu");
		System.out.println("\033[0m"); // black
		int eChoice = sc.nextInt();
		if (eChoice == 0) {
			Test.homeScreen();
		} else {
			Test.employeeJob();
		}
	}

	public boolean loginEmplyeeUi() throws SomeThingWrongException {
		System.out.println("\033[34m"); // blue
		System.out.println("--Welcome to Employee Login Page--");
		System.out.println("\033[0m"); // black
		System.out.println("Enter Username: ");
		String username = sc.next();
		System.out.println("Enter Password: ");
		String password = sc.next();
		boolean check = emp.loginEmployee(username, password);
		return check;
	}

	public void complainRegisterUi() throws SomeThingWrongException {
		ArrayList<String> descComplain = new ArrayList<String>();
		System.out.println("Please enter a type issue you have from below: ");
		System.out.println("\033[33m"); // yellow
		System.out.println("1. Hardware");
		System.out.println("2. Software");
		System.out.println("\033[0m"); // black
		String type = "Software";
		int cType = sc.nextInt();
		if (cType == 1) {
			type = "Hardware";
		} else {
			type = "Software";
		}

		System.out.println("Please enter short description: ");
		System.err.println(
				"(Note:- After completing your description press the 'Enter key' then type 'end' to complete your statement)");
		String line;
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			descComplain.add(line);

			if (line != null && line.equalsIgnoreCase("END")) {
				int ticketNum = emp.complainRegister(type, descComplain.get(1));
				System.out.println("\033[32m"); // green
				System.out.println(
						"Your complain sucessfully logged in our system & your Ticket number is: " + ticketNum);
				System.out.println("\033[0m"); // black
				break;
			}
		}
		System.out.println("\n");
		System.out.println("Please choose one option from below: ");
		System.out.println("\033[31m"); // red
		System.out.println("1. Back");
		System.out.println("0. Mainmenu");
		System.out.println("\033[0m"); // black
		int eChoice = sc.nextInt();
		if (eChoice == 0) {
			Test.homeScreen();
		} else {
			Test.employeeJob();
		}
	}

	public void getStatusOfComplainUi() throws SomeThingWrongException {
		System.out.println("Enter the ticket number: ");
		int ticketNum = sc.nextInt();
		List<Complain> lsComp = emp.getStatusOfComplain(ticketNum);
		lsComp.forEach((comp) -> {
			System.out.println("======================================");
			System.out.println("Ticket no: " + comp.getcTicketNo());
			System.out.println("Complain type: " + comp.getType());
			System.out.println("Description: " + comp.getcDesc());
			System.out.println("Status of Complain: " + comp.getcStatus());
			System.out.println("Complain assigned to: " + comp.getcAssignEngg());
			System.out.println("======================================");
		});

		if (lsComp.size() != 0) {
			System.out.println("\n");
			System.out.println("Please choose one option from below: ");
			System.out.println("\033[31m"); // red
			System.out.println("1. Back");
			System.out.println("0. Mainmenu");
			System.out.println("\033[0m"); // black
			int eChoice = sc.nextInt();
			if (eChoice == 0) {
				Test.homeScreen();
			} else {
				Test.employeeJob();
			}
		} else {
			System.out.println("\033[32m"); // green
			System.out.println("Ticket number " + ticketNum + " may be incorrect please try again");
			System.out.println("\033[0m"); // black
			getStatusOfComplainUi();
		}

	}

	public void getComplainHistoryUi() throws SomeThingWrongException {
		List<Complain> lsComp = emp.getComplainHistory();
		lsComp.forEach((comp) -> {
			System.out.println("======================================");
			System.out.println("Complain SNo: " + comp.getcSno());
			System.out.println("Ticket no: " + comp.getcTicketNo());
			System.out.println("Complain type: " + comp.getType());
			System.out.println("Description: " + comp.getcDesc());
			System.out.println("Status of Complain: " + comp.getcStatus());
			System.out.println("Complain assigned to: " + comp.getcAssignEngg());
			System.out.println("Complain create by: " + comp.getcByEmployee());
			System.out.println("======================================");
		});

		if (lsComp.size() != 0) {
			System.out.println("\n");
			System.out.println("Please choose one option from below: ");
			System.out.println("\033[31m"); // red
			System.out.println("1. Back");
			System.out.println("0. Mainmenu");
			System.out.println("\033[0m"); // black
			int eChoice = sc.nextInt();
			if (eChoice == 0) {
				Test.homeScreen();
			} else {
				Test.employeeJob();
			}
		} else {
			System.out.println("\033[32m"); // green
			System.out.println("No data found");
			System.out.println("\033[0m"); // black
			getStatusOfComplainUi();
		}

	}

	public void changePasswordUi() throws SomeThingWrongException {
		System.out.println("Enter username: ");
		String username = sc.next();
		System.out.println("Enter old password: ");
		String password = sc.next();
		System.out.println("Enter new password: ");
		String newpassword = sc.next();
		String msg = emp.changePassword(username, password, newpassword);
		System.out.println("\n");
		System.out.println("\033[32m"); // green
		System.out.println(msg);
		System.out.println("\033[0m"); // black
		System.out.println("\n");
		System.out.println("Please choose one option from below: ");
		System.out.println("\033[31m"); // red
		System.out.println("1. Back");
		System.out.println("0. Mainmenu");
		System.out.println("\033[0m"); // black
		int eChoice = sc.nextInt();
		if (eChoice == 0) {
			Test.homeScreen();
		} else {
			Test.employeeJob();
		}

	}

}
