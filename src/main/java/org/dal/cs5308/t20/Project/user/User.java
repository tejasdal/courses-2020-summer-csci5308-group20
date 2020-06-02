package org.dal.cs5308.t20.Project.user;

public class User {
    private Long id = null;
    private String bannerId = null;
    private String firstName = null;
    private String lastName = null;
    private String emailId = null;

    public User(Long id, String bannerId, String firstName, String lastName, String emailId) {
        this.id = id;
        this.bannerId = bannerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }

    public User(Long id, IUserService userService) throws Exception {
        User user = userService.getUserById(id);
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.bannerId = user.getBannerId();
        this.emailId = user.getEmailId();
    }

    public User(String emailId, IUserService userService) throws Exception {
        User user = userService.getUserByEmail(emailId);
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.bannerId = user.getBannerId();
        this.id = user.getId();
    }

    public User(String emailId) {
        this.emailId = emailId;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getBannerId() {
        return bannerId;
    }

    public User addUser(IUserService userService, String password) throws Exception {
        return userService.addUser(firstName, lastName, emailId, bannerId, password);
    }

    public String changePassword(IUserService userService, String oldPassword, String newPassword) throws Exception {
        return userService.changePassword(emailId, oldPassword, newPassword);
    }

    public String resetPassword(IUserService userService) throws Exception {
        return userService.resetPassword(emailId);
    }
}