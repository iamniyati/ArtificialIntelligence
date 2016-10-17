import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * This class is the front page of the system.
 * user needs to enter their username and password
 * to enter into the application to check how 
 * secure their system is.
 * 
 *
 * @version   $Id$ 1.0 securityEvaluationHierarchicalExpertSystem.java
 *
 * @author   Niyati Shah & Aarti Gorade
 *
 * Revisions:
 *	$Log$
 */
public class pr2_AartiGorade_NiyatiShah {

	public JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField txtEnterYourUsername;
	private JPasswordField passwordField_1;
	public static char[] pwd;
	public static String user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pr2_AartiGorade_NiyatiShah window = new pr2_AartiGorade_NiyatiShah();
					window.frame.setVisible(true);
				} catch (Exception e) {
					System.out
							.println("Exception occurred in securityEvaluationHierarchicalExpertSystem main frame");
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public pr2_AartiGorade_NiyatiShah() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame("Security Evaluation Expert System");
		frame.setFont(new Font("Dialog", Font.BOLD, 15));
		frame.setForeground(Color.LIGHT_GRAY);
		frame.setBackground(UIManager.getColor("Button.background"));
		frame.getContentPane().setBackground(Color.ORANGE);

		frame.setSize(600, 600);

		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension d = t.getScreenSize();

		int x = (d.width / 2) - (frame.getWidth() / 2);
		int y = (d.height / 2) - (frame.getHeight() / 2);

		frame.setBounds(x, y, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblUsername.setBounds(303, 178, 83, 16);
		frame.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblPassword.setBounds(318, 224, 68, 16);
		frame.getContentPane().add(lblPassword);

		JLabel Title = new JLabel(
				"Welcome to Security Evaluation Expert System");
		Title.setFont(new Font("Lucida Grande", Font.ITALIC, 20));
		Title.setBounds(81, 52, 447, 25);
		frame.getContentPane().add(Title);

		// Action event for the submit button
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				pwd = passwordField_1.getPassword();
				user = txtEnterYourUsername.getText();

				if (!(pwd.length == 0) && !(user.length() == 0)) {

					frame.dispose();
					firstEvaluationPage frame2 = new firstEvaluationPage();
					frame2.setVisible(true);

				} else {
					JOptionPane.showMessageDialog(frame,
							"Enter valid username and password");
				}
			}
		});
		btnSubmit.setBounds(398, 283, 130, 29);
		frame.getContentPane().add(btnSubmit);

		txtEnterYourUsername = new JTextField();
		txtEnterYourUsername.setBounds(398, 174, 130, 26);
		frame.getContentPane().add(txtEnterYourUsername);
		txtEnterYourUsername.setColumns(10);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(398, 220, 130, 26);
		frame.getContentPane().add(passwordField_1);

		JLabel lblAartiGorade = new JLabel("Aarti Gorade");
		lblAartiGorade.setBounds(493, 486, 83, 40);
		frame.getContentPane().add(lblAartiGorade);

		JLabel lblNiyatiShah = new JLabel("Niyati Shah");
		lblNiyatiShah.setBounds(493, 517, 83, 40);
		frame.getContentPane().add(lblNiyatiShah);

		JLabel lblEnterUsernameAnd = new JLabel(
				"*Enter Username and Password used for this Computer system");
		lblEnterUsernameAnd.setBounds(126, 99, 398, 40);
		frame.getContentPane().add(lblEnterUsernameAnd);

		// Exit action listener event
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(398, 336, 130, 29);
		frame.getContentPane().add(btnExit);

		JLabel lblNewLabel = new JLabel("");
		Image first = new ImageIcon(this.getClass()
				.getResource("/isItSafe.jpg")).getImage();

		lblNewLabel.setIcon(new ImageIcon(first));
		lblNewLabel.setBounds(17, 162, 225, 282);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblforDetailsRefer = new JLabel("*Please refer report");
		lblforDetailsRefer.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblforDetailsRefer.setBounds(17, 547, 100, 25);
		frame.getContentPane().add(lblforDetailsRefer);

	}
}
