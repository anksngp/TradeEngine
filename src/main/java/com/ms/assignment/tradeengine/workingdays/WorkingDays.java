package com.ms.assignment.tradeengine.workingdays;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class with working day map
 * 
 * @author Anks
 *
 */
public abstract class WorkingDays {

	/**
	 * Map that has details of the wrking days with status
	 * 
	 */
	protected Map<DayOfWeek, Boolean> workingDays = new HashMap<>();

	abstract void setupWorkingDays();

	/**
	 * Compute whether the date provided is a working day, if not compute the
	 * next day that is working
	 * 
	 * @param date
	 * @return
	 */
	public LocalDate findWorkingDate(LocalDate date) {
		final DayOfWeek inputDay = date.getDayOfWeek();

		if (workingDays.get(inputDay)) {
			return date;
		} else {
			return findWorkingDate(date.plusDays(1));
		}
	}
}
