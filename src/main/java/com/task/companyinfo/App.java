package com.task.companyinfo;

import java.util.Scanner;

import com.task.companyinfo.dao.DAO;
import com.task.companyinfo.employee.EmployeeInfo;
import com.task.companyinfo.employee.EmployeeLeaveInfo;

public class App {
	static Scanner input = new Scanner(System.in);
	static DAO dao = new DAO();

	static boolean response = true;

	public static void main(String[] args) {

		do {
			System.out.println("Press\nOne(1) to Register Employee !\nTwo(2) to Login \nThree(3) to Exit !");
			int choice = input.nextInt();
			switch (choice) {
			case 1: {
				registerEmployee();
				break;
			}
			case 2: {
				

				break;
			}
			case 3: {
				response = false;
				break;
			}

			default:
				break;
			}

		} while (response);
	}

	public static void registerEmployee() {
		System.out.println("Hey !\nAfter you Register We give you a Unique Enployee ID \n");
		System.out.println("Your Name?");
		String name = input.next();
		System.out.println("Choose-Type of employee\n -> 1 Employee\n -> 2 Manager");
		int typeChoice = input.nextInt();
		String type = "";
		// -----Start
		switch (typeChoice) {
		case 1: {
			type = "Employee";
			break;
		}
		case 2: {
			type = "Manager";
			break;
		}
		default:
			break;
		}// -------End
		System.out.println("Your Email?");
		String email = input.next();
		System.out.println("Enter New Password?");
		String password = input.next();
		int empID = dao.getID();
		EmployeeInfo employee = new EmployeeInfo(empID, name, type, email, password);
		dao.doRegister(employee);
		System.out.println("\n-------------------------------------\nRegistered Successfully\n  Your Employee ID :"
				+ empID + "\n-------------------------------------");

	}

	public static void login() {
		System.out.println("Enter User ID?");
		int userID = input.nextInt();
		System.out.println("Enter Password?");
		String userPassword = input.next();
		String choice = dao.doLogin(userID, userPassword);
		switch (choice) {
		case "false":
			System.out.println("Opps Not Found !");

			break;
		case "employee":
			forEmployee(userID);
			break;
		case "manager":
			forManager();
			break;

		default:
			break;
		}

	}

	public static void forEmployee(int userID) {
		boolean response = true;
		do {
			System.out.println("\nPress\n -> 1 Show All Leave Requests \n -> 2 Apply For leave Request \n -> 3 Exit !");
			int choice = input.nextInt();
			switch (choice) {
			case 1:
				dao.showEmployeeLeaveStatus(userID);
				break;
			case 2:
				System.out.println("Your Employee ID :" + userID);
//			int employeeID = input.nextInt();
				System.out.println("Leave-Date?");
				String leaveDate = input.next();
				EmployeeLeaveInfo leaveInfo = new EmployeeLeaveInfo(userID, leaveDate, "Pending");
				dao.doRegister(leaveInfo);
				break;
			case 3:
				response = false;
				break;

			default:
				break;
			}
		} while (response);

	}

	public static void forManager() {
		boolean response = true;
		do {
			System.out.println("\nPress\n -> 1 Show all Requests \n -> 2 Approve/Reject Leave Request \n -> 3 Exit !");
			int choice = input.nextInt();
			switch (choice) {
			case 1:
				dao.showAllLeave();
				break;
			case 2:
				leaveResponse();
				break;
			case 3:
				response = false;
				break;

			default:
				break;
			}
		} while (response);

	}

	public static void leaveResponse() {
		System.out.println(
				"\nPress 1 \nFor response all LeaveStatus to specified Employee ? \n2-> Response to particular date for Employee?");
		int response = input.nextInt();
		String query;
		System.out.println("Enter EmployeeID?");
		int empID = input.nextInt();
		System.out.println("Enter a Response for Leave?");
		String responseLeave = input.next();
		switch (response) {
		case 1:

			query = "update EmployeeLeaveInfo set leaveStatus='" + responseLeave + "' where employeeID='" + empID+"'";
			dao.update(query);

			break;
		case 2:
			System.out.println("Enter a Date?");
			String leaveDate = input.next();
			query = "update EmployeeLeaveInfo set leaveStatus='" + responseLeave + "' where employeeID='" + empID
					+ "' and leaveDate='" + leaveDate+"'";
			dao.update(query);
			break;

		default:
			break;
		}

	}
}
