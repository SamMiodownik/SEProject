package ds.projects.calendar; 

import java.util.ArrayList;
import ds.projects.calendar.events.Event;


public class CalendarManager{

	private final int molad = 765433;
	private final int leapYearMolad = molad * 13;
	private final int nonLeapYearMolad = molad * 12;
	private final int chalakim2016Molad = 177844; 
	private final int chalakimInDay = 25920;
	private final int chalakimInCompleteNonLaepYear = chalakimInDay * 355;
	private final int chalakimInNormalNonLeapYear = chalakimInDay * 354;
	private final int chalakimInDeficentNonLeapYear = chalakimInDay * 353;
	private final int chalakimInCompleteLeapYear = chalakimInDay * 385;
	private final int chalakimInNormalLeapYear = chalakimInDay * 384;
	private final int chalakimInDeficentLeapYear = chalakimInDay * 383;
	private int chalakimSinceBegingOfWeekLastMolad = chalakim2016Molad + chalakimInDay;
	private final int startYearHebrew = 5777;
	private final int startYearEnglish = 2016;
	private int currentYearHebrew = startYearHebrew;
	private int currentYearEnglish = startYearEnglish;
	private boolean previousYearWasALeapYear = true;
	private Year[] englishYears;
	private Year[] hebrewYears;
	private int day = 1;

// print(177844 + (765433* 12)) = 9363040
// print(7 * 25920) = 181440
//print(9363040 % 181440) = 109600
// print(109600 // 25920) = 4
// 

	public CalendarManager() {
		Year year = new Year(this.currentYearHebrew, 1);
		calculateYear()
		englishYears = new Year[10];
		hebrewYears = new Year[10];
	}

	// peram month, startDay
	// returns start day of next month
	private int addMonthDays(Month month, int d) {
		int length = month.length();
		for (i=0; i<length; i++) {
			Day day = new Day(d % 7);
			month.addDay(day);
			this.englishCallendarAddYear(day, d);
			d++;
		}
		return d % 7;
	}
private 
	private int englishCallendarAddYear(Day day, int d) {

		if ()
	}

	public void addYears(int untilYearHebrew) {
		while(currentYearHebrew < untilYearHebrew) {
			this.calculateYear();			
		}
	}

	private void calculateYear() {
		int yearInCycle = currentYearHebrew % 19;
		boolean leapYear = (yearInCycle == 3) || (yearInCycle == 6) || (yearInCycle == 8) || (yearInCycle == 11) || (yearInCycle == 14) || (yearInCycle == 17) || (yearInCycle == 19);
		int months = leapYear? 13:12;
		Year year = new Year(this.currentYearHebrew, months);
		if (leapYear) {
			this.day = this.calculateLeapYear(year, this.day);
			this.previousYearWasALeapYear = true;
		} else {
			this.day = this.calculateNonLeapYear(year, this.day);
			this.previousYearWasALeapYear = false;
		}
		this.hebrewYears[currentYearHebrew - startYearHebrew] = year;
		this.currentYearHebrew++;
	}

	private int calculateLeapYear(Year year, int d) {
		int chalakimSinceBegingOfWeek = ((this.chalakimSinceBegingOfWeekLastMolad + this.leapYearMolad) % (7 * chalakimInDay));
		int dayOfWeek = chalakimSinceBegingOfWeek / chalakimInDay;
		int chalakimSinceBeginingOfDay = chalakimSinceBegingOfWeek - (chalakimInDay * (dayOfWeek));
		boolean ruleA = false;
		boolean ruleB = false;
		// 19440 18 hours
		} if (chalakimSinceBeginingOfDay < 19440) {
			ruleA = true;
			dayOfWeek++
		}
		if (dayOfWeek == 1 || dayOfWeek == 4 || dayOfWeek == 6 || dayOfWeek == 8) {
			ruleB = true;
			dayOfWeek++;
		}
		if (ruleA && ruleB) {
			d = this.addCompleteLeapYear(year, d);
		} else if (ruleA || ruleB) {
			d = this.addNormalLeapYear(year, d);
		} else {
			d = this.addDeficientLeapYear(year, d);
		}
		return d;

	private int calculateNonLeapYear(Year year, int d) {
		int chalakimSinceBegingOfWeek = ((this.chalakimSinceBegingOfWeekLastMolad + this.nonLeapYearMolad) % (7 * chalakimInDay));
		int dayOfWeek = chalakimSinceBegingOfWeek / chalakimInDay;
		int chalakimSinceBeginingOfDay = chalakimSinceBegingOfWeek - (chalakimInDay * (dayOfWeek));
		boolean ruleA = false;
		boolean ruleB = false;
		// 77760 = 6pm on Tuesday  
		// 61764 = 3:11:20 am on Tuesday
		if (chalakimSinceBegingOfWeek > 61764 && chalakimSinceBegingOfWeek < 77760) {
			ruleA = true;
			dayOfWeek++;
		// 51840 = 6pm on Monday  
		// 42709 = 6pm on 9:32:43.3333 am  
		} else if (previousYearWasALeapYear && (chalakimSinceBegingOfWeek > 42709 && chalakimSinceBegingOfWeek < 51840)) {
			ruleA = true;
			dayOfWeek++;
		// 19440 18 hours
		} else if (chalakimSinceBeginingOfDay < 19440) {
			ruleA = true;
			dayOfWeek++
		}
		if (dayOfWeek == 1 || dayOfWeek == 4 || dayOfWeek == 6 || dayOfWeek == 8) {
			ruleB = true;
			dayOfWeek++;
		}
		if (ruleA && ruleB) {
			d = this.addCompleteNonLeapYear(year, d)
		} else if (ruleA || ruleB) {
			d = this.addNormalNonLeapYear(year, d)
		} else {
			d = this.addDeficientNonLeapYear(year, d)
		}
		return d;
	}

