package CSCI5308.GroupFormationTool.SurveyManagement.matchcriteria.standarddeviation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.SurveyManagement.matchcriteria.standarddeviation.StandardDeviation;

public class StandardDeviationTest {
	
	@Test
	public void getDeviationTest() {
		final int[] values = {1, 2, 3, 4, 5};
		final double standardDeviation = 10d;
		assertEquals(standardDeviation, StandardDeviation.getDeviation(values));
	}
	
	@Test
	public void getMaxDeviationTest() {
		final int[] values = {1, 2, 3, 4, 5};
		final double maxSd = 19.200000000000003d;
		assertEquals(maxSd, StandardDeviation.getMaxDeviation(values));
	}
}
