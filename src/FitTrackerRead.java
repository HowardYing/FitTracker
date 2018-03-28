import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FitTrackerRead {
	private static final String COMMA_DELIMITER = ",";
	public static String recalledY = "";
	public static String recalledM = "";
	public static String recalledD = "";
	public static String recalledSleep = "";
	public static String recalledSteps = "";
	public static String recalledSleepGoal = "";
	public static String recalledStepsGoal = "";

	public static void readData(String fileName, int year, int month, int day) throws IOException {
		String line = "";
		String stringMonth = String.valueOf(month);
		String stringDay = String.valueOf(day);
		//Standardize user inputted date code by affixing 0 before months or days < 10
		if (stringMonth.length() == 1) {
			stringMonth = "0" + stringMonth;
		}
		if (stringDay.length() == 1) {
			stringDay = "0" + stringDay;
		}
		String searchDateCode = String.valueOf(year) + stringMonth + stringDay; //Reconstruct date code from user input
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			br.readLine();
			while ((line = br.readLine()) != null) {
				//Search for matches in CSV by date code, index [0] of the array
				String[] entry = line.split(",");
				if (searchDateCode.equals(entry[0])) {
					//Store matches in variables
					recalledY = Character.toString(entry[0].charAt(0)) + Character.toString(entry[0].charAt(1))
							+ Character.toString(entry[0].charAt(2)) + Character.toString(entry[0].charAt(3));
					recalledM = Character.toString(entry[0].charAt(4)) + Character.toString(entry[0].charAt(5));
					recalledD = Character.toString(entry[0].charAt(6)) + Character.toString(entry[0].charAt(7));
					recalledSleep = entry[1];
					recalledSteps = entry[2];
					boolean found = true;
					break;
				}
			}
			//Catch statement for no result
			if (recalledY == "") {
				System.out.print("No findings");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void readSettings(String fileName) throws IOException {
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			br.readLine();
			while ((line = br.readLine()) != null) {
				//Fetch settings values; location is always same for these values in CSV
				String[] setting = line.split(",");
				//Store matches
				recalledSleepGoal = setting[0];
				recalledStepsGoal = setting[1];
				}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
