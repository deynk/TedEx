import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }

    // Loads the users log data
    public static List<User> getLogins(File saveUsersDataFile) {
        List<User> users = new ArrayList<>();

        try{
            FileReader fr = new FileReader(saveUsersDataFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(":");
                users.add(new User(userData[0], userData[1]));
            }
        }catch(Exception e){
            System.out.println("Error in reading users file.");
            //System.out.println(saveUsersDataFile.exists());

            return null;
        }

        return users;
    }

    public static User findLogin(String username, String password, List<User> users) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) return user;
        }
        return null;
    }

}
