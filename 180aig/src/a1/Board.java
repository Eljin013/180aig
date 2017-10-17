package a1;

public class Board {
	private static Board boardInstance = null;
	private Game game;
	private GamePiece[][] positions;
	
	protected Board(Game game) {
		this.game = game;
		initializeBoard();
	}
	
	public static Board getInstance(Game game) {
		if(boardInstance == null)
			boardInstance = new Board(game);
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
	
	public void setPositions(GamePiece[][] pos) {
		this.positions = pos;
	}
	
	/*
	 * Heuristic for evaluating the progress of game
	 */
	public int evaluate() {
		int cPieces = 0;
		int hPieces = 0; 
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 7; j++) {
				if(positions[i][j] == GamePiece.COMPUTER_KING) cPieces += 1;
				if(positions[i][j] == GamePiece.COMPUTER_MINI_NINJA ||
						positions[i][j] == GamePiece.COMPUTER_MINI_SAMURAI) cPieces += 2;
				if(positions[i][j] == GamePiece.COMPUTER_NINJA ||
						positions[i][j] == GamePiece.COMPUTER_SAMURAI) cPieces += 3;
				if(positions[i][j] == GamePiece.HUMAN_KING) hPieces += 1;
				if(positions[i][j] == GamePiece.HUMAN_MINI_NINJA ||
						positions[i][j] == GamePiece.HUMAN_MINI_SAMURAI) cPieces += 2;
				if(positions[i][j] == GamePiece.HUMAN_NINJA ||
						positions[i][j] == GamePiece.HUMAN_SAMURAI) cPieces += 3;
			}  //for
		}  //for
		return cPieces - hPieces;
	}
	
	public void updateBoard(int[] move, Player player) {
		//Add GamePiece to the "to" position
		positions[move[3]][move[2]] = positions[move[1]][move[0]];
		//Remove GamePiece from the "from" position
		positions[move[1]][move[0]] = GamePiece.NONE;

<<<<<<< HEAD
		//Checks if the HUMAN's move is an attack
		if(player == Player.HUMAN && 
				( positions[move[3]-1][move[2]] == GamePiece.COMPUTER_KING || 
				  positions[move[3]-1][move[2]] == GamePiece.COMPUTER_MINI_NINJA ||
				  positions[move[3]-1][move[2]] == GamePiece.COMPUTER_MINI_SAMURAI ||
				  positions[move[3]-1][move[2]] == GamePiece.COMPUTER_NINJA  ||
				  positions[move[3]-1][move[2]] == GamePiece.COMPUTER_SAMURAI )) {
			System.out.println("HI-YA!!!!!!");
			if(positions[move[3]-1][move[2]] == GamePiece.COMPUTER_KING) {
				game.setGameOver(true);
				game.setWinner(Player.HUMAN);
			}
			//Removes the GamePiece from the Board
			if(positions[move[3]-1][move[2]] == GamePiece.COMPUTER_MINI_NINJA ||
					positions[move[3]-1][move[2]] == GamePiece.COMPUTER_MINI_SAMURAI)
				positions[move[3]-1][move[2]] = GamePiece.NONE;
			//Sets the Ninja to Mini-Ninja
			if(positions[move[3]-1][move[2]] == GamePiece.COMPUTER_NINJA)
				positions[move[3]-1][move[2]] = GamePiece.COMPUTER_MINI_NINJA;
			//Sets the Samurai to Mini-Samurai
			if(positions[move[3]-1][move[2]] == GamePiece.COMPUTER_SAMURAI)
				positions[move[3]-1][move[2]] = GamePiece.COMPUTER_MINI_SAMURAI;
			
		}  //if: Human attack
		
		//Checks if the COMPUTER's move is an attack
		if(player == Player.COMPUTER && 
				( positions[move[3]+1][move[2]] == GamePiece.HUMAN_KING || 
				  positions[move[3]+1][move[2]] == GamePiece.HUMAN_MINI_NINJA ||
				  positions[move[3]+1][move[2]] == GamePiece.HUMAN_MINI_SAMURAI ||
				  positions[move[3]+1][move[2]] == GamePiece.HUMAN_NINJA  ||
				  positions[move[3]+1][move[2]] == GamePiece.HUMAN_SAMURAI )) {
			System.out.println("HI-YA!!!!!!");
			//Sets gameOver to true if the COMPUTER attacks the King
			if(positions[move[3]+1][move[2]] == GamePiece.HUMAN_KING) {
				game.setGameOver(true);
				game.setWinner(Player.COMPUTER);
			}
			//Removes the GamePiece from the Board
			if(positions[move[3]+1][move[2]] == GamePiece.HUMAN_MINI_NINJA ||
					positions[move[3]+1][move[2]] == GamePiece.HUMAN_MINI_SAMURAI)
				positions[move[3]+1][move[2]] = GamePiece.NONE;
			//Sets the Ninja to Mini-Ninja
			if(positions[move[3]+1][move[2]] == GamePiece.HUMAN_NINJA)
				positions[move[3]+1][move[2]] = GamePiece.HUMAN_MINI_NINJA;
			//Sets the Samurai to Mini-Samurai
			if(positions[move[3]+1][move[2]] == GamePiece.HUMAN_SAMURAI)
				positions[move[3]+1][move[2]] = GamePiece.HUMAN_MINI_SAMURAI;

		}  //if: Computer attack
		System.out.println("The board has been updated.");
=======
		//Checks if the within bounds
		if( !(move[3]-1 < 0) && !(move[3]+1 > 7)) {
			//Checks if the HUMAN's move is an attack
			if(player == Player.HUMAN && 
					( positions[move[3]-1][move[2]] == GamePiece.COMPUTER_KING || 
					  positions[move[3]-1][move[2]] == GamePiece.COMPUTER_MINI_NINJA ||
					  positions[move[3]-1][move[2]] == GamePiece.COMPUTER_MINI_SAMURAI ||
					  positions[move[3]-1][move[2]] == GamePiece.COMPUTER_NINJA  ||
					  positions[move[3]-1][move[2]] == GamePiece.COMPUTER_SAMURAI )) {
				System.out.println("HI-YA!!!!!!");
				if(positions[move[3]-1][move[2]] == GamePiece.COMPUTER_KING) {
					game.setGameOver(true);
					game.setWinner(Player.HUMAN);
				}
				//Sets the Ninja to Mini-Ninja
				if(positions[move[3]-1][move[2]] == GamePiece.COMPUTER_NINJA)
					positions[move[3]-1][move[2]] = GamePiece.COMPUTER_MINI_NINJA;
				//Sets the Samurai to Mini-Samurai
				if(positions[move[3]-1][move[2]] == GamePiece.COMPUTER_SAMURAI)
					positions[move[3]-1][move[2]] = GamePiece.COMPUTER_MINI_SAMURAI;
				//Removes the GamePiece from the Board
				if(positions[move[3]-1][move[2]] == GamePiece.COMPUTER_MINI_NINJA ||
						positions[move[3]-1][move[2]] == GamePiece.COMPUTER_MINI_SAMURAI)
					positions[move[3]-1][move[2]] = GamePiece.NONE;
			}  //if: Human attack
			
			//Checks if the COMPUTER's move is an attack
			if(player == Player.COMPUTER && 
					( positions[move[3]+1][move[2]] == GamePiece.HUMAN_KING || 
					  positions[move[3]+1][move[2]] == GamePiece.HUMAN_MINI_NINJA ||
					  positions[move[3]+1][move[2]] == GamePiece.HUMAN_MINI_SAMURAI ||
					  positions[move[3]+1][move[2]] == GamePiece.HUMAN_NINJA  ||
					  positions[move[3]+1][move[2]] == GamePiece.HUMAN_SAMURAI )) {
				System.out.println("HI-YA!!!!!!");
				//Sets gameOver to true if the COMPUTER attacks the King
				if(positions[move[3]+1][move[2]] == GamePiece.HUMAN_KING) {
					game.setGameOver(true);
					game.setWinner(Player.COMPUTER);
				}
				//Sets the Ninja to Mini-Ninja
				if(positions[move[3]+1][move[2]] == GamePiece.HUMAN_NINJA)
					positions[move[3]+1][move[2]] = GamePiece.HUMAN_MINI_NINJA;
				//Sets the Samurai to Mini-Samurai
				if(positions[move[3]+1][move[2]] == GamePiece.HUMAN_SAMURAI)
					positions[move[3]+1][move[2]] = GamePiece.HUMAN_MINI_SAMURAI;
				//Removes the GamePiece from the Board
				if(positions[move[3]+1][move[2]] == GamePiece.HUMAN_MINI_NINJA ||
						positions[move[3]+1][move[2]] == GamePiece.HUMAN_MINI_SAMURAI)
					positions[move[3]+1][move[2]] = GamePiece.NONE;
			}  //if: Computer attack
		}  //if: within bounds
//		System.out.println("The board has been updated.");
>>>>>>> refs/remotes/origin/master
	}
	
	public void minimaxUpdate(int[] move, Player player) {
		//Add GamePiece to the "to" position
		positions[move[3]][move[2]] = positions[move[1]][move[0]];
		//Remove GamePiece from the "from" position
		positions[move[1]][move[0]] = GamePiece.NONE;

		//Checks if the within bounds
		if( !(move[3]-1 < 0) && !(move[3]+1 > 7)) {
			//Checks if the HUMAN's move is an attack
			if(player == Player.HUMAN &&
					( positions[move[3]-1][move[2]] == GamePiece.COMPUTER_KING || 
					  positions[move[3]-1][move[2]] == GamePiece.COMPUTER_MINI_NINJA ||
					  positions[move[3]-1][move[2]] == GamePiece.COMPUTER_MINI_SAMURAI ||
					  positions[move[3]-1][move[2]] == GamePiece.COMPUTER_NINJA  ||
					  positions[move[3]-1][move[2]] == GamePiece.COMPUTER_SAMURAI )) {
	//			System.out.println("HI-YA!!!!!!");
				if(positions[move[3]-1][move[2]] == GamePiece.COMPUTER_KING) {
					game.setGameOver(true);
					game.setWinner(Player.HUMAN);
				}
				//Sets the Ninja to Mini-Ninja
				if(positions[move[3]-1][move[2]] == GamePiece.COMPUTER_NINJA)
					positions[move[3]-1][move[2]] = GamePiece.COMPUTER_MINI_NINJA;
				//Sets the Samurai to Mini-Samurai
				if(positions[move[3]-1][move[2]] == GamePiece.COMPUTER_SAMURAI)
					positions[move[3]-1][move[2]] = GamePiece.COMPUTER_MINI_SAMURAI;
				//Removes the GamePiece from the Board
				if(positions[move[3]-1][move[2]] == GamePiece.COMPUTER_MINI_NINJA ||
						positions[move[3]-1][move[2]] == GamePiece.COMPUTER_MINI_SAMURAI)
					positions[move[3]-1][move[2]] = GamePiece.NONE;
			}  //if: Human attack
			
			//Checks if the COMPUTER's move is an attack
			if(player == Player.COMPUTER &&
					( positions[move[3]+1][move[2]] == GamePiece.HUMAN_KING || 
					  positions[move[3]+1][move[2]] == GamePiece.HUMAN_MINI_NINJA ||
					  positions[move[3]+1][move[2]] == GamePiece.HUMAN_MINI_SAMURAI ||
					  positions[move[3]+1][move[2]] == GamePiece.HUMAN_NINJA  ||
					  positions[move[3]+1][move[2]] == GamePiece.HUMAN_SAMURAI )) {
	//			System.out.println("HI-YA!!!!!!");
				//Sets gameOver to true if the COMPUTER attacks the King
				if(positions[move[3]+1][move[2]] == GamePiece.HUMAN_KING) {
					game.setGameOver(true);
					game.setWinner(Player.COMPUTER);
				}
				//Sets the Ninja to Mini-Ninja
				if(positions[move[3]+1][move[2]] == GamePiece.HUMAN_NINJA)
					positions[move[3]+1][move[2]] = GamePiece.HUMAN_MINI_NINJA;
				//Sets the Samurai to Mini-Samurai
				if(positions[move[3]+1][move[2]] == GamePiece.HUMAN_SAMURAI)
					positions[move[3]+1][move[2]] = GamePiece.HUMAN_MINI_SAMURAI;
				//Removes the GamePiece from the Board
				if(positions[move[3]+1][move[2]] == GamePiece.HUMAN_MINI_NINJA ||
						positions[move[3]+1][move[2]] == GamePiece.HUMAN_MINI_SAMURAI)
					positions[move[3]+1][move[2]] = GamePiece.NONE;
			}  //if: Computer attack
		}  //if: within bounds
//		System.out.println("The board has been updated.");
	}  //minimaxupdate()
	
	public void displayBoard() {
		System.out.println("\t   ----------------------------  Computer");
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
				if(positions[i][j] == GamePiece.NONE) System.out.print("    ");
				if(positions[i][j] == GamePiece.HUMAN_KING) System.out.print(" hK ");
				if(positions[i][j] == GamePiece.HUMAN_NINJA) System.out.print(" hJ ");
				if(positions[i][j] == GamePiece.HUMAN_SAMURAI) System.out.print(" hS ");
				if(positions[i][j] == GamePiece.HUMAN_MINI_NINJA) System.out.print(" hj ");
				if(positions[i][j] == GamePiece.HUMAN_MINI_SAMURAI) System.out.print(" hs ");
				if(positions[i][j] == GamePiece.COMPUTER_KING) System.out.print(" cK ");
				if(positions[i][j] == GamePiece.COMPUTER_NINJA) System.out.print(" cJ ");
				if(positions[i][j] == GamePiece.COMPUTER_SAMURAI) System.out.print(" cS ");
				if(positions[i][j] == GamePiece.COMPUTER_MINI_NINJA) System.out.print(" cj ");
				if(positions[i][j] == GamePiece.COMPUTER_MINI_SAMURAI) System.out.print(" cs ");
			}  //for(j)
			System.out.println(" ");
		}  //for(i)
		System.out.println("\t   ----------------------------  Human");
		System.out.println("\t     A   B   C   D   E   F   G");
	}
	
	
}
