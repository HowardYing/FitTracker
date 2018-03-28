import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author ashraf
 * 
 */
public class FitTrackerWrite {

	// Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";

	// CSV file header
	private static final String FILE_HEADER_DATA = "datecode, sleep, steps";
	private static final String FILE_HEADER_SETTINGS = "sleepGoal, stepsGoal";

	public static void writeData(String fileName, int year, int month, int day, int sleep, int steps)
			throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("data/data.csv"));
		FileWriter fileWriter = null;
		String stringMonth = String.valueOf(month);
		String stringDay = String.valueOf(day);
		//Standardize user inputted date code by affixing 0 before months or days < 10
		if (stringMonth.length() == 1) {
			stringMonth = "0" + stringMonth;
		}
		if (stringDay.length() == 1) {
			stringDay = "0" + stringDay;
		}
		String encodedDate = String.valueOf(year) + stringMonth + stringDay; //Construct date code from user input
		if (br.readLine() == null) { //Detect if CSV file is blank or has old entries
			try { //If detects CSV file is blank
				fileWriter = new FileWriter(fileName);

				// Write the CSV file header
				fileWriter.append(FILE_HEADER_DATA.toString());

				// Add a new line separator after the header
				fileWriter.append(NEW_LINE_SEPARATOR);

				// Write the new entry values to file
				fileWriter.append(String.valueOf(encodedDate));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sleep));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(steps));
				fileWriter.append(NEW_LINE_SEPARATOR);


			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				try {
					fileWriter.flush();
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else { 
			try {//If detects CSV file has previous information
				fileWriter = new FileWriter(fileName, true);

				// Write the new entry values to file
				fileWriter.append(String.valueOf(encodedDate));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sleep));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(steps));
				fileWriter.append(NEW_LINE_SEPARATOR);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				try {
					fileWriter.flush();
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void writeSettings(String fileName, int sleepGoal, int stepsGoal) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("data/settings.csv"));
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);

			// Write the CSV file header
			fileWriter.append(FILE_HEADER_SETTINGS.toString());

			// Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);

			// Write a new settings values to the CSV file
			fileWriter.append(String.valueOf(sleepGoal));
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(String.valueOf(stepsGoal));
			fileWriter.append(NEW_LINE_SEPARATOR);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
