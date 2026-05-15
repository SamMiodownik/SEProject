package ds.projects.calendar; 

import java.util.ArrayList;
import ds.projects.calendar.events.Event;
import java.util.Set;


public class CalendarManager{

	private final int startYearHebrew = 5777;
	private final int startYearEnglish = 2015;
	private final int molad = 765433;
	private final int leapYearMolad = molad * 13;
	private final int nonLeapYearMolad = molad * 12;
	private final int chalakimInDay = 25920;
	private final int chalakimInCompleteNonLaepYear = chalakimInDay * 355;
	private final int chalakimInNormalNonLeapYear = chalakimInDay * 354;
	private final int chalakimInDeficentNonLeapYear = chalakimInDay * 353;
	private final int chalakimInCompleteLeapYear = chalakimInDay * 385;
	private final int chalakimInNormalLeapYear = chalakimInDay * 384;
	private final int chalakimInDeficentLeapYear = chalakimInDay * 383;
	// 177844 = chalakim after 6 pm previous motzei shabbos for 5777 Molad
	private int chalakimSinceBegingOfWeekLastMolad = 177844;
	private int currentYearHebrew = startYearHebrew;
	private int currentYearEnglish = startYearEnglish;
	private int currentMonthEnglish = 0;
	private int currentDayEnglish = 0;
	private int d = 1;
	private boolean previousYearWasALeapYear = true;
	private Year[] englishYears;
	private Year[] hebrewYears;
	private EventTrie trie;

	public CalendarManager() {
		englishYears = new Year[10];
		hebrewYears = new Year[10];
		this.createFirstEnglishYear();
		this.createFirstHebrewYear();
		trie = new EventTrie();
	}

	private Year[] doubleArray(Year[] oldYear) {
		Year[] newYear = new Year[oldYear.length*2];
		for (int i=0; i < oldYear.length; i++) {
			newYear[i] = oldYear[i];
		}
		return newYear;
	}

	private void createFirstEnglishYear() {
		Year year = new Year(2015, 3);
		Month october = new Month(29, 10);
		Month november = new Month(30, 11);
		Month december = new Month(31, 12);
		year.addMonth(october);
		year.addMonth(november);
		year.addMonth(december);
		this.currentYearEnglish ++;
	}

	private void createFirstHebrewYear(){
		Year currentYear = new Year(5777, 12);
		addDeficientNonLeapYear(currentYear);
		this.previousYearWasALeapYear = false;
		this.currentYearHebrew++;
	}

	// peram month
	private void addMonthDays(Month month, int hebrewMonth, Year hebrewYear) {
		for (int i=0; i<month.getDays().length; i++) {
			Day day = new Day(this.d);
			day.setHebrewYear(hebrewYear.getYear());
			day.setHebrewMonth(hebrewMonth);
			day.setHebrewDay(i + 1);
			month.addDay(day);
			this.englishCallendarAdd(day);
			this.d++;
		}
		this.d = this.d % 7;
	}

	private void englishCallendarAdd(Day hebrewDay) {
		Year year = this.englishYears[this.currentYearEnglish];
		Month[] months = year.getMonths();
		Month month = months[this.currentMonthEnglish];
		Day[] days = month.getDays();
		this.addEnglishDay(months, days, hebrewDay);
	}

	private void addEnglishDay(Month[] year, Day[] month, Day hebrewDay) {
		if (this.currentDayEnglish >= month.length) {
			if (this.currentMonthEnglish >= year.length) {
				month = this.addEnglishYear();
				this.currentMonthEnglish = 0;
				this.currentDayEnglish = 0;
			}
			this.currentMonthEnglish ++;
			month = year[this.currentMonthEnglish].getDays();
			this.currentDayEnglish = 0;
		}
		hebrewDay.setEnglishDay(this.currentDayEnglish + 1);
		hebrewDay.setEnglishMonth(this.currentMonthEnglish + 1);
		hebrewDay.setEnglishYear(this.currentYearEnglish +1);
		month[this.currentDayEnglish] = hebrewDay;
		this.currentDayEnglish ++;
	}

