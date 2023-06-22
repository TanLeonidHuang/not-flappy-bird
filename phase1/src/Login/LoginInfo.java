package Login;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class LoginInfo {

    private String username;
    private String password;
    private boolean admin;
    private String option1;
    private ValidateCredentials cred;



    public LoginInfo() {
        this.username = null;
        this.password = null;
        this.admin = false;
    }

    public ArrayList<String> identitySplitter(String id) {
        //splits a string using "."
        ArrayList<String> stringList = new ArrayList<>();
        String[] arrOfStr = id.split("[.]", 0);
        Collections.addAll(stringList, arrOfStr);
        return stringList;
    }

    public void menuInputs(ArrayList<String> infoList) {
        //adds elements from index 1 and 2 into userList, and then returns them. These elements are the username,
        //password and admin choice. Boolean creation is true if the user is trying to create an account
        option1 = infoList.get(0);
        username = infoList.get(1);
        password = infoList.get(2);
        if (Objects.equals(option1, "NEW")) {
            if (Objects.equals(infoList.get(3), "ADMIN")) {
                admin = true;
            }
        }
    }

    public boolean getOption1() {
        return !Objects.equals(option1, "NEW");
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getAdmin() {
        return admin;
    }


}
