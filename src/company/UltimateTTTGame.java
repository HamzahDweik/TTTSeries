package company;

public class UltimateTTTGame {

    //holds the name of the game
    private String name = "Ultimate TTTGame";

    //to hold the players and each of their marks
    private APlayer[] players = new APlayer[2];
    private String[] marks = {"X", "O"};

    //contains the game board and each of its inner boards
    private Board[] squares = new Board[9];
    private Board board;

    //contains the dimensions for the board and inner boards
    private int gameRowSize = 3;
    private int gameColSize = 3;
    private int innerRowSize = 3;
    private int innerColSize = 3;

    //holds the current players index value
    private int currentPlayerIndex = -1;

    //contains the selected board and square
    private int selectedBoard = -1;
    private int selectedSquare;

    //keeps track of the scoring to determine winner
    private int[] scoreCounter = {0, 0};
    private int gameScoreToWin = 3;

    //default constructor
    public UltimateTTTGame(){
        setPlayers();
        setBoards();
    }

    //sets the players with player parameters
    public void setPlayers(APlayer player1, APlayer player2){
        players[0] = player1;
        players[1] = player2;
    }

    //sets the players automatically to computer players
    public void setPlayers(){
        for(int i = 0; i < players.length; i++){
            players[i] = new ComputerPlayer("Player " + (i+1), marks[i]);
        }
    }

    //initializes the main board and the inner boards
    public void setBoards(){
        board = new Board(gameRowSize, gameColSize, "Main Board");
        for(int i = 0; i < squares.length; i++){
            squares[i] = new Board(innerRowSize, innerColSize, i/gameColSize, i%gameColSize, "Board " + i);
        }
    }

    //starts the game
    public void start(){

        //prints the game start prompt
        System.out.println("===== WELCOME TO THE ULTIMATE TIC-TAC-TOE GAME!! =====");

        //plays until the game is over
        do{

            //prints the board
            print();

            //switches player after each round
            switchPlayer();

            //prints out the current player
            System.out.println("Current player is: " + players[currentPlayerIndex].getMark());

            //if not the first move of the game
            if(selectedBoard != -1 || players[currentPlayerIndex] instanceof ComputerPlayer){

                //the selected board must be the square of the last move made
                selectedBoard = selectedSquare;

                //prints out the selected board
                System.out.println("Selected Board: " + selectedBoard);

                //shows all the possible legal moves
                System.out.println("List of possible legal moves: ");
                for (int i = 0; i < innerColSize * innerRowSize; i++){
                    if(squares[selectedBoard].boxes[i].isAvailable()) System.out.print(i + " ");
                }
            }

            do {

                //lets the player choose the board if its the first move of the game
                if(selectedBoard == -1){
                    selectedBoard = players[currentPlayerIndex].getBoard(gameColSize * gameRowSize);
                    System.out.println();
                }

                //if the current board is full, allow the player to choose the next board
                if(squares[selectedBoard].isFull()){
                    selectedBoard = players[currentPlayerIndex].getBoard(gameColSize * gameRowSize);
                }

                //lets the player choose a square value
                this.selectedSquare = players[currentPlayerIndex].selectValue(innerColSize * innerRowSize);

                //trys to make a move until it is successful
            } while(!squares[selectedBoard].makeMove(players[this.currentPlayerIndex].getMark(),
                    selectedSquare));

            //prints out the selected square
            System.out.println("Selected Square: " + selectedSquare);

            //if there is a winner on one of the inner boards, makes move on the game board
            if(isWinner(squares[selectedBoard])){
                board.makeMove(players[this.currentPlayerIndex].getMark(), selectedBoard);
            } else if(squares[selectedBoard].isFull()){

                //if there is a tie on an inner board, makes a "T" move on the game board
                board.makeMove("T", selectedBoard);
            }

            //prints the game board
            board.print();

        }while(!gameOver());

        //ends the game with a summary prompt if there is a winner or the board has filled
        if(isWinner(board))
            System.out.println(players[currentPlayerIndex].getName() + " has won!");
        else
            System.out.println("The game has ended in a draw!");
    }

    //checks if the given board has a winner
    private boolean isWinner(Board board) {

        //checks whether there is a solution in both the rows and columns
        if(checkRow(board) || checkCol(board)){
            return true;
        }

        //checks for a solution in the diagonals only if the game board is a square
        if(gameColSize == gameRowSize && (checkDiagLR(board) || checkDiagRL(board))){
            return true;
        }

        return false;
    }

    //checks the scores to see if there is a winner
    private boolean checkWinner(){

        //checks for a winner by comparing the scores to the win condition and resets scores
        if(scoreCounter[0] >= gameScoreToWin || scoreCounter[1] >= gameScoreToWin){

            //resets scores so that another call to checkWinner() will still have scores of 0.
            scoreCounter[0] = 0;
            scoreCounter[1] = 0;
            return true;

        } else {

            //resets scores so that another row/col can be checked
            scoreCounter[0] = 0;
            scoreCounter[1] = 0;
            return false;

        }
    }

