package a1;

public class Board {
	private static Board boardInstance = null;
	private int[][] positions;
	
	//Game Pieces
	final private int HUMAN_KING = 1;
	final private int HUMAN_NINJA = 2;
	final private int HUMAN_SAMURAI = 3;
	final private int HUMAN_MINI_NINJA = 4;
	final private int HUMAN_MINI_SAMURAI = 5;
	final private int COMPUTER_KING = 1;
	final private int COMPUTER_NINJA = 6;
	final private int COMPUTER_SAMURAI = 7;
	final private int COMPUTER_MINI_NINJA = 8;
	final private int COMPUTER_MINI_SAMURAI = 9;

	
	
	protected Board() {
		initializeBoard();
	}
	
	public static Board createBoard() {
		if(boardInstance == null)
			boardInstance = new Board();
		return boardInstance;
	}
	
	private void initializeBoard() {
		positions = new int[][] {
			{0, 0, 0, 1, 0, 0, 0},
			{6, 6, 6, 0, 7, 7, 7},
			{9, 9, 9, 0, 8, 8, 8},
			{0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0},
			{4, 4, 4, 0, 5, 5, 5},
			{3, 3, 3, 0, 2, 2, 2},
			{0, 0, 0, 1, 0, 0, 0}
		};
	}  //initializeBoard()
	
	public int[][] getPositions() {
		return positions;
	}
	
	public void updateBoard() {
		System.out.println("The board has been updated.");
	}
	
	public void displayBoard() {
		System.out.println("\t   ---------------------  Computer");
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 7; j++) {
				//prints the left-hand side of the board
				if(i == 0 && j == 0) System.out.print("\t 8 ");
				if(i == 1 && j == 0) System.out.print("\t 7 ");
				if(i == 2 && j == 0) System.out.print("\t 6 ");
				if(i == 3 && j == 0) System.out.print("\t 5 ");
				if(i == 4 && j == 0) System.out.print("\t 4 ");
				if(i == 5 && j == 0) System.out.print("\t 3 ");
				if(i == 6 && j == 0) System.out.print("\t 2 ");
				if(i == 7 && j == 0) System.out.print("\t 1 ");
				//prints out the game pieces
				if(positions[i][j] == 0) System.out.print("   ");
				if(positions[i][j] == HUMAN_KING  || positions[i][j] == COMPUTER_KING) 
					System.out.print(" K ");
				if(positions[i][j] == HUMAN_NINJA) System.out.print(" J ");
				if(positions[i][j] == HUMAN_SAMURAI) System.out.print(" S ");
				if(positions[i][j] == HUMAN_MINI_NINJA) System.out.print(" j ");
				if(positions[i][j] == HUMAN_MINI_SAMURAI) System.out.print(" s ");
				if(positions[i][j] == COMPUTER_NINJA) System.out.print(" J ");
				if(positions[i][j] == COMPUTER_SAMURAI) System.out.print(" S ");
				if(positions[i][j] == COMPUTER_MINI_NINJA) System.out.print(" j ");
				if(positions[i][j] == COMPUTER_MINI_SAMURAI) System.out.print(" s ");
			}  //for(j)
			System.out.println(" ");
		}  //for(i)
		System.out.println("\t   ---------------------  Human");
		System.out.println("\t    A  B  C  D  E  F  G");
	}
	
	
}
