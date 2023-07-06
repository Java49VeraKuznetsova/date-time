package telran.time;

import static org.junit.jupiter.api.Assertions.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Set;

import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;

class DateTimeTests {

	@Test
	void test() {
		LocalDate birthAS = LocalDate.of(1799, 6, 6);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM dd, YYYY EEEE");
		
		System.out.println(birthAS.format(dtf));
		System.out.printf("Bar MIzva of AS %s", birthAS.plusYears(13).format(dtf));
		LocalDate barMizva = birthAS.plusYears(13);
		assertEquals(barMizva, birthAS.with(new BarMizvaAdjuster()));
		//System.out.printf("Bar MIzva of AS %s", barMizva.format(dtf));
	assertThrowsExactly(UnsupportedTemporalTypeException.class, 
			() -> LocalTime.now().with(new BarMizvaAdjuster()));
	}
	
	@Test
	void nextFriday13Test() {
		TemporalAdjuster fr13 = new NextFriday13();
		ZonedDateTime zdt = ZonedDateTime.now();
		ZonedDateTime fr13Expected = ZonedDateTime.of(2023, 10, 13, 0, 0, 0, 0, ZoneId.systemDefault());
		assertEquals(fr13Expected, zdt.with(fr13));
		LocalDate fr13Expected2 = LocalDate.of(2024, 9, 13);
		LocalDate ld = LocalDate.of(2023, 10, 13);
		assertEquals(fr13Expected2, ld.with(fr13));
		
			
	}
	@Test
	void canadaCurrentTime() {
		//displayCurrentTime(""); all Zones
		displayCurrentTime("Canada/Mountain");
		//TODO display current date & time in all time zones related to Canada
		//Date / Time (HH:mm) / Time Zone name
		String strCanada = "Canada";
		Set setAllZones = ZoneId.getAvailableZoneIds();
		setAllZones.contains(strCanada);
		setAllZones.stream()
		.filter(el -> el.toString().contains(strCanada))
		.forEach(el -> displayCurrentTime(el.toString()));
		
		
	}
void displayCurrentTime(String zoneName) {
	//ZoneId.getAvailableZoneIds()
	//.forEach(System.out::println);
	//ZonedDateTime.now().ofInstant(Instant.now(),ZoneId.of(zoneName));
	//System.out.println(ZonedDateTime.now().ofInstant(Instant.now(),ZoneId.of(zoneName)));
	ZonedDateTime zoneCurrent = ZonedDateTime.now().ofInstant(Instant.now(),ZoneId.of(zoneName));
	
	DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
	String strToPrint = "Date "+zoneCurrent.format(formatter1)+" / Time  "+zoneCurrent.format(formatter2)+" / Time zone name "+zoneCurrent.getZone();
	
	System.out.println(strToPrint);
	
}
}