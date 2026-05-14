package ds.projects.calendar; 

import java.util.ArrayList;

public class Month{

	protected Months monthName;

	private Day[] days;

	protected enum Months {JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER, TISHREI, CHESHVAN, KISLEV, TEVET, SHVAT, ADDAR_ALEF, ADDAR_BET, NISSAN, IYAR, SIVAN, TAMUZ, AV, ELLUL}

	//positve if english negative if hebrew
	public Month(int length, int month){
		this.days = new Day[length];
		this.monthName = switch (month) {
			case 1 -> Months.JANUARY;
			case 2 -> Months.FEBRUARY;
			case 3 -> Months.MARCH;
			case 4 -> Months.APRIL;
			case 5 -> Months.MAY;
			case 6 -> Months.JUNE;
			case 7 -> Months.JULY;
			case 8 -> Months.AUGUST;
			case 9 -> Months.SEPTEMBER;
			case 10 -> Months.OCTOBER;
			case 11 -> Months.NOVEMBER;
			case 12 -> Months.DECEMBER;

			case -1 -> Months.TISHREI;
			case -2 -> Months.CHESHVAN;
			case -3 -> Months.KISLEV;
			case -4 -> Months.TEVET;
			case -5 -> Months.SHVAT;
			case -6 -> Months.ADDAR_ALEF;
			case -7 -> Months.ADDAR_BET;
			case -8 -> Months.NISSAN;
			case -9 -> Months.IYAR;
			case -10 -> Months.SIVAN;
			case -11 -> Months.TAMUZ;
			case -12 -> Months.AV;
			case -13 -> Months.ELLUL;
		};

	}

	protected void addDay(Day day) {
		int index = this.findNextDay();
		this.days[index] = day;
	}

	private int findNextDay(){
		int x = 0;
		while(days[x] != null){
			x++;
		}
		return x;
	}

	public Day[] getDays(){
		return this.days;
	}

	public int length() {
		return this.days.length;
	}

}