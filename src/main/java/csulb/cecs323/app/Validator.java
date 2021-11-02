package csulb.cecs323.app;

import java.util.List;
import java.util.Scanner;

public class Validator {
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
