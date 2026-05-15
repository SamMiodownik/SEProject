package ds.projects.calendar;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class CalendarManagerTest{

CalendarManager cm;

 	@BeforeEach
    public void setUp() {
    	cm = new CalendarManager();

    }

    @Test
    public void CreatingYearsCreatesProperNumberOfHebrewYears() {
    	cm.addYears(5781);
    	assertEquals(5, cm.getHebrewYears().length);
    }

    @Test
    public void CreatingYearsCreatesProperNumberOfEnglishYears() {
    	cm.addYears(5781);
    	assertEquals(5, cm.getEnglishYears().length	);
    }

    @Test
    public void HebrewNonLeapYearContainsProperMonths() {
    	cm.addYears(5781);
    	Year[] years = cm.getHebrewYears();
    	Year year = years[cm.getHebrewYears().length -1];
    	Months[] months = year.getMonths();
    	assertEquals(12, months);
    }

    @Test
    public void EnglishYearsContainsProperNumberOfMonths() {
     	cm.addYears(5781);
    	Year[] years = cm.getEnglishYears();
    	Year year = years[cm.getEnglishYears().length -1];
    	Months[] months = year.getMonths();
    	assertEquals(12, months);
    }

    @Test
    public void EnglishAndHebrewDayMatch() {
     	cm.addYears(5786);

    	Year[] years1 = cm.getHebrewYears();
    	Year year1 = years1[cm.getHebrewYears().length -1];
    	Months[] months1 = year1.getMonths();
    	Month month1 = months1[7-1];
    	Days[] days1 = month1.getDays();
    	Day day1 = day1[18-1];

    	Year[] years2 = cm2.getEnglishYears();
    	Year year2 = years2[cm.getEnglishYears().length -1];
    	Months[] months2 = year2.getMonths();
    	Month month2 = months2[5-1];
    	Days[] days2 = month2.getDays();
    	Day day2 = day2[5-1];
	
    	assertEquals(day1, day2);
    }
}