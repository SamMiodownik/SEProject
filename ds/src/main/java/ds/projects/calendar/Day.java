package ds.projects.calendar; 

import ds.projects.calendar.events.Event; 

public class Day{

	protected EnglishDayOfWeek englishDay;

	protected HebrewDayOfWeek hebrewDay;

	private Event[] events;

	private int dayNum;

	protected enum EnglishDayOfWeek {SUNDAY, MONDAY, TUESDAY, WENDESDAY, THURSDAY, FRIDAY, SATUDAY}

	protected enum HebrewDayOfWeek {YOM_RISHON , YOM_SHENI, YOM_SHLISHI, YOM_REVII, YOM_CHAMISHI, YOM_SHISHI, SHABBOS}

	public Day(int dayOfWeek){
		switch (dayOfWeek) {
			case 0:
				this.englishDay = EnglishDayOfWeek.SUNDAY;
				this.hebrewDay = HebrewDayOfWeek.YOM_RISHON;
				this.dayNum = 1;
				break;
			case 1:
				this.englishDay = EnglishDayOfWeek.MONDAY;
				this.hebrewDay = HebrewDayOfWeek.YOM_SHENI;
				this.dayNum = 2;
				break;
			case 2:
				this.englishDay = EnglishDayOfWeek.TUESDAY;
				this.hebrewDay = HebrewDayOfWeek.YOM_SHLISHI;
				this.dayNum = 3;
				break;
			case 3:
				this.englishDay = EnglishDayOfWeek.WENDESDAY;
				this.hebrewDay = HebrewDayOfWeek.YOM_REVII;
				this.dayNum = 4;
				break;
			case 4:
				this.englishDay = EnglishDayOfWeek.THURSDAY;
				this.hebrewDay = HebrewDayOfWeek.YOM_CHAMISHI;
				this.dayNum = 5;
				break;
			case 5:
				this.englishDay = EnglishDayOfWeek.FRIDAY;
				this.hebrewDay = HebrewDayOfWeek.YOM_SHISHI;
				this.dayNum = 6;
				break;
			case 6:
				this.englishDay = EnglishDayOfWeek.SATUDAY;
				this.hebrewDay = HebrewDayOfWeek.SHABBOS;
				this.dayNum = 7;
				break;
		}
	}

	public int getDayNum() {
		return this.dayNum;
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
}