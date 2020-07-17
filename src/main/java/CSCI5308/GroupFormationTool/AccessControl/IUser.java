package CSCI5308.GroupFormationTool.AccessControl;

import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;

public interface IUser {
    void setDefaults();

    void setID(long id);

    long getID();

    void setId(long id);

    long getId();

    void setPassword(String password);

    String getPassword();

    void setBannerID(String bannerID);

    String getBannerID();

    String getBanner();

    void setFirstName(String name);

    String getFirstName();

    void setLastName(String name);

    String getLastName();

    void setEmail(String email);

    String getEmail();

    boolean isValidUser();

    boolean createUser(
            IUserPersistence userDB,
            IPasswordEncryption passwordEncryption,
            IUserNotifications notification);

    boolean updateUser(IUserPersistence userDB);
}
