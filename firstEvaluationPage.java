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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * This class is the first evaluation page or the home page
 * from where we can check the entire security of the system
 * or just the safety or vulnerability of the system.
 * 
 * @version   $Id$ 1.0 firstEvaluationPage.java
 *
 * @author   Aarti Gorade & Niyati Shah
 *
 * Revisions:
 *	$Log$
 */

public class firstEvaluationPage extends JFrame {

	private JPanel contentPane;
	public static boolean entireEval;
	public static boolean safetyEval;
	public static boolean vulnerableEval;

	static firstEvaluationPage frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new firstEvaluationPage();
					frame.setVisible(true);
				} catch (Exception e) {
					System.out
							.println("Exception occurred in firstEvaluationPage main frame");
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public firstEvaluationPage() {
		
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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.ORANGE);

		JLabel lblLetsEvaluateYour = new JLabel("Let's Evaluate your System");
		lblLetsEvaluateYour.setFont(new Font("Lucida Grande", Font.ITALIC, 20));
		lblLetsEvaluateYour.setBounds(164, 413, 276, 25);
		contentPane.add(lblLetsEvaluateYour);

		// Action event for button - Entire System Evaluation
		JButton btnEntireSystemEvaluation = new JButton(
				"Entire System Evaluation");

		btnEntireSystemEvaluation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				entireEval = true;
				JOptionPane.showMessageDialog(contentPane,
						"Evaluation started. Please wait...");
				// Check the entire safety and vulnerability aspects of the system
				safetyEvaluationFrame s = new safetyEvaluationFrame();
				int scoreOS = s.evaluateOperatingSystem();
				int scoreFirewall = s.evaluateFirewallStatus();
				int scorePassword = s.evaluatePassword();
				int scoreAntivirus = s.evaluateAntivirusProtection();
				int scoreSoftwareUpdate = s.evaluateSoftwareUpdate();
				int scoreGatekeeper = s.evalauteGatekeeper();

				vulnerabilityEvaluationFrame v = new vulnerabilityEvaluationFrame();
				int scoreApps = v.evaluateInstalledApplications();
				int scorePort = v.evaluatePorts();
				int scoreWIFI = v.evaluateWIFI();
				int scoreEncryption = v.evaluateEncryption();
				
				// Calculate the final score of all the aspects together
				int score = (scoreOS + scoreFirewall + scorePassword
						+ scoreAntivirus + scoreSoftwareUpdate
						+ scoreGatekeeper + scoreApps + scorePort + scoreWIFI + scoreEncryption) / 10;

				JOptionPane.showMessageDialog(contentPane,
						"Evaluation finished!!!");

				contentPane.setVisible(false);
				setVisible(false);

				//Calculate the display the final score
				if (scorePassword < 10) {
					mainScoreFrame.scoreGlobal = score;
					mainScoreFrame entireFb = new mainScoreFrame();
					entireFb.setVisible(true);
					entireFb.lblNewLabel
							.setText("Entire Security evaluation score is "
									+ score + " out of 10");
					entireFb.progressBar.setValue(score);

				} else {
					feedbackFrame fbFrame = new feedbackFrame();
					fbFrame.setVisible(true);
					fbFrame.textArea
							.setText("Excellent!!! \nYour system is extremely Safe!");
				}

			}
		});
		btnEntireSystemEvaluation.setBounds(201, 461, 208, 29);
		contentPane.add(btnEntireSystemEvaluation);

		// Action event for button - Safety Evaluation
		JButton btnSafetyEvalution = new JButton("Safety Evalution");
		btnSafetyEvalution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				safetyEval = true;
				contentPane.setVisible(false);
				setVisible(false);
				safetyEvaluationFrame safetyFrame = new safetyEvaluationFrame();
				safetyFrame.setVisible(true);
			}
		});
		btnSafetyEvalution.setBounds(82, 502, 208, 29);
		contentPane.add(btnSafetyEvalution);

		// Action event for button - Vulnerability Evaluation
		JButton btnVulnerabilityEvalution = new JButton(
				"Vulnerability Evalution");
		btnVulnerabilityEvalution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vulnerableEval = true;
				contentPane.setVisible(false);
				setVisible(false);
				vulnerabilityEvaluationFrame vulnerableFrame = new vulnerabilityEvaluationFrame();
				vulnerableFrame.setVisible(true);
			}
		});
		btnVulnerabilityEvalution.setBounds(309, 502, 208, 29);
		contentPane.add(btnVulnerabilityEvalution);

		// Exit button action listener event
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(526, 543, 68, 29);
		contentPane.add(btnExit);

		JLabel lblNewLabel = new JLabel("");
		Image second = new ImageIcon(this.getClass().getResource(
				"/DoctorsBed.jpg")).getImage();

		lblNewLabel.setIcon(new ImageIcon(second));
		lblNewLabel.setBounds(58, 34, 500, 361);
		contentPane.add(lblNewLabel);

	}
}
