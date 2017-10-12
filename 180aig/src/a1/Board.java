package a1;

public class Board {
	private static Board boardInstance = null;
	private GamePiece[][] positions;


	
	
	protected Board() {
		initializeBoard();
	}
	
	public static Board getInstance() {
		if(boardInstance == null)
			boardInstance = new Board();
		return boardInstance;
	}
	
	private void initializeBoard() {
		positions = new GamePiece[][] {
			{GamePiece.NONE, 
				GamePiece.NONE, 
				GamePiece.NONE, 
				GamePiece.COMPUTER_KING, 
				GamePiece.NONE, 
				GamePiece.NONE, 
				GamePiece.NONE},
			{GamePiece.COMPUTER_NINJA, 
				GamePiece.COMPUTER_NINJA, 
				GamePiece.COMPUTER_NINJA, 
				GamePiece.NONE, 
				GamePiece.COMPUTER_SAMURAI, 
				GamePiece.COMPUTER_SAMURAI, 
				GamePiece.COMPUTER_SAMURAI},
			{GamePiece.COMPUTER_MINI_SAMURAI, 
				GamePiece.COMPUTER_MINI_SAMURAI, 
				GamePiece.COMPUTER_MINI_SAMURAI, 
				GamePiece.NONE, 
				GamePiece.COMPUTER_MINI_NINJA, 
				GamePiece.COMPUTER_MINI_NINJA, 
				GamePiece.COMPUTER_MINI_NINJA},
			{GamePiece.NONE, 
				GamePiece.NONE, 
				GamePiece.NONE, 
				GamePiece.NONE,
				GamePiece.NONE, 
				GamePiece.NONE, 
				GamePiece.NONE},
			{GamePiece.NONE, 
				GamePiece.NONE, 
				GamePiece.NONE, 
				GamePiece.NONE, 
				GamePiece.NONE, 
				GamePiece.NONE, 
				GamePiece.NONE},
			{GamePiece.HUMAN_MINI_NINJA, 
				GamePiece.HUMAN_MINI_NINJA, 
				GamePiece.HUMAN_MINI_NINJA, 
				GamePiece.NONE, 
				GamePiece.HUMAN_MINI_SAMURAI, 
				GamePiece.HUMAN_MINI_SAMURAI, 
				GamePiece.HUMAN_MINI_SAMURAI},
			{GamePiece.HUMAN_SAMURAI, 
				GamePiece.HUMAN_SAMURAI, 
				GamePiece.HUMAN_SAMURAI,
				GamePiece.NONE, 
				GamePiece.HUMAN_NINJA, 
				GamePiece.HUMAN_NINJA, 
				GamePiece.HUMAN_NINJA},
			{GamePiece.NONE, 
				GamePiece.NONE, 
				GamePiece.NONE, 
				GamePiece.HUMAN_KING, 
				GamePiece.NONE, 
				GamePiece.NONE, 
				GamePiece.NONE}
		};
	}  //initializeBoard()
	
	public GamePiece[][] getPositions() {
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
				if(positions[i][j] == GamePiece.NONE) System.out.print("   ");
				if(positions[i][j] == GamePiece.HUMAN_KING  || positions[i][j] == GamePiece.COMPUTER_KING) 
					System.out.print(" K ");
				if(positions[i][j] == GamePiece.HUMAN_NINJA) System.out.print(" J ");
				if(positions[i][j] == GamePiece.HUMAN_SAMURAI) System.out.print(" S ");
				if(positions[i][j] == GamePiece.HUMAN_MINI_NINJA) System.out.print(" j ");
				if(positions[i][j] == GamePiece.HUMAN_MINI_SAMURAI) System.out.print(" s ");
				if(positions[i][j] == GamePiece.COMPUTER_NINJA) System.out.print(" J ");
				if(positions[i][j] == GamePiece.COMPUTER_SAMURAI) System.out.print(" S ");
				if(positions[i][j] == GamePiece.COMPUTER_MINI_NINJA) System.out.print(" j ");
				if(positions[i][j] == GamePiece.COMPUTER_MINI_SAMURAI) System.out.print(" s ");
			}  //for(j)
			System.out.println(" ");
		}  //for(i)
		System.out.println("\t   ---------------------  Human");
		System.out.println("\t    A  B  C  D  E  F  G");
	}
	
	
}
