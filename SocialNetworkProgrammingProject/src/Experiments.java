import java.io.FileNotFoundException;
import java.util.Scanner;

public class Experiments {

	public static void main(String[] args) {
		PeopleInTheNetwork database = new PeopleInTheNetwork();
		Scanner input = new Scanner(System.in);
		System.out.println("Introduce a name for the file with people:");
		String path = input.next();
		/*try {
			database.modifiedAdd(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.println("Users: " + database.getNumberOfUsers());
		/*
		//Person p = new Person("Ane50");
		Person p = new Person("Ane50", "Ane", "Peñalva", "4-7-1998", "female", "Donostia", "Zarautz", "Amara Berri", "student", "Indiana Jones", "G611732");
		if (database.inTheNetwork(p))
			System.out.print("CORRECT");
		else
			System.out.print("NOT CORRECT");
		if (database.contain(p))
			System.out.print("CORRECT");
		else
			System.out.print("NOT CORRECT");
		*/
		input.close();
	}

}
