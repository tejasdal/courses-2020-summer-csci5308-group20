package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;

public class UserTestConcreteFactory implements UserTestAbstractFactory {
    @Override
    public IUserPersistence makeUserPersistence() {
        return new UserDBMock();
    }

    @Override
    public IUser makeUser() {
        return new User();
    }
}
