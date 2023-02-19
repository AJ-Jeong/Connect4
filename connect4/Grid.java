package connect4;

import java.awt.Color;
import java.util.ArrayList;

public class Grid {
	
	public char[][] grid;
	private ArrayList<Location> winLocation;
	
	public Grid(int x, int y) {
		grid = new char[x][y];
		clearGrid();
		winLocation = new ArrayList<Location> ();
		
	}
	
	private void fill(char c) {
		for (int i = 0 ; i < grid.length ; i++)
			for (int j = 0 ; j < grid[0].length ; j++)
				grid[i][j] = c;
		
	}
	
	public int drop(char c, int posX) {
		if (posX != -1) {
			for (int posY = grid[0].length-1 ; posY >= 0 ; posY--) {
				if (grid[posX][posY] == ' ') {
					grid[posX][posY] = c;
					return posY;
				}
			}
		}

		return -1;
	}
	
	public void clearGrid() {
		
		fill(' ');
		
	}
	
	public boolean isFull() {
		for (int posX = 0 ; posX< grid.length ; posX++) {
			if ( grid[posX][0] == ' ')
				return false;
				
		}
		return true;
	}
	
    public boolean isLegal(int posX) {
    	return posX >= 0 &&
    			posX < grid.length &&
    			grid [posX][ 0 ] == ' ';
    }
    
    public void undoDrop(int posX) {
    	int posY = 0;
    	while (posY < grid[0].length-1 && grid[posX][posY] == ' ')
    		posY++;
		
    	grid[posX][posY] = ' ';

    }
	
	
	public char isWin(int posX, int posY) {
		
		char c = grid[posX][posY];
		boolean win = false;
		winLocation.add(new Location(posX, posY));
		
		if ( (checkConnect(c, posX,posY-1, 0, -1) + checkConnect(c, posX,posY+1, 0, 1)) >= 3 )
			win = true;
		else if ( (checkConnect(c, posX+1,posY, 1, 0)+checkConnect (c, posX-1,posY, -1, 0)) >= 3 )
			win = true;
		else if ( (checkConnect (c,posX+1,posY-1, 1, -1) + checkConnect(c,posX-1,posY+1, -1, 1)) >= 3 )
			win = true;
		else if ( (checkConnect (c,posX+1,posY+1,1,1) + checkConnect(c,posX-1,posY-1,-1,-1)) >= 3 )
			win = true;
		
		if (win == true)
			return c;
		return ' ';

	}

	private int checkConnect (char c, int x, int y, int xOffset, int yOffset) {
		
		if (x < 0 || x > 6 || y < 0 || y > 5)
			return 0;
		else if ( grid[x][y] != c)
			return 0;
		
		winLocation.add(new Location(x,y));
		return 1+ checkConnect(c, x+xOffset, y+yOffset, xOffset, yOffset);
	}
/*
	private int checkDown (char c, int x, int y) {
		
		if (x < 0 || x > 6 || y < 0 || y > 5)
			return 0;
		else if ( grid[x][y] != c)
			return 0;
		return 1+ checkDown(c, x, y+1);
	}
	private int checkLeft (char c, int x, int y) {
		
		if (x < 0 || x > 6 || y < 0 || y > 5)
			return 0;
		else if ( grid[x][y] != c)
			return 0;
		return 1+ checkLeft(c, x-1, y);
	}
	private int checkRight (char c, int x, int y) {
		
		if (x < 0 || x > 6 || y < 0 || y > 5)
			return 0;
		else if ( grid[x][y] != c)
			return 0;
		return 1+ checkRight(c, x+1, y);
	}
	private int checkRightUp (char c, int x, int y) {
		
		if (x < 0 || x > 6 || y < 0 || y > 5)
			return 0;
		else if ( grid[x][y] != c)
			return 0;
		return 1+ checkRightUp(c, x+1, y-1);
	}
	private int checkLeftDown (char c, int x, int y) {
		
		if (x < 0 || x > 6 || y < 0 || y > 5)
			return 0;
		else if ( grid[x][y] != c)
			return 0;
		return 1+ checkLeftDown(c, x-1, y+1);
	}
	private int checkRightDown (char c, int x, int y) {
		
		if (x < 0 || x > 6 || y < 0 || y > 5)
			return 0;
		else if ( grid[x][y] != c)
			return 0;
		return 1+ checkRightDown(c, x+1, y+1);
	}
	private int checkLeftUp (char c, int x, int y) {
		
		if (x < 0 || x > 6 || y < 0 || y > 5)
			return 0;
		else if ( grid[x][y] != c)
			return 0;
		return 1+ checkLeftUp(c, x-1, y-1);
	}
*/
}
