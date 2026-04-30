package ds.projects.calendar.events; 

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class HolidayTest{
	@Test
	void holidaySetAndGetTitle(){
		Holiday hldy = new Holiday();
		String name = "Pesach";
		hldy.setTitle(name);
		assertEquals(hldy.getTitle(), name);
	}
	@Test
	void holidayInstanceOfEvent(){
		Holiday hldy = new Holiday();
		assertTrue(hldy instanceof Event);
	}
}