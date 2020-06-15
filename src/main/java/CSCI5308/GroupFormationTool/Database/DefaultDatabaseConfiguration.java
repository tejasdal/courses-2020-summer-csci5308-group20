package CSCI5308.GroupFormationTool.Database;

public class DefaultDatabaseConfiguration implements IDatabaseConfiguration {
    private static final String URL = ("jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_20_DEVINT?createDatabaseIfNotExist=true&serverTimezone=UTC");
    private static final String USER = ("CSCI5308_20_DEVINT_USER");
    private static final String PASSWORD = ("CSCI5308_20_DEVINT_20125");

    public String getDatabaseUserName() {
        return USER;
    }

    public String getDatabasePassword() {
        return PASSWORD;
    }

    public String getDatabaseURL() {
        return URL;
    }
}
