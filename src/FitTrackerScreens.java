import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FitTrackerScreens {
	JPanel buttonPanel;
	JFrame frame;
	JButton sleepButton, stepsButton, settingsButton;
	JButton button = new JButton();
	int pageState = 0;

	public static void main(String[] args) {
		FitTrackerScreens swingFitTrackerScreens = new FitTrackerScreens();
		swingFitTrackerScreens.prepareGUI();
	}

	private void prepareGUI() {
		frame = new JFrame("Fitness Tracker");
		frame.setSize(450, 510);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(3, 1));
		buttonPanel = new JPanel();

		// Display Page Switcher Buttons
		stepsButton = new JButton("Steps");
		stepsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageState = 0;
			}
		});
		sleepButton = new JButton("Sleep");
		sleepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageState = 1;
			}
		});
		settingsButton = new JButton("Settings");
		settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageState = 2;
			}
		});
		buttonPanel.add(sleepButton);
		buttonPanel.add(stepsButton);
		buttonPanel.add(settingsButton);
		frame.add(buttonPanel);

		if (pageState == 0) {
			System.out.println("Steps Pressed");
		} else if (pageState == 1) {
			System.out.println("Sleep Pressed");
		} else {
			System.out.println("Settings Pressed");
		}

		frame.setVisible(true);
	}

}
