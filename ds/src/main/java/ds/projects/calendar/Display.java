package ds.projects.calendar; 

import java.util.*;
import ds.projects.calendar.events.PersonalEvent;

public class Display {
	CalendarManager cm;
	Scanner scanner;
	int latestHebrewYear = 5790;
	public static void main(String args[]) {
		cm = new CalendarManager();
		scanner = new Scanner(System.in);
		welcome();
		giveOptions();
	}

	private void welcome(){
		System.out.println("Welcome to the Hebrew-English Lunar-Solar Calendar.\n");
		System.out.println("Adding Calendar years...\n");
		cm.addYears(5790);
	}
	//Gives options for next user operation
	private void giveOptions(){
		System.out.println("Would you like to:\n1. View a specific day.\n2. Access a certain event's day.\n3. Add an event.\nPlease answer by typing \"1\" \"2\" or \"3\".");
		int x = scannerOptions(1, 3);
		if(x == 1){
			getDay();
		}
		if(x == 2){
			getDayByEvent();
		}
		if(x == 3){
			addEvent();
		}

	}
	//Chooses an int option from the command line in range first-last
	private int scannerOptions(int first, int last){
		int option;
		while(true){
			if(scanner.hasNextInt()){
				option = scanner.next();
				if(option < first || option > last){
					System.out.println("Please enter a number between " + first + " and " + last + ".");
				}else{
					return option;
				}
			}else{
				System.out.println("Please enter a valid integer.");
			}
		}
	}

	private void getDay(){
		System.out.println("Would you like to access:\n1. An english date\n2. A hebrew date?");
		int option = scannerOptions(1, 2);
		if(option == 1){
			getDayEnglish();
		}else{
			getDayHebrew();
		}
	}

	private void getDayEnglish(){
		System.out.println("Please enter an English year > 2015");
		int year;
		boolean valid = false;
		while(!valid){
			if(scanner.hasNextInt()){
				year = scanner.next();
				if(year < 2016){
					System.out.println("Invalid year. Please enter a year > 2015");
				}else{
					valid = true;
				}
			}else{
				System.out.println("Invalid input. Please enter a year > 2015");
			}
		}
		getDayMonth(1, year - 2016);
	}

	private void getDayMonth(int englishOrHebrew, int index){
		Year[] years;
		if(englishOrHebrew == 1){
			years = cm.getHebrewYears(); //Need to make this method in CalendarManager!
		}else{
			years = cm.getEnglishYears(); //Need to make this method in CalendarManager!
		}
		if(index > years.length-1){
			//Not sure how this is going to be implemented yet but we will have to generate more years!
		}
		System.out.println("Please enter which month you would like to access in number form (Ex: \"3\", \"11\".");
		int monthIndex = scannerOptions(1, years[index].length) - 1;
		getDayFinal(years[index].getMonths()[monthIndex]);
	}

	private void getDayFinal(Month month){
		System.out.println("Please enter which day of the month you would like to view.");
		int day = scannerOptions(1, month.length) -1;
		System.out.println(month[day]); // Need to make a toString method for day to allow a print!
	}

	private void getDayHebrew(){
		System.out.println("Please enter a Hebrew year > 5776");
		int year;
		boolean valid = false;
		while(!valid){
			if(scanner.hasNextInt()){
				year = scanner.next();
				if(year < 5777){
					System.out.println("Invalid year. Please enter a year > 5776");
				}else{
					valid = true;
				}
			}else{
				System.out.println("Invalid input. Please enter a year > 5776");
			}
		}
		getDayMonth(2, year - 5777);
	}

	private void getDayByEvent(){
		System.out.println("Please enter an event, or an event prefix. (Ex: \"Basketball practice\", \"Baske\".");
		String event = scanner.next();
		Set<Day> days = cm.searchForEvent(event); //Create this method in CalendarManager!
		if(days.size() == 0){
			System.out.println("No matches. Would you like to:\n1. Try a new search.\n2. Exit.");
			int option = scannerOptions(1, 2);
			if(option == 1){
				getDayByEvent();
			}else{
				giveOptions();
			}
		}else{
			for(Day day : days){
				System.out.println(day); // Need to make a toString method for day to allow a print!
			}
		}
	}

