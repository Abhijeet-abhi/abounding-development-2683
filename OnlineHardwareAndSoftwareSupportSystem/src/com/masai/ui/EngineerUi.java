package com.masai.ui;

import java.util.List;
import java.util.Scanner;

import com.masai.dao.EngineerDao;
import com.masai.dao.EngineerDaoImpl;
import com.masai.dto.Complain;
import com.masai.exception.SomeThingWrongException;

public class EngineerUi {
	Scanner sc = new Scanner(System.in);
	EngineerDao eng = new EngineerDaoImpl();

	public boolean loginEngineerUi() throws SomeThingWrongException {
		System.out.println("\033[34m"); // blue
		System.out.println("--Welcome to Engineer Login Page--");
		System.out.println("\033[0m"); // black
		System.out.println("Enter Username: ");
		String username = sc.next();
		System.out.println("Enter Password: ");
		String password = sc.next();
		boolean check = eng.loginEngineer(username, password);
		return check;
	}

	public void getListOfProblemAssignByHodUi() throws SomeThingWrongException {
		List<Complain> lsComp = eng.getListOfProblemAssignByHod();
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
		if (lsComp.size() == 0) {
			System.out.println("\n");
			System.out.println("No data Found");
			System.out.println("\n");
		}
		System.out.println("Please choose one option from below: ");
		System.out.println("\033[31m"); // red
		System.out.println("1. Back");
		System.out.println("0. Mainmenu");
		System.out.println("\033[0m"); // black
		int eChoice = sc.nextInt();
		if (eChoice == 0) {
			Test.homeScreen();
		} else {
			Test.engineerJob();
		}
	}

	public void changeStatusOfComplainUi() throws SomeThingWrongException {
		System.out.println("Enter the ticket number: ");
		int ticketNum = sc.nextInt();
		System.out.println("Enter status of the ticket: ");
		String status = sc.next();
		String msg = eng.changeStatusOfComplain(ticketNum, status);
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
			Test.engineerJob();
		}
	}

	public void getListOfProblemAttendByEngineerUi() throws SomeThingWrongException {
		List<Complain> lsComp = eng.getListOfProblemAttendByEngineer();
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
		if (lsComp.size() == 0) {
			System.out.println("\n");
			System.out.println("No data Found");
			System.out.println("\n");
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
			Test.engineerJob();
		}
	}

	public void changePasswordUi() throws SomeThingWrongException {
		System.out.println("Enter username: ");
		String username = sc.next();
		System.out.println("Enter old password: ");
		String password = sc.next();
		System.out.println("Enter new password: ");
		String newpassword = sc.next();
		String msg = eng.changePassword(username, password, newpassword);
		System.out.println("\033[32m"); // green
		System.out.println(msg);
		 System.out.println("\033[0m"); //black
		System.out.println("Please choose one option from below: ");
		System.out.println("\033[31m"); // red
		System.out.println("1. Back");
		System.out.println("0. Mainmenu");
		System.out.println("\033[0m"); // black
		int eChoice = sc.nextInt();
		if (eChoice == 0) {
			Test.homeScreen();
		} else {
			Test.engineerJob();
		}
	}
}
