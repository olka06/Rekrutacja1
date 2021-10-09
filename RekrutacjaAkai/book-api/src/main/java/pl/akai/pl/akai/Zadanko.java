package pl.akai.pl.akai;

public class Zadanko {
	
	private String id;
	private String title;
    private String author;
    private double rating;
    
    
    public void setId(String idIn) { id = idIn; }
    public String getId() {return id;}
    
    public void setTitle(String titleIn) { title = titleIn; }
    public String getTitle() {return title;}
    
    public void setAuthor(String authorIn) { author = authorIn; }
    public String getAuthor() {return author;}
    
    public void setRating(double ocenaIn) { rating = ocenaIn; }
    public double getRating() {return rating;}
    
}
