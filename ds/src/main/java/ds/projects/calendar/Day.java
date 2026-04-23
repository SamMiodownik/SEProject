package ds.projects.calendar; 

import ds.projects.calendar.events.Event; 

public class Day{

	protected enum EnglishDayOfWeek {SUNDAY, MONDAY, TUESDAY, WENDESDAY, THURSDAY, FRIDAY, SATUDAY}

	protected enum HebrewDayOfWeek {YOM_RISHON , YOM_SHENI, YOM_SHLISHI, YOM_REVII, YOM_CHAMISHI, YOM_SHISHI, SHABBOS}

	public Day(int dayOfWeek){

	}

	public boolean addEvent() {
		return false;
	}

	public Event[] getEvents(){
		return null;
	}
	public EnglishDayOfWeek getEnglishDayOfWeek(){
		return null;
	}

	public HebrewDayOfWeek getHebrewDayOfWeek(){
		return null;
	}
}