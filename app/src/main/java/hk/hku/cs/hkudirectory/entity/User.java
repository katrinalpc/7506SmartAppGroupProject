package hk.hku.cs.hkudirectory.entity;

public class User {
    private int id;
    private String userEmail;
    private String userPassword;
    private String userName;

    private int userType;

    public User() {

    }

    public User(int id, String userEmail, String userPassword, String userName, int userType) {
        this.id = id;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
