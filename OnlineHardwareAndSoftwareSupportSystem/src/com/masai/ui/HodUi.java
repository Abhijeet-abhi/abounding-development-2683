package com.masai.ui;

import java.util.List;
import java.util.Scanner;

import com.masai.dao.HodDao;
import com.masai.dao.HodDaoImpl;
import com.masai.dto.Complain;
import com.masai.dto.Engineer;
import com.masai.dto.EngineerImpl;
import com.masai.exception.SomeThingWrongException;

public class HodUi {
	Scanner sc = new Scanner(System.in);
	HodDao hd = new HodDaoImpl();

	public boolean loginHodUi() throws SomeThingWrongException {
		System.out.println("Enter Username: ");
		String username = sc.next();
		System.out.println("Enter Password: ");
		String password = sc.next();
		boolean check = hd.loginHod(username, password);
		return check;
	}

	public void registerEngineerUi() throws SomeThingWrongException {
		System.out.println("Enter Engineer Name: ");
		String engName = sc.next();
		System.out.println("Enter Engineer Username: ");
		String engUsername = sc.next();
		System.out.println("Enter Engineer Password: ");
		String engPassword = sc.next();
		System.out.println("Please enter a choice from below:");
		System.out.println("\033[33m"); // yellow
		System.out.println("1. Hardware");
		System.out.println("2. Software");
		System.out.println("\033[0m"); // black
		int engTypeChoice = sc.nextInt();
		String engCategory = null;

		if (engTypeChoice == 1) {
			engCategory = "Hardware";
		} else {
			engCategory = "Software";
		}
		Engineer engg = new EngineerImpl(engName, engUsername, engPassword, engCategory);
		String msg = hd.registerEngineer(engg);
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
			Test.hodJob();
		}
	}

	public void getAllRegisterEngineerUi() throws SomeThingWrongException {
		HodDao hd = new HodDaoImpl();
		List<Engineer> lsAll = hd.getRegisterEngineer();
		lsAll.forEach((eng) -> {
			System.out.println("======================================");
			System.out.println("Engineer id: " + eng.getEngId());
			System.out.println("Engineer category: " + eng.getEngCategory());
			System.out.println("Engineer name: " + eng.getEngName());
			System.out.println("Engineer username: " + eng.getEngUserName());
			System.out.println("Engineer password: " + eng.getEngPass());
			System.out.println("======================================");
		});

		if (lsAll.size() == 0) {
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
			Test.hodJob();
		}
	}

	public void deleteEngineerUi() throws SomeThingWrongException {
		System.out.println("Enter the engineer username that you wanted to delete:");
		String engUsername = sc.next();
		String msg = hd.deleteEngineer(engUsername);
		if (msg.equalsIgnoreCase("no")) {
			System.out.println(
					"No record found with Engineer with username: " + engUsername + "\n\n" + "Please try again.\n");
			deleteEngineerUi();
		} else {
			System.out.println("Engineer with " + engUsername + " has been deleted.");
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
				Test.hodJob();
			}
		}
	}

	public void getListOfAllComplainUi() throws SomeThingWrongException {
		List<Complain> lsComp = hd.getListOfAllComplain();
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
			Test.hodJob();
		}
	}

	public void assigningProblemToEngineerUi() throws SomeThingWrongException {
		System.out.println("Please type the ticket number: ");
		int ticketNo = sc.nextInt();
		System.out.println("Please type Engineer username: ");
		String engUsername = sc.next();
		String msg = hd.assigningProblemToEngineer(ticketNo, engUsername);
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
			Test.hodJob();
		}
	}
}
