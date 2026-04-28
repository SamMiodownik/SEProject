package ds.projects.calendar; 

import java.util.Scanner;

public class Display {
	CalendarManager cm;
	Scanner scanner;
	public static void main(String args[]) {
		cm = new CalendarManager();
		scanner = new Scanner(System.in);
		welcome();
		giveOptions();
	}
	private void welcome(){
		System.out.println("Welcome to the Hebrew-English Lunar-Solar Calendar.\n");
		System.out.println("Adding Calendar years...\n");
		cm.addYears(); //How many years are we adding and where are we starting from?
	}
	//Gives options for next user operation
	private void giveOptions(){
		System.out.println("Would you like to:\n1. View a specific day?\n2. Access a certain event's day. Please answer by typing \"1\" or \"2\".");
		int x = scannerOptions(1, 2);
		if(x == 1){
			getDay();
		}else{
			getDayByEvent();
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
	//Not finished
	private void getDayEnglish(){
		System.out.println("Please enter an English year > 2016");

	}

	private void getDayHebrew();

	private void getDayByEvent();
}