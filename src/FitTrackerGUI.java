import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

public class FitTrackerGUI {
	JPanel recordPanelMain, viewPanelMain, settingsPanelMain;
	JTabbedPane menuTabs;
	JFrame frame;
	JButton button = new JButton();

	public static void main(String[] args) throws IOException {
		FitTrackerGUI swingFitTrackerGUI = new FitTrackerGUI();
		swingFitTrackerGUI.prepareGUI();
	}

	private void prepareGUI() throws IOException {

		frame = new JFrame("Fitness Tracker");
		frame.setSize(450, 510);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuTabs = new JTabbedPane();

		// "Record" Panel GUI Setup
		recordPanelMain = new JPanel(); // Declare and initialize Master & sub JPanels
		recordPanelMain.setLayout(new GridLayout(3, 1));
		JPanel recordPanel1 = new JPanel();
		recordPanel1.setLayout(new GridLayout(2, 1));
		JPanel recordPanel2 = new JPanel();
		recordPanel2.setLayout(new FlowLayout());
		JPanel recordPanel3 = new JPanel();
		recordPanel3.setLayout(new GridLayout(3, 2, 10, 10));
		// Add sub JPanels to master
		recordPanelMain.add(recordPanel1);
		recordPanelMain.add(recordPanel2);
		recordPanelMain.add(recordPanel3);
		// Declare and initialize panel components
		JTextField rDateEntryY = new JTextField(4);
		JTextField rDateEntryM = new JTextField(2);
		JTextField rDateEntryD = new JTextField(2);
		JTextField sleepEntry = new JTextField(5);
		JTextField stepsEntry = new JTextField(5);
		JButton rSaveButton = new JButton("Save Entry");
		JLabel saveLabel = new JLabel("");
		JLabel monthLabel = new JLabel("MM: ");
		JLabel dayLabel = new JLabel("DD: ");
		JLabel yearLabel = new JLabel("YYYY: ");
		JLabel sleepPromptLabel = new JLabel("Enter hours slept below:  ", JLabel.CENTER);
		JLabel stepsPromptLabel = new JLabel("Enter steps walked below: ", JLabel.CENTER);
		// Add components to respective panels
		recordPanel1.add(new JLabel("Record Entries", JLabel.CENTER));
		recordPanel1.add(new JLabel("Enter Date:", JLabel.CENTER));
		recordPanel2.add(monthLabel);
		recordPanel2.add(rDateEntryM);
		recordPanel2.add(dayLabel);
		recordPanel2.add(rDateEntryD);
		recordPanel2.add(yearLabel);
		recordPanel2.add(rDateEntryY);
		recordPanel3.add(sleepPromptLabel);
		recordPanel3.add(stepsPromptLabel);
		recordPanel3.add(sleepEntry);
		recordPanel3.add(stepsEntry);
		recordPanel3.add(rSaveButton);
		recordPanel3.add(saveLabel);
		// Add ActionListener - call FitTrackerWrite.writeData() and pass user input
		rSaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Check for unrealistic or invalid inputs
				if (Integer.parseInt(rDateEntryM.getText()) <= 12 && Integer.parseInt(rDateEntryD.getText()) <= 31) {
					try {
						FitTrackerWrite.writeData("data/data.csv", Integer.parseInt(rDateEntryY.getText()),
								Integer.parseInt(rDateEntryM.getText()), Integer.parseInt(rDateEntryD.getText()),
								Integer.parseInt(sleepEntry.getText()), Integer.parseInt(stepsEntry.getText()));
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					//Display action confirmation
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

		// "View" Panel GUI Setup
		viewPanelMain = new JPanel();// Declare and Initialize Master & Sub JPanels
		viewPanelMain.setLayout(new GridLayout(4, 1));
		JPanel viewPanel1 = new JPanel();
		viewPanel1.setLayout(new GridLayout(2, 1));
		JPanel viewPanel2 = new JPanel();
		viewPanel2.setLayout(new FlowLayout());
		JPanel viewPanel3 = new JPanel();
		viewPanel3.setLayout(new GridLayout(2, 1));
		JPanel viewPanel4 = new JPanel();
		viewPanel4.setLayout(new GridLayout(2, 2, 10, 10));
		// Add sub JPanels to master
		viewPanelMain.add(viewPanel1);
		viewPanelMain.add(viewPanel2);
		viewPanelMain.add(viewPanel3);
		viewPanelMain.add(viewPanel4);
		// Declare and initialize panel components
		JTextField vDateEntryY = new JTextField(4);
		JTextField vDateEntryM = new JTextField(2);
		JTextField vDateEntryD = new JTextField(2);
		JButton vLoadButton = new JButton("Load Entry");
		JLabel loadDate = new JLabel("", JLabel.CENTER);
		JLabel sleepLabel = new JLabel("", JLabel.CENTER);
		JLabel stepsLabel = new JLabel("", JLabel.CENTER);
		JLabel sleepGoal = new JLabel("", JLabel.CENTER);
		JLabel stepsGoal = new JLabel("", JLabel.CENTER);
		JLabel vMonthLabel = new JLabel("MM: ");
		JLabel vDayLabel = new JLabel("DD: ");
		JLabel vYearLabel = new JLabel("YYYY: ");
		// Add components to respective panels
		viewPanel1.add(new JLabel("View Past Entries", JLabel.CENTER));
		viewPanel1.add(new JLabel("Enter a Recall Date:", JLabel.CENTER));
		viewPanel2.add(vMonthLabel);
		viewPanel2.add(vDateEntryM);
		viewPanel2.add(vDayLabel);
		viewPanel2.add(vDateEntryD);
		viewPanel2.add(vYearLabel);
		viewPanel2.add(vDateEntryY);
		viewPanel3.add(vLoadButton);
		viewPanel3.add(loadDate);
		viewPanel4.add(sleepLabel);
		viewPanel4.add(stepsLabel);
		viewPanel4.add(sleepGoal);
		viewPanel4.add(stepsGoal);
		// Add ActionListener - call FitTrackerRead.readData() and pass user input
		vLoadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//Check for unrealistic or invalid inputs
					if (Integer.parseInt(vDateEntryM.getText()) <= 12
							&& Integer.parseInt(vDateEntryD.getText()) <= 31) {
						FitTrackerRead.readData("data/data.csv", Integer.parseInt(vDateEntryY.getText()),
								Integer.parseInt(vDateEntryM.getText()), Integer.parseInt(vDateEntryD.getText()));
						String metSleep;
						String metSteps;
						if (FitTrackerRead.recalledY != "") {
							loadDate.setText("Recalled Date: " + FitTrackerRead.recalledM + "/"
									+ FitTrackerRead.recalledD + "/" + FitTrackerRead.recalledY);
							viewPanel3.repaint();
							viewPanel3.revalidate();
							//Display recalled values from CSV
							sleepLabel.setText("You Slept for: " + FitTrackerRead.recalledSleep + " hours");
							stepsLabel.setText("You walked about: " + FitTrackerRead.recalledSteps + " steps");
							//Check if stored data meets current goal
							if (Integer.valueOf(FitTrackerRead.recalledSleep) > Integer
									.valueOf(FitTrackerRead.recalledSleepGoal)) {
								metSleep = "met";
							} else {
								metSleep = "didn't meet";
							}
							if (Integer.valueOf(FitTrackerRead.recalledSteps) > Integer
									.valueOf(FitTrackerRead.recalledStepsGoal)) {
								metSteps = "met";
							} else {
								metSteps = "didn't meet";
							}
							sleepGoal.setText("You " + metSleep + " your sleep goal!");
							stepsGoal.setText("You " + metSteps + " your step goal!");
							viewPanel4.repaint();
							viewPanel4.revalidate();
						} else {
							//Return if no entry found from FitTrackerRead.readData()
							loadDate.setText("No entry found for " + vDateEntryM.getText() + "/" + vDateEntryD.getText()
									+ "/" + vDateEntryY.getText());
							viewPanel3.repaint();
							viewPanel3.revalidate();
						}

					} else {
						loadDate.setText("Error: Invalid Values Entered");
						recordPanel3.repaint();
						recordPanel3.revalidate();
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		viewPanelMain.setVisible(true);
		menuTabs.addTab("View", viewPanelMain);

		// "Settings" Panel GUI Setup
		settingsPanelMain = new JPanel();// Declare and Initialize Master & Sub JPanels
		settingsPanelMain.setLayout(new GridLayout(3, 1, 10, 10));
		JPanel settingsPanel1 = new JPanel();
		settingsPanel1.setLayout(new GridLayout(3, 1));
		JPanel settingsPanel2 = new JPanel();
		settingsPanel2.setLayout(new GridLayout(2, 2, 20, 20));
		JPanel settingsPanel3 = new JPanel();
		settingsPanel3.setLayout(new GridLayout(2, 1));
		// Add sub JPanels to master
		settingsPanelMain.add(settingsPanel1);
		settingsPanelMain.add(settingsPanel2);
		settingsPanelMain.add(settingsPanel3);
		// Declare and initialize panel components
		JTextField sleepGoalEntry = new JTextField(5);
		JTextField stepsGoalEntry = new JTextField(5);
		JButton sSaveButton = new JButton("Save Entry");
		JLabel updateLabel = new JLabel("");
		settingsPanel1.add(new JLabel("Settings", JLabel.CENTER));
		FitTrackerRead.readSettings("data/settings.csv");// Read current settings values from CSV by calling FitTrackerRead.readSettings()
		// Add components to respective panels
		settingsPanel1
				.add(new JLabel("Current Sleep Goal: " + FitTrackerRead.recalledSleepGoal + " hours", JLabel.CENTER));
		settingsPanel1
				.add(new JLabel("Current Step Goal: " + FitTrackerRead.recalledStepsGoal + " steps", JLabel.CENTER));
		settingsPanel2.add(new JLabel("Update your sleep goal below:", JLabel.CENTER));
		settingsPanel2.add(new JLabel("Update your step goal below:", JLabel.CENTER));
		settingsPanel2.add(sleepGoalEntry);
		settingsPanel2.add(stepsGoalEntry);
		settingsPanel3.add(sSaveButton);
		settingsPanel3.add(updateLabel);
		// Add ActionListener - call FitTrackerWrite.writeSettings() and pass user input
		sSaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Check for unrealistic or invalid inputs
				if (Integer.parseInt(sleepGoalEntry.getText()) <= 18
						&& Integer.parseInt(stepsGoalEntry.getText()) <= 50000) {
					try {
						FitTrackerWrite.writeSettings("data/settings.csv", Integer.parseInt(sleepGoalEntry.getText()),
								Integer.parseInt(stepsGoalEntry.getText()));
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					//Display action confirmation
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