    //checks the rows for a win
    private boolean checkRow(Board board){

        //checks each row
        for(int i = 0; i < gameRowSize; i++) {

            //checks each element of the row and increments it accordingly
            for (int j = 0; j < gameColSize; j++) {

                //breaks the for loop if a solution exists so the score doesnt get reset
                if(scoreCounter[0] == gameScoreToWin || scoreCounter[1] == gameScoreToWin)
                    break;

                if (board.getMark(i, j).equals(players[0].getMark())) {

                    //increments players score and resets score for other player to account for interrupted solutions
                    scoreCounter[0]++;
                    scoreCounter[1] = 0;

                } else if (board.getMark(i, j).equals(players[1].getMark())) {

                    //increments players score and resets score for other player to account for interrupted solutions
                    scoreCounter[1]++;
                    scoreCounter[0] = 0;

                } else {

                    //resets scores for both if space is available to account for spaces in solutions
                    scoreCounter[0] = 0;
                    scoreCounter[1] = 0;

                }
            }

            //checks the score to see if a player has won
            if (checkWinner())
                return true;
        }
        return false;
    }

    //checks the columns for a win
    private boolean checkCol(Board board){

        //checks each column
        for(int j = 0; j < gameColSize; j++){

            //checks each element of the col and increments score accordingly
            for(int i = 0; i < gameRowSize; i++){
                if(scoreCounter[0] >= gameScoreToWin || scoreCounter[1] >= gameScoreToWin)
                    break;
                if(board.getMark(i, j).equals(players[0].getMark())) {

                    //increments players score and resets score for other player to account for interrupted solutions
                    scoreCounter[0]++;
                    scoreCounter[1] = 0;

                } else if(board.getMark(i, j).equals(players[1].getMark())) {

                    //increments players score and resets score for other player to account for interrupted solutions
                    scoreCounter[1]++;
                    scoreCounter[0] = 0;

                } else {

                    //resets scores for both if space is available to account for spaces in solutions
                    scoreCounter[0] = 0;
                    scoreCounter[1] = 0;

                }
            }

            //checks the score to see if a player has won and resets score values
            if(checkWinner())
                return true;
        }
        return false;
    }

    //checks the LR diagonal for a win
    private boolean checkDiagLR(Board board){

        //increments score to check the diagonal
        for(int i = 0; i < gameRowSize; i++){
            if(board.getMark(i, i).equals(players[0].getMark())) {

                //increments players score and resets score for other player to account for interrupted solutions
                scoreCounter[0]++;
                scoreCounter[1] = 0;

            } else if(board.getMark(i, i).equals(players[1].getMark())) {

                //increments players score and resets score for other player to account for interrupted solutions
                scoreCounter[1]++;
                scoreCounter[0] = 0;

            } else {

                //resets scores for both if space is available to account for spaces in solutions
                scoreCounter[0] = 0;
                scoreCounter[1] = 0;
            }
        }

        //checks the score to see if a player has won and resets score values
        if(checkWinner())
            return true;
        else return false;
    }

    //checks the RL diagonal for a win
    private boolean checkDiagRL(Board board){

        //increments the score to check the diagonal
        for(int i = 0, j = gameColSize-1; i < gameRowSize; i++, j--){
            if(board.getMark(i, j).equals(players[0].getMark())){

                //increments players score and resets score for other player to account for interrupted solutions
                scoreCounter[0]++;
                scoreCounter[1] = 0;

            }else if(board.getMark(i, j).equals(players[1].getMark())) {

                //increments players score and resets score for other player to account for interrupted solutions
                scoreCounter[1]++;
                scoreCounter[0] = 0;

            } else {

                //resets scores for both if space is available to account for spaces in solutions
                scoreCounter[0] = 0;
                scoreCounter[1] = 0;
            }
        }

        //checks the score to see if a player has won and resets score values
        if(checkWinner())
            return true;
        else return false;
    }

    //checks if the game is over
    private boolean gameOver() {
        //checks if the board is full or if a winner has been determined
        if(board.isFull() || isWinner(board)) return true;

        //returns false if neither of the previous conditions were true
        else return false;
    }

    //switches to the next player
    public void switchPlayer(){
        if(currentPlayerIndex == -1 || currentPlayerIndex == 1) currentPlayerIndex = 0;
        else currentPlayerIndex = 1;
    }

    //prints all the inner boards
    public void print(){
        int x;
        System.out.println("-------------------------------------------------------------------------");
        for(int i = 0; i < gameColSize; i++){
           for(int j = 0; j <gameColSize; j++){
               System.out.print("|\t");
               for(int k = i*3; k < (i+1) * 3; k++){
                   if(isWinner(squares[k])) x = 1;
                   else x = 0;
                   squares[k].print(j, x);
                   System.out.print("\t|\t");
               }
               System.out.println();
           }
           System.out.println("-------------------------------------------------------------------------");
       }



        /*int row = 0;
        int gameRow = -1;
        boolean done = false;
        for(int i = 0; i < squares.length; i++){
            if(i != 0 && i % gameColSize == 0 && row < innerRowSize-1 && i != gameRow+1){
                System.out.println();
                row++;
                i = gameRow;
                continue;
            } else if(i != 0 && i%gameColSize == 0 && i != gameRow+1){
                System.out.println();
                row = 0;
                gameRow += gameColSize;
            }
            squares[i].print(row);
            if(i == squares.length-1 && i != gameRow && !done){
                System.out.println();
                row++;
                i = gameRow;
                if(row == 2) done = true;
            }
        }*/
    }


}