	private Day[] addEnglishYear() {
		this.currentYearEnglish ++;
		Year year = new Year(currentYearEnglish, 12);
		this.addMonthsToYear(year);
		Month[] months = year.getMonths();
		Month month = months[0];
		Day[] days = month.getDays();
		if ((this.currentYearEnglish - this.startYearEnglish) < englishYears.length) {
			this.englishYears = this.doubleArray(this.englishYears);
		}
		this.englishYears[this.currentYearEnglish - this.startYearEnglish] = year;
		return days;
	}

	private void addMonthsToYear(Year year) {
		Month january = new Month(31, 1);
		Month march = new Month(31, 3);
		Month may = new Month(31, 5);
		Month july = new Month(31, 7);
		Month august = new Month(31, 8);
		Month october = new Month(31, 10);
		Month december = new Month(31, 12);
		
		Month april = new Month(30, 4);
		Month june = new Month(30, 6);
		Month september = new Month(30, 9);
		Month november = new Month(30, 11);

		int febuaryDays = (this.currentYearEnglish % 4) == 0? 29:28;
		Month febuary = new Month(febuaryDays, 2);

		year.addMonth(january);
		year.addMonth(febuary);
		year.addMonth(march);
		year.addMonth(april);
		year.addMonth(may);
		year.addMonth(june);
		year.addMonth(july);
		year.addMonth(august);
		year.addMonth(september);
		year.addMonth(october);
		year.addMonth(november);
		year.addMonth(december);
	}

	public void addYears(int untilYearHebrew) {
		while(currentYearHebrew < untilYearHebrew) {
			this.calculateYear();			
		}
	}

	private void calculateYear() {
		int yearInCycle = currentYearHebrew % 19;
		boolean leapYear = (yearInCycle == 2) || (yearInCycle == 5) || (yearInCycle == 7) || (yearInCycle == 10) || (yearInCycle == 13) || (yearInCycle == 16) || (yearInCycle == 0);
		int months = leapYear? 13:12;
		Year year = new Year(this.currentYearHebrew, months);
		if (leapYear) {
			this.calculateLeapYear(year);
			this.previousYearWasALeapYear = true;
		} else {
			this.calculateNonLeapYear(year);
			this.previousYearWasALeapYear = false;
		}
		if ((this.currentYearHebrew - this.startYearHebrew) < hebrewYears.length) {
			this.hebrewYears = this.doubleArray(this.hebrewYears);
		}
		this.hebrewYears[this.currentYearHebrew - this.startYearHebrew] = year;
		this.currentYearHebrew++;
	}

	private void calculateLeapYear(Year year) {
		int chalakimSinceBegingOfWeek = ((this.chalakimSinceBegingOfWeekLastMolad + this.leapYearMolad) % (7 * chalakimInDay));
		int dayOfWeek = chalakimSinceBegingOfWeek / chalakimInDay;
		int chalakimSinceBeginingOfDay = chalakimSinceBegingOfWeek - (chalakimInDay * (dayOfWeek));
		boolean ruleA = false;
		boolean ruleB = false;
		// 19440 18 hours
		if (chalakimSinceBeginingOfDay < 19440) {
			ruleA = true;
			dayOfWeek++;
		}
		if (dayOfWeek == 1 || dayOfWeek == 4 || dayOfWeek == 6 || dayOfWeek == 8) {
			ruleB = true;
			dayOfWeek++;
		}
		if (ruleA && ruleB) {
			this.addCompleteLeapYear(year);
		} else if (ruleA || ruleB) {
			this.addNormalLeapYear(year);
		} else {
			this.addDeficientLeapYear(year);
		}
	}

