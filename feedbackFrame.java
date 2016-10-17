import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 * This class gives the feedback on the evaluation of the 
 * system. Whether it is poor or excellent. It also gives 
 * out suggestions and directions to improve a systems
 * security.
 * 
 * @version   $Id$ 1.0 feedbackFrame.java
 *
 * @author   Niyati Shah 
 *
 * Revisions:
 *	$Log$
 */

public class feedbackFrame extends JFrame {

	private JPanel contentPane;
	public JLabel lblNewLabel;
	public JTextArea textArea;
	public JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					feedbackFrame frame = new feedbackFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					System.out
							.println("Exception occurred in feedbackFrame main frame");
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public feedbackFrame() {
		
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
		lblSecurityEvaluationReport.setBounds(171, 6, 263, 39);
		contentPane.add(lblSecurityEvaluationReport);

		lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel.setBounds(41, 83, 416, 50);
		contentPane.add(lblNewLabel);

		JLabel lblStayInformedstaySafe = new JLabel("Stay Informed, Stay Safe");
		lblStayInformedstaySafe.setFont(new Font("Lucida Grande", Font.ITALIC,
				15));
		lblStayInformedstaySafe.setBounds(213, 154, 182, 39);
		contentPane.add(lblStayInformedstaySafe);

		textArea = new JTextArea();
		textArea.setBounds(41, 192, 518, 237);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setAutoscrolls(true);

		contentPane.add(textArea);

		// Go back button action listener event 
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				contentPane.setVisible(false);
				setVisible(false);
				
				// select which page has called the go back button
				// and which page to navigate to.
				
				// if page is entire evaluation
				if (firstEvaluationPage.entireEval) {
					firstEvaluationPage.entireEval = false;
					firstEvaluationPage.safetyEval = false;
					firstEvaluationPage.vulnerableEval = false;

					firstEvaluationPage goBackFirstFrame = new firstEvaluationPage();
					goBackFirstFrame.setVisible(true);
				
				// if page is safety evaluation
				} else if (firstEvaluationPage.safetyEval) {
					firstEvaluationPage.entireEval = false;
					firstEvaluationPage.safetyEval = false;
					firstEvaluationPage.vulnerableEval = false;

					safetyEvaluationFrame goBackSafetyFrame = new safetyEvaluationFrame();
					goBackSafetyFrame.setVisible(true);
				
				// if page is vulnerability evaluation
				} else if (firstEvaluationPage.vulnerableEval) {
					firstEvaluationPage.entireEval = false;
					firstEvaluationPage.safetyEval = false;
					firstEvaluationPage.vulnerableEval = false;

					vulnerabilityEvaluationFrame goBackVulnerableFrame = new vulnerabilityEvaluationFrame();
					goBackVulnerableFrame.setVisible(true);
				}

			}
		});
		btnGoBack.setBounds(314, 429, 80, 29);
		contentPane.add(btnGoBack);

		// Exit action listener event
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(412, 429, 68, 29);
		contentPane.add(btnExit);

		JLabel label = new JLabel("Poor(0) -> Excellent(10)");
		label.setBounds(213, 47, 157, 38);
		contentPane.add(label);

		// Progress bar characteristics
		progressBar = new JProgressBar();
		progressBar.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		progressBar.setForeground(Color.GREEN);
		progressBar.setMaximum(10);
		progressBar.setStringPainted(true);
		progressBar.setBounds(41, 133, 518, 25);
		contentPane.add(progressBar);

	}
}
