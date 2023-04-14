
/**
 * ALVIN ANTO
 * NET ID: AXA220139
 * UTD ID: 2021686750
 * CS 2337.002
 * PROJECT ZERO JAVA IMPLEMENTATION
 */

import java.io.BufferedReader; //import statement for BufferedReader
import java.io.FileNotFoundException; //import statement for FileNotFoundException
import java.io.FileReader; //import statement for FileReader
import java.io.IOException; //import statement for IOException
import java.util.Scanner; //import statement for Scanner

/**
 * @author alvinanto Program to calculate player stats and determine the league
 *         leaders in several different baseball categories, such as hits,
 *         strikeouts and walks, from the input file.
 */

@SuppressWarnings("unused")
public class Main {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		// Creating a Scanner object
		Scanner keyboard = new Scanner(System.in);

		// Prompting the user to enter the filename
		System.out.println("Enter the filename");

		// Storing the filename entered by the user to the String variable filename
		String fileName = keyboard.nextLine();

		// Creating a String array listNames to store the names of the players
		String[] listNames = new String[30];

		// Creating float arrays for each category to save the stats for all players
		float[] battingAverages = new float[30];
		float[] onBasePercentages = new float[30];
		float[] numHits = new float[30];
		float[] numStrikeouts = new float[30];
		float[] numWalks = new float[30];
		float[] numHitsByPitch = new float[30];

		// Creating float 2D array to store all the arrays which contain category stats
		// for all players.
		float[][] statArray = { battingAverages, onBasePercentages, numHits, numWalks, numStrikeouts, numHitsByPitch };

		// Creating a FileReader object passing it the filename
		FileReader file1 = new FileReader(fileName);
		// Creating a BufferedReader object passing FileReader object
		BufferedReader file2 = new BufferedReader(file1);
		// Reading the first line of the file
		String line = file2.readLine();

		// Initializing the count variable to zero, for keeping track of the index
		// number of the String array for saving the list of player names
		int count = 0;

		// Loop until the end of the file
		while (line != null) {
			// Initializing the variable c to zero, to keep track of the index of the line
			// read from the file
			int c = 0;

			// Declaring a String variable name and initializing it to "" to store the
			// player name from the file name later.
			String name = "";

			// Declaring a String variable battingRecord and initializing it to "" to store
			// the batting record of each player from the file name later.
			String battingRecord;

			// The player name is stored to the variable name letter by letter using a while
			// loop, until there is a space in the line,
			// which means that the pointer is in between the player name and the batting
			// record
			while (!(line.charAt(c) == ' ')) {
				name += line.charAt(c);
				c++;
			}

			// Storing the player name to the appropriate index in the array listNames
			listNames[count] = name;

			// Storing the battle record part from the line to the variable battingRecord
			battingRecord = line.substring(c + 1);

			// Calling parseBattingRecord method that parses the batting record and
			// calculate the stats according to the letters in the batting reecord
			parseBattingRecord(battingRecord, statArray, count);

			// Counter variable increased by 1 for the next player name to be stored in the
			// ext index in the listNames array
			count++;

			// Reading the next line from the file for the loop to do the next iteration
			line = file2.readLine();
		}

		// Closing the file after reading from that.
		file2.close();

		// Calling displayPlayerDate method that displays the name and stats of all the
		// players.
		displayPlayerData(listNames, statArray);

		// Calling displayLeaders method that displays the league leaders for each
		// categories
		displayLeaders(listNames, statArray);

