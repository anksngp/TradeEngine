package com.ms.assignment.tradeengine.workingdays;

import org.junit.Before;
import org.junit.Test;

import com.ms.assignment.tradeengine.workingdays.GulfWorkingDays;
import com.ms.assignment.tradeengine.workingdays.WorkingDays;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class GulfWorkingDaysTest {
	private WorkingDays workingDays;

	@Before
	public void setUp() throws Exception {
		workingDays = GulfWorkingDays.getInstance();
	}

	@Test
	public void testFindFirstWorkingDate_Sunday() throws Exception {
		final LocalDate sunday = LocalDate.of(2017, 3, 26);

		assertEquals(sunday, workingDays.findWorkingDate(sunday));
	}

	@Test
	public void testFindFirstWorkingDate_Thursday() throws Exception {
		final LocalDate thursday = LocalDate.of(2017, 3, 23);

		assertEquals(thursday, workingDays.findWorkingDate(thursday));
	}

	@Test
	public void testFindFirstWorkingDate_Friday() throws Exception {
		final LocalDate friday = LocalDate.of(2017, 3, 24);

		assertEquals(LocalDate.of(2017, 3, 26), workingDays.findWorkingDate(friday));
	}

	@Test
	public void testFindFirstWorkingDate_Saturday() throws Exception {
		final LocalDate saturday = LocalDate.of(2017, 3, 25);

		assertEquals(LocalDate.of(2017, 3, 26), workingDays.findWorkingDate(saturday));
	}

}