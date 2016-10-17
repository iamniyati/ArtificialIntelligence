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
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;


/**
 * This class is the frame class for the feedback form.
 * It forms a structure for all the different
 * feedback forms that are required in the system.
 * 
 * @version   $Id$ 1.0 entireFbFrame.java
 *
 * @author   Niyati Shah 
 *
 * Revisions:
 *	$Log$
 */

public class entireFbFrame extends JFrame {

	private JPanel contentPane;
	public JLabel lblNewLabel;
	public JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					entireFbFrame frame = new entireFbFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					System.out
							.println("Exception occurred in entireFbFrame main frame");
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public entireFbFrame() {
		
		// Initialize the frame and 
		// create basic functionalities.
		
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
		lblSecurityEvaluationReport.setBounds(180, 6, 263, 39);
		contentPane.add(lblSecurityEvaluationReport);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(68, 95, 379, 50);
		contentPane.add(lblNewLabel);

		JLabel lblStayInformedstaySafe = new JLabel("Stay Informed, Stay Safe");
		lblStayInformedstaySafe.setFont(new Font("Lucida Grande", Font.ITALIC,
				15));
		lblStayInformedstaySafe.setBounds(223, 28, 182, 39);
		contentPane.add(lblStayInformedstaySafe);

		textArea = new JTextArea();
		textArea.setBounds(19, 67, 561, 474);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(true);
		textArea.setAutoscrolls(true);
		// JScrollPane jscr = new JScrollPane(textArea);
		// jscr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		contentPane.add(textArea);

		// Go back button action listener event 
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstEvaluationPage.entireEval = false;
				firstEvaluationPage.safetyEval = false;
				firstEvaluationPage.vulnerableEval = false;
				contentPane.setVisible(false);
				setVisible(false);
				firstEvaluationPage goBackSafetyFrame = new firstEvaluationPage();
				goBackSafetyFrame.setVisible(true);

			}
		});
		
		btnGoBack.setBounds(420, 543, 80, 29);
		contentPane.add(btnGoBack);

		// Exit action listener event
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(512, 543, 68, 29);
		contentPane.add(btnExit);
	}

}