	private void calculateNonLeapYear(Year year) {
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
			dayOfWeek++;
		}
		if (dayOfWeek == 1 || dayOfWeek == 4 || dayOfWeek == 6 || dayOfWeek == 8) {
			ruleB = true;
			dayOfWeek++;
		}
		if (ruleA && ruleB) {
			this.addCompleteNonLeapYear(year);
		} else if (ruleA || ruleB) {
			this.addNormalNonLeapYear(year);
		} else {
			this.addDeficientNonLeapYear(year);
		}
	}

	public Year[] getEnglishYears(){
		return this.englishYears;
	}

	public Year[] getHebrewYears(){
		return this.hebrewYears;
	}

	public Event find(String eventPrefix) {
		return null;
	}


	// peram year, startDay
	// return start day of next year

	private void addLeapYear(Year year) {
		this.addTevetShvat(year);

		Month adar1 = new Month(29, -6);
		this.addMonthDays(adar1, 6, year);
		year.addMonth(adar1);

		Month adar2 = new Month(29, -7);
		this.addMonthDays(adar2, 7, year);
		year.addMonth(adar2);

		this.addYearEnd(year);
	}

	private void addTisrei(Year year) {
		Month tishrei = new Month(30, -1);
		this.addMonthDays(tishrei, 1, year);
		year.addMonth(tishrei);
	}

	private void addTevetShvat(Year year) {
		Month tevet = new Month(29, -4);
		this.addMonthDays(tevet, 4, year);
		year.addMonth(tevet);

		Month shvat = new Month(30, -5);
		this.addMonthDays(shvat, 5, year);
		year.addMonth(shvat);
	}

	private void addYearEnd(Year year) {
		Month nissan = new Month(30, -8);
		this.addMonthDays(nissan, 8, year);
		year.addMonth(nissan);

		Month iyar = new Month(29, -9);
		this.addMonthDays(iyar, 9, year);
		year.addMonth(iyar);

		Month sivan = new Month(30, -10);
		this.addMonthDays(sivan, 10, year);
		year.addMonth(sivan);

		Month tamuz = new Month(29, -11);
		this.addMonthDays(tamuz, 11, year);
		year.addMonth(tamuz);

		Month av = new Month(30, -12);
		this.addMonthDays(av, 12, year);
		year.addMonth(av);

		Month ellul = new Month(30, -13);
		this.addMonthDays(ellul, 13, year);
		year.addMonth(ellul);		
	}

	private void addDeficientNonLeapYear(Year year) {
		this.addTisrei(year);

		Month cheshvan = new Month(29, -2);
		this.addMonthDays(cheshvan, 2, year);
		year.addMonth(cheshvan);

		Month kislev = new Month(29, -3);
		this.addMonthDays(kislev, 3, year);
		year.addMonth(kislev);

		this.addNonLeapYear(year);
	}

	private void addDeficientLeapYear(Year year) {
		this.addTisrei(year);

		Month cheshvan = new Month(29, -2);
		this.addMonthDays(cheshvan, 2, year);
		year.addMonth(cheshvan);

		Month kislev = new Month(29, -3);
		this.addMonthDays(kislev, 3, year);
		year.addMonth(kislev);

		this.addLeapYear(year);
	}

	private void addNormalNonLeapYear(Year year) {
		this.addTisrei(year);

		Month cheshvan = new Month(29, -2);
		this.addMonthDays(cheshvan, 2, year);
		year.addMonth(cheshvan);

		Month kislev = new Month(30, -3);
		this.addMonthDays(kislev, 3, year);
		year.addMonth(kislev);

		this.addNonLeapYear(year);
	}

	private void addNormalLeapYear(Year year) {
		this.addTisrei(year);

		Month cheshvan = new Month(29, -2);
		this.addMonthDays(cheshvan, 2, year);
		year.addMonth(cheshvan);

		Month kislev = new Month(30, -3);
		this.addMonthDays(kislev, 3, year);
		year.addMonth(kislev);

		this.addLeapYear(year);
	}

	private void addCompleteNonLeapYear(Year year) {
		this.addTisrei(year);

		Month cheshvan = new Month(30, -2);
		this.addMonthDays(cheshvan, 2, year);
		year.addMonth(cheshvan);

		Month kislev = new Month(30, -3);
		this.addMonthDays(kislev, 3, year);
		year.addMonth(kislev);

		this.addNonLeapYear(year);
	}

	private void addCompleteLeapYear(Year year) {
		this.addTisrei(year);

		Month cheshvan = new Month(30, -2);
		this.addMonthDays(cheshvan, 2, year);
		year.addMonth(cheshvan);

		Month kislev = new Month(30, -3);
		this.addMonthDays(kislev, 3, year);
		year.addMonth(kislev);

		this.addLeapYear(year);
	}

	private void addNonLeapYear(Year year) {
		this.addTevetShvat(year);

		Month adar = new Month(29, -7);
		this.addMonthDays(adar, 7, year);
		year.addMonth(adar);

		addYearEnd(year);
	}

	public void addEventToTrie(String eventTitle, Day day){
		this.trie.put(eventTitle, day);
	}
	public Set<Day> searchForEvent(String eventTitle){
		return this.trie.get(eventTitle);
	}
}