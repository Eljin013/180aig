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
		board =  board.createBoard();
		scan = new Scanner(System.in);
		runGameLoop(board);
	}
	
	private void runGameLoop(Board b) {
		System.out.println("Hello! My name is Scruffles, the Master of Karate Chops!");
		askPlayer();
		
		for(int i = 0; i < 1; i++) {
			if(!humanFirst) {
				System.out.println("Round " + (i+1));
				moveGenerator(b, COMPUTER);
				computerTurn(b);
				isGameOver(b, COMPUTER);
				moveGenerator(b, HUMAN);
				humanTurn(b);
				isGameOver(b, HUMAN);
			}  //if
			else {
				System.out.println("Round " + (i+1));
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
		System.out.println("Care to test your might first [1] or second [2]?");

		int input = scan.nextInt();
		if(input ==1)
			humanFirst = true;
		else
			humanFirst = false;
	}  //askPlayerInput()
	
	private void humanTurn(Board b) {
		System.out.println("It's the human's turn.");
		//display the possible moves
		for(String move : moveList) {
			System.out.println("\t" + move);
		}  //for
		//Human takes turn
		//update the board
		b.updateBoard();

		//clear the moveList
		moveList.clear();
		b.displayBoard();
	}  //humanTurn()
	
	private void computerTurn(Board b) {
		System.out.println("It's the computer's turn.");
		//display the possible moves
		for(String move : moveList) {
			System.out.println("\t" + move);
		}  //for
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
							insertMove(moveString(j, 1, j-1, i+1));
						//check the lower right square and whether it can attack
						if(!(i+1 > 7) && !(j+1 > 6) && b.getPositions()[i+1][j+1] == GamePiece.NONE &&
								( b.getPositions()[i][j+1] == GamePiece.COMPUTER_KING || 
								  b.getPositions()[i][j+1] == GamePiece.COMPUTER_MINI_NINJA ||
								  b.getPositions()[i][j+1] == GamePiece.COMPUTER_MINI_SAMURAI ||
								  b.getPositions()[i][j+1] == GamePiece.COMPUTER_NINJA  ||
								  b.getPositions()[i][j+1] == GamePiece.COMPUTER_SAMURAI ) )
							insertMove(moveString(j, 1, j+1, i+1));
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
							insertMove(moveString(j, 1, j-1, i));
						//check the right square
						if(!(j+1 > 6) && b.getPositions()[i][j+1] == GamePiece.NONE &&
								( b.getPositions()[i-1][j+1] == GamePiece.COMPUTER_KING || 
								  b.getPositions()[i-1][j+1] == GamePiece.COMPUTER_MINI_NINJA ||
								  b.getPositions()[i-1][j+1] == GamePiece.COMPUTER_MINI_SAMURAI ||
								  b.getPositions()[i-1][j+1] == GamePiece.COMPUTER_NINJA  ||
								  b.getPositions()[i-1][j+1] == GamePiece.COMPUTER_SAMURAI ) )
							insertMove(moveString(j, 1, j+1, i));
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
							insertMove(moveString(j, 1, j-1, i-1));
						//check the lower right square and whether it can attack
						if(!(i-1 > 7) && !(j+1 > 6) && b.getPositions()[i-1][j+1] == GamePiece.NONE &&
								( b.getPositions()[i][j+1] == GamePiece.HUMAN_KING || 
								  b.getPositions()[i][j+1] == GamePiece.HUMAN_MINI_NINJA ||
								  b.getPositions()[i][j+1] == GamePiece.HUMAN_MINI_SAMURAI ||
								  b.getPositions()[i][j+1] == GamePiece.HUMAN_NINJA  ||
								  b.getPositions()[i][j+1] == GamePiece.HUMAN_SAMURAI ) )
							insertMove(moveString(j, 1, j+1, i-1));
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
							insertMove(moveString(j, 1, j-1, i));
						//check the right square and whether it can attack
						if(!(j+1 > 6) && b.getPositions()[i][j+1] == GamePiece.NONE &&
								( b.getPositions()[i+1][j+1] == GamePiece.HUMAN_KING || 
								  b.getPositions()[i+1][j+1] == GamePiece.HUMAN_MINI_NINJA ||
								  b.getPositions()[i+1][j+1] == GamePiece.HUMAN_MINI_SAMURAI ||
								  b.getPositions()[i+1][j+1] == GamePiece.HUMAN_NINJA  ||
								  b.getPositions()[i+1][j+1] == GamePiece.HUMAN_SAMURAI ) )
							insertMove(moveString(j, 1, j+1, i));
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
		return true;
	}
	
	private void insertMove(String s) {
		if(moveList.size() >= listCapacity) {
			listCapacity += 5;
			moveList.ensureCapacity(listCapacity);
		}
		moveList.add(s);
	}  //insertMove()
	
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
