package ds.projects.calendar; 

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class YearTest{}

 	@BeforeEach
    public void setUp() {
    	Year year = new year(12);

    	Month month = new Month(30, Month.JANUARY);
    }

    @Test
    public void addingMonthAdds() {
    	month.add(oneDay);
    	month.add(twoDay);
    	assertEquals(output, month.getDays());
    }

    @Test
    public void yearHasCorrectNumOfMonths() {
    	assertEquals(30, month.length());
    }

