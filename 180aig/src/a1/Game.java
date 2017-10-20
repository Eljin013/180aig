package a1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
	private Board board;
	private boolean humanFirst;
	private Scanner scan;
	private boolean gameOver;
	private Player winner;
	private final int INITIAL_SIZE = 20;
	private int MAXDEPTH = 4;

	//Testing variables
	private int makeMoveCount = 0;
	private int minCount = 0;
	private int maxCount = 0;
	
	
	public Game() {
		board = Board.getInstance(this);
		setGameOver(false);
		setWinner(Player.NONE);
		scan = new Scanner(System.in);
		runGameLoop(board);
	}
	
	private void runGameLoop(Board b) {
		System.out.println("\tI am Scruffles, the Master of Karate Chops!");
		System.out.println("\tWelcome to your demise!");
		askPlayer();
		b.displayBoard();
		OUTER_LOOP:
		for(int i = 0; ; i++) {
			if(!humanFirst) {
				System.out.println("\t\tRound " + (i+1));
				computerTurn(b);
				if(isGameOver(b))
					break OUTER_LOOP;
				humanTurn(b);
				if(isGameOver(b))
					break OUTER_LOOP;
			}  //if
			else {
				System.out.println("\t\tRound " + (i+1));
				humanTurn(b);
				if(isGameOver(b))
					break OUTER_LOOP;
				computerTurn(b);
				if(isGameOver(b))
					break OUTER_LOOP;
			}
		}  //for
		//Goes into as loop to keep the window open
		for(;;) {
			
		}
	}
	
	public void askPlayer() {
		System.out.println("\tCare to test your might first [1] or second [2]?");

		int input = scan.nextInt();
		if(input ==1)
			humanFirst = true;
		else
			humanFirst = false;
	}  //askPlayerInput()
	
	private void humanTurn(Board b) {
		ArrayList<String> hMoveList = new ArrayList<>(hMoveGen(b));
//		System.out.println("It's the human's turn.");
		if(hMoveList.isEmpty()) {
			setGameOver(true);
			setWinner(Player.COMPUTER);
		}
		else {
			//Display the possible Human moves
			outputPossMoves(hMoveList, Player.HUMAN);
			
			//Prompts the Human to enter a move
			String input;  												//Player input String
			boolean firstPass = true;  									//Control boolean for text output
			do{
				if(!firstPass)
					System.out.println("\tCan't do that.  Try again.");  	//Message after input mistake
				else
					System.out.println("\tGo ahead.  Make a move. [Not case sensitive.]");  	//Initial message for player input
				input = scan.next();  									//Takes in the players input
				makeUppercase(input);  									//Makes the player input upper-case
				firstPass = false;										//Changes the output control to false
			}while(!isValid(hMoveList, input));
	
			//Changes the String to an integer array
			int[] comInput = parseInput(input);
	
			//Update the board with the Humans move, clear the moveList, and display the board
			b.updateBoard(comInput, Player.HUMAN);
			b.displayBoard();
		}
	}  //humanTurn()
	
	private void computerTurn(Board b) {
		ArrayList<String> cMoveList = new ArrayList<>(cMoveGen(b));
//		System.out.println("It's the computer's turn.");
		if(cMoveList.isEmpty()) {
			setGameOver(true);
			setWinner(Player.HUMAN);
		}
		else {
			//Display the possible Computer moves
			outputPossMoves(cMoveList, Player.COMPUTER);
			
			makeMove(b);
//			Computer makes a random move
//			String cMove = new String();					//Computer input String
//			Random randNum = new Random();
//			int randMove = randNum.nextInt(moveList.size());
//			cMove = moveList.get(randMove);
//			String flipCMove = flipMove(cMove);
//			System.out.println("My move is " + cMove + " [" + flipCMove + "]");
				
			//Display the board.
			b.displayBoard();
		}  //else
	}  //computerTurn()
	
	private boolean isGameOver(Board b) {
		//Checks if game is over by captured King
		if(gameOver) {
			if(getWinner() == Player.HUMAN)
				System.out.println("You win this time...");
			else
				System.out.println("I am victorious!");
			return true;
		}
		else {	
//			System.out.println("Game is not over yet.");
			return false;
		}
	}  //isGameOver()



	
	private String moveString(int a, int b, int c, int d) {
		String move = null;
		if(a == 0) move = "A";
		else if(a == 1) move = "B";
		else if(a == 2) move = "C";
		else if(a == 3) move = "D";
		else if(a == 4) move = "E";
		else if(a == 5) move = "F";
		else if(a == 6) move = "G";
		move = move.concat(Integer.toString(8-b));
		if(c == 0) move = move.concat("A");
		else if(c == 1) move = move.concat("B");
		else if(c == 2) move = move.concat("C");
		else if(c == 3) move = move.concat("D");
		else if(c == 4) move = move.concat("E");
		else if(c == 5) move = move.concat("F");
		else if(c == 6) move = move.concat("G");
		move = move.concat(Integer.toString(8-d));
		//Prints out the Move for testing purposes
//		System.out.println(move);
		return move;
	}  //moveString
	
	private boolean isValid(ArrayList<String> ml, String m) {
		for(String pMove: ml) {
			if(pMove.equalsIgnoreCase(m))
				return true;
		}
		return false;
	}
	
	private String makeUppercase(String in) {
		String out = new String();
		for(int i = 0; i < 4; i++) {
			if(i == 0 || i == 2) {
				if(in.charAt(i) == 'a') out = out.concat("A");
				else if(in.charAt(i) == 'b') out = out.concat("B");
				else if(in.charAt(i) == 'c') out = out.concat("C");
				else if(in.charAt(i) == 'd') out = out.concat("D");
				else if(in.charAt(i) == 'e') out = out.concat("E");
				else if(in.charAt(i) == 'f') out = out.concat("F");
				else if(in.charAt(i) == 'g') out = out.concat("G");
			}
			else
				out = out.concat(String.valueOf(in.charAt(i)));
		}  //for
//		System.out.println(out);
		return out;
	}
	
	private String flipMove(String in) {
		String out = new String();
		for(int i = 0; i < 4; i++) {
			if(i == 0 || i == 2) {
				if(in.charAt(i) == 'A') out = out.concat("G");
				else if(in.charAt(i) == 'B') out = out.concat("F");
				else if(in.charAt(i) == 'C') out = out.concat("E");
				else if(in.charAt(i) == 'E') out = out.concat("C");
				else if(in.charAt(i) == 'F') out = out.concat("B");
				else if(in.charAt(i) == 'G') out = out.concat("A");
			}
			else
				if(in.charAt(i) == '1') out = out.concat("8");
				else if(in.charAt(i) == '2') out = out.concat("7");
				else if(in.charAt(i) == '3') out = out.concat("6");
				else if(in.charAt(i) == '4') out = out.concat("5");
				else if(in.charAt(i) == '5') out = out.concat("4");
				else if(in.charAt(i) == '6') out = out.concat("3");
				else if(in.charAt(i) == '7') out = out.concat("2");
				else if(in.charAt(i) == '8') out = out.concat("1");
		}  //for
//		System.out.println("[" + out + "]");
		return out;
	}
	
	private ArrayList<String> insertMove(ArrayList<String> ml, String s) {
		if(ml.size() >= INITIAL_SIZE) {
			int sizeInc = ml.size() + 5;
			ml.ensureCapacity(sizeInc);
		}
		ml.add(s);
		return ml;
	}  //insertMove()
	
	/*
	 * Method that displays the possible moves
	 */
	private void outputPossMoves(ArrayList<String> ml, Player p) {
		int count = 1;
		if(p == Player.HUMAN)
			System.out.println("\tThese are your possible moves:");
		for(String move : ml) {
			if(count % 6 == 0)
				System.out.println("\t" + move);
			else
				System.out.print("\t" + move);
			count++;
		}  //for
		System.out.println(" ");
	}
	
	/*
	 * Method returns the integer array equivalent of the move.
	 */
	private int[] parseInput(String in) {
		int[] out = new int[4];
		for(int i = 0; i < 4; i++) {
			if(in.charAt(i) == 'A' || in.charAt(i) == 'a' || in.charAt(i) == '8') out[i] = 0;
			else if(in.charAt(i) == 'B' || in.charAt(i) == 'b' || in.charAt(i) == '7') out[i] = 1;
			else if(in.charAt(i) == 'C' || in.charAt(i) == 'c' || in.charAt(i) == '6') out[i] = 2;
			else if(in.charAt(i) == 'D' || in.charAt(i) == 'd' || in.charAt(i) == '5') out[i] = 3;
			else if(in.charAt(i) == 'E' || in.charAt(i) == 'e' || in.charAt(i) == '4') out[i] = 4;
			else if(in.charAt(i) == 'F' || in.charAt(i) == 'f' || in.charAt(i) == '3') out[i] = 5;
			else if(in.charAt(i) == 'G' || in.charAt(i) == 'g' || in.charAt(i) == '2') out[i] = 6;
			else if(in.charAt(i) == '1') out[i] = 7;
		}  //for
		return out;
	}
	
	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public Player getWinner() {
		return winner;
	}
	
	public void setWinner(Player winner) {
		this.winner = winner;
	}
	
	/**
	 * 
	 * @param b
	 */
	private void makeMove(Board b) {
		MoveScore best = new MoveScore();			//best computer move
		best.setScore(-20000);						//best score
		MoveScore m = new MoveScore();				//current move
		
		GamePiece[][] bCopy = copyBoard(b.getPositions());	//state of Board
		ArrayList<String> ml = new ArrayList<>(cMoveGen(b));
		//Test of movelist 
//		System.out.println("Contents of movelist in MakeMove:");
//		outputPossMoves(ml, Player.COMPUTER);
		
		//Iterate through each Move
		for(String move: ml) {
			int depth = 0;
//			System.out.println("makeMove: " + makeMoveCount++ + " D: " + depth + " ml: " + ml.size());
			m.setMove(move);
			b.minimaxUpdate(parseInput(m.getMove()), Player.COMPUTER);
			m.setScore(min(b, depth+1));
			System.out.println("MakeMove: Score for " + m.getMove() + " is " + m.getScore());
//			b.displayBoard();
			if(m.getScore() > best.getScore())
				best = m;
			b.setPositions(bCopy);
			setGameOver(false);
			setWinner(Player.NONE);
		}
		setGameOver(false);
		setWinner(Player.NONE);
		String flipCMove = flipMove(best.getMove());
		System.out.println("My move is " + best.getMove() + " [" + flipCMove + "]");
		
		//Changes the String to an integer array
		int[] comInput = parseInput(best.getMove());
		b.updateBoard(comInput, Player.COMPUTER);
	}  //makeMove()
	
	/**
	 * 
	 * @param b
	 * @param d
	 * @return
	 */
	private int min(Board b, int d) {
//		System.out.println("Min: depth is " + d);
		MoveScore best = new MoveScore();			//best human move
		best.setScore(9999);						//best score
		MoveScore m = new MoveScore();				//current move
		
		GamePiece[][] bCopy = copyBoard(b.getPositions());
		ArrayList<String> mlMin = new ArrayList<>(hMoveGen(b));
//		System.out.println("Contents of movelist in Min:");
//		outputPossMoves(mlMin, Player.COMPUTER);
		
		if(isGameOver()) {
			if(getWinner() == Player.COMPUTER)
				return +999;
			else
				return -999;
		}  //if: gameOver
		else if(d == MAXDEPTH) {
//			System.out.print("inside min eval");
			return b.evaluate();  //reached the leaf node
		}  //if: depth
		else {
			for(String move : mlMin) {
//				System.out.println("min: " + minCount++ + " D: " + d + " ml: " + mlMin.size());
				m.setMove(move);
				b.minimaxUpdate(parseInput(m.getMove()), Player.HUMAN);
				m.setScore(max(b, d+1));
//				System.out.println("Min: Score for " + m.getMove() + " is " + m.getScore());
//				b.displayBoard();
				if(m.getScore() < best.getScore())
					best = m;
				b.setPositions(bCopy);
				setGameOver(false);
				setWinner(Player.NONE);
			}
			setGameOver(false);
			setWinner(Player.NONE);
			return best.getScore();
		}  //else
	}  //min()
	
	/**
	 * 
	 * @param b
	 * @param d
	 * @return
	 */
	private int max(Board b, int d) {
//		System.out.println("Max: depth is " + d);
		MoveScore best = new MoveScore();			//best computer move
		best.setScore(-9999);						//best score
		MoveScore m = new MoveScore();				//current move
		
		GamePiece[][] bCopy = copyBoard(b.getPositions());
		ArrayList<String> mlMax = new ArrayList<>(cMoveGen(b));
		
//		System.out.println("before for mlMax: " + mlMax.size());
		
		if(isGameOver()) {
			if(getWinner() == Player.COMPUTER)
				return +999;
			else
				return -999;
		}  //if: gameOver
		else if(d == MAXDEPTH) {
//			System.out.print("inside max eval");
			return b.evaluate();  //reached the leaf node
		}  //if: depth
		else {
			for(String move : mlMax) {
//				System.out.println("max: " + maxCount++ + " D: " + d + " ml: " + mlMax.size());
				m.setMove(move);
				b.minimaxUpdate(parseInput(m.getMove()), Player.COMPUTER);
				m.setScore(min(b, d+1));
//				System.out.println("Max: Score for " + m.getMove() + " is " + m.getScore());
//				b.displayBoard();
				if(m.getScore() > best.getScore()) {
					best = m;
				}
				b.setPositions(bCopy);
				setGameOver(false);
				setWinner(Player.NONE);
			}
			setGameOver(false);
			setWinner(Player.NONE);
			return best.getScore();
		}  //else
	}  //max()
	
	private GamePiece[][] copyBoard(GamePiece[][] copy) {
		GamePiece[][] pos = new GamePiece[8][7];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 7; j++) {
				pos[i][j] = copy[i][j];
			}
		}
		return pos;
	}
	
	private ArrayList<String> hMoveGen(Board b) {
		ArrayList<String> hMoves = new ArrayList<>();
		//Generates the moves only for the Human player
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 7; j++) {
				
				//checking  possible moves for Human: Mini Ninja
				if(b.getPositions()[i][j] == GamePiece.HUMAN_MINI_NINJA) {
					//check the upper left square
					if(!(i-1 < 0) && !(j-1 < 0) && b.getPositions()[i-1][j-1] == GamePiece.NONE)
						hMoves = insertMove(hMoves, moveString(j, i, j-1, i-1));
					//check the upper right square
					if(!(i-1 < 0) && !(j+1 > 6) && b.getPositions()[i-1][j+1] == GamePiece.NONE)
						hMoves = insertMove(hMoves, moveString(j, i, j+1, i-1));
					//check the lower left square and whether it can attack
					if(!(i+1 > 7) && !(j-1 < 0) && b.getPositions()[i+1][j-1] == GamePiece.NONE &&
							( b.getPositions()[i][j-1] == GamePiece.COMPUTER_KING || 
							  b.getPositions()[i][j-1] == GamePiece.COMPUTER_MINI_NINJA ||
							  b.getPositions()[i][j-1] == GamePiece.COMPUTER_MINI_SAMURAI ||
							  b.getPositions()[i][j-1] == GamePiece.COMPUTER_NINJA  ||
							  b.getPositions()[i][j-1] == GamePiece.COMPUTER_SAMURAI ) )
						hMoves = insertMove(hMoves, moveString(j, i, j-1, i+1));
					//check the lower right square and whether it can attack
					if(!(i+1 > 7) && !(j+1 > 6) && b.getPositions()[i+1][j+1] == GamePiece.NONE &&
							( b.getPositions()[i][j+1] == GamePiece.COMPUTER_KING || 
							  b.getPositions()[i][j+1] == GamePiece.COMPUTER_MINI_NINJA ||
							  b.getPositions()[i][j+1] == GamePiece.COMPUTER_MINI_SAMURAI ||
							  b.getPositions()[i][j+1] == GamePiece.COMPUTER_NINJA  ||
							  b.getPositions()[i][j+1] == GamePiece.COMPUTER_SAMURAI ) )
						hMoves = insertMove(hMoves, moveString(j, i, j+1, i+1));
				}  //Human: Mini Ninja check
				
				//checking  possible moves for Human: Mini Samurai
				if(b.getPositions()[i][j] == GamePiece.HUMAN_MINI_SAMURAI) {
					//check the square ahead
					if(!(i-1 < 0) && b.getPositions()[i-1][j] == GamePiece.NONE)
						hMoves = insertMove(hMoves, moveString(j, i, j, i-1));
					//check the left square
					if( !(i - 1 < 0) && !(j-1 < 0) && b.getPositions()[i][j-1] == GamePiece.NONE &&
							( b.getPositions()[i-1][j-1] == GamePiece.COMPUTER_KING || 
							  b.getPositions()[i-1][j-1] == GamePiece.COMPUTER_MINI_NINJA ||
							  b.getPositions()[i-1][j-1] == GamePiece.COMPUTER_MINI_SAMURAI ||
							  b.getPositions()[i-1][j-1] == GamePiece.COMPUTER_NINJA  ||
							  b.getPositions()[i-1][j-1] == GamePiece.COMPUTER_SAMURAI ) )
						hMoves = insertMove(hMoves, moveString(j, i, j-1, i));
					//check the right square
					if(!(i - 1 < 0) && !(j+1 > 6) && b.getPositions()[i][j+1] == GamePiece.NONE &&
							( b.getPositions()[i-1][j+1] == GamePiece.COMPUTER_KING || 
							  b.getPositions()[i-1][j+1] == GamePiece.COMPUTER_MINI_NINJA ||
							  b.getPositions()[i-1][j+1] == GamePiece.COMPUTER_MINI_SAMURAI ||
							  b.getPositions()[i-1][j+1] == GamePiece.COMPUTER_NINJA  ||
							  b.getPositions()[i-1][j+1] == GamePiece.COMPUTER_SAMURAI ) )
						hMoves = insertMove(hMoves, moveString(j, i, j+1, i));
				}  //Human: Mini Samurai check
				
				//checking  possible moves for Human: Ninja
				if(b.getPositions()[i][j] == GamePiece.HUMAN_NINJA) {
					//check the the upper left squares until something is in its path
					for(int z = 1; z < 6; z++) {
						if((i-z < 0) || (j-z < 0) || b.getPositions()[i-z][j-z] != GamePiece.NONE)
							break;
						else if(!(i-z < 0) && !(j-z < 0) && b.getPositions()[i-z][j-z] == GamePiece.NONE)
							hMoves = insertMove(hMoves, moveString(j, i, j-z, i-z));
					}  //for: upper left
					//check the upper right squares until something is in its path
					for(int z = 1; z < 6; z++) {
						if((i-z < 0) || (j+z > 6) || b.getPositions()[i-z][j+z] != GamePiece.NONE)
							break;
						if(!(i-z < 0) && !(j+z > 6) && b.getPositions()[i-z][j+z] == GamePiece.NONE )
							hMoves = insertMove(hMoves, moveString(j, i, j+z, i-z));
					}  //for: upper right
					//check the lower left squares until something is in its path and whether it can attack
					for(int z = 1; z < 6; z++) {
						if((i+z > 7) || (j-z < 0) || b.getPositions()[i+z][j-z] != GamePiece.NONE)
							break;
						else if(!(i+z > 7) && !(j-z < 0) && b.getPositions()[i+z][j-z] == GamePiece.NONE &&
								( b.getPositions()[i+z-1][j-z] == GamePiece.COMPUTER_KING || 
								  b.getPositions()[i+z-1][j-z] == GamePiece.COMPUTER_MINI_NINJA ||
								  b.getPositions()[i+z-1][j-z] == GamePiece.COMPUTER_MINI_SAMURAI ||
								  b.getPositions()[i+z-1][j-z] == GamePiece.COMPUTER_NINJA  ||
								  b.getPositions()[i+z-1][j-z] == GamePiece.COMPUTER_SAMURAI ) )
							hMoves = insertMove(hMoves, moveString(j, i, j-z, i+z));
					}  //for: lower left
					//check the lower right squares until something is in its path and whether it can attack
					for(int z = 1; z < 6; z++) {
						if((i+z > 7) || (j+z > 6) || b.getPositions()[i+z][j+z] != GamePiece.NONE)
							break;
						else if(!(i+z > 7) && !(j+z > 6) && b.getPositions()[i+z][j+z] == GamePiece.NONE &&
								( b.getPositions()[i+z-1][j+z] == GamePiece.COMPUTER_KING || 
								  b.getPositions()[i+z-1][j+z] == GamePiece.COMPUTER_MINI_NINJA ||
								  b.getPositions()[i+z-1][j+z] == GamePiece.COMPUTER_MINI_SAMURAI ||
								  b.getPositions()[i+z-1][j+z] == GamePiece.COMPUTER_NINJA  ||
								  b.getPositions()[i+z-1][j+z] == GamePiece.COMPUTER_SAMURAI ) )
							hMoves = insertMove(hMoves, moveString(j, i, j+z, i+z));
					}  //for: lower right
				}  //Human: Ninja check
				
				//checking  possible moves for Human: Samurai
				if(b.getPositions()[i][j] == GamePiece.HUMAN_SAMURAI) {
					//check the forward squares
					for(int z = 1; z < 6; z++) {
						if((i-z <0) || b.getPositions()[i-z][j] != GamePiece.NONE)
							break;
						if(!(i-z < 0) && b.getPositions()[i-z][j] == GamePiece.NONE)
							hMoves = insertMove(hMoves, moveString(j, i, j, i-z));
					}  //for: forward squares
					//check the left squares until something is in its path and whther it can attack
					for(int z = 1; z < 6; z++) {
						if((i-1 < 0) || (j-z < 0) || b.getPositions()[i][j-z] != GamePiece.NONE)
							break;
						else if(!(i-1 < 0) && !(j-z < 0) && b.getPositions()[i][j-z] == GamePiece.NONE &&
								( b.getPositions()[i-1][j-z] == GamePiece.COMPUTER_KING || 
								  b.getPositions()[i-1][j-z] == GamePiece.COMPUTER_MINI_NINJA ||
								  b.getPositions()[i-1][j-z] == GamePiece.COMPUTER_MINI_SAMURAI ||
								  b.getPositions()[i-1][j-z] == GamePiece.COMPUTER_NINJA  ||
								  b.getPositions()[i-1][j-z] == GamePiece.COMPUTER_SAMURAI ) )
							hMoves = insertMove(hMoves, moveString(j, i, j-z, i));
					}  //for: left
					//check the right squares until something is in its path and whether it can attack
					for(int z = 1; z < 6; z++) {
						if((i-1 < 0) || (j+z > 6) || b.getPositions()[i][j+z] != GamePiece.NONE)
							break;
						else if(!(i-1 < 0) && !(j+z > 6) && b.getPositions()[i][j+z] == GamePiece.NONE &&
								( b.getPositions()[i-1][j+z] == GamePiece.COMPUTER_KING || 
								  b.getPositions()[i-1][j+z] == GamePiece.COMPUTER_MINI_NINJA ||
								  b.getPositions()[i-1][j+z] == GamePiece.COMPUTER_MINI_SAMURAI ||
								  b.getPositions()[i-1][j+z] == GamePiece.COMPUTER_NINJA  ||
								  b.getPositions()[i-1][j+z] == GamePiece.COMPUTER_SAMURAI ) )
							hMoves = insertMove(hMoves, moveString(j, i, j+z, i));
					}  //for: left
				}  //Human: Samurai check
				
			}  //HUMAN for(j)
		}  //HUMAN for(i)
		return hMoves;
	}  //hMoveGen()
		
	private ArrayList<String> cMoveGen(Board b) {
		ArrayList<String> cMoves = new ArrayList<>();
		//Generates the moves only for the Computer
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 7; j++) {
				
				//checking  possible moves for Computer: Mini Ninja
				if(b.getPositions()[i][j] == GamePiece.COMPUTER_MINI_NINJA) {
					//check the lower left square
					if(!(i+1 > 7) && !(j-1 < 0) && b.getPositions()[i+1][j-1] == GamePiece.NONE)
						cMoves = insertMove(cMoves, moveString(j, i, j-1, i+1));
					//check the lower right square
					if(!(i+1 > 7) && !(j+1 > 6) && b.getPositions()[i+1][j+1] == GamePiece.NONE)
						cMoves = insertMove(cMoves, moveString(j, i, j+1, i+1));
					//check the lower left square and whether it can attack
					if(!(i-1 < 0) && !(j-1 < 0) && b.getPositions()[i-1][j-1] == GamePiece.NONE &&
							( b.getPositions()[i][j-1] == GamePiece.HUMAN_KING || 
							  b.getPositions()[i][j-1] == GamePiece.HUMAN_MINI_NINJA ||
							  b.getPositions()[i][j-1] == GamePiece.HUMAN_MINI_SAMURAI ||
							  b.getPositions()[i][j-1] == GamePiece.HUMAN_NINJA  ||
							  b.getPositions()[i][j-1] == GamePiece.HUMAN_SAMURAI ) )
						cMoves = insertMove(cMoves, moveString(j, i, j-1, i-1));
					//check the lower right square and whether it can attack
					if(!(i-1 > 7) && !(j+1 > 6) && b.getPositions()[i-1][j+1] == GamePiece.NONE &&
							( b.getPositions()[i][j+1] == GamePiece.HUMAN_KING || 
							  b.getPositions()[i][j+1] == GamePiece.HUMAN_MINI_NINJA ||
							  b.getPositions()[i][j+1] == GamePiece.HUMAN_MINI_SAMURAI ||
							  b.getPositions()[i][j+1] == GamePiece.HUMAN_NINJA  ||
							  b.getPositions()[i][j+1] == GamePiece.HUMAN_SAMURAI ) )
						cMoves = insertMove(cMoves, moveString(j, i, j+1, i-1));
				}  //Computer: Mini Ninja check
				
				//checking  possible moves for Computer: Mini Samurai
				if(b.getPositions()[i][j] == GamePiece.COMPUTER_MINI_SAMURAI) {
					//check the square below
					if(!(i+1 > 7) && !(j-1 < 0) && b.getPositions()[i+1][j] == GamePiece.NONE)
						cMoves = insertMove(cMoves, moveString(j, i, j, i+1));
					//check left square and whether it can attack
					if(!(i+1 > 7) && !(j-1 < 0) && b.getPositions()[i][j-1] == GamePiece.NONE &&
							( b.getPositions()[i+1][j-1] == GamePiece.HUMAN_KING || 
							  b.getPositions()[i+1][j-1] == GamePiece.HUMAN_MINI_NINJA ||
							  b.getPositions()[i+1][j-1] == GamePiece.HUMAN_MINI_SAMURAI ||
							  b.getPositions()[i+1][j-1] == GamePiece.HUMAN_NINJA  ||
							  b.getPositions()[i+1][j-1] == GamePiece.HUMAN_SAMURAI ) )
						cMoves = insertMove(cMoves, moveString(j, i, j-1, i));
					//check the right square and whether it can attack
					if(!(i+1 > 7) && !(j+1 > 6) && b.getPositions()[i][j+1] == GamePiece.NONE &&
							( b.getPositions()[i+1][j+1] == GamePiece.HUMAN_KING || 
							  b.getPositions()[i+1][j+1] == GamePiece.HUMAN_MINI_NINJA ||
							  b.getPositions()[i+1][j+1] == GamePiece.HUMAN_MINI_SAMURAI ||
							  b.getPositions()[i+1][j+1] == GamePiece.HUMAN_NINJA  ||
							  b.getPositions()[i+1][j+1] == GamePiece.HUMAN_SAMURAI ) )
						cMoves = insertMove(cMoves, moveString(j, i, j+1, i));
				}  //Computer: Mini Samurai check
				
				//checking  possible moves for Computer: Ninja
				if(b.getPositions()[i][j] == GamePiece.COMPUTER_NINJA) {
					//check the the lower left squares until something is in path
					for(int z = 1; z < 6; z++) {
						if((i+z > 6) || (j-z < 0) || b.getPositions()[i+z][j-z] != GamePiece.NONE)
							break;
						else if(!(i+z > 7) && !(j-z < 0) && b.getPositions()[i+z][j-z] == GamePiece.NONE)
							cMoves = insertMove(cMoves, moveString(j, i, j-z, i+z));
					}  //for
					//check the lower right squares until something is in path
					for(int z = 1; z < 6; z++) {
						if((i+z > 6) || (j+z > 6) || b.getPositions()[i+z][j+z] != GamePiece.NONE)
							break;
						if(!(i+z < 0) && !(j+z > 6) && b.getPositions()[i+z][j+z] == GamePiece.NONE )
							cMoves = insertMove(cMoves, moveString(j, i, j+z, i+z));
					}  //for
					//check the upper left squares until something is in its path
					//and whether it can attack
					for(int z = 1; z < 6; z++) {
						if((i-z < 0) || (j-z < 0) || b.getPositions()[i-z][j-z] != GamePiece.NONE)
							break;
						else if(!(i-z < 0) && !(j-z < 0) && b.getPositions()[i-z][j-z] == GamePiece.NONE &&
								( b.getPositions()[i-z+1][j-z] == GamePiece.HUMAN_KING || 
								  b.getPositions()[i-z+1][j-z] == GamePiece.HUMAN_MINI_NINJA ||
								  b.getPositions()[i-z+1][j-z] == GamePiece.HUMAN_MINI_SAMURAI ||
								  b.getPositions()[i-z+1][j-z] == GamePiece.HUMAN_NINJA  ||
								  b.getPositions()[i-z+1][j-z] == GamePiece.HUMAN_SAMURAI ) )
							cMoves = insertMove(cMoves, moveString(j, i, j-z, i-z));
					}  //for: upper left
					//check the upper right squares until something is in its path
					//and whether it can attack
					for(int z = 1; z < 6; z++) {
						if((i+z > 7) || (j-z < 0) || b.getPositions()[i+z][j-z] != GamePiece.NONE)
							break;
						else if(!(i-z < 0) && !(j+z > 6) && b.getPositions()[i+z][j+z] == GamePiece.NONE &&
								( b.getPositions()[i-z+1][j+z] == GamePiece.HUMAN_KING || 
								  b.getPositions()[i-z+1][j+z] == GamePiece.HUMAN_MINI_NINJA ||
								  b.getPositions()[i-z+1][j+z] == GamePiece.HUMAN_MINI_SAMURAI ||
								  b.getPositions()[i-z+1][j+z] == GamePiece.HUMAN_NINJA  ||
								  b.getPositions()[i-z+1][j+z] == GamePiece.HUMAN_SAMURAI ) )
							cMoves = insertMove(cMoves, moveString(j, i, j+z, i-z));
					}  //for: upper right
				}  //Computer: Ninja check
				
				//checking  possible moves for Computer: Samurai
				if(b.getPositions()[i][j] == GamePiece.COMPUTER_SAMURAI) {
					//check the squares ahead
					for(int z = 1; z < 6; z++) {
						if((i+z > 7) || b.getPositions()[i+z][j] != GamePiece.NONE)
							break;
						if(!(i+z > 7) && b.getPositions()[i+z][j] == GamePiece.NONE)
							cMoves = insertMove(cMoves, moveString(j, i, j, i+z));
					}  //for
					//check the left squares until something is in its path and whther it can attack
					for(int z = 1; z < 6; z++) {
						if((i+1 > 7) || (j-z < 0) || b.getPositions()[i][j-z] != GamePiece.NONE)
							break;
						else if(!(j-z < 0) && b.getPositions()[i][j-z] == GamePiece.NONE &&
								( b.getPositions()[i+1][j-z] == GamePiece.HUMAN_KING || 
								  b.getPositions()[i+1][j-z] == GamePiece.HUMAN_MINI_NINJA ||
								  b.getPositions()[i+1][j-z] == GamePiece.HUMAN_MINI_SAMURAI ||
								  b.getPositions()[i+1][j-z] == GamePiece.HUMAN_NINJA  ||
								  b.getPositions()[i+1][j-z] == GamePiece.HUMAN_SAMURAI ) )
							cMoves = insertMove(cMoves, moveString(j, i, j-z, i));
					}  //for: left
					//check the right squares until something is in its path and whether it can attack
					for(int z = 1; z < 6; z++) {
						if((i+1 > 7) || (j+z > 6) || b.getPositions()[i][j+z] != GamePiece.NONE)
							break;
						else if(!(i+1 > 7) && !(j+z > 6) && b.getPositions()[i][j+z] == GamePiece.NONE &&
								( b.getPositions()[i+1][j+z] == GamePiece.HUMAN_KING || 
								  b.getPositions()[i+1][j+z] == GamePiece.HUMAN_MINI_NINJA ||
								  b.getPositions()[i+1][j+z] == GamePiece.HUMAN_MINI_SAMURAI ||
								  b.getPositions()[i+1][j+z] == GamePiece.HUMAN_NINJA  ||
								  b.getPositions()[i+1][j+z] == GamePiece.HUMAN_SAMURAI ) )
							cMoves = insertMove(cMoves, moveString(j, i, j+z, i));
					}  //for: left
				}  //Computer: Samurai check
				
			}  //COMPUTER for(j)
		}  //COMPUTER for(i)
		return cMoves;
	}  //moveGenerator()
}
