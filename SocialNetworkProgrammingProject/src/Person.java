import java.util.ArrayList;

public class Person {
	
	//Fields for the PERSON type:
	/* Some of this fields will have to be changed to ArrayList<String>
	 * or String[] in order to store more than one film, workplace, etc.
	 * For the time being, we just store the raw information as a String.
	 */
	private String identifier;
	private String name;
	private String surnames;
	private String gender;
	private String date;
	private String birthplace;
	private String home;
	private String studiedat;
	private String workedat;
	private String movies;
	private String groupcode;
	private ArrayList<String> friends; //We don't store Person, just identifiers (String).
	
	//Constructor with fields:
	public Person(String identifier, String name, String surnames, String gender, String date, String birthplace, String home,
			String studiedat, String workedat, String movies, String groupcode) {
		super();
		this.identifier = identifier;
		this.name = name;
		this.surnames = surnames;
		this.gender = gender;
		this.date = date;
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
		System.out.println(gender);
		System.out.println(date);
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
			System.out.println(name + " has the following friends: ");
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
	
}
