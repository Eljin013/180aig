package a1;

public class Board {
	private Board board;
	private int[] y;
	private char[] x;
	
	public Board() {
		new Board(board);
	}
	
	public Board(Board board) {
		this.board = board;
	}
	
	public void updateBoard() {
		System.out.println("The board has been updated.");
	}
	
	public void displayBoard() {
		System.out.println("The board has been displayed.");
	}
	
	
}
