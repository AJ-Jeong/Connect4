package connect4;

import java.awt.Color;

public class Strategy {

	private Grid board;
	static final int WIDTH = 7;
	static final int HEIGHT = 6;
	private char myColor = 'r';
	private char oppColor = 'b';

	public Strategy(Game board, char c) {

		this.board = new Grid(WIDTH,HEIGHT);
		this.board.grid = board.grid;
		myColor = c;
		oppColor = (c=='b') ? 'r' : 'b';

	}

	private double calculatepower(double n) {
		return Math.pow(4, n);
	}

	private boolean validBounds(int x, int y) {
		if (x >= WIDTH || x < 0) {
			return false;
		}
		if (y >= HEIGHT || y < 0) {
			return false;
		}
		if (board.grid[x][y] != ' ') {
			return false;
		}
		return true;
	}

	public double calcScore(int x, int y, int xOffset, int yOffset) {

		double result = 0;
		char color = board.grid[x][y];

		for (int i = 0; i < 3; i++) {
			int posX = x + i * xOffset;
			int posY = y+ i * yOffset;

			if (posX < 0 || posX >= WIDTH || posY < 0 || posY >= HEIGHT || board.grid[posX][posY] != color) {

				color = ' ';
				break;
			}
			if ( validBounds(x + (i + 1) * xOffset, y + (i + 1) * yOffset)) { // one in a x
				if (color == myColor) {
					result = calculatepower(i+1);
				} else if (color == oppColor) {
					result = calculatepower(i+1) * (-2);
				}
			}

		}

		return result;

	}

	public double evaluateBoard() {

		double result = 0;

		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {

				result += calcScore(x, y, 1, 0);

				result += calcScore(x, y, 0, -1); // up

				result += calcScore(x, y, 1, -1); // up diagonal

				result += calcScore(x, y, 1, 1); // down diagonal

			}
		}

		return result;

	}


	public int bestMoveForComputer(int maxDepth) {

		double alpha = -100000; //initialize alpha and beta values for pruning
		double beta = 1000000;

		int bestCol = -1;
		double result, bestResult = Double.NEGATIVE_INFINITY;
		int y;
		char winner;
		
		
		for (int x = 0; x < WIDTH; x++) {
			if (board.isLegal(x)) {
				
				y = board.drop(myColor, x);
				winner = board.isWin(x, y);

				if (winner == myColor) {
				
//					result = Double.POSITIVE_INFINITY;
					board.undoDrop(x);
					return x;
				}
				
				else if (winner == oppColor) {

					result = Double.NEGATIVE_INFINITY;
				
				}
				else {
					
					result = min(x, y, maxDepth, 0, alpha, beta);
				
				}

				board.undoDrop(x);
				if (result > bestResult) {
					
					bestResult = result;
					bestCol = x;

				}

				if (result > alpha) { //alpha-beta pruning
					alpha = result;
				}

			}

		}
		
		if (bestCol == -1)
			for (int x=0 ; x<WIDTH ; x++) {
				if (board.isLegal(x)) {
					if (Math.abs(x-3)<Math.abs(bestCol-3))
						bestCol = x;
				}
					
			}
		return bestCol;
	}

	public double max(int posX, int posY, int maxDepth, int depth, double alpha, double beta) {

		char winner = board.isWin(posX, posY);
		if (winner == myColor) {
			return Double.POSITIVE_INFINITY;
		} else if (winner == oppColor) {
			return Double.NEGATIVE_INFINITY;
		}
		

		
		if (board.isFull() || (depth == maxDepth)) {
			double r = evaluateBoard();
			return r;
		}

		else {
			double bestResult = Double.NEGATIVE_INFINITY;
			double result;
			int y;
			
			for (int x = 0; x < WIDTH; x++) {
				if (board.isLegal(x)) {

					y = board.drop(myColor, x);
					result = min(x,y,maxDepth, depth + 1, alpha, beta);			
					board.undoDrop(x);
					if (result > bestResult) {
						
						bestResult = result;
						
					}

					if (result > alpha) {
						alpha = result;
					}
					
					if (alpha >= beta) {
						return alpha;
					}
				}

			}

			return bestResult;
		}

	}


	public double min(int posX, int posY, int maxDepth, int depth, double alpha, double beta) {

		char winner = board.isWin(posX, posY);
		
		if (winner == myColor) {
			
			return Double.POSITIVE_INFINITY;

		} else if (winner == oppColor) {
			
			return Double.NEGATIVE_INFINITY;
		}

		
		if (board.isFull() || (depth == maxDepth)) {

			double r = evaluateBoard();
			return r;

		} 
		else {

			double bestResult = Double.POSITIVE_INFINITY;
			double result;
			int y;
			
			for (int x = 0; x < WIDTH; x++) {
				if (board.isLegal(x)) {
					
					y = board.drop(oppColor, x);
					result = max(x, y, maxDepth, depth + 1, alpha, beta);
					board.undoDrop(x);

					if (result <= bestResult) {
						bestResult = result;

					}
					
					if (result < beta) {
						beta = result;
					}
					
					if (alpha >= beta) {
						return beta;
					}
				}

			}

			return bestResult;
		}
	}
}

