package ds.projects.calendar; 

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import ds.projects.calendar.Day;

public class DayTest{
	@Test
	void addEventAndGetEvents(){
		Day day = new Day(1);
		Holiday hldy = new Holiday();
		hldy.setTitle("Sukkos");
		day.addEvent(hldy);
		PersonalEvent pe = new PersonalEvent();
		pe.setTitle("Activity");
		Event[] array = new Event[2];
		array[0] = hldy;
		array[1] = pe;
		assertEquals(array, day.getEvents);
	}
	@Test
	void addEventBoolean(){
		Day day = new Day(1);
		Holiday hldy = new Holiday();
		hldy.setTitle("Sukkos");
		assertTrue(day.addEvent(hldy));
		assertFalse(day.addEvent(hldy));
	}
	@Test
	void constructorException(){
		assertThrows(IllegalArgumentException.class, ()->{
			Day day = new Day(0);
		});
		assertThrows(IllegalArgumentException.class, ()->{
			Day day = new Day(8);
		});
		assertThrows(IllegalArgumentException.class, ()->{
			Day day = new Day(50);
		});
		assertThrows(IllegalArgumentException.class, ()->{
			Day day = new Day(-50);
		});
	}
	@Test
	void getEnglishDayOfWeek(){
		Day day = new Day(3);
		assertEquals(day.getEnglishDayOfWeek(), Day.EnglishDayOfWeek.TUESDAY);
	}
	@Test
	void getHebrewDayOfWeek(){
		Day day = new Day(7);
		assertEquals(day.getHebrewDayOfWeek(), Day.getHebrewDayOfWeek.SHABBOS);
	}
}