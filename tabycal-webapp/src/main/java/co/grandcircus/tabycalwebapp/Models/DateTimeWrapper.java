package co.grandcircus.tabycalwebapp.Models;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateTimeWrapper {

	private LocalDate date;
	
	public DateTimeWrapper() {
		
	}

	public DateTimeWrapper(LocalDate date) {
		this.date = date;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	
	public DayOfWeek getDayOfWeek() {
		
		return date.getDayOfWeek();
	}
	
	public String getSpecificDayString() {
		String month = date.getMonth().toString();
		int day = date.getDayOfMonth();
		return month+ " " +day;
	}
}