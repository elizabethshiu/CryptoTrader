package main;
import java.util.*;
import java.io.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
 
public class main{


    public static void main(String[] args){
        User temp = new User("user", "pass");
        User temp2 = new User("user2", "pass2");

        // ArrayList<String> sampleCoinList = new ArrayList<String>();
        // sampleCoinList.add("bitcoin");
        // sampleCoinList.add("ethereum");
        // sampleCoinList.add("tether");


        LoginUI login = LoginUI.getInstance();
        
        
        // Login login = new LoginUI();

        //start the UI 
        // MainUI userInterface = new MainUI();
        // new LoginUI();

        // LoginUI login = new LoginUI();
        // if(login.verifyUser("user2", "pass2") == true){
        //     System.out.println("verified");
        // } else {
        //     System.out.println("NO D:");
        // }
        

    }
}