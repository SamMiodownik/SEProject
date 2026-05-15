package ds.projects.calendar; 

import java.util.ArrayList;

public class Year{

	private int name;

	private Month[] months;

	public Year(int yearNumber, int monthLength){
		this.name = yearNumber;
		this.months = new Month[monthLength];
	}

	protected void addMonth(Month month){
		int index = this.findNextMonth();
		months[index] = month;
	}

	private int findNextMonth(){
		int x = 0;
		while(months[x] != null){
			x++;
		}
		return x;
	}

	public Month[] getMonths(){
		return this.months;
	}

	public int getYear() {
		return this.name;
	}
}