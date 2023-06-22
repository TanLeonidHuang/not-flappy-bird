package Login;

import java.util.Properties;
import java.io.*;
import java.io.IOException;
import java.io.InputStream;

public class LoginReader {

    private Properties p;
    private InputStream i;
    private String user;
    private String password;
    private String admin;
    public LoginReader(){
        this.p = new Properties();
        this.user = null;
        this.password = null;
        this.admin = null;
    }

    public void storeInfo(UserAccount user){
        String admin = "0";
        if (user.getStatus()) {admin = "1";}
        p.setProperty("username", user.getUsername());
        p.setProperty("password", user.getPassword());
        p.setProperty("admin", admin);
        try {
            p.store(new FileWriter("loginInfo.properties"), "User Login Information");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readUser(FileReader reader) throws IOException {
        try {
            String fileName = "loginInfo.properties";
            i = getClass().getClassLoader().getResourceAsStream(fileName);

            if (i != null) {
                p.load(i);
            } else {throw new FileNotFoundException("property file '" + fileName + "'");}

            user = p.getProperty("username");
            password = p.getProperty("password");
            admin = p.getProperty("admin");

    } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }

    public String getFileUser() {
        return user;
    }
    public String getFilePassword() {
        return password;
    }
    public boolean getFileAdmin() {
        if (admin == "1") {
            return true;
        }
        return false;
    }
}




