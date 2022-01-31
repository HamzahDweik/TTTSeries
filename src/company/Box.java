package company;

public class Box {

    //holds the row and col value for its position
    private int row;
    private int col;

    //prepares the available spot place holder
    private final static String DASH = "-";
    private String placeHolder = Box.DASH;

    Box(int row, int col){

        //initializes the row and col of the box
        this.row = row;
        this.col = col;

    }

    //returns the placaeholder of the box
    public String getPlaceHolder() {
        return placeHolder;
    }

    //sets a placeholder for the box
    boolean setPlaceHolder(String placeHolder) {

        //chceks if the spot is available before setting the placeholder
        if(isAvailable()){
            this.placeHolder = placeHolder;
            return true;
        }
        return false;
    }

    //checks if the box is available
    boolean isAvailable(){

        //checks whether the mark at the spot is equal to the default place holder
        return (this.placeHolder.equals(Box.DASH) || this.placeHolder.compareTo("9") <= 0);
    }

    //prints the box
    void print(){

        //prints out the mark at the position
        System.out.print(placeHolder + " ");
    }
}
