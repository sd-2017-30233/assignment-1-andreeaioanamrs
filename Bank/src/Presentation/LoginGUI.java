package Presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Business.Logare;

public class LoginGUI {
	private JFrame fr;
	private JLabel user,pass;
	private JButton logare;
	private JTextField username;
	private JPasswordField password;
	LoginGUI(){
		fr = new JFrame("Welcome!Please Log In!");
		fr.setBounds(450, 300, 250, 185);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.getContentPane().setLayout(null);
		
		user = new JLabel("Username");
		user.setBounds(90, 10, 100, 20);
		username = new JTextField();
		username.setBounds(60, 40, 130, 20);	
		pass = new JLabel("Password");
		pass.setBounds(90, 70, 100, 20);
		password = new JPasswordField();
		password.setBounds(60, 100, 130, 20);
		logare = new JButton("Log IN!");
		logare.setBounds(75, 130, 100, 20);
		
		fr.getContentPane().add(user);
		fr.getContentPane().add(username);
		fr.getContentPane().add(pass);
		fr.getContentPane().add(password);
		fr.getContentPane().add(logare);
		
		logare.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (e.getSource() == logare){
					if (Logare.logAdmin(username.getText(),password.getText())){ 
						new AdminGUI();
						fr.setVisible(false);}
					else if(Logare.logEmployee(username.getText(),password.getText())) {
						new EmployeeGUI(username.getText());
						fr.setVisible(false);}
					else JOptionPane.showMessageDialog(null, "Incorrect Username or Password!");
				}
			}
		});
		fr.setVisible(true);
	}
	

	public static void main(String[] args) {
		new LoginGUI();
	}

}
