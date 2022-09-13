package co.grandcircus.tabycalwebapp.Util;

import java.time.LocalDate;
import java.util.List;

import co.grandcircus.tabycalwebapp.Models.Holiday;

public class HolidayHelper {
	
	private List<Holiday> holidays;

	public List<Holiday> getHolidays() {
		return holidays;
	}

	public void setHolidays(List<Holiday> holidays) {
		this.holidays = holidays;
	}

	public HolidayHelper(List<Holiday> holidays) {
		super();
		this.holidays = holidays;
	}

	public HolidayHelper() {
		super();
	}
	
	public boolean isThisAHoliday (LocalDate date) {
		for (Holiday holiday : holidays) {
			if (holiday.getDateAsLocalDate().equals(date)){
				return true;
			}
			}
			return false;
	}
	
	public Holiday showActualHoliday (LocalDate date) {
		for (Holiday holiday : holidays) {
			if (holiday.getDateAsLocalDate().equals(date)){
				return holiday;
			}
		}
		return null;
	}
	

}
