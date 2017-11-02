import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class cliqueG611732 {
	
	private static String [] identifiers = {"Andrea91", "Juan98", "Malena17", "Ane50"};

	public static void createFriendsFile(String [] clique) throws FileNotFoundException {
		String filePath = "C:/Users/ehu/Desktop/Files/friendsG611732.dsa"; //You may choose whatever other directory you wish.
		File fileInstance = new File(filePath);
		PrintWriter f = new PrintWriter(fileInstance);
		f.println("friend1,friend2");
		int l = clique.length;
		for (int i = 0; i < l; i++) {
			for (int j = i + 1; j < l; j++) {
				f.println(clique[i] + "," + clique[j]);
			}
		}
		f.close();
	}

	public static void main(String[] args) {
		try {
			createFriendsFile(identifiers);
			System.out.println("The friends file was created successfully.");
		}
		catch (FileNotFoundException e) {
			System.out.println("There was a problem with the file's name or path.");
			e.printStackTrace();
		}
	}
}
