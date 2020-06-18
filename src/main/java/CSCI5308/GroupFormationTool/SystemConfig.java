package CSCI5308.GroupFormationTool;

import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.UserDB;
import CSCI5308.GroupFormationTool.AdminConfig.AdminConfigPersistence;
import CSCI5308.GroupFormationTool.AdminConfig.AdminConfigService;
import CSCI5308.GroupFormationTool.AdminConfig.IAdminConfigPersistence;
import CSCI5308.GroupFormationTool.AdminConfig.IAdminConfigService;
import CSCI5308.GroupFormationTool.Courses.CourseDB;
import CSCI5308.GroupFormationTool.Courses.CourseUserRelationshipDB;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.Database.DefaultDatabaseConfiguration;
import CSCI5308.GroupFormationTool.Database.IDatabaseConfiguration;
import CSCI5308.GroupFormationTool.Security.BCryptPasswordEncryption;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.Security.PasswordPolicyEnforcer.*;
import CSCI5308.GroupFormationTool.Question.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Question.IQuestionService;
import CSCI5308.GroupFormationTool.Question.QuestionPersistence;
import CSCI5308.GroupFormationTool.Question.QuestionService;
import CSCI5308.GroupFormationTool.Security.*;
import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.Database.*;
import CSCI5308.GroupFormationTool.Courses.*;

/*
 * This is a singleton, we will learn about these when we learn design patterns.
 *
 * The single responsibility of this singleton is to store concrete classes
 * selected by the system for use in the rest of the system. This will allow
 * a form of dependency injection in places where we cannot use normal
 * dependency injection (for example classes that override or extend existing
 * library classes in the framework).
 */
public class SystemConfig {
    private static SystemConfig uniqueInstance = null;

    private IPasswordEncryption passwordEncryption;
    private IUserPersistence userDB;
    private IDatabaseConfiguration databaseConfiguration;
    private ICoursePersistence courseDB;
    private ICourseUserRelationshipPersistence courseUserRelationshipDB;
    private IAdminConfigService adminConfigService;
    private IAdminConfigPersistence adminConfigPersistence;
    private IPasswordPolicyService passwordPolicyService;
    private IPasswordPersistence passwordPersistence;
    private IPasswordPolicy maxLengthPolicy;
    private IPasswordPolicy minLengthPolicy;
    private IPasswordPolicy minLowerCasePolicy;
    private IPasswordPolicy minSymbolPolicy;
    private IPasswordPolicy minUpperCasePolicy;
    private IPasswordPolicy restrictedSymbolCasePolicy;
    private IPasswordPolicy rememberedPasswordPolicy;
    private IQuestionPersistence questionPersistence;
    private IQuestionService questionService;


    // This private constructor ensures that no class other than System can allocate
    // the System object. The compiler would prevent it.
    private SystemConfig() {
        // The default instantiations are the choices that would be used in the
        // production application. These choices can all be overridden by test
        // setup logic when necessary.
        passwordEncryption = new BCryptPasswordEncryption();
        userDB = new UserDB();
        databaseConfiguration = new DefaultDatabaseConfiguration();
        courseDB = new CourseDB();
        courseUserRelationshipDB = new CourseUserRelationshipDB();
        adminConfigService = new AdminConfigService();
        adminConfigPersistence = new AdminConfigPersistence();
        passwordPolicyService = new PasswordPolicyService();
        passwordPersistence = new PasswordPolicyPersistence();
        questionPersistence = new QuestionPersistence();
        questionService = new QuestionService();
    }

    // This is the way the rest of the application gets access to the System object.
    public static SystemConfig instance() {
        // Using lazy initialization, this is the one and only place that the System
        // object will be instantiated.
        if (null == uniqueInstance) {
            uniqueInstance = new SystemConfig();
        }
        return uniqueInstance;
    }

    public IPasswordEncryption getPasswordEncryption() {
        return passwordEncryption;
    }

    public void setPasswordEncryption(IPasswordEncryption passwordEncryption) {
        this.passwordEncryption = passwordEncryption;
    }

    public IUserPersistence getUserDB() {
        return userDB;
    }

    public void setUserDB(IUserPersistence userDB) {
        this.userDB = userDB;
    }

    public IDatabaseConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }

    public void setDatabaseConfiguration(IDatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
    }

    public void setCourseDB(ICoursePersistence courseDB) {
        this.courseDB = courseDB;
    }

    public ICoursePersistence getCourseDB() {
        return courseDB;
    }

    public void setCourseUserRelationshipDB(ICourseUserRelationshipPersistence courseUserRelationshipDB) {
        this.courseUserRelationshipDB = courseUserRelationshipDB;
    }

    public ICourseUserRelationshipPersistence getCourseUserRelationshipDB() {
        return courseUserRelationshipDB;
    }

    public IAdminConfigService getAdminConfigService() {
        return adminConfigService;
    }

    public IAdminConfigPersistence getAdminConfigPersistence() {
        return adminConfigPersistence;
    }

    public IPasswordPolicyService getPasswordPolicyService() {
        return passwordPolicyService;
    }

    public IPasswordPersistence getPasswordPersistence() {
        return passwordPersistence;
    }

    public IPasswordPolicy getPolicy(String policy, String policyValue) {
        switch (policy) {
            case MinLengthPolicy.POLICY_NAME:
                return new MinLengthPolicy(policyValue);
            case MaxLengthPolicy.POLICY_NAME:
                return new MaxLengthPolicy(policyValue);
            case MinLowerCasePolicy.POLICY_NAME:
                return new MinLowerCasePolicy(policyValue);
            case MinSymbolPolicy.POLICY_NAME:
                return new MinSymbolPolicy(policyValue);
            case MinUpperCasePolicy.POLICY_NAME:
                return new MinUpperCasePolicy(policyValue);
            case RestrictedSymbolCasePolicy.POLICY_NAME:
                return new RestrictedSymbolCasePolicy(policyValue);
            case RememberedPasswordPolicy.POLICY_NAME:
                return new RememberedPasswordPolicy(policyValue);
            default:
                return null;
        }
    }

    public IQuestionPersistence getQuestionPersistence() {
        return questionPersistence;
    }

    public IQuestionService getQuestionService() {
        return questionService;
    }
}
