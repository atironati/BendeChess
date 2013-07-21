package chess;

import java.util.*;

public class BendeChess {

    public static void main(String[] args) {

	BendeChess game = new BendeChess();
	game.gameLoop();
    }

    Board board;
    uciCom com;
    MoveGenerator moveGen;

    public BendeChess() {

	com = new uciCom();
	board = new Board();
	moveGen = new MoveGenerator();

    }
    
    /* this function is the most important loop.
     * this is where we listen for command and 
     * act accordingly.
     */
    public void gameLoop() {

	while (true) {
	    Command nextCmd = com.getCommand();
	    handleCommand(nextCmd);
	}
    }


    private boolean handleCommand(Command cmd) {
	if (cmd.topLevelCmd.equals("null")) {
	    return false;
	}
	if (cmd.topLevelCmd.equals("quit")) {
	    System.out.println("Thanks for playing BendeChess");
	    System.exit(0);
	}
	if (cmd.topLevelCmd.equals("go")) {
	    board.printBoard();
	}
	if (cmd.topLevelCmd.equals("position")) {
	    board.executeMove(cmd.gameHistory.peek());
	    board.printBoard();
	    board.printHistory();
	    ArrayList<Board> nexts = moveGen.nextMoves(board);
	    System.out.println(nexts.get(0).getLastMove());
	    if (!nexts.isEmpty()) {
		board.copy(nexts.get(0));
		board.printBoard();
		board.printHistory();
	    }
	}


	return true;
    }
}