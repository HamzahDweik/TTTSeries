package company;

public class Board {

    //uses a box array to simulate the board
    Box[] boxes;

    //contains the name of the board
    private String name;

    //holds the position of the board in respect to larger board
    private int row;
    private int col;

    //holds the dimensions of the board
    private int boardRowSize;
    private int boardColSize;

    //default constructor
    Board(){

        //sets the default row and col size, as well as the name
        this(3,3, "3*3 Board");

    }

    //constructor for inner boards
    Board(int rowSize, int colSize, int row, int col, String name){

        //sets name row col and size of the board
        this.setName(name);
        this.row = row;
        this.col = col;
        this.setSize(rowSize, colSize);
    }

    //constructor for main board
    Board(int rowSize, int colSize, String name){

        //constructor to initialize the board
        this.setName(name);
        this.setSize(rowSize, colSize);

    }

    //sets the name of the board
    public void setName(String name) {
        this.name = name;
    }

    //sets the size of the board
    public void setSize(int row, int col) {

        //checks if the row and column meet the minimum size requirement
        if(row < 3 || col < 3){

            System.out.println("Minimum board size is 3*3!");

            //terminates the program if the minimum board size is not met
            System.exit(0);

        } else {

            //sets the dimensions of the board to board state variables
            this.boardColSize = col;
            this.boardRowSize = row;
            init();

        }
    }

    //returns the column size
    public int getColSize() {
        return this.boardColSize;
    }

    //returns the row size
    public int getRowSize() {
        return this.boardRowSize;
    }

    //returns the name of the board
    public String getName() {
        return this.name;
    }

    //initializes the board
    private void init() {

        //initializes the board by making a box array
        boxes = new Box[boardColSize * boardRowSize];

        //initializes each box in the array
        for(int i = 0; i < boxes.length; i++){
            Box b = new Box(i/boardColSize, i%boardColSize);
            boxes[i] = b;
        }
    }

    //prints a single row of the board
    public void print(int row, int x){

        //if the board is not won, continue to output 0-8
        if(x == 0) {
            System.out.print(this.name + ":\t");

            for (int i = row * boardColSize; i < (row + 1) * boardColSize; i++) {
                if (boxes[i].isAvailable()) boxes[i].setPlaceHolder(String.valueOf(i));
                boxes[i].print();
            }
        }

        //if the board is already won, output * for all available spots
        else {
            System.out.print(this.name + ":\t");

            for (int i = row * boardColSize; i < (row + 1) * boardColSize; i++) {
                if (boxes[i].isAvailable()) boxes[i].setPlaceHolder("*");
                boxes[i].print();
            }
        }
    }

    //prints the entire board
    public void print() {

        //prints the board information
        System.out.println(this.name + ":");

        //prints the board
        for(int i = 0; i < boxes.length; i++){
            if(i != 0 && i % boardColSize == 0)
                System.out.println();
            if(boxes[i].isAvailable()) boxes[i].setPlaceHolder(String.valueOf(i));
            boxes[i].print();
        }
        System.out.println("");
    }

    //makes a move on the board
    public boolean makeMove(String mark, int value) {

        //if the spot is available, replaces the dash with the players mark
        if(boxes[value].isAvailable()) {

            //sets the players mark to the spot
            boxes[value].setPlaceHolder(mark);

            return true;

        } else {

            //if the spot is not available it prompts another attempt
            return false;
        }
    }

    //checks if the board is full
    public boolean isFull(){

        //checks for any available spots and returns true if none are available
        for(Box b: boxes) {
            if (b.isAvailable())
                return false;
        }
        return true;
    }

    //gives the mark at a given position
    public String getMark(int row, int col){

        //returns the mark at the given position
        return boxes[row * this.boardColSize + col].getPlaceHolder();

    }
}
