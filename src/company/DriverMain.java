package company;/*
*
Hamzah Dweik - HAD190000
CS2336.0W1 - Spring 2021

Analysis: Ultimate Tic Tac Toe is a game played similarly to the original Tic Tac Toe, with a slightly
greater complexity however. The game board contains 9 boxes, each with 9 boxes of their own. This can
be thought of as a board with 9 smaller tic tac toe boards in each box. There are two players, each with
their own mark, similarly to tic tac toe. The first player gets to choose which board they would like to start
with (of the 9 smaller boards on the main board). Once a board has been selected, the player is allowed to
choose a square on the current board. This square will be marked with the signature of that player. It is
now the turn of the second player. Unlike the first player, the second player does not get to choose a board,
but instead has to play on the board of the last players chosen square. For example, if player 1 chose square
4 on the board of his choice, player 2 must now play on board 4 with a square of his choice. Player 1 will have
to play this way as well after the first move. When a player has won on a specific board (by getting three in a
row), they are essentially marking their signature on that board. For a player to win the entire game, they need
to win 3 sequential boards on the main board. If a player encounters a full board, they are allowed to choose the
board of their next move. If there is a tie on any of the boards, neither player gets that board and it gets marked
as "T". The players can tie if neither of them manages to win three sequential boards and the game ends with a draw
once the board is filled.

Design: To begin, we need to break down the game into its smaller constituents. We would need 2 players, each of which
could be either a human player or a computer player. This gives us the opportunity to use an interface. We will need
a class for both human player and computer player as well. We will also need to break down the board a bit. The main
board would be composed of 9 smaller classical tic tac toe boards, each with 9 boxes themselves. We can create two classes
out of this: one for the box of a board, and another for the board itself. We can implement the main board as an array
of smaller boards. Now we can begin by working on the UltimateTTTGame class. Right off the bat, the game should have
a name, dimensions, a player array, a main board, a inner board array, selection variables, and a score counter. We
wil also need methods to initialize the players and the boards of the game. We will need another method to print the
game board after each move is made. At the same time, we will need to switch players after each move and this too can
be implemented with a method. With all that in place, we can finally work on the creation of a start method. This method
will start the game and continually play until the game is over - which of course we will need to be able to test for using
a boolean method. The game can end in two ways: either the main board fills up, or a winner has been established on the
main board. If there is a winner, we need to be print the winner out to screen. However if the board fills up with no
winner, then the game is a tie. We can check for a winner by checking the rows, columns, and diagonals of each board
and incrementing the score counter. If a winner is found on any board, we can make a move on the main board in its position.
A player will need to be able to choose a board value as well as a square value. This can be handled in the interface
and overridden in the human and computer player classes. We will show the current players turn and the selected board
and square to minimize confusion and keep track of inputs. We will also exception handle the inputs of the user to only
allow integers in the range of 0-8. The user will receive an error prompt if they try to input anything else. We will also
integrate a list of all possible legal moves for the player to choose from. Overall, this game will be able to be played
by both computers and humans, in any combination.

Test: We can test this game in a few different ways to handle all the cases. We can begin by testing the game with two
computer players to see if the game will even finish at all. We need to ensure that everything is working properly so
we can check to see that there are cases of wins for each player, as well as draws. Next we can test the individual
move making and exception handling of the program by playing as a human. We can try to put characters and out-of-range
numbers to see how the program handles it. We can also visually verify that the program is doing what it is intended.
If all goes well, then the program can be considered well-tested.
* */

public class DriverMain {
    public static void main(String[] args){

        //establishes an UltimateTTTGame
        UltimateTTTGame game = new UltimateTTTGame();

        //sets the players of the game
        game.setPlayers(new ComputerPlayer("Player1", "X"), new ComputerPlayer("Player2", "O"));

        //begins the game
        game.start();
    }

}
