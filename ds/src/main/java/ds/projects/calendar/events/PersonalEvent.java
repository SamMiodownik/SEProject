package ds.projects.calendar.events; 

import ds.projects.calendar.events.Event;

public class PersonalEvent extends Event{

	private String description;

	public void setDescription(String string) {
		this.description = string;
	}

	public String getDescription() {
		return this.description;
	}

}
