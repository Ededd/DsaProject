public class Movie implements Comparable<Movie> {
	
	private String title;
	private int likes;
	
	public Movie(String title) {
		this.title = title;
		likes = 0;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getLikes() {
		return likes;
	}
	
	public boolean equals(Movie m) {
		return this.title.equals(m.title);
	}
	
	public void addLike() {
		likes++;
	}
	
	public int compareTo(Movie m) {
		if (this.likes > m.likes)
			return -1;
		else if (this.likes < m.likes)
			return 1;
		else
			return this.title.compareTo(m.title);
	}
	
}
