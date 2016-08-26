package com.ZooWeekFour;

import java.util.Scanner;

public class connToDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	// DAO.connToDB();
	//	DAO.readFromDB();
		
		
		Scanner sc = new Scanner(System.in);
		
		String userMenuInput = null;
		boolean menuCorrect = false;
		
		System.out.println("Welcome to the student information program");
		
		do {
			System.out.println("Press 1 to read the database \n" +  "Press 2 to add to the database \n" 
		+ "Press 3 to DELETE from the database \n" + "Press 4 to make an UPDATE the database \n"
					+ "Press 5 to QUIT & EXIT");
			userMenuInput = sc.nextLine();
			switch (userMenuInput) {
			case "1":
				DAO.readFromDB();
				break;
			case "2":
				DAO.writeToDB(); // write to the database
				break;
			case "3":
				DAO.deleteFromDB();  // delete from the database
				break;
			case "4":
				DAO.updateDB();  // update the database
				break;
			case "5":
				// does nothing except close the database and allow the program to exit
				break;
			default:
				System.out.println("You have entered an invalid option");
				menuCorrect = true;
				break;
			}
		} while (menuCorrect || !(userMenuInput.equals("5")));
		
	}

}
