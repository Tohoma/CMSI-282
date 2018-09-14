import java.util.Random;
public class DiceSolver {
    public static void main(String[] args) {
        //Test cases
        //oneMillionGames(3);
        //oneMillionGames(5);
        //oneMillionGames(8);

    }
    static void oneMillionGames(int diceAmount) {
        Random rand = new Random();
        int numOfGames = 1000000;
        int gamesCompleted = 0;
        int gamesWon = 0;
        int gamesLost = 0;
        int [] dice = new int[diceAmount];
        int numOfSixes = 0;
        while(gamesCompleted < numOfGames) {
            for (int i = 0; i<dice.length; i++) {
                //Generate number between 1 and 6 inclusive and assign the dice result of it
                dice[i] = rand.nextInt(6) + 1;
                if(dice[i] == 6) {
                    numOfSixes++;
                }
            }
            //Test to see if we won
            if(numOfSixes == 1) {
                gamesWon++;
                gamesCompleted++;
            } else if (numOfSixes == 0) {
                gamesLost++;
                gamesCompleted++;
            }
            //reset attempt
            numOfSixes = 0;
            //System.out.printf("Games Completed: %d\n",gamesCompleted);
        }
        float winPercentage = ((float) gamesWon)/numOfGames;
        float lossPercentage = ((float) gamesLost)/numOfGames;
        System.out.printf("Results %d dice", diceAmount);
        System.out.printf("Games Won: %d\n", gamesWon);
        System.out.printf("Games Lost: %d\n", gamesLost);
        System.out.printf("Percentage Won: %f\n", (winPercentage));
        System.out.printf("Percentage Loss: %f\n\n", (lossPercentage));
    }
}
