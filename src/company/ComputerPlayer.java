package company;

public class ComputerPlayer extends APlayer{

    public ComputerPlayer(String name, String mark) {

        //initializes the name and mark of the player
        super(name, mark);

    }

    //returns a random number in the range as the selected value
    @Override
    public int selectValue(int range) {
        return randomNumber(range);
    }

    //returns a random number in the range as the selected value
    @Override
    public int getBoard(int range){
        return randomNumber(range);
    }

    //returns a random number
    private int randomNumber(int range){

        //returns a random number along the range
        return (int)(Math.random() * range);

    }
}
