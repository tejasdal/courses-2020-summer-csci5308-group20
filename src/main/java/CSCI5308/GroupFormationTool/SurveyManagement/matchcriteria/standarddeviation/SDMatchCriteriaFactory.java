package CSCI5308.GroupFormationTool.SurveyManagement.matchcriteria.standarddeviation;

import CSCI5308.GroupFormationTool.SurveyManagement.matchcriteria.IMatchCriteria;
import CSCI5308.GroupFormationTool.SurveyManagement.matchcriteria.IMatchCriteriaFactory;

public class SDMatchCriteriaFactory implements IMatchCriteriaFactory {
	private static final IMatchCriteria SIMILARITY_CRITERIA = new Similar();
	private static final IMatchCriteria DISSIMILARITY_CRITERIA = new Dissimilar();
	
	@Override
	public IMatchCriteria getSimilarityCriteria() {
		return SIMILARITY_CRITERIA;
	}

	@Override
	public IMatchCriteria getDissimilarityCriteria() {
		return DISSIMILARITY_CRITERIA;
	}

	@Override
	public IMatchCriteria getXGreaterThanYCriteria(int x, int y) {
		return new XGreaterThanY(x, y);
	}

	@Override
	public IMatchCriteria getXLesserThanYCriteria(int x, int y) {
		return new XLesserThanY(x, y);
	}

}
