import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class FitTrackerScreens {
	JPanel recordPanelMain, viewPanelMain, settingsPanelMain;
	JTabbedPane menuTabs;
	JFrame frame;
	JButton button = new JButton();

	public static void main(String[] args) {
		FitTrackerScreens swingFitTrackerScreens = new FitTrackerScreens();
		swingFitTrackerScreens.prepareGUI();
	}

	private void prepareGUI() {

		frame = new JFrame("Fitness Tracker");
		frame.setSize(450, 510);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuTabs = new JTabbedPane();

		recordPanelMain = new JPanel();
		recordPanelMain.setLayout(new GridLayout(3, 1));
		JPanel recordPanel1 = new JPanel();
		recordPanel1.setLayout(new GridLayout(2, 1));
		JPanel recordPanel2 = new JPanel();
		recordPanel2.setLayout(new FlowLayout());
		JPanel recordPanel3 = new JPanel();
		recordPanel3.setLayout(new GridLayout(3, 2, 10, 10));
		recordPanelMain.add(recordPanel1);
		recordPanelMain.add(recordPanel2);
		recordPanelMain.add(recordPanel3);
		JTextField rDateEntryY = new JTextField("YYYY", 4);
		JTextField rDateEntryM = new JTextField("MM", 2);
		JTextField rDateEntryD = new JTextField("DD", 2);
		JTextField sleepEntry = new JTextField("Hours", 5);
		JTextField stepsEntry = new JTextField("Steps", 5);
		JButton rSaveButton = new JButton("Save Entry");
		JLabel saveLabel = new JLabel("");
		recordPanel1.add(new JLabel("Record Entries", JLabel.CENTER));
		recordPanel1.add(new JLabel("Enter Date:", JLabel.CENTER));
		recordPanel2.add(rDateEntryY);
		recordPanel2.add(rDateEntryM);
		recordPanel2.add(rDateEntryD);
		recordPanel3.add(sleepEntry);
		recordPanel3.add(stepsEntry);
		recordPanel3.add(rSaveButton);
		recordPanel3.add(saveLabel);
		rSaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt(rDateEntryM.getText()) <= 12 && Integer.parseInt(rDateEntryD.getText()) <= 31) {
					try {
						FitTrackerIO.writeData("data.csv", Integer.parseInt(rDateEntryY.getText()),
								Integer.parseInt(rDateEntryM.getText()), Integer.parseInt(rDateEntryD.getText()),
								Integer.parseInt(sleepEntry.getText()), Integer.parseInt(stepsEntry.getText()));
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					saveLabel.setText("Step & Sleep Data Saved");
					recordPanel3.repaint();
					recordPanel3.revalidate();
				} else {
					saveLabel.setText("Error: Invalid Values Entered");
					recordPanel3.repaint();
					recordPanel3.revalidate();
				}
			}
		});
		recordPanelMain.setVisible(true);
		menuTabs.addTab("Record", recordPanelMain);

		viewPanelMain = new JPanel();
		viewPanelMain.setLayout(new GridLayout(4, 1));
		JPanel viewPanel1 = new JPanel();
		viewPanel1.setLayout(new GridLayout(2, 1));
		JPanel viewPanel2 = new JPanel();
		viewPanel2.setLayout(new FlowLayout());
		JPanel viewPanel3 = new JPanel();
		viewPanel3.setLayout(new GridLayout(2, 1));
		JPanel viewPanel4 = new JPanel();
		viewPanel4.setLayout(new GridLayout(3, 2, 10, 10));
		viewPanelMain.add(viewPanel1);
		viewPanelMain.add(viewPanel2);
		viewPanelMain.add(viewPanel3);
		JTextField vDateEntryY = new JTextField("YYYY", 4);
		JTextField vDateEntryM = new JTextField("MM", 2);
		JTextField vDateEntryD = new JTextField("DD", 2);
		JButton vLoadButton = new JButton("Load Entry");
		JLabel loadDate = new JLabel("LOAD DATE HERE", JLabel.CENTER);
		viewPanel1.add(new JLabel("View Past Entries", JLabel.CENTER));
		viewPanel1.add(new JLabel("Enter a Recall Date:", JLabel.CENTER));
		viewPanel2.add(vDateEntryY);
		viewPanel2.add(vDateEntryM);
		viewPanel2.add(vDateEntryD);
		viewPanel3.add(vLoadButton);
		viewPanel3.add(loadDate);
		viewPanelMain.setVisible(true);
		menuTabs.addTab("View", viewPanelMain);

		settingsPanelMain = new JPanel();
		settingsPanelMain.setLayout(new GridLayout(3, 1, 10, 10));
		JPanel settingsPanel1 = new JPanel();
		settingsPanel1.setLayout(new GridLayout(2, 1));
		JPanel settingsPanel2 = new JPanel();
		settingsPanel2.setLayout(new GridLayout(2, 2, 20, 20));
		JPanel settingsPanel3 = new JPanel();
		settingsPanel3.setLayout(new GridLayout(2, 1));
		settingsPanelMain.add(settingsPanel1);
		settingsPanelMain.add(settingsPanel2);
		settingsPanelMain.add(settingsPanel3);
		JTextField sleepGoalEntry = new JTextField("Hours", 5);
		JTextField stepsGoalEntry = new JTextField("Steps", 5);
		JButton sSaveButton = new JButton("Save Entry");
		JLabel updateLabel = new JLabel("");
		settingsPanel1.add(new JLabel("Settings", JLabel.CENTER));
		settingsPanel1.add(new JLabel("Enter Step and Sleep Goals:", JLabel.CENTER));
		settingsPanel2.add(new JLabel("SLEEP IMAGE HERE", JLabel.CENTER));
		settingsPanel2.add(new JLabel("STEPS IMAGE HERE", JLabel.CENTER));
		settingsPanel2.add(sleepGoalEntry);
		settingsPanel2.add(stepsGoalEntry);
		settingsPanel3.add(sSaveButton);
		settingsPanel3.add(updateLabel);
		sSaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt(sleepGoalEntry.getText()) <= 18
						&& Integer.parseInt(stepsGoalEntry.getText()) <= 50000) {
					try {
						FitTrackerIO.writeSettings("settings.csv", Integer.parseInt(sleepGoalEntry.getText()),
								Integer.parseInt(stepsGoalEntry.getText()));
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					updateLabel.setText("Settings Updated");
					settingsPanel3.repaint();
					settingsPanel3.revalidate();
				} else {
					updateLabel.setText("Error: Invalid Values Entered");
					settingsPanel3.repaint();
					settingsPanel3.revalidate();
				}
			}
		});
		settingsPanelMain.setVisible(true);
		menuTabs.addTab("Settings", settingsPanelMain);
		frame.add(menuTabs);
		frame.setVisible(true);
	}

}