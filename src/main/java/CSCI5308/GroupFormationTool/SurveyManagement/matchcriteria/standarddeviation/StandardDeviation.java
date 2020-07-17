package CSCI5308.GroupFormationTool.SurveyManagement.matchcriteria.standarddeviation;

class StandardDeviation {
	private StandardDeviation() {	
	}
	
	static double getMaxDeviation(int[] values) {
		int count = values.length;
		int[] tvalues = new int[values.length];
		Integer min = null, max = null;
		for (int value : values) {
			if (min == null || min > value) {
				min = value;
			}
			if (max == null || max < value) {
				max = value;
			}
		}
		for (int i = 0; i < count; i++) {
			if (i < count / 2) {
				tvalues[i] = min;
			} else {
				tvalues[i] = max;
			}
		}
		return getDeviation(tvalues);
	}

	static double getDeviation(int[] values) {
		int sum = 0;
		for (int value : values) {
			sum += value;
		}
		double mean = sum / (double) values.length;
		double sqDiff = 0;
		for (int value : values) {
			sqDiff += Math.pow((mean - value), 2);
		}
		return sqDiff;
	}
}
