/**
 * The User class is used for the login process of the system.
 * @author all
 *
 */


package main;
import java.io.*;

public class User {

    private String username;
    private String password;

    /**
     * User constructor
     * @param username
     * @param password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * This method stores the user's username and password inside the user database
     */
    public void store() throws IOException {
        String name = this.username;
        String pass = this.password;

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true));
            
            bw.write(name + "\n");
            bw.write(pass + "\n");
            System.out.println(name + " " + pass);
            
            bw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the username
     */
    public String getUsername(){
        return username;
    }  

    /**
     * Returns the password
     */
    public String getPassword(){
        return password;
    }
 
      
}