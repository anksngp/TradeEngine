package com.ms.assignment.tradeengine.workingdays;

import java.time.DayOfWeek;

/**
 * Gulf working days
 * 
 * @author Anks
 *
 */
public class GulfWorkingDays extends WorkingDays {

	private static GulfWorkingDays instance = null;

	public static GulfWorkingDays getInstance() {
		if (instance == null) {
			instance = new GulfWorkingDays();
		}
		return instance;
	}

	private GulfWorkingDays() {
		super();
		setupWorkingDays();
	}

	/*
	 * These have Sunday to Thursday as working days
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ms.assignment.tradeengine.workingdays.WorkingDays#setupWorkingDays()
	 */
	@Override
	public void setupWorkingDays() {
		this.workingDays.put(DayOfWeek.SUNDAY, true);
		this.workingDays.put(DayOfWeek.MONDAY, true);
		this.workingDays.put(DayOfWeek.TUESDAY, true);
		this.workingDays.put(DayOfWeek.WEDNESDAY, true);
		this.workingDays.put(DayOfWeek.THURSDAY, true);
		this.workingDays.put(DayOfWeek.FRIDAY, false);
		this.workingDays.put(DayOfWeek.SATURDAY, false);
	}

}
