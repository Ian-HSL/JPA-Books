package csulb.cecs323.app;

import java.util.List;
import java.util.Scanner;

/**
 * This class holds functions to validate input from user in used in other functions*/
public class Validator {
    /**
     * Takes in a scanner and a length and checks that the string input from the user
     * is less than that length so that it can fit in the database
     * @param length : length for input to be less than
     * @param in : scanner object
     * */
    public static String checkStringLength(Scanner in, int length)
    {
        String input;
        do {
            input = in.nextLine();
            if(input.length()> length || input.length() <= 0)
            {
                System.out.println("Your input length is too long for our database. Please Try again: ");
            }
        }while(input.length() > length || input.length() <= 0);
        return input;
    }
    /**
     * Takes in a scanner and a high and low int value to restrict what numbers the user can select.
     * @param low : the lowest int that the user can select
     * @param high: the highest in the user can select
     * @param in : scanner object
     * */
    public static int checkInput(Scanner in, int low, int high)
    {
        int returnedInt = -1;
        while(returnedInt < low || returnedInt > high)
        {
            System.out.println("Select Choice: ");

            if (in.hasNextInt())
            {
                returnedInt = in.nextInt();
                if (returnedInt < low || returnedInt > high)
                {
                    System.out.println("Bad input. Try again.");
                    returnedInt = -1;
                }
                in.nextLine();
            }
            else {
                if(in.hasNextLine()) {
                    in.nextLine();
                }
            }
        }

        return returnedInt;
    }
}
