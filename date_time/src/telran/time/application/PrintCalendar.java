package telran.time.application;

import java.time.*;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.Locale;

public class PrintCalendar {
private static final int TITLE_OFFSET = 8;
static DayOfWeek[] daysOfWeek = DayOfWeek.values();
	public static void main(String[] args)  {
		try {
			RecordArguments recordArguments = getRecordArguments(args);
			printCalendar(recordArguments);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		

	}
	private static void setFirstDay(DayOfWeek dayOfWeek) {
		DayOfWeek[] sourceDays = DayOfWeek.values();
		if (dayOfWeek != daysOfWeek[0]) {
			{
				int dayNumber = dayOfWeek.getValue();
				for (int i = 0; i < daysOfWeek.length; i++) {
					int ind = dayNumber <= daysOfWeek.length ?
							dayNumber : dayNumber - daysOfWeek.length;
					daysOfWeek[i] = sourceDays[ind - 1];
					dayNumber++;
				}
			}
		}

	}

	private static void printCalendar(RecordArguments recordArguments) {
		setFirstDay(recordArguments.firstWeekDay());
		printTitle(recordArguments.month(), recordArguments.year());
		printWeekDays();
		printDays(recordArguments.month(), recordArguments.year());
		
	}

	private static void printDays(int month, int year) {
		int nDays = getNumberOfDays(month, year);
		int currentWeekDay = getFirstWeekDay(month, year);
		printOffset(currentWeekDay);
		for (int day = 1; day <= nDays; day++) {
			System.out.printf("%4d",day);
			currentWeekDay++;
			if(currentWeekDay == 7) {
				currentWeekDay = 0;
				System.out.println();
			}
			
		}
		
	}

	private static void printOffset(int currentWeekDay) {
		System.out.printf("%s", " ".repeat(4 * currentWeekDay));
		
	}

	private static int getFirstWeekDay(int month, int year) {
		LocalDate firstDateMonth = LocalDate.of(year, month, 1);
		int firstWeekDay = firstDateMonth.getDayOfWeek().getValue();
		int firstValue = daysOfWeek[0].getValue();
		int delta = firstWeekDay - firstValue;

		return delta >= 0 ? delta : delta + daysOfWeek.length;
	}

	private static int getNumberOfDays(int month, int year) {
		YearMonth ym = YearMonth.of(year, month);
		return ym.lengthOfMonth();
	}

	private static void printWeekDays() {
		System.out.print("  ");
		Arrays.stream(daysOfWeek).forEach(dw ->
		System.out.printf("%s ",
				dw.getDisplayName(TextStyle.SHORT, Locale.getDefault())));
		System.out.println();
		
	}

	private static void printTitle(int monthNumber, int year) {
		// <year>, <month name>
		Month month = Month.of(monthNumber);
		String monthName = month.getDisplayName(TextStyle.FULL_STANDALONE,
				Locale.getDefault());
		System.out.printf("%s%s, %d\n", " ".repeat(TITLE_OFFSET),monthName, year);
		
	}

	private static RecordArguments getRecordArguments(String[] args) throws Exception {
		LocalDate ld = LocalDate.now();
		int month = args.length == 0 ? ld.get(ChronoField.MONTH_OF_YEAR) :
			getMonth(args[0]);
		int year = args.length > 1 ? getYear(args[1]) :
			ld.get(ChronoField.YEAR);
		DayOfWeek firstDayOfWeek = args.length > 2 ? getFirstDayOfWeek(args[2]) :
			DayOfWeek.MONDAY;
		return new RecordArguments(month, year, firstDayOfWeek);
	}

	private static DayOfWeek getFirstDayOfWeek(String firstDayStr) throws Exception {
		try {
			DayOfWeek res = DayOfWeek.valueOf(firstDayStr.toUpperCase());
			return res;
		} catch (Exception e) {
			throw new Exception(firstDayStr.toUpperCase() + " wrong day of week");
		}
	}
	private static int getYear(String yearStr) throws Exception {
		String message = "";
		int year = 0;
		try {
			year = Integer.parseInt(yearStr);
			if(year < 0) {
				message = "year must be a positive number";
			}
			
		} catch (NumberFormatException e) {
			message = "year must be a number";
		}
		if(!message.isEmpty()) {
			throw new Exception(message);
		}
		return year;
	}

	private static int getMonth(String monthStr) throws Exception {
		String message = "";
		int month = 0;
		try {
			month = Integer.parseInt(monthStr);
			if(month < 1 || month > 12) {
				message = "month must be in the range [1-12]";
			}
			
		} catch (NumberFormatException e) {
			message = "month must be a number";
		}
		if(!message.isEmpty()) {
			throw new Exception(message);
		}
		return month;
	}

}