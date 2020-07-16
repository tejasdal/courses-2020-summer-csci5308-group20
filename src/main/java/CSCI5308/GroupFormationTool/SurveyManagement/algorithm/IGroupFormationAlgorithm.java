package CSCI5308.GroupFormationTool.SurveyManagement.algorithm;

import java.io.IOException;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;

public interface IGroupFormationAlgorithm {

	public List<List<User>> createGroups() throws IOException;
}
