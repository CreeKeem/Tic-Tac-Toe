package project2;

	import java.util.Scanner;

	public class Main {
		public static void main(String[] args) {
			playGame();
		}
		
		public static void playGame() {
			Scanner scanner = new Scanner(System.in);
			TTTGame game = new TTTGame();
			
			System.out.println("Game started ...");
			do {
				System.out.println("Current board:");
				game.print();
				System.out.println("Enter a move for " + game.getTurn());
				/*if(game.currentPlayer.equals(game.player1))	
				{
					int row = scanner.nextInt();
					int col = scanner.nextInt();
					
					while (row > 2 || col > 2 || row < 0 || col < 0 || !game.move(row, col)) {
						System.out.println("Invalid move, please try again:");
						row = scanner.nextInt();
						col = scanner.nextInt();
					}
				}
				else {
					while (!game.move()) {
					}
				}*//*
				int row = scanner.nextInt();
				int col = scanner.nextInt();
				while (row > 2 || col > 2 || row < 0 || col < 0 || !game.move(row, col)) {
					System.out.println("Invalid move, please try again:");
					row = scanner.nextInt();
					col = scanner.nextInt();
				}*/
				game.move();
				
			} while (!game.isGameOver());
			game.print();
			System.out.print("Game is over! ");
			
			if (game.getWinner() != null) {
				System.out.println(game.getWinner().getMark() + " wins!");
			} else {
				System.out.println("Tie game!");
			}
			
			scanner.close();
		}
	}

	


