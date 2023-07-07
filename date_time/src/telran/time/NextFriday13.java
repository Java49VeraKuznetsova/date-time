package telran.time;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalField;
import java.time.temporal.UnsupportedTemporalTypeException;


public class NextFriday13 implements TemporalAdjuster {

	

	@Override
	public Temporal adjustInto(Temporal temporal) {
		//  Auto-generated method stub
		 final int  day13 = 13;
		 final int day5 = 5;
	
		if( !temporal.isSupported(ChronoUnit.MONTHS)) {
			throw new UnsupportedTemporalTypeException("Temporal must support  MONTHS ");
		}
		
		Temporal res;
		int currentDay=temporal.get(ChronoField.DAY_OF_MONTH);
	
		int i = currentDay < day13 ? 0 : 1;
		
		temporal = temporal.plus((day13-currentDay), ChronoUnit.DAYS);

	
		do {
		res = temporal.plus(i, ChronoUnit.MONTHS);	
		currentDay = res.get(ChronoField.DAY_OF_WEEK);	
		i++;
		
		
		} while ( currentDay != day5);
 		
		return res;
	}

}

