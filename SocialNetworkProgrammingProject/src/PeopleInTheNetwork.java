import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class PeopleInTheNetwork {
	
	//Fields of the objects PeopleInTheNetwork:
	private ArrayList<Person> list;
	
	//Constructor without fields:
	public PeopleInTheNetwork() {
		list = new ArrayList<Person>();
	}
	
	/* We get the number of users in the network (aka the size of the ArrayList):
	 */
	public int getNumberOfUsers() {
		return list.size();
	}
	
	/* Given a file path (String), the method reads the people
	 * in the file and adds them to the network as new users.
	 */
	public void addPeopleToTheNetwork(String filePath) throws FileNotFoundException {
		//We open the file:
		File fileInstance = new File(filePath);
		Scanner f = new Scanner(fileInstance);
		f.nextLine();
		f.useDelimiter(",");
		//We read the file and create people of type Person. When that user is not in our list, we add it:
		while (f.hasNextLine()) {
			Person p = new Person(f.next(), f.next(), f.next(), f.next(), f.next(), f.next(), f.next(), f.next(), f.next(), f.next(), f.nextLine());
			if (!inTheNetwork(p))
				list.add(p);
		}
		f.close();
	}
		
	/* Given the id (String) of a person, it tell us if the user is already in the network.
	 */
	public boolean idInTheNetwork(String id) {
		int i = 0;
		boolean found = false;
		while (i < list.size() && !found) {
			if (list.get(i).getIdentifier().equals(id)) {
				found = true;
			}
			i++;
		}
		return found;
	}
	
	/* Given a person p of type Person, it tell us if the user is already in the network.
	 */
	public boolean inTheNetwork(Person p) {
		return idInTheNetwork(p.getIdentifier());
	}
	
	/* Given the id (String) of a user, it tells us the index
	 * at which we can find that user in the list.
	 * ATTENTION: It is private. 
	 */
	private int indexOfId(String id) {
		int i = 0;
		while (i < list.size()) {
			if (list.get(i).getIdentifier().equals(id)) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	/* Given the id (String) of a user, it returns
	 * the whole user's Person object. 
	 */
	public Person getUser(String id) {
		return list.get(indexOfId(id));
	}
	
	/* Given a file path (String), the method reads the identifiers
	 * in the file and adds them friend relationships to the network.
	 */
	public void addFriendsToTheNetwork(String filePath) throws FileNotFoundException {
		File fileInstance = new File(filePath);
		Scanner f = new Scanner(fileInstance);
		f.nextLine();
		f.useDelimiter(",");
		String f1, f2;
		while (f.hasNextLine()) {
			f1 = f.next();
			f2 = f.nextLine();
			f2 = f2.substring(1, f2.length());
			if (idInTheNetwork(f1) && idInTheNetwork(f2)) {
				list.get(indexOfId(f1)).addFriend(f2);
				list.get(indexOfId(f2)).addFriend(f1);
			}
		}
		f.close();
	}
	
	/*Given a file path (String), the method creates a file in which
	 * it lists the identifiers of all the users in the network:
	 */
	public void printListOfUsers(String filePath) throws FileNotFoundException {
		File fileInstance = new File(filePath);
		PrintWriter f = new PrintWriter(fileInstance);
		for (Person p : list) {
			f.println(p.getIdentifier());
		}
		f.close();
	}
}
