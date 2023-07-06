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
		// TODO Auto-generated method stub
		 int  day13 = 13;
	
		if(!temporal.isSupported(ChronoUnit.YEARS) &&
				!temporal.isSupported(ChronoUnit.MONTHS) &&
				!!temporal.isSupported(ChronoUnit.DAYS)) {
			throw new UnsupportedTemporalTypeException("Temporal must support YEARS / MONTHS / DAYS");
		}
		//LocalDateTime currentDay;
		int currentDay=temporal.get(ChronoField.DAY_OF_MONTH);
		System.out.println(currentDay);
		long newCurrentDay = currentDay < day13 ? day13-currentDay : currentDay - day13;
		temporal = temporal.plus(newCurrentDay, ChronoUnit.DAYS);
	
	
		if(temporal.isSupported(ChronoUnit.NANOS)) {
		//long currentTime = temporal.getLong(ChronoField.SECOND_OF_DAY);
		long currentTime = temporal.getLong(ChronoField.NANO_OF_DAY);
		 System.out.println (currentTime);
		
		temporal = temporal.minus(currentTime, ChronoUnit.NANOS);
		//temporal = temporal.minus(currentTime, ChronoUnit.MICROS);
		}
		int i=1;
		Temporal res;
		do {
		res = temporal.plus(i, ChronoUnit.MONTHS);	
		currentDay = res.get(ChronoField.DAY_OF_WEEK);	
		i++;
		
		System.out.println(currentDay + "  " + res);
	
		} while ( currentDay != 5);
 		
		return res;
	}

}

