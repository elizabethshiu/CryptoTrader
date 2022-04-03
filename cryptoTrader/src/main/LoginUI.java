/**
 * This class serves as the login UI window, and also verifies user credentials using the users.txt file
 * It implements a singleton design pattern
 * @author all
 */

package main;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

import gui.MainUI;

/**
 * Login UI for user to log in
 */
public class LoginUI extends JFrame implements ActionListener {
    private static LoginUI instance = null;

    JPanel panel;
    JLabel user_label, password_label, message;
    JTextField userName_text;
    JPasswordField password_text;
    JButton submit, cancel;
    
    /**
     * constructor for LoginUI
     */
    private LoginUI() {     
        
        // Username Label
        user_label = new JLabel();
        user_label.setText("User Name :");
        userName_text = new JTextField();
        
        // Password Label
        password_label = new JLabel();
        password_label.setText("Password :");
        password_text = new JPasswordField();
        
        // Submit
        submit = new JButton("SUBMIT");
        panel = new JPanel(new GridLayout(3, 1));
        panel.add(user_label);
        panel.add(userName_text);
        panel.add(password_label);
        panel.add(password_text);
        message = new JLabel();
        panel.add(message);
        panel.add(submit);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Adding the listeners to components
        submit.addActionListener(this);
        add(panel, BorderLayout.CENTER);
        setTitle("Please Login Here !");
        setSize(450,350);
        setVisible(true);
        
    }

    /**
     * @param ae and action evente
     * on Login button click verify user
    */
    @Override
    public void actionPerformed(ActionEvent ae) {
        String userName = userName_text.getText();
        String password = password_text.getText();
        if(verifyUser(userName, password)) {
            message.setText(" Hello " + userName + "");
            //automatically close login window
            dispose();
            //open main UI
            JFrame frame = MainUI.getInstance();
		    frame.setSize(900, 600);
		    frame.pack();
		    frame.setVisible(true);
        } else {
            message.setText(" Invalid user!");
        }
    }

    /**
     * @param username the user username
     * @param pasword the user password
     * @return if the user input the correct login credentials or not
     * check users.txt if the given password matches the username
     */ 

    public boolean verifyUser(String username, String password) {

        BufferedReader objReader = null;
        try {
            String strCurrentLine;
            String filePath = new File("").getAbsolutePath();
            objReader = new BufferedReader(new FileReader(filePath + "/cryptoTrader/src/users.txt"));

            //parse users.txt file
            while ((strCurrentLine = objReader.readLine()) != null) {
                if(username.equals(strCurrentLine)){        //if given username matches one in the file
                    strCurrentLine = objReader.readLine();
                    if(password.equals(strCurrentLine)) {   //check if given password matches coresponding password in file
                        return true;
                    }
                }
                strCurrentLine = objReader.readLine();
            }
            return false;
        } catch (IOException e) {
            
            e.printStackTrace();
        } finally {
            try {
                if (objReader != null) {
                    objReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.exit(0);
        return false;
    }

    /**
     *retrieves the login UI instance
     */
    public static LoginUI getInstance() {
        if(instance == null)
            instance = new LoginUI();

        return instance;
    }
    
}    