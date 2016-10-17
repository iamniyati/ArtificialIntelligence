import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

/**
 * This class checks the safety aspects of the system.
 * The safety aspects are Gatekeeper, Antivirus, 
 * Operating system version, firewall and stealth mode,
 * Software updates and password strength.
 * It also checks the overall safety using all the 
 * above aspects.
 * 
 *
 * @version   $Id$ 1.0 safetyEvaluationFrame.java
 *
 * @author   Niyati Shah & Aarti Gorade
 *
 * Revisions:
 *	$Log$
 */
public class safetyEvaluationFrame extends JFrame {

	JPanel contentPane;

	String pass, username;
	String keyboard = "qwertyuiopasdfghjklzxcvbnm ";
	String keyboardRev = "mnbvcxzlkjhgfdsapoiuytrewq ";
	String letters = "abcdefghijklmnopqrstuvwxyz ";
	String lettersRev = "zyxwvutsrqponmlkjihgfedcba ";
	String keyboardNumbers = "1234567890 ";
	String numbers = "0123456789 ";
	String passwardReview = " ";

	public String currentAntiVirus;

	public static boolean gatekeeperPresent;
	public static boolean antivirusPresent;
	public static String currentProductName;
	public static String currentOSversion;
	public static boolean modeON;
	public static boolean stealthMode;
	public static boolean pwdStatus;
	public static boolean softUpdate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					safetyEvaluationFrame frame = new safetyEvaluationFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					System.out
							.println("Exception occurred in safetyEvaluationFrame main frame");
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public safetyEvaluationFrame() {
		
		// intialize the frame
		// and create basic functionalities
		
		setFont(new Font("Dialog", Font.BOLD, 15));
		this.setTitle("Security Evaluation Expert System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 600);

		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension d = t.getScreenSize();

		int x = (d.width / 2) - (getWidth() / 2);
		int y = (d.height / 2) - (getHeight() / 2);
		setBounds(x, y, 600, 600);

		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.ORANGE);

		JLabel lblSafetyEvaluation = new JLabel("   Safety Evaluation");
		lblSafetyEvaluation.setFont(new Font("Lucida Grande", Font.ITALIC, 20));
		lblSafetyEvaluation.setBounds(200, 44, 200, 25);
		contentPane.add(lblSafetyEvaluation);

		// Action event for button - Operating system
		JButton btnOperatingSystem = new JButton("Operating System");
		btnOperatingSystem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstEvaluationPage.safetyEval = true;
				JOptionPane.showMessageDialog(contentPane,
						"Evaluation started. Please wait...");
				int scoreOS = evaluateOperatingSystem();

				JOptionPane.showMessageDialog(contentPane,
						"Evaluation finished!!!");

				contentPane.setVisible(false);
				setVisible(false);
				feedbackFrame fbFrame = new feedbackFrame();
				fbFrame.setVisible(true);

				// Display final Score
				fbFrame.lblNewLabel
						.setText("Your Operating System safety score is "
								+ scoreOS + " out of 10");
				fbFrame.progressBar.setValue(scoreOS);
				
				// Display suggestions and tips
				if (scoreOS < 10) {

					fbFrame.textArea
							.setText("You currently have "
									+ currentProductName
									+ " "
									+ currentOSversion
									+ "\n\nTo improve safety of your system, You should upgrade to latest Operating system\nMAC OS 10.11 EI CAPITAN available now");
				} else {
					fbFrame.textArea
							.setText("Excellent!!! \nYour system has latest Operating System!");
				}
			}
		});
		btnOperatingSystem.setBounds(35, 128, 177, 29);
		contentPane.add(btnOperatingSystem);

		// Action event for button - Firewall Status
		JButton btnFirewallStatus = new JButton("Firewall Status");
		btnFirewallStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstEvaluationPage.safetyEval = true;
				JOptionPane.showMessageDialog(contentPane,
						"Evaluation started. Please wait...");

				int scoreFirewall = evaluateFirewallStatus();

				JOptionPane.showMessageDialog(contentPane,
						"Evaluation finished!!!");

				contentPane.setVisible(false);
				setVisible(false);
				feedbackFrame fbFrame = new feedbackFrame();
				fbFrame.setVisible(true);

				fbFrame.lblNewLabel
						.setText("Your Firewall System safety score is "
								+ scoreFirewall + "  out of 10");
				fbFrame.progressBar.setValue(scoreFirewall);
				
				// Display suggestions and tips
				if (scoreFirewall == 0) {
					fbFrame.textArea
							.setText("Currently your system's firewall and Stealth mode both are turned off. \n\nTo improve safety of your system, You should switch ON Firewall and Steal mode.\nSteps to turn on Firewall:\n\nSystem Preferences -> Security & Privacy -> Firewall -> Turn on \nUnder Firewall option, turn on Stealth mode");
				} else if (scoreFirewall == 3) {
					fbFrame.textArea
							.setText("Your Stealth mode is ON but Firewall is turned off.\n\nTo improve safety of your system, You should switch ON Firewall mode.\nSteps to turn on Firewall mode:\n\nSystem Preferences -> Security & Privacy -> Firewall -> Turn on");
				} else if (scoreFirewall == 7) {
					fbFrame.textArea
							.setText("Your Firewall mode is ON but Stealth mode is turned off.\n\nTo improve safety of your system, You should switch ON Stealth mode.\nSteps to turn on Stealth mode:\n\nSystem Preferences -> Security & Privacy -> Firewall options -> Enable Stealth mode");
				} else {
					fbFrame.textArea
							.setText("Excellent!!! \nYour system's Firewall and Stealth mode both are turned ON!");
				}
			}
		});

		btnFirewallStatus.setBounds(392, 128, 177, 29);
		contentPane.add(btnFirewallStatus);
		
		// Action event for button - Authentication Strength
		JButton btnAuthenticationStrength = new JButton(
				"Authentication Strength");

		btnAuthenticationStrength.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstEvaluationPage.safetyEval = true;
				JOptionPane.showMessageDialog(contentPane,
						"Evaluation started. Please wait...");
				int scorePassword = 0;

				scorePassword = evaluatePassword();

				JOptionPane.showMessageDialog(contentPane,
						"Evaluation finished!!!");

				contentPane.setVisible(false);
				setVisible(false);
				feedbackFrame fbFrame = new feedbackFrame();
				fbFrame.setVisible(true);

				// Display final Score
				fbFrame.lblNewLabel
						.setText("Your Authentication Password Strength score is "
								+ scorePassword + " out of 10");
				fbFrame.progressBar.setValue(scorePassword);
				
				// Display suggestions and tips
				if (scorePassword < 10) {
					System.out.println(passwardReview);
					fbFrame.textArea
							.setText("To improve safety of your system, You should follow below guidelines for creating Stronger password: "+passwardReview);
				} else {
					fbFrame.textArea
							.setText("Excellent!!! \nYour system's Authentication Password is Very Strong!");
				}
			}
		});

		btnAuthenticationStrength.setBounds(35, 169, 177, 29);
		contentPane.add(btnAuthenticationStrength);

		JButton btnOverall = new JButton("Overall");
		btnOverall.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				firstEvaluationPage.safetyEval = true;
				JOptionPane.showMessageDialog(contentPane,
						"Evaluation started. Please wait...");

				int scoreOS = evaluateOperatingSystem();
				int scoreFirewall = evaluateFirewallStatus();
				int scorePassword = evaluatePassword();
				int scoreAntivirus = evaluateAntivirusProtection();
				int scoreSoftwareUpdate = evaluateSoftwareUpdate();
				int scoreGatekeeper = evalauteGatekeeper();
				int score = (scoreOS + scoreFirewall + scorePassword
						+ scoreAntivirus + scoreSoftwareUpdate + scoreGatekeeper) / 6;

				JOptionPane.showMessageDialog(contentPane,
						"Evaluation finished!!!");

				contentPane.setVisible(false);
				setVisible(false);

				// Display final Score
				if (score < 10) {
					mainScoreFrame.scoreGlobal = score;
					mainScoreFrame entireFb = new mainScoreFrame();
					entireFb.setVisible(true);
					entireFb.lblNewLabel
							.setText("Overall Safety evaluation score is "
									+ score + " out of 10");
					entireFb.progressBar.setValue(score);

					System.out.println("mainScoreFrame.scoreGlobal = "
							+ mainScoreFrame.scoreGlobal);
				} else {
					feedbackFrame fbFrame = new feedbackFrame();
					fbFrame.setVisible(true);
					fbFrame.textArea
							.setText("Excellent!!! \nYour system is extremely Safe!");
				}
			}
		});
		btnOverall.setBounds(210, 97, 179, 29);
		contentPane.add(btnOverall);

		// Go back button action listener event
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstEvaluationPage.entireEval = false;
				firstEvaluationPage.safetyEval = false;
				firstEvaluationPage.vulnerableEval = false;

				contentPane.setVisible(false);
				setVisible(false);
				firstEvaluationPage firstFrameGoBack = new firstEvaluationPage();
				firstFrameGoBack.setVisible(true);
			}
		});
		btnGoBack.setBackground(UIManager.getColor("text"));
		btnGoBack.setBounds(434, 543, 80, 29);
		contentPane.add(btnGoBack);

		// Exit action listener event
		JButton btnNewButton = new JButton("EXIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(526, 543, 68, 29);
		contentPane.add(btnNewButton);

		// Action event for button - Antivirus Protection
		JButton btnAntivirusProtection = new JButton("Antivirus Protection");
		btnAntivirusProtection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstEvaluationPage.safetyEval = true;
				JOptionPane.showMessageDialog(contentPane,
						"Evaluation started. Please wait...");

				int score = evaluateAntivirusProtection();

				JOptionPane.showMessageDialog(contentPane,
						"Evaluation finished!!!");

				contentPane.setVisible(false);
				setVisible(false);
				feedbackFrame fbFrame = new feedbackFrame();
				fbFrame.setVisible(true);

				// Display final Score
				fbFrame.lblNewLabel.setText("Antivirus Protection score is "
						+ score + " out of 10");
				fbFrame.progressBar.setValue(score);
				String antiV = (currentAntiVirus == null) ? "None"
						: currentAntiVirus;
				if (score < 10) {
					fbFrame.textArea
							.setText("Your system's current status - antivirus software = "
									+ antiV
									+ " is installed.\n\nTo improve safety, install Antivirus software.\nBelow are few suggestions of recommended antivirus softwares followed by ratings and cost =>\n  1.Avira  9/10 Free\n  2.BitDefender 7/10 PAID 39.95$/year\n  3.Avast 7/10  FREE\n  4.Kaspersky  6/10   19.99$/year\n  5.Sophos  4/10  FREE\n  6.Webroot  3/10 60$/year");
				} else {
					fbFrame.textArea
							.setText("Excellent!!! \nYour system has great antivirus protection!");
				}

			}
		});
		btnAntivirusProtection.setBounds(392, 169, 177, 29);
		contentPane.add(btnAntivirusProtection);

		JLabel lblNewLabel = new JLabel("");
		Image third = new ImageIcon(this.getClass().getResource("/safety.jpg"))
				.getImage();

		lblNewLabel.setIcon(new ImageIcon(third));

		lblNewLabel.setBounds(106, 252, 400, 267);
		contentPane.add(lblNewLabel);

		// Action event for button - Software Update
		JButton btnSoftwareButton = new JButton("Software Update");
		btnSoftwareButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				firstEvaluationPage.safetyEval = true;
				JOptionPane.showMessageDialog(contentPane,
						"Evaluation started. Please wait...");

				int score = evaluateSoftwareUpdate();

				JOptionPane.showMessageDialog(contentPane,
						"Evaluation finished!!!");

				contentPane.setVisible(false);
				setVisible(false);
				feedbackFrame fbFrame = new feedbackFrame();
				fbFrame.setVisible(true);

				// Display final Score
				if (score > -1) {
					fbFrame.lblNewLabel.setText("Software Update score is "
							+ score + " out of 10");
				} else {
					fbFrame.lblNewLabel
							.setText("Software Update score is 0 out of 10");
				}

				fbFrame.progressBar.setValue(score);
				
				// Display suggestions and tips
				if (score == -1) {
					fbFrame.textArea
							.setText("Can't connect to the Apple Software Update server, because you are not connected to the Internet. Please connect to internet successfully and try again");
				} else if (score < 10) {
					fbFrame.textArea
							.setText("For your system, there are software updates available to install.\n\nTo improve safety, install pending software updates from Apple Store\nUse command 'softwareUpdate --list' to check available software updates");
					softUpdate = true;
				} else {
					fbFrame.textArea
							.setText("Excellent!!! \nYour system has no pending software updates, Your System is Up-to-date!");
				}

			}
		});
		btnSoftwareButton.setBounds(35, 210, 177, 29);
		contentPane.add(btnSoftwareButton);

		// Action event for button - Gatekeeper
		JButton btnGatekeeper = new JButton("Gatekeeper");
		btnGatekeeper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				firstEvaluationPage.safetyEval = true;
				JOptionPane.showMessageDialog(contentPane,
						"Evaluation started. Please wait...");
				evaluateOperatingSystem();
				String s = currentOSversion;
				String[] sp = s.split("\\.+");
				int score = 0;
				feedbackFrame fbFrame = new feedbackFrame();
				if (Integer.parseInt(sp[1]) > 6) {
					score = evalauteGatekeeper();

					JOptionPane.showMessageDialog(contentPane,
							"Evaluation finished!!!");

					contentPane.setVisible(false);
					setVisible(false);

					fbFrame.setVisible(true);

					// Display final Score
					fbFrame.lblNewLabel.setText("Software Update score is "
							+ score + " out of 10");

					fbFrame.progressBar.setValue(score);

					// Display suggestions and tips
					if (score < 10) {
						fbFrame.textArea
								.setText("For your system, Gatekeeper is not activated.\n\nTo improve safety, activate Gatekeeper. Go to System preferences -> Security & Privacy -> General -> Allow apps downloaded from -> Mac App store and Identified developers ");
						gatekeeperPresent = true;
					} else {
						fbFrame.textArea
								.setText("Excellent!!! \nYour system has Gatekeeper activated already !");
					}

				} else {
					contentPane.setVisible(false);
					setVisible(false);

					fbFrame.setVisible(true);

					fbFrame.lblNewLabel
							.setText("Software Update score is 0 out of 10");

					fbFrame.progressBar.setValue(score);

					// Display suggestions and tips
					fbFrame.textArea
							.setText("For your system's operating system version, Gatekeeper application is not available");
				}
			}
		});
		btnGatekeeper.setBounds(392, 210, 177, 29);
		contentPane.add(btnGatekeeper);
	}

	/*
	 * Function to check if the users gatekeeper is enabled or not.
	 * 
	 * returns: int: Score 
	 */
	protected int evalauteGatekeeper() {
		String command = "spctl --status";
		int score = 0;

		try {
			// execute the command on terminal
			Process p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			String str;
			
			// check the output of the executed command and 
			// match with the different possible status of the
			// system
			while ((str = reader.readLine()) != null) {
				if (str.startsWith("assessments enabled")) {
					score = 10;
				}

				if (str.startsWith("assessments disabled")) {
					score = 0;
				}
			}

			reader.close();
		} catch (Exception e) {
			System.out
					.println("Exception occurred during Software Update evaluation");
		}
		return score;
	}

	/*
	 * Function to check if the users software are up to date or not.
	 * 
	 * returns: int: Score 
	 */
	protected int evaluateSoftwareUpdate() {
		String command = "softwareUpdate --list";
		int score = 0;
		int count = 0;
		try {
			// execute the command on terminal
			Process p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			BufferedReader error = new BufferedReader(new InputStreamReader(
					p.getErrorStream()));

			String str;
			// check the output of the executed command and 
			// match with the different possible status of the
			// system
			while ((str = reader.readLine()) != null) {
				System.out.println("str= " + str);
				if (str.contains("[recommended]")) {
					count += 1;
				}
			}

			// Check the number of applications which are
			// updated or not and edit the score
			while ((str = error.readLine()) != null) {
				if (str.startsWith("No new software available")) {
					score = 10;
					break;
				} else if (str.startsWith("Can't connect")) {
					score = -1;
					break;
				}

			}
			
			// Check the score from above method and
			// update it to be between 0-9
			if (score != 10 && score != -1) {
				if (count <= 2) {
					score = 9;
				} else if (count < 4) {
					score = 8;
				} else if (count < 6) {
					score = 7;
				} else if (count < 8) {
					score = 6;
				} else if (count < 10) {
					score = 5;
				} else if (count < 12) {
					score = 4;
				} else if (count < 14) {
					score = 3;
				} else if (count < 16) {
					score = 2;
				} else if (count < 18) {
					score = 1;
				} else if (count > 18) {
					score = 0;
				}
			}

			System.out.println("score= " + score);
			reader.close();
		} catch (Exception e) {
			System.out
					.println("Exception occurred during Software Update evaluation");
		}
		return score;
	}

	/*
	 * Function to check if the users has an antivirus software installed
	 * in their system or not.
	 * 
	 * returns: int: Score 
	 */
	protected int evaluateAntivirusProtection() {
		String command = "system_profiler SPApplicationsDataType";
		int score = 0;

		try {
			// execute the command on terminal
			Process proc = Runtime.getRuntime().exec(command);
			InputStream istr = proc.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(istr));
			String str;
			
			// check the output of the executed command and 
			// match with the different possible status of the
			// system 
			while ((str = br.readLine()) != null) {

				if (str.startsWith("    Avira:")) {
					score += 10;
					String s[] = str.split("\\s+");
					currentAntiVirus = s[0];
					antivirusPresent = true;
				} else if (str.startsWith("    BitDefender:")) {
					score += 8;
					String s[] = str.split("\\s+");
					currentAntiVirus = s[0];
				} else if (str.startsWith("    Avast:")) {
					score += 8;
					String s[] = str.split("\\s+");
					currentAntiVirus = s[0];
				} else if (str.startsWith("    Kaspersky:")) {
					score += 6;
					String s[] = str.split("\\s+");
					currentAntiVirus = s[0];
				} else if (str.startsWith("    Sophos:")) {
					score += 4;
					String s[] = str.split("\\s+");
					currentAntiVirus = s[0];
				} else if (str.startsWith("    Webroot:")) {
					score += 3;
					String s[] = str.split("\\s+");
					currentAntiVirus = s[0];
				}
			}
			int resultCode = proc.waitFor();
			br.close();

		} catch (Exception e) {
			System.out
					.println("Exception occurred during antivirus evaluation");
		}
		return score;
	}

	/*
	 * Function to check if the users firewall and stealth mode is 
	 * enabled or not
	 * 
	 * returns: int: Score 
	 */
	protected int evaluateFirewallStatus() {
		Process p;
		String command = "system_profiler SPFirewallDataType";
		modeON = false;
		stealthMode = false;

		try {
			// execute the command on terminal
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			String line = "";
			// check the output of the executed command and 
			// match with the different possible status of the
			// system
			while ((line = reader.readLine()) != null) {

				if (line.startsWith("      Mode:")) {
					String[] spl = line.split("\\s+");
					if (spl[2].equals("Limit")) {
						modeON = true;
					}
				}

				if (line.startsWith("      Stealth Mode:")) {
					String[] spl = line.split("\\s+");
					if (spl[3].equals("Yes")) {
						stealthMode = true;
					}
				}
			}
			// Check the different cases where the firewall and stealth mode 
			// are enabled or not and return a score.
			if (modeON && stealthMode) {
				return 10;
			} else if (modeON && !stealthMode) {
				return 7;
			} else if (!modeON && stealthMode) {
				return 3;
			} else if (!modeON && !stealthMode) {
				return 0;
			}
			reader.close();
		} catch (Exception e) {
			System.out
					.println("Exception occurred during Firewall status evaluation");
		}
		return 0;
	}

	/*
	 * Function to check if the users operating system is up to date or not.
	 * 
	 * returns: int: Score 
	 */
	protected int evaluateOperatingSystem() {

		Process p;
		String command = "sw_vers";
		try {
			// execute the command on terminal
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			String line = "";
			// check the output of the executed command and 
			// match with the different possible status of the
			// system
			while ((line = reader.readLine()) != null) {

				if (line.startsWith("ProductName")) {
					String[] spl = line.split("\\:+");
					currentProductName = spl[1].trim();
				}

				if (line.startsWith("ProductVersion:")) {
					String[] spl = line.split("\\s+");
					String[] spl2 = spl[1].split("\\.+");
					currentOSversion = spl[1];

					// check which version of the operating system
					// and return a score depending on the version
					switch (spl2[1]) {

					case "11":
						return 10;
					case "10":
						return 9;
					case "9":
						return 8;
					case "8":
						return 7;
					case "7":
						return 6;
					case "6":
						return 5;
					case "5":
						return 4;
					case "4":
						return 3;
					case "3":
						return 2;
					case "2":
						return 1;
					case "1":
						return 0;
					default:
						return 0;
					}

				}

			}

			reader.close();
		} catch (Exception e) {
			System.out
					.println("Some exception occurred during operating system evaluation");

		}
		return 0;

	}

	/**
	 * 
	 */
	protected int evaluatePassword() {
		char[] pwd = pr2_AartiGorade_NiyatiShah.pwd;
		String userName = pr2_AartiGorade_NiyatiShah.user;

		try {
			pass = new String(pwd);
		} catch (Exception e) {
			contentPane.setVisible(false);
			pr2_AartiGorade_NiyatiShah start = new pr2_AartiGorade_NiyatiShah();
			start.frame.setVisible(true);
		}

		int strength = 0;
		if (pass.length() >= 8) { // atleast 8 characters long

			// checks if password has username in it.
			if (checkIfMatchesUsername(pass, userName)) {

				strength = -10; // weak password or 1
			} else {
				strength += 2; // username not part of password
				// Checking 1st minimum criteria :
				// atleast 1 special character, digit, upper and lowercase.
				strength += checkSingleTerms(pass);

				// // Checking pattern criteria:
				// no sequential pattern from : keyborad, numerical and
				// alphabets allowed.

				strength += checkPatternTerms(pass);

				// checking repeated sequence of patterns

				strength += checkRepeatedSequence(pass);

			}
		} else {
			// Password has length of less than 8
			passwardReview += "\n Password should be atleast 8 characters long ";
			strength = -10; // weak password or 1
		}
		int score = 2 * calculateFinalStrength(strength);

		if (score == 10) {
			pwdStatus = true;
		}

		return score;
	}

	/*
	 * Function to calculate strengths of password by checking different single
	 * term standards present in password. Here strength is calculated between
	 * the range 1-10 where 1 being weak and 10 being a string password.
	 * 
	 * Single terms: Counts the number of upper case letters in the password
	 * Counts the number of lower case letters in the password Counts the number
	 * of special characters in the password Counts the number of digits in the
	 * password Counts the number of not allowed characters in the password like
	 * space
	 * 
	 * @param pass: users given password
	 * 
	 * returns: int: strength of password
	 */
	public int checkSingleTerms(String pass) {
		int countNumber, countUpperCase, countLowerCase, countSplChar, strength = 0;
		countUpperCase = checkNumberofUpperCase(pass);
		countLowerCase = checkNumberofLowerCase(pass);
		countNumber = checkNumberofDigits(pass);
		countSplChar = checkNumberofSplChars(pass);

		// calculate strength
		strength += calculateFirstStrength(countUpperCase, pass);
		strength += calculateFirstStrength(countLowerCase, pass);
		strength += calculateFirstStrength(countNumber, pass);
		strength += calculateFirstStrength(countSplChar, pass);

		return strength;
	}

	/*
	 * Function to count the number of upper case letters in the password. Here
	 * if all the characters are upper case letters then returns -1.
	 * 
	 * @param pass: users given password
	 * 
	 * returns: int: count of uppercase characters
	 */
	public int checkNumberofUpperCase(String pass) {
		int count = 0;
		for (char c : pass.toCharArray()) {
			if (Character.isUpperCase(c)) {
				count += 1;
			}
		}
		return count;
	}

	/*
	 * Function to count the number of lower case letters in the password. Here
	 * if all the characters are lower case letters then returns -1.
	 * 
	 * @param pass: users given password
	 * 
	 * returns: int: count of lower case characters
	 */
	public int checkNumberofLowerCase(String pass) {
		int count = 0;

		for (char c : pass.toCharArray()) {
			if (Character.isLowerCase(c)) {
				count += 1;
			}
		}
		return count;
	}

	/*
	 * Function to count the number of digits in the password. Here if all the
	 * characters are digits then returns -1.
	 * 
	 * @param pass: users given password
	 * 
	 * returns: int: count of digits characters
	 */
	public int checkNumberofDigits(String pass) {
		int count = 0;
		for (char c : pass.toCharArray()) {
			if (Character.isDigit(c)) {
				count += 1;
			}
		}
		return count;
	}

	/*
	 * Function to count the number of special character in the password. Here
	 * if all the characters are special character then returns -1.
	 * 
	 * @param pass: users given password
	 * 
	 * returns: int: count of special characters
	 */
	public int checkNumberofSplChars(String pass) {
		int count = 0;
		for (char c : pass.toCharArray()) {
			if (!Character.isLetterOrDigit(c) && !Character.isSpaceChar(c)) {
				count += 1;
			}
		}
		return count;
	}

	/*
	 * Function to count the number of character not allowed in the password
	 * like space and blanks Here if all the characters are special character
	 * then returns -1.
	 * 
	 * @param pass: users given password
	 * 
	 * returns: int: count of characters not allowed
	 */
	public int checkNumberofNotAllowedChars(String pass) {
		int count = 0;
		for (char c : pass.toCharArray()) {
			if (Character.isWhitespace(c)) {
				count += 1;
			}
		}
		return count;
	}

	/*
	 * Function to check if the username and password are same. or if password
	 * contains username in it.
	 * 
	 * Cases in which password cannot have username: 1. All uppercase or all
	 * lowercase or camelcase. 2. Can have any other characters except letters
	 * in between 3. Cannot have reverse of username.
	 * 
	 * @param pass: users given password
	 * 
	 * @param username: users given username
	 * 
	 * returns: boolean: true if any case is true
	 */
	public boolean checkIfMatchesUsername(String pass, String username) {
		String user = "", revuser;

		for (char c : pass.toCharArray()) { // Separate all characters of
											// username
			if (Character.isLetter(c)) {
				user += c + "";
			}
		}
		revuser = new StringBuffer(user).reverse().toString();
		if (user.toLowerCase().equals(username.toLowerCase())) { // check
			passwardReview += "\n Avoid using username in the password ";// strings
			System.out.println(passwardReview);
			return true;

		} else if (revuser.toLowerCase().equals(username.toLowerCase())) { // check
			passwardReview += "\n Avoid using username in the password "; // reverse
			System.out.println(passwardReview);							  // strings
			return true;
		}

		return false;
	}

	/*
	 * Function to calculate strengths of password by checking different pattern
	 * term standards present in password. Here strength is calculated between
	 * the range 1-10 where 1 being weak and 10 being a string password.
	 * 
	 * Pattern terms: Checks if password contains a same sequence of letters as
	 * in a qwerty keyboard and reverse of it. Checks if password contains a
	 * same sequence of letters as in the alphabet sequence and reverse of
	 * it.a-zA-Z/z-aZ-A Checks if password contains a same sequence of numbers
	 * as in the number sequence and reverse of it. 0-9/ 9-0 Checks if password
	 * contains a same sequence of numbers as in a qwerty keyboard and reverse
	 * of it. 1-0/0-1
	 * 
	 * @param pass: users given password
	 * 
	 * returns: int: strength of password
	 */
	public int checkPatternTerms(String pass) {
		String letters = "", digits = "";
		char prev = pass.charAt(0);
		int digitcount, strength = 0;
		;
		for (char c : pass.toCharArray()) { // seperate all characters of
											// username
			if (Character.isLetter(c)) {
				letters += c + "";
			} else if (Character.isDigit(c)) {
				digits += c + "";
			}
			prev = c;
		}
		// check strength
		strength += checkPatternKeyboard(letters);
		strength += checkPatternLetters(letters);

		// penalty check for digits only if there are more 2
		if (digits.length() > 2) {
			strength += checkpatterndigits(digits);
		}

		return strength;
	}

	/*
	 * Function to check if the letters of password are part of a QWERTY
	 * keyboard pattern and reverse of it
	 * 
	 * @param letterstring: letter string from the password
	 * 
	 * returns: int: strength
	 */
	public int checkPatternKeyboard(String letterstring) {
		if (keyboard.contains(letterstring.toLowerCase()) || (keyboardRev.contains(letterstring.toLowerCase())) ) {
			passwardReview += "\n Avoid using characters from the keyboard sequence ";
			return -3;
		} else {
			return 3;
		}
	}

	/*
	 * Function to check if the letters of password are part of the normal
	 * alphabet pattern and reverse of it
	 * 
	 * @param letterstring: letter string from the password
	 * 
	 * returns: int: strength
	 */
	public int checkPatternLetters(String letterstring) {
		System.out.println("4");
		if (letters.contains(letterstring.toLowerCase()) || (lettersRev.contains(letterstring.toLowerCase()))) {
			passwardReview += "\n Avoid using characters from the alphabet sequence ";
			return -3;
		} else {
			return 3;
		}
	}

	/*
	 * Function to check if the numbers of password are part of a qwerty
	 * keyboard pattern or normal number sequence and reverse of it
	 * 
	 * @param letterstring: letter string from the password
	 * 
	 * returns: int: strength
	 */
	public int checkpatterndigits(String digits) {
		String revDigits = new StringBuffer(digits).reverse().toString();
		if (numbers.contains(digits) || keyboardNumbers.contains(digits)) {
			passwardReview += "\n Avoid using characters from the number sequence ie (0-9) ";
			return -3;
		} else if (numbers.contains(revDigits)
				|| keyboardNumbers.contains(revDigits)) {
			passwardReview += "\n Avoid using characters from the keyboard number sequence (1-9,0) ";
			return -3;
		} else {
			return 3;
		}
	}

	/*
	 * Function to check if a certain letter, digit or special character is
	 * repeated in a password. No pattern can be repeated with
	 * 
	 * @param pass: user given password
	 * 
	 * returns: int: strength
	 */
	public int checkRepeatedSequence(String pass) {
		int count = 0;
		int length = 0;
		String noCasePass = pass.toLowerCase();
		int unallowed = checkNumberofNotAllowedChars(pass);
		if (unallowed == pass.length()) {
			return -20; // all space charachters only
		}
		Matcher repeats = Pattern.compile("(\\S+)(?=.*?\\1)").matcher(
				noCasePass);
		while (repeats.find()) {
			for (int i = 0; i < repeats.groupCount(); i++) {
				if (length < repeats.group(i).length()) {
					length = repeats.group(i).length();
				}
				count += 1;
			}
		}
		if (unallowed > 0) {
			passwardReview += "\n Avoid any kind of spaces characters in password";
			
			count += 1;
		}
		if (unallowed > length) {
			length = unallowed;
			passwardReview += "\n Avoid any repeating characters in password"; 
			
		}

		// if password only contains same word repeated twice
		if (length >= pass.length() / 3) {
			return -10; // penalty -10 very weak password
		} else {
			if (count >= 1) { // password contains atleast 1 repeated pattern
				return -5; // penalty -5
			} else {
				return 5; // no repeats reward +5
			}

		}
	}

	/*
	 * Function to calculate the strength based on the first term Here if
	 * atleast 1 of each type(uppercase,lowercase,special character,numbers) is
	 * present then increase strength by 1. ALso bonus is if there are
	 * approximately minimum of 1/3 and maximum of 3/4 of same type of chaacters
	 * then a bonus of 2 points. if any is absent then penalty of 1 point and if
	 * all are of same type then a penalty of 3.
	 * 
	 * @param count: count of each type
	 * 
	 * @param pass: user given password
	 * 
	 * returns: int: strength
	 */
	public int calculateFirstStrength(int count, String pass) {
		int strength = 0;
		int total = pass.length();
		double min = 1 / (double) 3; // need min of 1/3 of each allowed type
		double max = 3 / (double) 4; // need max of 3/4 of each allowed type
		if (count >= 1) { // minimum criteria met
			strength = 1;

			if (count == pass.length()) {
				strength = -3;
			} else if (count >= min * total - 2 && count <= max * total + 2) { // bonus
																				// criteria
				strength += 2;

			}
		} else { // min count is criteria not met
			passwardReview += "\n Add atleast one number, special character and uppercase and lowercase alphabet in password ";
			strength = -1;

		}
		return strength;
	}

	/*
	 * Function to calculate the final strength of the password after all the
	 * criteria's are checked. here we decide if password is weakest, weak,
	 * moderate strong or strongest. Maximum possible score that can be achieved
	 * is 23. and minimum can be negative. so we have managed the 5 groups and
	 * accordingly scored.
	 * 
	 * @param strength: strength after all criterias are checked
	 * 
	 * returns: int: finalscore
	 */
	public int calculateFinalStrength(int strength) {
		int finalScore;

		if (strength < 7) {
			finalScore = 1; // weakest
		} else if (strength >= 8 && strength <= 11) {
			finalScore = 2; // weak
		} else if (strength >= 12 && strength <= 15) {
			finalScore = 3; // moderate
		} else if (strength >= 16 && strength <= 19) {
			finalScore = 4; // strong
		} else {
			finalScore = 5; // strongest
		}

		return finalScore;
	}

}
