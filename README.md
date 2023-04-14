# Baseball Statistics Calculator
This is a Java-based application that calculates baseball player statistics and determines the league leaders in several different baseball categories, such as hits, strikeouts, and walks. The application reads player data from a text file and displays the stats for each player, as well as the league leaders in each category.

**Getting Started**

To run the application, clone the repository and open the project using a Java IDE (e.g., Eclipse or IntelliJ).

**Prerequisites**

- JDK 8 or higher
- A Java IDE

**Input File Format**

The input file should contain player data in the following format:
<player_name> <batting_record>

  <player_name>: A string containing the player's name.
  <batting_record>: A string containing the player's batting record, with each character representing an event in the following manner:
  H: Hit
  O: Out
  K: Strikeout
  W: Walk
  P: Hit by Pitch
  S: Sacrifice
  
 Each player should be on a separate line, and there should be no more than 30 players in the input file.
 
**Running the Application**

1) Compile and run the Main.java file.
2) When prompted, enter the name of the input file containing player data.
3) The application will display the stats for each player and the league leaders in each category.

**Features**

- Calculate player stats based on their batting records, including batting average, on-base percentage, hits, walks, strikeouts, and hit by pitch.
- Determine the league leaders for each category based on the calculated stats.
- Display the stats for each player and the league leaders in a readable format.
