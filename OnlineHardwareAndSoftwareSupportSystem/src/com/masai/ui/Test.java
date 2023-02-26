package com.masai.ui;

import java.util.Scanner;
import com.masai.exception.SomeThingWrongException;

public class Test {
	static Scanner sc = new Scanner(System.in);
	static HodUi hod = new HodUi();
	static EngineerUi engui = new EngineerUi();
	static EmployeeUi empUi = new EmployeeUi();

	public static void main(String[] args) throws SomeThingWrongException {
		homeScreen();
		sc.close();
	}

	public static void homeScreen() throws SomeThingWrongException {
		System.out.println("\033[45m"); //MAGENTA
		System.out.println("🙏 "+"Welcome to Web Solutions"+" 🙏");
		System.out.println("\033[0m"); //Black
		System.out.println("\nEnter a choice from below: ");
		System.out.println("\033[33m");
		System.out.println("1. HOD");
		System.out.println("2. Engineer");
		System.out.println("3. Employee \n");
		System.out.println("\033[31m");
		System.out.println("9. Exit");
		System.out.println("\033[0m");
		int choice = sc.nextInt();

		if (choice == 1) {
			System.out.println("\033[34m"); // blue
			System.out.println("--Welcome to HOD Login Page--");
			System.out.println("\033[0m"); // black
			boolean check = hod.loginHodUi();
			while (check == false) {
				System.out.println("\033[31m"); // red
				System.out.println("Invalid credential please try again \n");
				System.out.println("\033[0m"); // black
				check = hod.loginHodUi();
			}
			if (check == true) {
				hodJob();
			}
		} else if (choice == 2) {
			boolean check = engui.loginEngineerUi();
			while (check == false) {
				System.out.println("\033[31m"); // red
				System.out.println("\nInvalid credential please try again \n");
				System.out.println("\033[0m"); // black
				check = engui.loginEngineerUi();
			}
			if (check == true) {
				engineerJob();
			}
		} else if (choice == 3) {
			System.out.println("\nEnter a choice from below: ");
			System.out.println("\033[33m"); // yellow
			System.out.println("1. login Employee");
			System.out.println("2. Register Employee");
			System.out.println("\033[0m"); // black
			System.out.println("\033[31m"); // red
			System.out.println("0. main Menu");
			System.out.println("\033[0m"); // black
			int eChoice = sc.nextInt();
			if (eChoice == 1) {
				boolean check = empUi.loginEmplyeeUi();
				while (check == false) {
					System.out.println("\033[31m"); // red
					System.out.println("Invalid credential please try again \n");
					System.out.println("\033[0m"); // black
					check = empUi.loginEmplyeeUi();
				}
				if (check == true) {
					employeeJob();
				}

			} else if (eChoice == 2) {
				empUi.registerEmployeeUi();
			} else if (eChoice == 0) {
				homeScreen();
			}

		} else if (choice == 9) {
			System.out.println("\033[36m"); // cyan
			System.out.println("...Thankyou for using our service...");
			System.exit(0);
		} else {
			System.out.println("\033[34m"); // blur
			System.out.println("Oops you have enter a wrong choice please try again");
			System.out.println("\033[0m"); // black
			homeScreen();
		}
	}

	public static void hodJob() throws SomeThingWrongException {
		System.out.println("\nEnter a choice: ");
		System.out.println("\033[33m"); // yellow
		System.out.println("1. Register a Engineer");
		System.out.println("2. Check all Engineer");
		System.out.println("3. Delete a Engineer");
		System.out.println("4. Check all Complains");
		System.out.println("5. Assign any complain to Engineer");
		System.out.println("\033[31m"); // red
		System.out.println("0. Main Menu");
		System.out.println("\033[0m"); // black
		int hChoice = sc.nextInt();
		if (hChoice == 1) {
			hod.registerEngineerUi();
		} else if (hChoice == 2) {
			hod.getAllRegisterEngineerUi();
		} else if (hChoice == 3) {
			hod.deleteEngineerUi();
		} else if (hChoice == 4) {
			hod.getListOfAllComplainUi();
		} else if (hChoice == 5) {
			hod.assigningProblemToEngineerUi();
		} else if (hChoice == 0) {
			homeScreen();
		} else {
			System.out.println("Oops you have enter a wrong choice please try again");
			hodJob();
		}
	}

	public static void engineerJob() throws SomeThingWrongException {
		System.out.println("\nEnter a choice from below: ");
		System.out.println("\033[33m"); // yellow
		System.out.println("1. List of prob assign by Hod");
		System.out.println("2. Change status of the Complain");
		System.out.println("3. list of all problem by Engineer");
		System.out.println("4. Change Password");
		System.out.println("\033[31m"); // red
		System.out.println("0. Main Menu");
		System.out.println("\033[0m"); // black
		int eChoice = sc.nextInt();
		if (eChoice == 1) {
			engui.getListOfProblemAssignByHodUi();
		} else if (eChoice == 2) {
			engui.changeStatusOfComplainUi();
		} else if (eChoice == 3) {
			engui.getListOfProblemAttendByEngineerUi();
		} else if (eChoice == 4) {
			engui.changePasswordUi();
		} else if (eChoice == 0) {
			homeScreen();
		} else {
			System.out.println("Oops you have enter a wrong choice please try again");
			engineerJob();
		}
	}

	public static void employeeJob() throws SomeThingWrongException {
		System.out.println("\nEnter a choice from below: ");
		System.out.println("\033[33m"); // yellow
		System.out.println("1. Register a Complain");
		System.out.println("2. Get Status of the Complain");
		System.out.println("3. Get all complain History");
		System.out.println("4. Change Password");
		System.out.println("\033[31m"); // red
		System.out.println("0. Main Menu");
		System.out.println("\033[0m"); // black
		int empChoice = sc.nextInt();
		if (empChoice == 1) {
			empUi.complainRegisterUi();
		} else if (empChoice == 2) {
			empUi.getStatusOfComplainUi();
		} else if (empChoice == 3) {
			empUi.getComplainHistoryUi();
		} else if (empChoice == 4) {
			empUi.changePasswordUi();
		} else if (empChoice == 0) {
			homeScreen();
		} else {
			employeeJob();
		}

	}

}
