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
		if (stringMonth.length() == 1) {
			stringMonth = "0" + stringMonth;
		}
		if (stringDay.length() == 1) {
			stringDay = "0" + stringDay;
		}
		String encodedDate = String.valueOf(year) + stringMonth + stringDay;
		if (br.readLine() == null) {
			try {
				fileWriter = new FileWriter(fileName);

				// Write the CSV file header
				fileWriter.append(FILE_HEADER_DATA.toString());

				// Add a new line separator after the header
				fileWriter.append(NEW_LINE_SEPARATOR);

				// Write a new student object list to the CSV file
				fileWriter.append(String.valueOf(encodedDate));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sleep));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(steps));
				fileWriter.append(NEW_LINE_SEPARATOR);

				System.out.println("CSV file was created successfully !!!");

			} catch (Exception e) {
				System.out.println("Error in CsvFileWriter !!!");
				e.printStackTrace();
			} finally {

				try {
					fileWriter.flush();
					fileWriter.close();
				} catch (IOException e) {
					System.out.println("Error while flushing/closing fileWriter !!!");
					e.printStackTrace();
				}
			}
		} else {
			try {
				fileWriter = new FileWriter(fileName, true);

				// Write a new object list to the CSV file
				fileWriter.append(String.valueOf(encodedDate));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(sleep));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(steps));
				fileWriter.append(NEW_LINE_SEPARATOR);

				System.out.println("CSV file was appended successfully !!!");

			} catch (Exception e) {
				System.out.println("Error in CsvFileWriter !!!");
				e.printStackTrace();
			} finally {

				try {
					fileWriter.flush();
					fileWriter.close();
				} catch (IOException e) {
					System.out.println("Error while flushing/closing fileWriter !!!");
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

			// Write a new student object list to the CSV file
			fileWriter.append(String.valueOf(sleepGoal));
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(String.valueOf(stepsGoal));
			fileWriter.append(NEW_LINE_SEPARATOR);

			System.out.println("CSV file was created successfully !!!");

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {

			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}
	}
}
