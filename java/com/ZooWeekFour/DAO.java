package com.ZooWeekFour;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DAO {
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/?user=root&autoReconnect=true&useSSL=false";
	static final String USER = "root";
	static final String PASSWORD = "sesame";

	static Connection CONN = null;
	static Statement STMT = null;
	static PreparedStatement PREP_STMT = null;
	static ResultSet RES_SET = null;
	
	public Scanner sc = new Scanner(System.in);
	
	public static void connToDB() {
		
		try {
			System.out.println("Connecting to the Database...");
			CONN = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("Connected to the database \n");
			
		} catch (SQLException e) {
			System.out.println("Database connection failed.");
			e.printStackTrace();
		}
	}   // end of method connToDB
	
	public static void readFromDB() {
		connToDB();
		ArrayList<ZooExhibits> ourAnimals = new ArrayList<>();
		
		try {
			STMT = CONN.createStatement();
			RES_SET = STMT.executeQuery("SELECT * FROM students.animals_at_the_zoo;");
			
			while (RES_SET.next()) {
				ZooExhibits saidAnimal = new ZooExhibits();
				saidAnimal.setAnimal_ID(RES_SET.getInt("animal_id"));
				saidAnimal.setSpecies(RES_SET.getString("species"));
				saidAnimal.setCageType(RES_SET.getString("type_of_cage"));
				saidAnimal.setFood(RES_SET.getString("food"));
				saidAnimal.setName(RES_SET.getString("name"));
				saidAnimal.setWeight(RES_SET.getDouble("weight"));
				
				ourAnimals.add(saidAnimal);
				
			}  // end of while loop
			
			for (ZooExhibits zooExhibits : ourAnimals) {
				System.out.println(zooExhibits.toString());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}   // end of try-catch
		
	}  // end of method readFromDB
	
	
	public static void writeToDB() {
		
		ZooExhibits addAnimal = new ZooExhibits();
		addAnimal = aboutTheAnimal();
		connToDB();
		
		try {
			PREP_STMT = CONN.prepareStatement(insertToDB);
			PREP_STMT.setString(1, addAnimal.getSpecies());
			PREP_STMT.setString(2, addAnimal.getCageType());
			PREP_STMT.setString(3, addAnimal.getFood());
			PREP_STMT.setString(4, addAnimal.getName());
			PREP_STMT.setDouble(5, addAnimal.getWeight());

			PREP_STMT.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}  // end of method writeToDB
	
		
	
	private static String insertToDB = "INSERT INTO `students`.`animals_at_the_zoo`" + "(species, type_of_cage, food, name, weight)" + "VALUES" 
			+ "(?,?,?,?,?)";
			
			public static ZooExhibits aboutTheAnimal() {
				Scanner sc = new Scanner(System.in);

				ZooExhibits addAnimal = new ZooExhibits();
				
				System.out.println("What is the animal's genus or species?");
				addAnimal.setSpecies(sc.nextLine());
				
				System.out.println("In what kind of cage or enclosue is the animal?");
				addAnimal.setCageType(sc.nextLine());

				System.out.println("What food does the animal eat?");
				addAnimal.setFood(sc.nextLine());
				
				System.out.println("What is the animal's name?");
				addAnimal.setName(sc.nextLine());
				
				System.out.println("What is the weight of the animal?");
				addAnimal.setWeight(Double.parseDouble(sc.nextLine()));
				
				return addAnimal;
				} // end of aboutTheAnimal method

			public static void deleteFromDB() {
				Scanner sc = new Scanner(System.in);
				ZooExhibits animalToDelete = new ZooExhibits();
				connToDB();	
				
				System.out.println("Which animal would you like to delete? \n"
						+ "Please select and enter the correspondnig animal ID of the animal to DELETE");
				String checkInput = sc.nextLine();
				String regex = "^[a-zA-Z]+$";
				do {
					if (checkInput.matches(regex)) {
						System.out.println("Error! Invalid integer value.");
						System.out
								.println("Please select and enter the correspondnig animal ID of the animal to DELETE");
						animalToDelete.setAnimal_ID(Integer.parseInt(sc.nextLine()));
					} else
						animalToDelete.setAnimal_ID(Integer.parseInt(checkInput));
				} while (!(sc.hasNextInt()));
				
				try {
					PREP_STMT = CONN.prepareStatement(removeFromDB);
							PREP_STMT.setInt(1, animalToDelete.getAnimal_ID());
							
							PREP_STMT.executeUpdate();


				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
							
			}  // end of method deletFromDB

			private static String removeFromDB = "DELETE FROM `students`.`animals_at_the_zoo`" 
			+ "WHERE `animals_at_the_zoo`.`animal_id`="  + "(?)";

			
			public static void updateDB() {
				Scanner sc = new Scanner(System.in);
				ZooExhibits updateAnimal = new ZooExhibits();
				//updateAnimal = aboutTheAnimal();
				connToDB();
				String userMenuInput = null;
				String strUpdate = null;
				boolean menuChoiceNotCorrect = false;
				
				//species, type_of_cage, food, name, weight
				
				System.out.println("Please select and enter the correspondnig animal ID of the animal to UPDATE");
				String checkInput = sc.nextLine();
				String regex = "^[a-zA-Z]+$";
				do {
					if (checkInput.matches(regex)) {
						System.out.println("Error! Invalid integer value.");
						System.out
								.println("Please select and enter the correspondnig animal ID of the animal to UPDATE");
						updateAnimal.setAnimal_ID(Integer.parseInt(sc.nextLine()));
					} else
						updateAnimal.setAnimal_ID(Integer.parseInt(checkInput));
				} while (!(sc.hasNextInt()));
				
				do {
					System.out.println("Press 1 to update the Species \n" + "Press 2 to update the Cage \n"
							+ "Press 3 to update the Food \n" + "Press 4 to update the Name of the animal \n"
							+ "Press 5 to update the Weight of the animal");
					userMenuInput = sc.nextLine();
					switch (userMenuInput) {
					case "1":
						System.out.println("For SPECIES, please enter the new information");
						strUpdate = sc.nextLine();
						try {
							PREP_STMT = CONN.prepareStatement(
									updatingDB + "`species` = ?" + "WHERE `animals_at_the_zoo`.`animal_id`= ?");
							PREP_STMT.setString(1, strUpdate);
							PREP_STMT.setInt(2, updateAnimal.getAnimal_ID());

							PREP_STMT.executeUpdate();

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case "2":
						System.out.println("For CAGE, please enter the new information");
						strUpdate = sc.nextLine();
						try {
							PREP_STMT = CONN.prepareStatement(
									updatingDB + "`type_of_cage` = ?" + "WHERE `animals_at_the_zoo`.`animal_id`= ?");
							PREP_STMT.setString(1, strUpdate);
							PREP_STMT.setInt(2, updateAnimal.getAnimal_ID());

							PREP_STMT.executeUpdate();

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case "3":
						System.out.println("For FOOD, please enter the new information");
						strUpdate = sc.nextLine();
						try {
							PREP_STMT = CONN.prepareStatement(
									updatingDB + "`food` = ?" + "WHERE `animals_at_the_zoo`.`animal_id`= ?");
							PREP_STMT.setString(1, strUpdate);
							PREP_STMT.setInt(2, updateAnimal.getAnimal_ID());

							PREP_STMT.executeUpdate();

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case "4":
						System.out.println("For NAME, please enter the new information");
						strUpdate = sc.nextLine();
						try {
							PREP_STMT = CONN.prepareStatement(
									updatingDB + "`name` = ?" + "WHERE `animals_at_the_zoo`.`animal_id`= ?");
							PREP_STMT.setString(1, strUpdate);
							PREP_STMT.setInt(2, updateAnimal.getAnimal_ID());

							PREP_STMT.executeUpdate();

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case "5":
						System.out.println("For WEIGHT, please enter the new information");
						strUpdate = sc.nextLine();
						try {
							PREP_STMT = CONN.prepareStatement(
									updatingDB + "`weight` = ?" + "WHERE `animals_at_the_zoo`.`animal_id`= ?");
							PREP_STMT.setString(1, strUpdate);
							PREP_STMT.setInt(2, updateAnimal.getAnimal_ID());

							PREP_STMT.executeUpdate();

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					default:
						System.out.println("You have entered an invalid option");
						menuChoiceNotCorrect = true;
						break;
					}
				} while (menuChoiceNotCorrect);

				
			}  // end of method updateDB
			
			private static String updatingDB = "UPDATE `students`.`animals_at_the_zoo` SET ";
					
			
			
}  // end of class
