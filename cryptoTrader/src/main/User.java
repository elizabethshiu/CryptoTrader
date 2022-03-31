package main;
import java.io.*;

public class User {

    private String username;
    private String password;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void store() throws IOException {
        //TODO: store the user information inside user database

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
    
    public String getUsername(){
        return username;
    }   
    
    public String getPassword(){
        return password;
    }
 
      
}