import java.util.ArrayList;
import java.util.Comparator;

public class Person {
	
	//Fields for the PERSON type:
	/* Some of this fields will have to be changed to ArrayList<String>
	 * or String[] in order to store more than one film, workplace, etc.
	 * For the time being, we just store the raw information as a String.
	 */
	private String identifier;
	private String name;
	private String surnames;
	private String[] date; //For the moment, this is the only one changed.
	private String gender;
	private String birthplace;
	private String home;
	private String studiedat;
	private String workedat;
	private String movies;
	private String groupcode;
	private ArrayList<String> friends; //We don't store Person, just identifiers (String).
	
	//Constructor with only one field:
	public Person(String id, String date) {
		identifier = id;
		this.date = date.split("-");
	}
	
	//Constructor with fields:
	public Person(String identifier, String name, String surnames, String date, String gender, String birthplace, String home,
			String studiedat, String workedat, String movies, String groupcode) {
		super();
		this.identifier = identifier;
		this.name = name;
		this.surnames = surnames;
		this.gender = gender;
		this.date = date.split("-");
		this.birthplace = birthplace;
		this.home = home;
		this.studiedat = studiedat;
		this.workedat = workedat;
		this.movies = movies;
		this.groupcode = groupcode;
		friends = new ArrayList<String>();
	}

	//Getters for identifier and friends:
	public String getIdentifier() {
		return identifier;
	}
	
	public ArrayList<String> getFriends() {
		return friends;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSurnames() {
		return surnames;
	}
	
	public String getBirthplace() {
		return birthplace;
	}
	
	public String[] getDate() {
		return date;
	}
	
	public String getStudies() {
		return studiedat;
	}
	
	/* Given the id (String) of a user, it adds it as a friend.
	 */
	public void addFriend(String id) {
		if(!friends.contains(id))
			friends.add(id);
	}
	
	/* Prints all the fields of a user.
	 */
	public void printUserInfo() {
		System.out.println(identifier);
		System.out.println(name);
		System.out.println(surnames);
		System.out.println(date[0] + "-" + date[1] + "-" + date[2]);
		System.out.println(gender);
		System.out.println(birthplace);
		System.out.println(home);
		System.out.println(studiedat);
		System.out.println(workedat);
		System.out.println(movies);
		System.out.println(groupcode);
	}
	
	/* Prints the id of the user.
	 */
	public void printUserId() {
		System.out.println(identifier);
	}
	
	/* Print the list of identifiers of the user's friends.
	 */
	public void printFriends() {
		if (!friends.isEmpty()) {
			System.out.println(name + surnames + " has the following friends: ");
			for (String a : friends) {
				System.out.println(a);
			}
		} else {
			System.out.println(name + " has no friends.");
		}
	}
	
	/* Returns the number of friends of a given user:
	 */
	public int numberOfFriends() {
		return friends.size();
	}
	
	//Comparators:
	//1. By ID
	public static Comparator<Person> IDComparator = new Comparator<Person>() {
		public int compare(Person p1, Person p2) {
			return p1.identifier.compareTo(p2.identifier);
		}
	};
	
	//2. By SURNAME and NAME (lexicographic)
	public static Comparator<Person> SurnameNameComparator = new Comparator<Person>() {
		public int compare(Person p1, Person p2) {
			int i = p1.surnames.compareTo(p2.surnames);
			if (i < 0)
				return -1;
			else if (i > 0)
				return 1;
			else
				return p1.name.compareTo(p2.name);
		}
	};
	
	//3.By BIRTHPLACE, SURNAME and NAME
	public static Comparator<Person> BirthplaceSurnameNameComparator = new Comparator<Person>() {
		public int compare(Person p1, Person p2) {
			int i = p1.birthplace.compareTo(p2.birthplace);
			if (i < 0)
				return -1;
			else if (i > 0)
				return 1;
			else
				return SurnameNameComparator.compare(p1, p2);
		}
	};
	
	//4. By BIRTHDATE, SURNAME and NAME
	public static Comparator<Person> BirthdateSurnameNameComparator = new Comparator<Person>() {
		public int compare(Person p1, Person p2) {
			int[] aux1 = {Integer.parseInt(p1.date[0]), Integer.parseInt(p1.date[1]), Integer.parseInt(p1.date[2])};
			int[] aux2 = {Integer.parseInt(p2.date[0]), Integer.parseInt(p2.date[1]), Integer.parseInt(p2.date[2])};
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
						return SurnameNameComparator.compare(p1, p2);
				}
			}
		}
	};
	
	//Compare only by DATE:
	public static Comparator<Person> BirthdateOnlyComparator = new Comparator<Person>() {
		public int compare(Person p1, Person p2) {
			int[] aux1 = {Integer.parseInt(p1.date[0]), Integer.parseInt(p1.date[1]), Integer.parseInt(p1.date[2])};
			int[] aux2 = {Integer.parseInt(p2.date[0]), Integer.parseInt(p2.date[1]), Integer.parseInt(p2.date[2])};
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
	};
}
