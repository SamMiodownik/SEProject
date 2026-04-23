package ds.projects.calendar.events; 

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PersonalEventTest{
	@Test
	void personalEventSetAndGetTitle(){
		PersonalEvent pe = new PersonalEvent();
		String name = "Biology Test";
		pe.setTitle(name);
		assertEquals(hldy.getTitle(), name);
	}
	@Test
	void personalEventSetAndGetDescription(){
		PersonalEvent pe = new PersonalEvent();
		String description = "Biology things";
		pe.setDescription(description);
		assertEquals(hldy.getDescription(), description);
	}
	@Test
	void personalEventInstanceOfEvent(){
		PersonalEvent pe = new PersonalEvent();
		assertTrue(pd instanceof Event);
	}
}