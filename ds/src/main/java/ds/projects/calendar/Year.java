package ds.projects.calendar; 

import java.util.ArrayList;

public class Year{

	private int name;

	private Month[] months;

	public Year(int yearNumber, int monthLength){
		this.name = yearNumber;
		this.months = new Month[monthLength];
	}

	private void addMonth(Month month, int number) {
		if(number > this.months.length){
			throw new IllegalArgumentException("Year does not contain " + number + " months.");
		}
		months[number] = month;
	}

	public Month[] getMonths(){
		return this.months;
	}
}