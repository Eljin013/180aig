package a1;

import java.util.Scanner;

public class Game {
	private Board board;
	private boolean humanFirst;
	private Scanner scan;
	
	public Game() {
		board = new Board();
		scan = new Scanner(System.in);
		runGameLoop();
	}
	
	private void runGameLoop() {
		
		askPlayer();
		
		for(int i = 0; i < 5; i++) {
			if(!humanFirst) {
				System.out.println("Round " + (i+1));
				computerTurn(board);
				isGameOver(board);
				humanTurn(board);
				isGameOver(board);
			}  //if
			else {
				System.out.println("Round " + (i+1));
				humanTurn(board);
				isGameOver(board);
				computerTurn(board);
				isGameOver(board);
			}
		}  //for
	}
	
	public void askPlayer() {
		System.out.println("Enter 1 to go first or 2 to go second.");
//		try {
//			int input = scan.nextInt();
//		}
//		catch ()
		int input = scan.nextInt();
		if(input ==1)
			humanFirst = true;
		else
			humanFirst = false;
	}  //askPlayerInput()
	
	private void humanTurn(Board board) {
		System.out.println("It's the human's turn.");
		board.updateBoard();
		board.displayBoard();
	}  //humanTurn()
	
	private void computerTurn(Board board) {
		System.out.println("It's the computer's turn.");
		board.updateBoard();
		board.displayBoard();
	}  //computerTurn()
	
	private void isGameOver(Board board) {
		System.out.println("Game is not over yet.");
	}  //isGameOver()
}
