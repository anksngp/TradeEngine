package com.ms.assignment.tradeengine.workingdays;

import org.junit.Before;
import org.junit.Test;

import com.ms.assignment.tradeengine.workingdays.GeneralWorkingDays;
import com.ms.assignment.tradeengine.workingdays.WorkingDays;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class GeneralWorkingDaysTest {

	private WorkingDays workingDays;

	@Before
	public void setUp() throws Exception {
		workingDays = GeneralWorkingDays.getInstance();
	}

	@Test
	public void testFindFirstWorkingDate_Monday() throws Exception {
		final LocalDate monday = LocalDate.of(2017, 3, 20);
		assertEquals(monday, workingDays.findWorkingDate(monday));
	}

	@Test
	public void testFindFirstWorkingDate_Friday() throws Exception {
		final LocalDate friday = LocalDate.of(2017, 3, 24);
		assertEquals(friday, workingDays.findWorkingDate(friday));
	}

	@Test
	public void testFindFirstWorkingDate_Saturday() throws Exception {
		final LocalDate saturday = LocalDate.of(2017, 3, 25);
		assertEquals(LocalDate.of(2017, 3, 27), workingDays.findWorkingDate(saturday));
	}

	@Test
	public void testFindFirstWorkingDate_Sunday() throws Exception {
		final LocalDate sunday = LocalDate.of(2017, 3, 26);
		assertEquals(LocalDate.of(2017, 3, 27), workingDays.findWorkingDate(sunday));
	}
}