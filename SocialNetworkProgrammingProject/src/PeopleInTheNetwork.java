import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PeopleInTheNetwork {
	
	//Fields of the objects PeopleInTheNetwork:
	private ArrayList<Person> list;
	//private ArrayList<Movie> moviesDatabase;
	
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
	public void addPeopleToTheNetwork(String name) throws FileNotFoundException {
		//We open the file:
		File fileInstance = new File(name);
		Scanner f = new Scanner(fileInstance);
		f.nextLine();
		f.useDelimiter(",");
		//We read the file and create people of type Person. When that user is NOT in our list, we add it:
		while (f.hasNextLine()) {
			//public Person(String identifier, String name, String surnames, String gender, String[] date, String birthplace, String home,
			//String studiedat, String workedat, String movies, String groupcode)
			Person p = new Person(f.next(), f.next(), f.next(), f.next(), f.next(), f.next(), f.next(), f.next(), f.next(), f.next(), f.nextLine().substring(1));
			if (!inTheNetwork(p)) {
				list.add(p);
				//System.out.println();
				//p.printUserInfo();
			}
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
	
	public boolean surnameInTheNetwork(String s) {
		int i = 0;
		boolean found = false;
		while (i < list.size() && !found) {
			if (list.get(i).getSurnames().equals(s)) {
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
	
	public void printFriendsOfSurname(String s) {
		Person aux;
		for (Person p : list) {
			if (p.getSurnames().equals(s)) {
				System.out.println(p.getName() + " " + p.getSurnames() + " has the following friends:");
				for (String id : p.getFriends()) {
					aux = getUser(id);
					System.out.println(id + " / " + aux.getSurnames());
				}
			}
		}
	}
	
	public void printUsersBornIn(String city) {
		int i = 0;
		System.out.println("Users born in " + city + ": ");
		for (Person p : list) {
			if (p.getBirthplace().equals(city)) {
				i++;
				System.out.println(p.getIdentifier() + " / " + p.getSurnames());
			}
		}
		if (i == 0)
			System.out.println("There are NONE.");
	}
	
	public void printUsersBetweenDates(String d1, String d2) {
		if (compareDates(d1, d2) == 1)
			System.out.println("The introduced dates are in wrong order. Please, try again.");
		else {
			list.sort(Person.BirthplaceSurnameNameComparator);
			Person c1 = new Person("Comparable1", d1);
			Person c2 = new Person("Comparable2", d2);
			int i, j, count = 0;
			for (Person p : list) {
				i = Person.BirthdateOnlyComparator.compare(p, c1);
				j = Person.BirthdateOnlyComparator.compare(p, c2);
				if (i >= 0 && j <= 0) {
					System.out.println(p.getBirthplace() + " / " + p.getSurnames() + ", " + p.getName());
					count++;
				}
			}
			if (count == 0)
				System.out.println("There are no users born between those two dates.");
		}
	}
	
	private int compareDates(String d1, String d2) {
		String[] date1 = d1.split("-");
		String[] date2 = d2.split("-");
		int[] aux1 = {Integer.parseInt(date1[0]), Integer.parseInt(date1[1]), Integer.parseInt(date1[2])};
		int[] aux2 = {Integer.parseInt(date2[0]), Integer.parseInt(date2[1]), Integer.parseInt(date2[2])};
		if (aux1[2] < aux2[2])
			return -1;
		else if (aux1[2] > aux2[2])
			return 1;
		else {
			if (aux1[1] < aux2[1])
				return -1;
			else if (aux1[1] > aux2[1])
				return 1;
			else {
				if (aux1[0] < aux2[0])
					return -1;
				else if (aux1[0] > aux2[0])
					return 1;
				else
					return 0;
			}
		}
	}
	
	public void printUsersFrom(String filePath) throws FileNotFoundException {
		File fileInstance = new File(filePath);
		Scanner f = new Scanner(fileInstance);
		ArrayList<String> cities = new ArrayList<String>();
		String id, place = "Unknown";
		while (f.hasNextLine()) {
			id = f.nextLine();
			if (this.idInTheNetwork(id))
				place = getUser(id).getBirthplace();
				if (!cities.contains(place))
					cities.add(place);
		}
		
		cities.sort(null);
		this.birthplaceSurnameNameSort();
		for (String c : cities) {
			System.out.println();
			System.out.println("Users from " + c + ":");
			for (Person p : list) {
				if (p.getBirthplace().equals(c))
					System.out.println(p.getName() + " " + p.getSurnames() + ", " +  p.getBirthplace() + ", " +  p.getStudies());		
			}
		}
		f.close();
	}
	
	/* Given a file path (String), the method reads the identifiers
	 * in the file and adds them friend relationships to the network.
	 */
	public void addFriendsToTheNetwork(String name) throws FileNotFoundException {
		File fileInstance = new File(name);
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
	public void printListOfIDs(String name) throws FileNotFoundException {
		File fileInstance = new File(name);
		PrintWriter f = new PrintWriter(fileInstance);
		for (Person p : list) {
			f.println(p.getIdentifier());
		}
		f.close();
	}
	
	public void printListOfSurnameNames(String name) throws FileNotFoundException {
		File fileInstance = new File(name);
		PrintWriter f = new PrintWriter(fileInstance);
		for (Person p : list) {
			f.println(p.getSurnames() + ", " + p.getName());
		}
		f.close();
	}
	
	public void printListOfBirthplaceSurNames(String name) throws FileNotFoundException {
		File fileInstance = new File(name);
		PrintWriter f = new PrintWriter(fileInstance);
		for (Person p : list) {
			f.println(p.getBirthplace() + " / " + p.getSurnames() + ", " + p.getName());
		}
		f.close();
	}
	
	public void printListOfBirthdates(String name) throws FileNotFoundException {
		File fileInstance = new File(name);
		PrintWriter f = new PrintWriter(fileInstance);
		for (Person p : list) {
			f.println(p.getDate()[0] + "-" + p.getDate()[1] + "-" + p.getDate()[2] + " / " + p.getSurnames() + ", " + p.getName());
		}
		f.close();
	}
	
	public void moviesClass() {
		Map<String[], String[]> moviesClass = new HashMap<String[], String[]>();
		for (Person p : list) {
			String[] movieNames = p.getMovies().toArray(new String[p.getMovies().size()]);
			if (!moviesClass.containsKey(movieNames)) {//if moviesclass not created
				String[] newidentifiers = new String[10];//create new class
				newidentifiers[0] = p.getIdentifier();				
				moviesClass.put(movieNames, newidentifiers); //add user
			}else {
				String[] newval = moviesClass.get(movieNames);//add user to the list
				newval[moviesClass.get(movieNames).length]=p.getIdentifier();
				moviesClass.put(movieNames, newval);//put new list in the class
			}
		}	
		for (String[] movies : moviesClass.keySet()) {
		    System.out.print("Movies : " + Arrays.asList(movies) + " Users:");
		    for (String id : moviesClass.get(movies)) {
		    	if(id !=null)
		    	System.out.print(" " + id + " ");    
		    }
		    System.out.println();
	    }
		
	}
	
	//Sorter by IDs
	public void IDSort() {
		list.sort(Person.IDComparator);
	}
	
	//Sorter by SURNAME and NAME
	public void surnameNameSort() {
		list.sort(Person.SurnameNameComparator);
	}
	
	//Sorter by BIRTHPLACE, SURNAME and NAME
	public void birthplaceSurnameNameSort() {
		list.sort(Person.BirthplaceSurnameNameComparator);
	}
	
	//Sorter by BIRTHDATE, SURNAME and NAME
	public void birthdateSort() {
		list.sort(Person.BirthdateSurnameNameComparator);
	}
	
	//Sorter by MOVIES
	public void movieSort() {
		list.sort(Person.GeneralMovieComparator);
	}
	
	
	
}
