package company;

import java.util.Scanner;

public class HumanPlayer extends APlayer{

    //scanner to take input from the console
    Scanner input = new Scanner(System.in);

    public HumanPlayer(String name, String mark) {
        super(name, mark);
    }

    //allows the player to select a value
    @Override
    public int selectValue(int range) {
        int value = 0;
        do{
            //prints a warning message if the user input too large or too small of a number
            if(value < -1 || value >= range) System.out.println("Please only enter numbers in the range of 0-8!");

            //prompts the user to input a value
            System.out.print("Please select a valid square on the selected board: ");

            //checks if the value is an int
            if(input.hasNextInt()) value = input.nextInt();

            //otherwise restarts loop and gives a warning prompt
            else{
                value = -1;
                input.next();
                System.out.println("Please enter an integer only!");
            }
        } while (value < 0 || value >= range);
        return value;
    }

    //allows the player to select a board
    @Override
    public int getBoard(int range){
        int board = 0;
        do {
            //prints a warning message if the user input too large or too small of a number
            if(board < -1 || board >= range) System.out.println("Please only enter numbers in the range of 0-8!");

            //prompts the user to input a value
            System.out.print("Please select a valid board: ");

            //checks if the value is an int
            if(input.hasNextInt()) board = input.nextInt();

            //otherwise restarts loop and gives a warning prompt
            else{
                board = -1;
                input.next();
                System.out.println("Please enter an integer only!");
            }
        } while (board < 0 || board >= range);
        return board;
    }
}
