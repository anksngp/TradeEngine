package com.ms.assignment.tradeengine.workingdays;

import java.time.DayOfWeek;

/**
 * The Default Working days for all types are currency
 * 
 * @author Anks
 *
 */
public class GeneralWorkingDays extends WorkingDays {

	private static GeneralWorkingDays instance = null;

	public static GeneralWorkingDays getInstance() {
		if (instance == null) {
			instance = new GeneralWorkingDays();
		}
		return instance;
	}

	private GeneralWorkingDays() {
		super();
		setupWorkingDays();
	}

	/* (non-Javadoc)
	 * @see com.ms.assignment.tradeengine.workingdays.WorkingDays#setupWorkingDays()
	 */
	public void setupWorkingDays() {
		this.workingDays.put(DayOfWeek.MONDAY, true);
		this.workingDays.put(DayOfWeek.TUESDAY, true);
		this.workingDays.put(DayOfWeek.WEDNESDAY, true);
		this.workingDays.put(DayOfWeek.THURSDAY, true);
		this.workingDays.put(DayOfWeek.FRIDAY, true);
		this.workingDays.put(DayOfWeek.SATURDAY, false);
		this.workingDays.put(DayOfWeek.SUNDAY, false);
	}
}
