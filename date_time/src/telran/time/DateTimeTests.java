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
		System.out.println();
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
		assertEquals(fr13Expected.toLocalDate(), zdt.with(fr13).toLocalDate());
		LocalDate ld = LocalDate.of(2023, 10, 13);
		
		assertEquals(ld, zdt.with(fr13).toLocalDate());
		LocalDate fr13Expected2 = LocalDate.of(2024, 9, 13);
		
		assertEquals(fr13Expected2, ld.with(fr13));
		LocalDate ld1 = LocalDate.of(2023, 10, 12);
		LocalDate ld2 = LocalDate.of(2023, 10, 14);
		LocalDate fr13Expected3 = LocalDate.of(2023, 10, 13);
		assertEquals(fr13Expected3, ld1.with(fr13));
		assertEquals(fr13Expected2, ld2.with(fr13));
		
		assertThrowsExactly(UnsupportedTemporalTypeException.class, 
				() -> LocalTime.now().with(new NextFriday13()));
		
			
	}
	@Test
	void canadaCurrentTime() {
		//displayCurrentTime(""); all Zones
	//	displayCurrentTime("Canada/Mountain");
		// display current date & time in all time zones related to Canada
		//Date / Time (HH:mm) / Time Zone name
		final String zoneName = "Canada";
		Set setAllZones = ZoneId.getAvailableZoneIds();
		ZonedDateTime zoneTimeNow = ZonedDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy / HH:mm / VV");
		setAllZones.stream()
		.filter(el -> el.toString().contains(zoneName))
		.forEach(el -> 
			System.out.println((zoneTimeNow.ofInstant(Instant.now(),ZoneId.of(el.toString())).format(formatter)))
		);
		
		
	}
void displayCurrentTime(String zoneName) {
	//ZoneId.getAvailableZoneIds()
	//.forEach(System.out::println);
	//ZonedDateTime.now().ofInstant(Instant.now(),ZoneId.of(zoneName));
	System.out.println(ZonedDateTime.now().ofInstant(Instant.now(),ZoneId.of(zoneName)));

}


}
