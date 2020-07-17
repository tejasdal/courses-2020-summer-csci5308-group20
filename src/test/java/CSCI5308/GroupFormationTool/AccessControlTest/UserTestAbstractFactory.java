package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;

public interface UserTestAbstractFactory {

    public IUserPersistence makeUserPersistence();
    public IUser makeUser();


}
