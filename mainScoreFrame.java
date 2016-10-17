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
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

/**
 * This class gives out the score of the security evaluation
 * of the system.
 * 
 * @version   $Id$ 1.0 mainScoreFrame.java
 *
 * @author   Aarti Gorade & Niyati Shah
 *
 * Revisions:
 *	$Log$
 */
public class mainScoreFrame extends JFrame {

	private JPanel contentPane;
	public JLabel lblNewLabel;
	public JProgressBar progressBar;
	public static JLabel lblNewLabel_1;
	public static int scoreGlobal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainScoreFrame frame = new mainScoreFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					System.out
							.println("Exception occurred in mainScoreFrame main frame");
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainScoreFrame() {
		
		// intialize the frame
		// and create basic functionalities
		
		this.setTitle("Security Evaluation Expert System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(600, 600);

		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension d = t.getScreenSize();

		int x = (d.width / 2) - (getWidth() / 2);
		int y = (d.height / 2) - (getHeight() / 2);
		setBounds(x, y, 600, 600);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.ORANGE);

		JLabel lblSecurityEvaluationReport = new JLabel(
				"Security Evaluation Report");
		lblSecurityEvaluationReport.setFont(new Font("Lucida Grande",
				Font.ITALIC, 20));
		lblSecurityEvaluationReport.setBounds(163, 17, 263, 50);
		contentPane.add(lblSecurityEvaluationReport);

		lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel.setBounds(135, 95, 379, 50);
		contentPane.add(lblNewLabel);

		JLabel lblPoorExcellent = new JLabel("Poor(0) -> Excellent(10)");
		lblPoorExcellent.setBounds(216, 60, 157, 38);
		contentPane.add(lblPoorExcellent);

		// Go back button action listener event 
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstEvaluationPage.entireEval = false;
				firstEvaluationPage.safetyEval = false;
				firstEvaluationPage.vulnerableEval = false;
				lblNewLabel_1.setIcon(null);
				contentPane.setVisible(false);
				setVisible(false);
				firstEvaluationPage goBackSafetyFrame = new firstEvaluationPage();
				goBackSafetyFrame.setVisible(true);

			}
		});
		btnGoBack.setBounds(414, 532, 80, 29);
		contentPane.add(btnGoBack);

		// Exit action listener event
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(511, 532, 68, 29);
		contentPane.add(btnExit);

		// Detailed feedback and Suggestions button action listener event 
		JButton btnDetailedFeedbackAnd = new JButton(
				"Detailed feedback and Suggestions");
		btnDetailedFeedbackAnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				setVisible(false);
				entireFbFrame fbFrame = new entireFbFrame();
				fbFrame.setVisible(true);

				// Detailed Description of the security evaluation 
				if (firstEvaluationPage.entireEval) {
					fbFrame.textArea
							.setText("Your system's current status is as follows =>\n\n1.You currently have "
									+ safetyEvaluationFrame.currentProductName
									+ " "
									+ safetyEvaluationFrame.currentOSversion
									+ "\n2.Is your Firewall on? "
									+ safetyEvaluationFrame.modeON
									+ "\n3.Is Stealth mode on? "
									+ safetyEvaluationFrame.stealthMode
									+ "\n4.Is authentication password very strong? "
									+ safetyEvaluationFrame.pwdStatus
									+ "\n5.Is recommended antivirus software installed? "
									+ safetyEvaluationFrame.antivirusPresent
									+ "\n6.is FileFault ON? "
									+ vulnerabilityEvaluationFrame.FileVaultOn
									+ "\n7.Are ports fully secured?  "
									+ vulnerabilityEvaluationFrame.portFullySecured
									+ "\n8.Are WIFI connections fully secured? "
									+ vulnerabilityEvaluationFrame.wifiFullySecured
									+ "\n9.Are installed application fully secured and trustworthy? "
									+ vulnerabilityEvaluationFrame.applicationsFullySecured
									+ "\n10.Are your system's all softwares updated? "
									+ safetyEvaluationFrame.softUpdate
									+ "\n\n=================================\n\nTo improve Security of your system =>\n\n1.Upgrade your operating system to latest version\n2.Turn on Firewall and Stealth mode\n3.Increase Password Strength\n4.Install Antivirus software\n5.Install pending software updates\n6.Active Gatekeeper under security & Privacy option"
									+ "\n7.Enable Data Encryption facility using FileFault\n8.Close unnecessary OPEN ports listening on TCP connections\n9.Do not connect to OPEN WIFI networks without any authentication\n10.Install applications from trustworthy and known source\n\n*For detailed feedback and suggestion, evaluate individual metric");

				} else if (firstEvaluationPage.safetyEval) {
					fbFrame.textArea
							.setText("Your system's current Safety status is as follows =>\n\n1.You currently have "
									+ safetyEvaluationFrame.currentProductName
									+ " "
									+ safetyEvaluationFrame.currentOSversion
									+ "\n2.Is your Firewall on? "
									+ safetyEvaluationFrame.modeON
									+ "\n3.Is Stealth mode on? "
									+ safetyEvaluationFrame.stealthMode
									+ "\n4.Is authentication password very strong? "
									+ safetyEvaluationFrame.pwdStatus
									+ "\n5.Is recommended antivirus software installed? "
									+ safetyEvaluationFrame.antivirusPresent
									+ "\n6.Are your system's all softwares updated? "
									+ safetyEvaluationFrame.softUpdate
									+ "\n7.Is your system's gatekeeper activated? "
									+ safetyEvaluationFrame.gatekeeperPresent
									+ "\n\n=================================\n\nTo improve safety of your system =>\n\n1.Upgrade your operating system to latest version\n2.Turn on Firewall and Stealth mode\n3.Increase Password Strength:\n  1.Password should be atleast 8 characters long\n  2.should not contain username\n  3.Atleast one each and almost equal quantity - Uppercase,Lowercase,digit,special character\n  4.No white space allowed\n  5.No repeating pattern of alphabets, numbers and keyboard\n4.Install Antivirus software\n5.Install pending software updates\n6.Active Gatekeeper under security & Privacy option\n\n*For detailed feedback and suggestion, evaluate individual metric");

				} else if (firstEvaluationPage.vulnerableEval) {
					fbFrame.textArea
							.setText("Your system's current Vulnerability status is as follows =>\n\n1.is FileFault ON? "
									+ vulnerabilityEvaluationFrame.FileVaultOn
									+ "\n2.Are ports fully secured?  "
									+ vulnerabilityEvaluationFrame.portFullySecured
									+ "\n3.Are WIFI connections fully secured? "
									+ vulnerabilityEvaluationFrame.wifiFullySecured
									+ "\n4.Are installed application fully secured and trustworthy? "
									+ vulnerabilityEvaluationFrame.applicationsFullySecured
									+ "\n\n=================================\n\nTo improve Vulnerability of your system =>\n\n1.Enable Data Encryption facility using FileFault\n2.Close unnecessary OPEN ports listening on TCP connections\n3.Do not connect to OPEN WIFI networks without any authentication\n4.Install applications from trustworthy and known source\n\n*For detailed feedback and suggestion, evaluate individual metric");

				}

			}
		});
		btnDetailedFeedbackAnd.setBounds(163, 463, 247, 29);
		contentPane.add(btnDetailedFeedbackAnd);

		lblNewLabel_1 = new JLabel("");

		System.out.println("scoreGlobal = " + scoreGlobal);

		if (scoreGlobal <= 5) {
			Image down = new ImageIcon(this.getClass().getResource(
					"/thumbpsDown.jpg")).getImage();
			lblNewLabel_1.setIcon(new ImageIcon(down));
		} else {
			Image up = new ImageIcon(this.getClass().getResource(
					"/thumbpsUp.jpg")).getImage();
			lblNewLabel_1.setIcon(new ImageIcon(up));
		}

		lblNewLabel_1.setBounds(163, 215, 236, 236);
		contentPane.add(lblNewLabel_1);

		// Progress bar characteristics
		progressBar = new JProgressBar();
		progressBar.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		progressBar.setMaximum(10);
		progressBar.setStringPainted(true);
		progressBar.setBounds(77, 147, 436, 20);
		contentPane.add(progressBar);
	}

}
