import java.io.FileNotFoundException;
import java.util.Scanner;

public class Experiments {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String s1 = input.next();
		String s2 = input.next();
		if (s1.compareTo(s2) < 0)
			System.out.println("Correct");
		System.out.println("End");
		input.close();
	}

}
