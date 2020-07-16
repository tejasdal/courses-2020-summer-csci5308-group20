package CSCI5308.GroupFormationTool.Security;

import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.AccessControl.UserPersistenceAbstractFactory;
import CSCI5308.GroupFormationTool.CustomExceptions.PasswordPolicyVoidException;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignupController {
    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private final String PASSWORD_CONFIRMATION = "passwordConfirmation";
    private final String FIRST_NAME = "firstName";
    private final String LAST_NAME = "lastName";
    private final String EMAIL = "email";

    @GetMapping("/signup")
    public String displaySignup(Model model) {
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView processSignup(
            @RequestParam(name = USERNAME) String bannerID,
            @RequestParam(name = PASSWORD) String password,
            @RequestParam(name = PASSWORD_CONFIRMATION) String passwordConfirm,
            @RequestParam(name = FIRST_NAME) String firstName,
            @RequestParam(name = LAST_NAME) String lastName,
            @RequestParam(name = EMAIL) String email) {
        boolean success = false;
        ModelAndView m;
        try {
            if (User.isBannerIDValid(bannerID) &&
                    User.isEmailValid(email) &&
                    User.isFirstNameValid(firstName) &&
                    User.isLastNameValid(lastName) &&
                    password.equals(passwordConfirm) &&
                    SystemConfig.instance().getPasswordPolicyService().validateUsingPolicies(password)) {
                User u = new User();
                u.setBannerID(bannerID);
                u.setPassword(password);
                u.setFirstName(firstName);
                u.setLastName(lastName);
                u.setEmail(email);
                IUserPersistence userDB = UserPersistenceAbstractFactory.instance().makeUserPersistence();
                IPasswordEncryption passwordEncryption = PasswordEncryptionAbstractFactory.instance().makePasswordEncryption();
                success = u.createUser(userDB, passwordEncryption, null);
            }
        } catch (PasswordPolicyVoidException e) {
            //add error messages in model
            m = new ModelAndView("signup");
            m.addObject("errorMessage", e.getMessage());
            return m;
        }

        if (success) {
            // This is lame, I will improve this with auto-signin for M2.
            m = new ModelAndView("login");
        } else {
            // Something wrong with the input data.
            m = new ModelAndView("signup");
            m.addObject("errorMessage", "Invalid data, please check your values.");
        }
        return m;
    }
}