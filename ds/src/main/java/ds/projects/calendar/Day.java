package ds.projects.calendar; 

import ds.projects.calendar.events.Event;
import java.util.ArrayList; 

public class Day{

	protected EnglishDayOfWeek englishDay;

	protected HebrewDayOfWeek hebrewDay;

	private ArrayList<Event> events;

	protected enum EnglishDayOfWeek {SUNDAY, MONDAY, TUESDAY, WENDESDAY, THURSDAY, FRIDAY, SATUDAY}

	protected enum HebrewDayOfWeek {YOM_RISHON , YOM_SHENI, YOM_SHLISHI, YOM_REVII, YOM_CHAMISHI, YOM_SHISHI, SHABBOS}

	public Day(int dayOfWeek){
		events = new ArrayList<>();
		switch (dayOfWeek) {
			case 1:
				this.englishDay = EnglishDayOfWeek.SUNDAY;
				this.hebrewDay = HebrewDayOfWeek.YOM_RISHON;
				break;
			case 2:
				this.englishDay = EnglishDayOfWeek.MONDAY;
				this.hebrewDay = HebrewDayOfWeek.YOM_SHENI;
				break;
			case 3:
				this.englishDay = EnglishDayOfWeek.TUESDAY;
				this.hebrewDay = HebrewDayOfWeek.YOM_SHLISHI;
				break;
			case 4:
				this.englishDay = EnglishDayOfWeek.WENDESDAY;
				this.hebrewDay = HebrewDayOfWeek.YOM_REVII;
				break;
			case 5:
				this.englishDay = EnglishDayOfWeek.THURSDAY;
				this.hebrewDay = HebrewDayOfWeek.YOM_CHAMISHI;
				break;
			case 6:
				this.englishDay = EnglishDayOfWeek.FRIDAY;
				this.hebrewDay = HebrewDayOfWeek.YOM_SHISHI;
				break;
			case 7:
				this.englishDay = EnglishDayOfWeek.SATUDAY;
				this.hebrewDay = HebrewDayOfWeek.SHABBOS;
				break;
		}
	}

	public boolean addEvent(Event e) {
		int x;
		for(x = 0; events.get(x) != null; x++){
			if(events.get(x).getTitle().equals(e.getTitle())){
				return false;
			}
		}
		events(x) = e;
		return true;
	}

	public ArrayList<Event> getEvents(){
		return this.events;
	}
}