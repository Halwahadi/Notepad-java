import java.util.Date;

public class Note {
	private String title, content;
	private Date date;
	private boolean urgent;
	
	public Note(String t, String c, Date d, boolean u) {
		title = t;
		content = c;
		date = d;
		urgent = u;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String t) {
		title = t;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String c) {
		content = c;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date d) {
		date = d;
	}
	
	public boolean getUrgent() {
		return urgent;
	}
	public void setUrgent(boolean u) {
		urgent = u;
	}
	
	public String toString() { 
		String toReturn = "";
		toReturn += "Title: " + title;
		toReturn +=	"\nDate: " + date;
		if(urgent) {
			toReturn += "\nURGENT";
		}
		toReturn += "\nContent: " + content + "\n";
		return toReturn; 
	}
}
