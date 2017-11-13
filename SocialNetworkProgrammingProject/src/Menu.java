import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Menu {
	
	/* Given an object of type PeopleInTheNetwork, that stores all the users in the newtwork,
	 * this procedure executes the user's menu of the social network. I will be exectuted in the main method.
	 */
	public static void menu(PeopleInTheNetwork people) {
		int choice;
		Scanner input = new Scanner(System.in);
		//filepath: C:\Users\noela\Desktop\filename.txt
		choice = -5;
		while (choice != 14) {
			System.out.println();
			System.out.println();
			System.out.println("Select one of the following options:");
			System.out.println("1. Load a file of people.");
			System.out.println("2. Load a file of friends.");
			System.out.println("3. Get the number of user in the network.");
			System.out.println("4. Get the list of friends of a given user.");
			System.out.println("5. Get the number of friends of a given user.");
			System.out.println("6. Print a file with the IDs of all users.");
			System.out.println("7. Sort the network based on IDs (natural order)");
			System.out.println("8. Sort the network based on SURNAMES and NAMES");
			System.out.println("9. Sort the network based on BIRTHPLACE, SURNAMES and NAMES");
			System.out.println("10. Sort the network based on BIRTHDATE, SURNAMES and NAMES");
			System.out.println("11. Get the list of users born in a given city");
			System.out.println("12. Get the list users born between two given dates.");
			System.out.println("13. Get the list of users born in the same place as other users");
			System.out.println("14. QUIT");
			System.out.println();
			choice = input.nextInt();
			switch (choice) {
			case 1:
				try {
					System.out.println("Introduce a path for the file with people:");
					String name = input.next();
					people.addPeopleToTheNetwork(name);
					System.out.println("File successfully added!");
				} catch (FileNotFoundException e) {
					System.out.println("The given path is invalid or the file does not exist. Try again.");
					break;
				}
				break;
			case 2:
				try {
					System.out.println("Introduce a path for the file with friends:");
					String path = input.next();
					people.addFriendsToTheNetwork(path);
					System.out.println("File successfully added!");
				} catch (FileNotFoundException e) {
					System.out.println("The given path is invalid or the file does not exist. Try again.");
					break;
				}
				break;
			case 3:
				System.out.println("There are "+ people.getNumberOfUsers() + " users registered in the social network.");
				break;
			case 4:
				System.out.println("Introduce the SURNAME of a user:");
				String s = input.next();
				if (people.surnameInTheNetwork(s)) {
					people.printFriendsOfSurname(s);
				} else {
					System.out.println("The given nickname is invalid or not in the network. Please, try again:");
				}
				break;
			case 5:
				System.out.println("Introduce the nickname of a user:");
				String ident = input.next();
				if (people.idInTheNetwork(ident))
					System.out.println(ident + " has " + people.getUser(ident).numberOfFriends() + " friends.");
				else
					System.out.println("The given nickname is invalid or not in the network. Please, try again:");
				break;
			case 6:
				try {
					System.out.println("Introduce a path for the file:");
					String path = input.next();
					people.printListOfIDs(path);
					System.out.println("File successfully printed!");
				} catch (FileNotFoundException e) {
					System.out.println("The given path is invalid or the file does not exist. Try again.");
					break;
				}
				break;
			case 7:
				people.IDSort();
				try {
					System.out.println("Introduce a path for the file:");
					String path = input.next();
					people.printListOfIDs(path);
					System.out.println("File successfully printed!");
				} catch (FileNotFoundException e) {
					System.out.println("The given path is invalid or the file does not exist. Try again.");
					break;
				}
				break;
			case 8:
				people.surnameNameSort();
				try {
					System.out.println("Introduce a path for the file:");
					String path = input.next();
					people.printListOfSurnameNames(path);
					System.out.println("File successfully printed!");
				} catch (FileNotFoundException e) {
					System.out.println("The given path is invalid or the file does not exist. Try again.");
					break;
				}
				break;
			case 9:
				people.birthplaceSurnameNameSort();
				try {
					System.out.println("Introduce a path for the file:");
					String path = input.next();
					people.printListOfBirthplaceSurNames(path);
					System.out.println("File successfully printed!");
				} catch (FileNotFoundException e) {
					System.out.println("The given path is invalid or the file does not exist. Try again.");
					break;
				}
				break;
			case 10:
				people.birthdateSort();
				try {
					System.out.println("Introduce a path for the file:");
					String path = input.next();
					people.printListOfBirthdates(path);
					System.out.println("File successfully printed!");
				} catch (FileNotFoundException e) {
					System.out.println("The given path is invalid or the file does not exist. Try again.");
					break;
				}
				break;
			case 11:
				System.out.println("Introduce a CITY:");
				String city = input.next();
				people.printUsersBornIn(city);
				break;
			case 12:
				System.out.println("Introduce a DATE (format dd-mm-yy):");
				String d1 = input.next();
				System.out.println("Introduce another DATE (format dd-mm-yy):");
				String d2 = input.next();
				people.printUsersBetweenDates(d1, d2);
				break;
			case 13:
				people.birthdateSort();
				try {
					System.out.println("Introduce a path for the file:");
					String path = input.next();
					people.printUsersFrom(path);
				} catch (FileNotFoundException e) {
					System.out.println("The given path is invalid or the file does not exist. Try again.");
					break;
				}
				break;
			case 14:
				System.out.println("END OF THE PROGRAM");
				break;
			default:
				System.out.println("Invalid selection. Please enter another option:");
				break;
			}
		}
		input.close();
	}
	
	public static void main(String[] args) {
		System.out.println("-------------------------------------------------------------");
		System.out.println("------ SOCIAL NETWORK - DSA Programing Project 2017-18 ------");	
		System.out.println("-------------------------------------------------------------");
		
		PeopleInTheNetwork database = new PeopleInTheNetwork();
		menu(database);
		
		System.out.println("===============================================================");
		System.out.println("--- Developed by Noel Arteche, Edmund Home and Pablo Felipe ---");	
		System.out.println("===============================================================");
	}

}
