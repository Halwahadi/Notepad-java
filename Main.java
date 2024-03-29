import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;


public class Main{

	static LinkedList<Note> list = new LinkedList<Note>();
	static Stack<Note> stack = new Stack<Note>();


	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		while(true) {
			System.out.println("Would you like to enter a note, retrieve a note, delete a note, or exit?" + "\nEnter: 1" + "\nRetrieve: 2" + "\nDelete: 3" + "\nExit: 4");
			int condition = console.nextInt();
			if(condition == 1) {
				enter();
			}
			else if(condition == 2) {
				retrieve();
			}
			else if(condition == 3) {
				delete();
			}
			else if(condition == 4) {
				break;
			}
			else {
				System.out.println("Not a valid entry, please try again.");
			}
		}

		/*
		THIS IS AN EXAMPLE TO SHOW HOW THE .toString() METHOD AND THE Note() CONSTRUCTOR WORK
		Note n = new Note("Test", "This is a test.", new Date(2019, 04, 9), true);
		System.out.println(n.toString());
		 */	

	}


	/**

	 * Outline:

	 * This method should prompt the user for the title, date, urgency status, and content of a note,

	 * then create a Note object with those specifications and enter it into both the list and the stack.

	 * Make sure to receive and enter the date field in the correct Date format: yyyy, mm, d as shown in the 

	 * example above.

	 */
	public static void enter() {
		String[] formats= {"dd-MM-yy","MM-dd-YYYY","yyyy-MM-dd","MMMMM-dd-yyyy","dd-MMMMM-yy","yyyy.MM.dd","dd/MM/yy","dd/MMMMM/yyyy","MMMMM/dd/yyyy","yyyy/MM/dd"};
		System.out.println("Please enter the title of your note: ");
		Scanner temp = new Scanner(System.in);
		String title=temp.nextLine();

		System.out.println("Please enter the content: ");
		String content=temp.nextLine();

		System.out.println("Please enter the date: ");
		String dueDate=temp.nextLine();
		String newDate = dueDate.replaceAll("\\s", "-");
		Date dateDue = parseDate(newDate,formats);
		/*
		 * Puts Date into MM/dd/yyyy format (Ex. 03/21/2034)
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String dateDueString = sdf.format(dateDue);
		System.out.println(dateDueString);
		 */
		System.out.println("Is this urgent? (Yes or No): ");
		boolean urgent = false;
		String urgentString=temp.nextLine();
		urgentString=urgentString.toLowerCase();
		if(urgentString.contains("y")) {
			urgent=true;
		}
		Note dave = new Note(title, content, dateDue, urgent);
		list.add(dave);
		stack.add(dave);
	}

	public static Date parseDate(String dateString, String[] formats) {
		Date date = null;
		boolean gotIt = false;

		for (int i = 0; i < formats.length; i++) {
			String format = formats[i];
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			try {
				// parse() will throw an exception if the given dateString doesn't match
				// the current format
				date = dateFormat.parse(dateString);
				gotIt = true;
				break;
			}
			catch(ParseException e) {
				// don't do anything. just let the loop continue.
				// we may miss on 99 format attempts, but match on one format,
				// but that's all we need.
			}
		}
		return date;
	}

	/**
	 * Outline:
	 * This method should prompt the user for what kind of retrieval method they want to use: by title, by date,
	 * by urgency, or by all. It should then traverse the list or the stack and print the appropriate Note objects.
	 */
	public static String retrieve() {
		String[] formats= {"dd-MM-yy","MM-dd-YYYY","yyyy-MM-dd","MMMMM-dd-yyyy","dd-MMMMM-yy","yyyy.MM.dd","dd/MM/yy","dd/MMMMM/yyyy","MMMMM/dd/yyyy","yyyy/MM/dd"};
		System.out.println("Would you like to retrieve a title, a date, by urgency, or by all?" + "\nTitle: 1" + "\nDate: 2" + "\nUrgency: 3" + "\nAll: 4");
		Scanner scan = new Scanner(System.in);
		int condition = scan.nextInt();
		if(condition == 1) {			//retrieval by title
			Scanner console = new Scanner(System.in);
			System.out.println("Enter the title of the note that you are looking for: ");
			String title = console.nextLine();
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i).getTitle().equals(title)) {
					System.out.println(list.get(i).toString());
					break;
				}
			}
			System.out.println("A note with this title does not exist.");
		}
		else if(condition == 2) {	//retrieval by date
			Scanner console = new Scanner(System.in);
			System.out.println("Enter the date you are looking for:");
			String date = console.nextLine();
			Date d = parseDate(date, formats);
			boolean isFound = false;
			for(Note n : list) { //for each loop is more efficient
				if(n.getDate().equals(d)) { 
					System.out.println(n);
					isFound = true;
				}
			}
			if(!isFound) {
				System.out.println("A note with that date does not exist. Try again...\n"); 
			}
		}
		else if(condition == 3) {	//retrieval by urgency
			for(int i = 0; i < list.size(); i++ ) {
				if(list.get(i).getUrgent() == true) {
					System.out.println(list.get(i).toString());
				}
			}
		}
		else if(condition == 4) {	//retrieval by all
			System.out.println(Arrays.toString(stack.toArray()));	
			return null;
		}
		else {
			System.out.println("Not a valid entry, please try again.");
		}
		return null;
	}

	public static String delete() {
		if(!list.isEmpty()) {
			Scanner sc = new Scanner (System.in);
			boolean isFound = false;
			System.out.println("Enter the title of the note you would like to delete or enter \"X\" to delete ALL notes:");
			String option = sc.nextLine();
			if(option.equalsIgnoreCase("x")) {
				System.out.println("All notes will be PERMANENTLY deleted...\nAre you sure you would like to delete ALL notes? (Y/N)");
				String yn = sc.nextLine();
				if(yn.equalsIgnoreCase("y")) {
					list.clear();
					stack.clear();
					System.out.println("All notes permanently deleted.");
				}else  if(yn.equalsIgnoreCase("n")) {
					return null;
				}else {
					System.out.println("Not a valid entry, please try again.");
				}
			}
			else {
				for(Note n : list) {
					if(n.getTitle().equalsIgnoreCase(option)) {
						System.out.println(n);
						System.out.println("This note will be permanently deleted. Are you sure you want to delete this note?(Y/N)");
						String r = sc.nextLine();
						if(r.equalsIgnoreCase("y")) {
							list.remove(n);
							stack.remove(n);
							System.out.println("Note successfully deleted.");
						} 
						else if(r.equalsIgnoreCase("n")) {
							return null;
						} 
						else {
							System.out.println("Not a valid entry, please try again.");
						}
						isFound = true;
					}
				}
				if(!isFound) {
					System.out.println("Note not found. Try again...");
				}
			}
			return null;
		}
		else {
			System.out.println("You have no notes to delete.");
			return null;
		}
	}
}