		// Closing the Scanner object keyboard
		keyboard.close();

	}

	/**
	 * The method parseBattingRecord parses through the batting record for each
	 * player and update respective variables and keep tract of the scores of each
	 * player in each category
	 * 
	 * @param battingRecordString
	 * @param statArray1
	 * @param arrayIndex
	 */
	public static void parseBattingRecord(String battingRecordString, float[][] statArray1, int arrayIndex) {
		// Creating float variables to store the stats for each category
		float numHits = 0;
		float numOuts = 0;
		float numStrikeouts = 0;
		float numWalks = 0;
		float numHitsByPitch = 0;
		float numSacrifices = 0;
		float atBats = 0;
		float plateAppearances = 0;
		float battingAverage;
		float onBasePercentage;

		// Count variable l for keeping track of the index of the string that contains
		// batting record
		int l = 0;

		// Parsing through the batting record and increasing the stats for respective
		// categories
		// Each letter in the batting record is compared using switch statement
		while (l < battingRecordString.length()) {
			switch (battingRecordString.charAt(l)) {
			case 'H':
				numHits++;
				break;
			case 'O':
				numOuts++;
				break;
			case 'K':
				numStrikeouts++;
				break;
			case 'W':
				numWalks++;
				break;
			case 'P':
				numHitsByPitch++;
				break;
			case 'S':
				numSacrifices++;
				break;
			default:
				break;
			}

			// Incrementing the count variable for the next iteration to be done with the
			// next letter in the string
			l++;
		}

		// Calculating at-bats and storing it in the variable atBats
		// by adding the number of hits, number of outs and number of strikeouts
		atBats = numHits + numOuts + numStrikeouts;

		// Calculating plate appearances and storing it in the variable plateAppearances
		// by adding all the stats together
		plateAppearances = numHits + numOuts + numStrikeouts + numWalks + numHitsByPitch + numSacrifices;

		// Calculating the batting average and storing it in the variable battingAverage
		// by dividing the number of hits by at-bats
		battingAverage = numHits / atBats;

		// Making batting average 0 if numHits and atBats are 0, to avoid undefined
		// value
		if (numHits == 0) {
			if (atBats == 0) {
				battingAverage = 0;
			}
		}

		// Calculating the onbase percentage and storing it in the variable
		// onBasePercentage
		// by dividing the sum of number of hits, number of walks and number of hits by
		// pitch by the number of plate appearances
		onBasePercentage = (numHits + numWalks + numHitsByPitch) / plateAppearances;

		// Making onbase percentage 0 if the sum of numHits, numWalks and numHitsByPitch
		// is 0, to avoid undefined value
		if (numHits + numWalks + numHitsByPitch == 0) {
			onBasePercentage = 0;
		}

		// Storing all the stats to respective arrays for those categories in the
		// respective index
		statArray1[0][arrayIndex] = battingAverage;
		statArray1[1][arrayIndex] = onBasePercentage;
		statArray1[2][arrayIndex] = numHits;
		statArray1[4][arrayIndex] = numStrikeouts;
		statArray1[3][arrayIndex] = numWalks;
		statArray1[5][arrayIndex] = numHitsByPitch;

	}

	/**
	 * The method displayPlayerData displays the player name and the stats for that
	 * player in all the categories
	 * 
	 * @param listNames1
	 * @param statArray1
	 */
	public static void displayPlayerData(String[] listNames1, float[][] statArray1) {
		// Declaring the control variable for the for loop
		int i;

		// The for loop runs till the end of the listNames1 array which contains the
		// name of all the players and display their name and stats for all categories.
		for (i = 0; i < 30; i++) {
			// Break statement to break from the for loop once the listNames1 array has no
			// more valid names.
			if (listNames1[i] == null) {
				break;
			}

			// Print statements for the player name and the stats for all the categories
			System.out.println(listNames1[i]);
			System.out.print("BA: ");
			System.out.printf("%.3f", statArray1[0][i]);
			System.out.println();
			System.out.print("OB%: ");
			System.out.printf("%.3f", statArray1[1][i]);
			System.out.println();
			System.out.println("H: " + (int) statArray1[2][i]);
			System.out.println("BB: " + (int) statArray1[3][i]);
			System.out.println("K: " + (int) statArray1[4][i]);
			System.out.println("HBP: " + (int) statArray1[5][i]);
			System.out.println();
		}
	}

	/**
	 * The method findLeaderIndex parses through the category array passed as the
	 * argument, and finds the index for the largest value and returns it
	 * 
	 * @param statArray1
	 * @return the index for the largest value
	 */
	public static int findLeaderIndex(float[] statArray1) {
		// Initializing the control variable for the while loop
		int i = 0;

		// Initializing the index variable to 0
		int index = 0;

		// Declaring the float variable value to store the largest value and storing the
		// first index value initially in order to compare with other values in the
		// while loop
		float value = statArray1[index];

		// While loop that parses through the statArray and if a larger value is found,
		// the value gets updated with that value and the index is updated with the
		// index for that value
		while (i < statArray1.length) {
			if (value < statArray1[i]) {
				value = statArray1[i];
				index = i;
			}
			i++;
		}

		// returning the index of the largest value
		return index;
	}

	/**
	 * The method findLeaderIndexForStrikeOuts parses through the category array
	 * passed as the argument, and finds the index for the smallest value and
	 * returns it
	 * 
	 * @param listNames1
	 * @param statArray1
	 * @return the index for the smallest value
	 */
	public static int findLeaderIndexForStrikeOuts(String[] listNames1, float[] statArray1) {
		// Initializing the control variable for the while loop
		int i = 0;

		// Initializing the index variable to 0
		int index = 0;

		// Declaring the float variable value to store the smallest value and storing
		// the first index value initially in order to compare with other values in the
		// while loop
		float value = statArray1[index];

		// While loop that parses through the statArray and if a smaller value is found,
		// the value gets updated with that value and the index is updated with the
		// index for that value
		while (i < listNames1.length) {
			if (listNames1[i] != null) {
				if (value > statArray1[i]) {
					value = statArray1[i];
					index = i;
				}
			}
			i++;
		}

		// returning the index of the largest value
		return index;
	}

	/**
	 * The method getLeader returns the string with the name(s) of the league
	 * leader(s) in each category
	 * 
	 * @param index
	 * @param statArray1
	 * @param listNames1
	 * @return the string with the name(s) of the league leader(s) in each category
	 */
	public static String getLeader(int index, float[] statArray1, String[] listNames1) {
		// Initializing the control variable for the for loop
		int i;

		// Initializing the String variable leader to "" to store the names of the
		// leaders later
		String leader = "";

		// Declaring the String leader1 to store the names of the leaders after removing
		// the comma at the end
		String leader1;

		// For loop for finding the indexes with the largest values and stores the names
		// of the leaders with the respective indexes from the listNames1 array
		for (i = 0; i < listNames1.length; i++) {
			// Breaking from the for loop if there are no more valid player names in the
			// listNames1 array
			if (listNames1[i] == null) {
				break;
			}

			// Finding the indexes with the largest values and stores the names of the
			// leaders with the respective indexes from the listNames1 array
			if (statArray1[i] == statArray1[index]) {
				leader += listNames1[i] + ", ";
			}
		}

		// Removing comma at the end of leader string and storing it to leader1
		if (leader.charAt(leader.length() - 1) == ' ') {
			leader1 = leader.substring(0, leader.length() - 2);
		} else {
			leader1 = leader;
		}

		// Returning the string with the name(s) of the league leader(s) in each
		// category
		return leader1;
	}

	/**
	 * The method diplayLeaders display the league leaders for each category
	 * 
	 * @param listNames1
	 * @param statArray1
	 */
	public static void displayLeaders(String[] listNames1, float[][] statArray1) {
		// Printing the title
		System.out.println("LEAGUE LEADERS");

		// Making a String array with the category names so it can be easily printed
		// along with the stats in a single loop
		String[] category = { "BA: ", "OB%: ", "H: ", "BB: ", "K: ", "HBP: " };

		// For loop for displaying the league leaders for each category with the stats
		// BA and OB% are float values and printed with 3 digits after the decimal point
		for (int i = 0; i < 2; i++) {
			// Calling findLeaderIndex method and storing the return value to the int
			// variable int
			int ind = findLeaderIndex(statArray1[i]);

			// Calling the getLeader method and printing the return value of the league
			// leaders along with the category name
			System.out.print(category[i] + getLeader(ind, statArray1[i], listNames1) + " ");

			// Printing the score with 3 digits after the decimal point
			System.out.printf("%.3f", statArray1[i][ind]);

			// Printing a blank line
			System.out.println();
		}

		// For loop for displaying the league leaders for each category with the stats
		// H, BB, K and HBP are casted to int values and printed as int values with no
		// decimal digits
		for (int i = 2; i < 6; i++) {
			// For Strikeouts, the smallest value is used for finding the leader for that
			// category
			if (i == 4) {
				// Calling findLeaderIndexForStrikeOuts method and storing the return value to
				// the int variable int
				int ind = findLeaderIndexForStrikeOuts(listNames1, statArray1[i]);

				// Calling the getLeader method and printing the return value of the league
				// leaders along with the category name and the score as an integer
				System.out.print(
						category[i] + getLeader(ind, statArray1[i], listNames1) + " " + (int) statArray1[i][ind]);

				// Printing a blank line
				System.out.println();

				// For loop moves to the next iteration
				continue;
			}

			// Calling findLeaderIndex method and storing the return value to the int
			// variable int
			int ind = findLeaderIndex(statArray1[i]);

			// Calling the getLeader method and printing the return value of the league
			// leaders along with the category name and the score as an integer
			System.out.print(category[i] + getLeader(ind, statArray1[i], listNames1) + " " + (int) statArray1[i][ind]);

			// Printing a blank line
			System.out.println();
		}

	}

}