	public Year[] getYears(){
		return null;
	}

	public Event find(String eventPrefix) {
		return null;
	}


	// peram year, startDay
	// return start day of next year
	private int addDeficientNonLeapYear(Year year, int d) {
		d = this.addTisrei(year, d);

		Month cheshvan = new Month(29, -2);
		d = this.addMonthDays(cheshvan, d);
		year.addMonth(cheshvan);

		Month kislev = new Month(29, -3);
		d = this.addMonthDays(kislev, d);
		year.addMonth(kislev);

		return this.addNonLeapYear(year, d);
	}

	private int addDeficientLeapYear(Year year, int d) {
		d = this.addTisrei(year, d);

		Month cheshvan = new Month(29, -2);
		d = this.addMonthDays(cheshvan, d);
		year.addMonth(cheshvan);

		Month kislev = new Month(29, -3);
		d = this.addMonthDays(kislev, d);
		year.addMonth(kislev);

		return this.addLeapYear(year, d);
	}

	private int addNormalNonLeapYear(Year year, int d) {
		d = this.addTisrei(year, d);

		Month cheshvan = new Month(29, -2);
		d = this.addMonthDays(cheshvan, d);
		year.addMonth(cheshvan);

		Month kislev = new Month(30, -3);
		d = this.addMonthDays(kislev, d);
		year.addMonth(kislev);

		return this.addNonLeapYear(year, d);
	}

	private int addNormalLeapYear(Year year, int d) {
		d = this.addTisrei(year, d);

		Month cheshvan = new Month(29, -2);
		d = this.addMonthDays(cheshvan, d);
		year.addMonth(cheshvan);

		Month kislev = new Month(30, -3);
		d = this.addMonthDays(kislev, d);
		year.addMonth(kislev);

		return this.addLeapYear(year, d);
	}

	private int addCompleteNonLeapYear(Year year, int d) {
		d = this.addTisrei(year, d);

		Month cheshvan = new Month(30, -2);
		d = this.addMonthDays(cheshvan, d);
		year.addMonth(cheshvan);

		Month kislev = new Month(30, -3);
		d = this.addMonthDays(kislev, d);
		year.addMonth(kislev);

		return this.addNonLeapYear(year, d);
	}

	private int addCompleteLeapYear(Year year, int d) {
		d = this.addTisrei(year, d);

		Month cheshvan = new Month(30, -2);
		d = this.addMonthDays(cheshvan, d);
		year.addMonth(cheshvan);

		Month kislev = new Month(30, -3);
		d = this.addMonthDays(kislev, d);
		year.addMonth(kislev);

		return this.addLeapYear(year, d);

	private int addNonLeapYear(Year year int d) {
		d = this.addTevetShvat(year, d);

		Month adar = new Month(29, -7);
		d = this.addMonthDays(adar, d);
		year.addMonth(adar);

		return addYearEnd(year, d);
	}

	private int addLeapYear(Year year int d) {
		d = this.addTevetShvat(year, d);

		Month adar1 = new Month(29, -6);
		d = this.addMonthDays(adar1, d);
		year.addMonth(adar1);

		Month adar2 = new Month(29, -7);
		d = this.addMonthDays(adar2, d);
		year.addMonth(adar2);

		return addYearEnd(year, d);
	}

	private int addTisrei(Year year, int d) {
		Month tishrei = new Month(30, -1);
		d = this.addMonthDays(tishrei, d);
		year.addMonth(tishrei);

		return d;
	}

	private int addTevetShvat(Year year, int d) {
		Month tevet = new Month(29, -4);
		d = this.addMonthDays(tevet, d);
		year.addMonth(tevet);

		Month shvat = new Month(30, -5);
		d = this.addMonthDays(shvat, d);
		year.addMonth(shvat);

		return d;
	}

	private int addYearEnd(Year year, int d) {
		Month nissan = new Month(30, -8);
		d = this.addMonthDays(nissan, d);
		year.addMonth(nissan);

		Month iyar = new Month(29, -9);
		d = this.addMonthDays(iyar, d);
		year.addMonth(iyar);

		Month sivan = new Month(30, -10);
		d = this.addMonthDays(sivan, d);
		year.addMonth(sivan);

		Month tamuz = new Month(29, -11);
		d = this.addMonthDays(tamuz, d);
		year.addMonth(tamuz);

		Month av = new Month(30, -12);
		d = this.addMonthDays(av, d);
		year.addMonth(av);

		Month ellul = new Month(30, -13);
		d = this.addMonthDays(ellul, d);
		year.addMonth(ellul);

		return d;		
	}
}