	private void addEvent(){
		PersonalEvent evt = new PersonalEvent();
		System.out.println("Please enter the title of the event you would like to add");
		evt.setTitle(scanner.next());
		System.out.println("Would you like to add an event description?\n1. Yes.\n2. No.");
		int option = scannerOptions(1, 2);
		if(option == 1){
			System.out.println("Please enter an event description");
			evt.setDescription(scanner.next());
		}
		System.out.println("Would you like to:\n1. Add the event to a specific day\n2. Make a recurring event?");
		int option = scannerOptions(1, 2);
		if(option == 1){
			getDayForEvent(evt);
		}else{
			addRecurringEvent(evt);
		}
	}
	private void getDayForEvent(PersonalEvent evt){
		System.out.println("Would you like to add an event to:\n1. An english date\n2. A hebrew date?");
		int option = scannerOptions(1, 2);
		if(option == 1){
			addEventEnglish(evt);
		}else{
			addEventHebrew(evy);
		}
	}
	private void addEventEnglish(PersonalEvent evt){
		System.out.println("Please enter an English year > 2015");
		int year;
		boolean valid = false;
		while(!valid){
			if(scanner.hasNextInt()){
				year = scanner.next();
				if(year < 2016){
					System.out.println("Invalid year. Please enter a year > 2015");
				}else{
					valid = true;
				}
			}else{
				System.out.println("Invalid input. Please enter a year > 2015");
			}
		}
		addEventMonth(1, year - 2016, evt);
	}
	private void addEventHebrew(PersonalEvent evt){
		System.out.println("Please enter a Hebrew year > 5776");
		int year;
		boolean valid = false;
		while(!valid){
			if(scanner.hasNextInt()){
				year = scanner.next();
				if(year < 5777){
					System.out.println("Invalid year. Please enter a year > 5776");
				}else{
					valid = true;
				}
			}else{
				System.out.println("Invalid input. Please enter a year > 5776");
			}
		}
		addEventMonth(2, year - 5777, evt);
	}
	private void addEventMonth(int englishOrHebrew, int index, PersonalEvent evt){
		Year[] years;
		if(englishOrHebrew == 1){
			years = cm.getHebrewYears(); //Need to make this method in CalendarManager!
		}else{
			years = cm.getEnglishYears(); //Need to make this method in CalendarManager!
		}
		System.out.println("Please enter which month you would like to add an event to in number form. (Ex: \"3\" -March, \"11\"-November)");
		int monthIndex = scannerOptions(1, years[index].length) - 1;
		getEventDay(years[index].getMonths()[monthIndex], evt);
	}
	private void getEventDay(Month month, PersonalEvent evt){
		System.out.println("Please enter which day of the month you would like to add an event to.");
		Day day = month[scannerOptions(1, month.length) -1;];
		addEvent(day, evt);
	}
	private void addEvent(Day day, PersonalEvent evt){
		day.addEvent(evt);
		cm.addEventToTrie(evt.getTitle(), day); //Need to make this method in CalendarManager: addEventToTrie(String, Day)
	}

	private void addRecurringEvent(PersonalEvent evt){
		System.out.println("Please choose from the following recurring event options:");
		System.out.println("1. Add an event on a specific day of the Hebrew year.");
		System.out.println("2. Add an event on a specific day of the English year.");
		System.out.println("3. Add an event on a specific day of the Hebrew month.");
		System.out.println("4. Add an event on a specific day of the Hebrew month.");
		System.out.println("5. Add an event to a specific day of the week.");
		int option = scannerOptions(1, 5);
		if(option == 1){
			recurringEventByYear(1, evt);
		}
		if(option == 2){
			recurringEventByYear(2, evt);
		}
		if(option == 3){
			recurringEventByMonth(1, evt); //Haven't created this method yet!
		}
		if(option == 4){
			recurringEventByMonth(2, evt); //Haven't created this method yet!
		}
		if(option == 5){
			recurringEventByWeek(evt); //Haven't created this method yet!
		}
	}
	private void recurringEventByYear(int englishOrHebrew, PersonalEvent evt){
		System.out.println("Which month would you like to add the yearly event to?");
		int monthIndex
		if(englishOrHebrew == 1){
			monthIndex = scannerOptions(1, 12) -1;
		}else{
			monthIndex = scannerOptions(1, 13) -1;
		}
		recurringEventByYearChooseDay(englishOrHebrew, monthIndex, evt);
	}
	private void recurringEventByYearChooseDay(int englishOrHebrew, int monthIndex, PersonalEvent evt){
		System.out.println("Please choose a day in the month to add a recurring event to.");
		int dayIndex = scannerOptions(1, 31) - 1;
		Year[] years;
		if(englishOrHebrew == 1){
			years = cm.getEnglishYears();
		}else{
			years = cm.getHebrewYears();
		}
		for(Year year : years){
			if(year.getMonths()[monthIndex] != null){
				if(year.getMonths()[monthIndex].getDays()[dayIndex] != null){
					addEvent(year.getMonths()[monthIndex].getDays()[dayIndex], evt);
				}
			}
		}
	}
}