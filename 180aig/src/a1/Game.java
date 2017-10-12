package a1;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	private Board board;
	private boolean humanFirst;
	private Scanner scan;
	
	private String possMove;
	private int listCapacity = 10;
	private ArrayList<String> moveList = new ArrayList<String>(listCapacity);

	//Constants
	final private int HUMAN = 111;
	final private int COMPUTER = 222;

	public Game() {
		board = Board.getInstance();
		scan = new Scanner(System.in);
		runGameLoop(board);
	}
	
	private void runGameLoop(Board b) {
		System.out.println("\tHello! My name is Scruffles, the Master of Karate Chops!");
		askPlayer();
		b.displayBoard();
		for(int i = 0; i < 1; i++) {
			if(!humanFirst) {
				System.out.println("\t\tRound " + (i+1));
				moveGenerator(b, COMPUTER);
				computerTurn(b);
				isGameOver(b, COMPUTER);
				moveGenerator(b, HUMAN);
				humanTurn(b);
				isGameOver(b, HUMAN);
			}  //if
			else {
				System.out.println("\t\tRound " + (i+1));
				moveGenerator(b, HUMAN);
				humanTurn(b);
				isGameOver(b, HUMAN);
				moveGenerator(b, COMPUTER);
				computerTurn(b);
				isGameOver(b, COMPUTER);
			}
		}  //for
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
		System.out.println("It's the human's turn.");
		//display the possible moves
		outputPossMoves(HUMAN);
		//Human takes turn
		String input;
		do{
			System.out.println("\tGo ahead.  Make a move.");
			input = scan.next();
			makeUppercase(input);
		}while(!isValid(input));

		
//		int[] comInput = parseInput(input);

		
		//update the board
		b.updateBoard();

		//clear the moveList
		moveList.clear();
		b.displayBoard();
	}  //humanTurn()
	
	private void computerTurn(Board b) {
		System.out.println("It's the computer's turn.");
		//display the possible moves
		outputPossMoves(COMPUTER);

		//Computer takes turn
		//update the board
		b.updateBoard();

		//clear the moveList
		moveList.clear();
		b.displayBoard();
	}  //computerTurn()
	
	private void isGameOver(Board b, int p) {
		System.out.println("Game is not over yet.");
	}  //isGameOver()


	private ArrayList<String> moveGenerator(Board b, int p) {
		//Generates the moves only for the Human player
		if(p == HUMAN) {
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 7; j++) {
					
					//checking  possible moves for Human: Mini Ninja
					if(b.getPositions()[i][j] == GamePiece.HUMAN_MINI_NINJA) {
						//check the upper left square
						if(!(i-1 < 0) && !(j-1 < 0) && b.getPositions()[i-1][j-1] == GamePiece.NONE)
							insertMove(moveString(j, i, j-1, i-1));
						//check the upper right square
						if(!(i-1 < 0) && !(j+1 > 6) && b.getPositions()[i-1][j+1] == GamePiece.NONE)
							insertMove(moveString(j, i, j+1, i-1));
						//check the lower left square and whether it can attack
						if(!(i+1 > 7) && !(j-1 < 0) && b.getPositions()[i+1][j-1] == GamePiece.NONE &&
								( b.getPositions()[i][j-1] == GamePiece.COMPUTER_KING || 
								  b.getPositions()[i][j-1] == GamePiece.COMPUTER_MINI_NINJA ||
								  b.getPositions()[i][j-1] == GamePiece.COMPUTER_MINI_SAMURAI ||
								  b.getPositions()[i][j-1] == GamePiece.COMPUTER_NINJA  ||
								  b.getPositions()[i][j-1] == GamePiece.COMPUTER_SAMURAI ) )
							insertMove(moveString(j, i, j-1, i+1));
						//check the lower right square and whether it can attack
						if(!(i+1 > 7) && !(j+1 > 6) && b.getPositions()[i+1][j+1] == GamePiece.NONE &&
								( b.getPositions()[i][j+1] == GamePiece.COMPUTER_KING || 
								  b.getPositions()[i][j+1] == GamePiece.COMPUTER_MINI_NINJA ||
								  b.getPositions()[i][j+1] == GamePiece.COMPUTER_MINI_SAMURAI ||
								  b.getPositions()[i][j+1] == GamePiece.COMPUTER_NINJA  ||
								  b.getPositions()[i][j+1] == GamePiece.COMPUTER_SAMURAI ) )
							insertMove(moveString(j, i, j+1, i+1));
					}  //Human: Mini Ninja check
					
					//checking  possible moves for Human: Mini Samurai
					if(b.getPositions()[i][j] == GamePiece.HUMAN_MINI_SAMURAI) {
						//check the square ahead
						if(!(i-1 < 0) && b.getPositions()[i-1][j] == GamePiece.NONE)
							insertMove(moveString(j, i, j, i-1));
						//check the left square
						if(!(j-1 < 0) && b.getPositions()[i][j-1] == GamePiece.NONE &&
								( b.getPositions()[i-1][j-1] == GamePiece.COMPUTER_KING || 
								  b.getPositions()[i-1][j-1] == GamePiece.COMPUTER_MINI_NINJA ||
								  b.getPositions()[i-1][j-1] == GamePiece.COMPUTER_MINI_SAMURAI ||
								  b.getPositions()[i-1][j-1] == GamePiece.COMPUTER_NINJA  ||
								  b.getPositions()[i-1][j-1] == GamePiece.COMPUTER_SAMURAI ) )
							insertMove(moveString(j, i, j-1, i));
						//check the right square
						if(!(j+1 > 6) && b.getPositions()[i][j+1] == GamePiece.NONE &&
								( b.getPositions()[i-1][j+1] == GamePiece.COMPUTER_KING || 
								  b.getPositions()[i-1][j+1] == GamePiece.COMPUTER_MINI_NINJA ||
								  b.getPositions()[i-1][j+1] == GamePiece.COMPUTER_MINI_SAMURAI ||
								  b.getPositions()[i-1][j+1] == GamePiece.COMPUTER_NINJA  ||
								  b.getPositions()[i-1][j+1] == GamePiece.COMPUTER_SAMURAI ) )
							insertMove(moveString(j, i, j+1, i));
					}  //Human: Mini Samurai check
					
					//checking  possible moves for Human: Ninja
					if(b.getPositions()[i][j] == GamePiece.HUMAN_NINJA) {
						//check the the upper left squares until something is in its path
						for(int z = 1; z < 6; z++) {
							if((i-z < 0) || (j-z < 0) || b.getPositions()[i-z][j-z] != GamePiece.NONE)
								break;
							else if(!(i-z < 0) && !(j-z < 0) && b.getPositions()[i-z][j-z] == GamePiece.NONE)
								insertMove(moveString(j, i, j-z, i-z));
						}  //for: upper left
						//check the upper right squares until something is in its path
						for(int z = 1; z < 6; z++) {
							if((i-z < 0) || (j+z > 6) || b.getPositions()[i-z][j] != GamePiece.NONE)
								break;
							if(b.getPositions()[i+z][j+z] == GamePiece.NONE && !(i-z < 0) && !(j+z > 6))
								insertMove(moveString(j, i, j+z, i-z));
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
								insertMove(moveString(j, i, j-z, i+z));
						}  //for: lower left
						//check the lower right squares until something is in its path and whether it can attack
						for(int z = 1; z < 6; z++) {
							if((i+z > 7) || (j+z > 6) || b.getPositions()[i+z][j-z] != GamePiece.NONE)
								break;
							else if(!(i+z > 7) && !(j+z > 6) && b.getPositions()[i+z][j+z] == GamePiece.NONE &&
									( b.getPositions()[i+z-1][j+z] == GamePiece.COMPUTER_KING || 
									  b.getPositions()[i+z-1][j+z] == GamePiece.COMPUTER_MINI_NINJA ||
									  b.getPositions()[i+z-1][j+z] == GamePiece.COMPUTER_MINI_SAMURAI ||
									  b.getPositions()[i+z-1][j+z] == GamePiece.COMPUTER_NINJA  ||
									  b.getPositions()[i+z-1][j+z] == GamePiece.COMPUTER_SAMURAI ) )
								insertMove(moveString(j, i, j+z, i+z));
						}  //for: lower right
					}  //Human: Ninja check
					
					//checking  possible moves for Human: Samurai
					if(b.getPositions()[i][j] == GamePiece.HUMAN_SAMURAI) {
						//check the forward squares
						for(int z = 1; z < 6; z++) {
							if((i-z <0) || b.getPositions()[i-z][j] != GamePiece.NONE)
								break;
							if(!(i-1 < 0) && b.getPositions()[i-z][j] == GamePiece.NONE)
								insertMove(moveString(j, i, j, i-z));
						}  //for: forward squares
						//check the left squares until something is in its path and whther it can attack
						for(int z = 1; z < 6; z++) {
							if((i-1 < 0) || (j-z < 0) || b.getPositions()[i][j-z] != GamePiece.NONE)
								break;
							else if(!(j-z < 0) && b.getPositions()[i][j-z] == GamePiece.NONE &&
									( b.getPositions()[i-1][j-z] == GamePiece.COMPUTER_KING || 
									  b.getPositions()[i-1][j-z] == GamePiece.COMPUTER_MINI_NINJA ||
									  b.getPositions()[i-1][j-z] == GamePiece.COMPUTER_MINI_SAMURAI ||
									  b.getPositions()[i-1][j-z] == GamePiece.COMPUTER_NINJA  ||
									  b.getPositions()[i-1][j-z] == GamePiece.COMPUTER_SAMURAI ) )
								insertMove(moveString(j, i, j-z, i));
						}  //for: left
						//check the right squares until something is in its path and whether it can attack
						for(int z = 1; z < 6; z++) {
							if((i-1 < 0) || (j+z > 6) || b.getPositions()[i][j+z] != GamePiece.NONE)
								break;
							else if(!(j+z > 6) && b.getPositions()[i][j+z] == GamePiece.NONE &&
									( b.getPositions()[i-1][j+z] == GamePiece.COMPUTER_KING || 
									  b.getPositions()[i-1][j+z] == GamePiece.COMPUTER_MINI_NINJA ||
									  b.getPositions()[i-1][j+z] == GamePiece.COMPUTER_MINI_SAMURAI ||
									  b.getPositions()[i-1][j+z] == GamePiece.COMPUTER_NINJA  ||
									  b.getPositions()[i-1][j+z] == GamePiece.COMPUTER_SAMURAI ) )
								insertMove(moveString(j, i, j+z, i));
						}  //for: left
					}  //Human: Samurai check
					
				}  //HUMAN for(j)
			}  //HUMAN for(i)
		}  //HUMAN if
		
		//Generates the moves only for the Computer
		if(p == COMPUTER) {
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 7; j++) {
					
					//checking  possible moves for Computer: Mini Ninja
					if(b.getPositions()[i][j] == GamePiece.COMPUTER_MINI_NINJA) {
						//check the lower left square
						if(!(i+1 > 7) && !(j-1 < 0) && b.getPositions()[i+1][j-1] == GamePiece.NONE)
							insertMove(moveString(j, i, j-1, i+1));
						//check the lower right square
						if(!(i+1 > 7) && !(j+1 > 6) && b.getPositions()[i+1][j+1] == GamePiece.NONE)
							insertMove(moveString(j, i, j+1, i+1));
						//check the lower left square and whether it can attack
						if(!(i-1 > 7) && !(j-1 < 0) && b.getPositions()[i-1][j-1] == GamePiece.NONE &&
								( b.getPositions()[i][j-1] == GamePiece.HUMAN_KING || 
								  b.getPositions()[i][j-1] == GamePiece.HUMAN_MINI_NINJA ||
								  b.getPositions()[i][j-1] == GamePiece.HUMAN_MINI_SAMURAI ||
								  b.getPositions()[i][j-1] == GamePiece.HUMAN_NINJA  ||
								  b.getPositions()[i][j-1] == GamePiece.HUMAN_SAMURAI ) )
							insertMove(moveString(j, i, j-1, i-1));
						//check the lower right square and whether it can attack
						if(!(i-1 > 7) && !(j+1 > 6) && b.getPositions()[i-1][j+1] == GamePiece.NONE &&
								( b.getPositions()[i][j+1] == GamePiece.HUMAN_KING || 
								  b.getPositions()[i][j+1] == GamePiece.HUMAN_MINI_NINJA ||
								  b.getPositions()[i][j+1] == GamePiece.HUMAN_MINI_SAMURAI ||
								  b.getPositions()[i][j+1] == GamePiece.HUMAN_NINJA  ||
								  b.getPositions()[i][j+1] == GamePiece.HUMAN_SAMURAI ) )
							insertMove(moveString(j, i, j+1, i-1));
					}  //Computer: Mini Ninja check
					
					//checking  possible moves for Computer: Mini Samurai
					if(b.getPositions()[i][j] == GamePiece.COMPUTER_MINI_SAMURAI) {
						//check the square below
						if(!(i+1 > 7) && b.getPositions()[i+1][j] == GamePiece.NONE)
							insertMove(moveString(j, i, j, i+1));
						//check left square and whether it can attack
						if(!(j-1 < 0) && b.getPositions()[i][j-1] == GamePiece.NONE &&
								( b.getPositions()[i+1][j-1] == GamePiece.HUMAN_KING || 
								  b.getPositions()[i+1][j-1] == GamePiece.HUMAN_MINI_NINJA ||
								  b.getPositions()[i+1][j-1] == GamePiece.HUMAN_MINI_SAMURAI ||
								  b.getPositions()[i+1][j-1] == GamePiece.HUMAN_NINJA  ||
								  b.getPositions()[i+1][j-1] == GamePiece.HUMAN_SAMURAI ) )
							insertMove(moveString(j, i, j-1, i));
						//check the right square and whether it can attack
						if(!(j+1 > 6) && b.getPositions()[i][j+1] == GamePiece.NONE &&
								( b.getPositions()[i+1][j+1] == GamePiece.HUMAN_KING || 
								  b.getPositions()[i+1][j+1] == GamePiece.HUMAN_MINI_NINJA ||
								  b.getPositions()[i+1][j+1] == GamePiece.HUMAN_MINI_SAMURAI ||
								  b.getPositions()[i+1][j+1] == GamePiece.HUMAN_NINJA  ||
								  b.getPositions()[i+1][j+1] == GamePiece.HUMAN_SAMURAI ) )
							insertMove(moveString(j, i, j+1, i));
					}  //Computer: Mini Samurai check
					
					//checking  possible moves for Computer: Ninja
					if(b.getPositions()[i][j] == GamePiece.COMPUTER_NINJA) {
						//check the the lower left squares until something is in path
						for(int z = 1; z < 6; z++) {
							if((i+z > 6) || (j-z < 0) || b.getPositions()[i+z][j-z] != GamePiece.NONE)
								break;
							else if(!(i+z > 7) && !(j-z < 0) && b.getPositions()[i+z][j-z] == GamePiece.NONE)
								insertMove(moveString(j, i, j-z, i+z));
						}  //for
						//check the lower right squares until something is in path
						for(int z = 1; z < 6; z++) {
							if((i+z > 6) || (j+z > 6) || b.getPositions()[i+z][j+z] != GamePiece.NONE)
								break;
							if(b.getPositions()[i+z][j+z] == GamePiece.NONE && !(i+z < 0) && !(j+z > 6))
								insertMove(moveString(j, i, j+z, i+z));
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
								insertMove(moveString(j, i, j-z, i-z));
						}  //for: upper left
						//check the upper right squares until something is in its path
						//and whether it can attack
						for(int z = 1; z < 6; z++) {
							if((i+z > 7) || (j-z < 0) || b.getPositions()[i+z][j-z] != GamePiece.NONE)
								break;
							else if(!(i+z > 7) && !(j+z > 6) && b.getPositions()[i+z][j+z] == GamePiece.NONE &&
									( b.getPositions()[i-z+1][j+z] == GamePiece.HUMAN_KING || 
									  b.getPositions()[i-z+1][j+z] == GamePiece.HUMAN_MINI_NINJA ||
									  b.getPositions()[i-z+1][j+z] == GamePiece.HUMAN_MINI_SAMURAI ||
									  b.getPositions()[i-z+1][j+z] == GamePiece.HUMAN_NINJA  ||
									  b.getPositions()[i-z+1][j+z] == GamePiece.HUMAN_SAMURAI ) )
								insertMove(moveString(j, i, j+z, i-z));
						}  //for: upper right
					}  //Computer: Ninja check
					
					//checking  possible moves for Computer: Samurai
					if(b.getPositions()[i][j] == GamePiece.COMPUTER_SAMURAI) {
						//check the squares ahead
						for(int z = 1; z < 6; z++) {
							if((i+z > 7) || b.getPositions()[i+z][j] != GamePiece.NONE)
								break;
							if(!(i+z > 7) && b.getPositions()[i+z][j] == GamePiece.NONE)
								insertMove(moveString(j, i, j, i+z));
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
								insertMove(moveString(j, i, j-z, i));
						}  //for: left
						//check the right squares until something is in its path and whether it can attack
						for(int z = 1; z < 6; z++) {
							if((i+1 > 7) || (j+z > 6) || b.getPositions()[i][j+z] != GamePiece.NONE)
								break;
							else if(!(j+z > 6) && b.getPositions()[i][j+z] == GamePiece.NONE &&
									( b.getPositions()[i+1][j+z] == GamePiece.HUMAN_KING || 
									  b.getPositions()[i+1][j+z] == GamePiece.HUMAN_MINI_NINJA ||
									  b.getPositions()[i+1][j+z] == GamePiece.HUMAN_MINI_SAMURAI ||
									  b.getPositions()[i+1][j+z] == GamePiece.HUMAN_NINJA  ||
									  b.getPositions()[i+1][j+z] == GamePiece.HUMAN_SAMURAI ) )
								insertMove(moveString(j, i, j+z, i));
						}  //for: left
					}  //Computer: Samurai check
					
				}  //COMPUTER for(j)
			}  //COMPUTER for(i)
		}  //COMPUTER if
		return moveList;
	}  //moveGenerator()
	
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
//		System.out.println(move);
		return move;
	}  //moveString
	
	private boolean isValid(String m) {
		for(String pMove: moveList) {
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
		System.out.println(out);
		return out;
	}
	
	private void insertMove(String s) {
		if(moveList.size() >= listCapacity) {
			listCapacity += 5;
			moveList.ensureCapacity(listCapacity);
		}
		moveList.add(s);
	}  //insertMove()
	
	/*
	 * Method that displays the possible moves
	 */
	private void outputPossMoves(int p) {
		int count = 1;
		if(p == HUMAN)
			System.out.println("\tThese are your possible moves:");
		for(String move : moveList) {
			if(count % 6 == 0)
				System.out.println("\t" + move);
			else
				System.out.print("\t" + move);
			count++;
		}  //for
		System.out.println(" ");
	}
	
	/*
	 * Method prints returns the integer array equivalent of the move.
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
	
	
	//Possible move to individual methods
//	private void humamNinjaMoves() {
//		
//	}  //humanNinjaMoves()
//	
//	private void humamSamuraiMoves() {
//		
//	}  //humanSamuraiMoves()
//	
//	private void humanMiniNinjaMoves() {
//		
//	}  //humanMiniNinjaMoves()
//	
//	private void humanMiniSamuraiMoves() {
//		
//	}  //humanMiniSamuraiMoves()
//	
//	private void computerNinjaMoves() {
//		
//	}  //computerNinjaMoves()
//	
//	private void computerSamuraiMoves() {
//		
//	}  //computerSamuraiMoves()
//	
//	private void computerMiniNinjaMoves() {
//		
//	}  //computerMiniNinjaMoves()
//	
//	private void computerMiniSamuraiMoves() {
//		
//	}  //computerMiniSamuraiMoves()		
}
