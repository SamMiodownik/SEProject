package ds.projects.calendar; 

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class MonthTest{

 	@BeforeEach
    public void setUp() {
    	Month month = new Month(30, Month.JANUARY);
    	Day oneDay = new Day(1);
    	Day twoDay = new Day(2);
    	Day threeDay = new Day(3);
    	Day[] output = new Day[30];
    }

    @Test
    public void addingDayAdds() {
    	month.add(oneDay);
    	month.add(twoDay);
    	assertEquals(output, month.getDays());
    }

    @Test
    public void monthHasCorrectNumOfDays() {
    	assertEquals(30, month.length());
    }

}