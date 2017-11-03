import java.io.FileNotFoundException;
import java.util.Scanner;
//import java.util.ArrayList;

public class Menu {
	
	/* Given an object of type PeopleInTheNetwork, that stores all the users in the newtwork,
	 * this procedure executes the MENU of the social network. I will be exectuted in the main method.
	 */
	public static void menu(PeopleInTheNetwork people) {
		int choice;
		Scanner input = new Scanner(System.in);
		//Comment
		System.out.println("Select one of the following options:");
		System.out.println("1. Load a file of people.");
		System.out.println("2. Load a file of friends.");
		System.out.println("3. Get the number of user in the network.");
		System.out.println("4. Get the list of friends of a given user.");
		System.out.println("5. Get the number of friends of a given user.");
		System.out.println("6. Print a file with the IDs of all users.");
		System.out.println("7. QUIT");
		
		choice = -5;
		while (choice != 7) {
			choice = input.nextInt();
			switch (choice) {
			case 1:
				try {
					System.out.println("Introduce a path for the file with people:");
					String path = input.next();
					people.addPeopleToTheNetwork(path);
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
				System.out.println("Introduce the nickname of a user:");
				String id = input.next();
				if (people.idInTheNetwork(id) ) {
					people.getUser(id).printFriends();
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
					people.printListOfUsers(path);
					System.out.println("File successfully printed!");
				} catch (FileNotFoundException e) {
					System.out.println("The given path is invalid or the file does not exist. Try again.");
					break;
				}
				break;
			case 7:
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
