package ds.projects.calendar; 

import ds.projects.calendar.events.Event; 

public class Day{

	private EnglishDayOfWeek englishDay;

	private HebrewDayOfWeek hebrewDay;

	private Event[] events;

	protected enum EnglishDayOfWeek {SUNDAY, MONDAY, TUESDAY, WENDESDAY, THURSDAY, FRIDAY, SATUDAY}

	protected enum HebrewDayOfWeek {YOM_RISHON , YOM_SHENI, YOM_SHLISHI, YOM_REVII, YOM_CHAMISHI, YOM_SHISHI, SHABBOS}

	public Day(int dayOfWeek){

	}

	public boolean addEvent(Event e) {
		int x;
		for(x = 0; events[x] != null; x++){
			if(events[x].getTitle().equals(e.getTitle())){
				return false;
			}
		}
		events[x] = e;
		return true;
	}

	public Event[] getEvents(){
		return this.events;
	}
	public EnglishDayOfWeek getEnglishDayOfWeek(){
		return this.englishDay;
	}

	public HebrewDayOfWeek getHebrewDayOfWeek(){
		return this.hebrewDay;
	}
}