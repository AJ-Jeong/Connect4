package connect4;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

public class Game extends Grid implements MouseListener{
	
	private BoardPanel board;
	private Player[] players;
	private int cPlayer, nplay;
	private boolean bGameEnd;

	static final int WIDTH = 7;
	static final int HEIGHT = 6;

	private int[] moves;
	
	public Game() {
		super(WIDTH,HEIGHT);
		moves = new int[WIDTH*HEIGHT];
		nplay = 0;
        cPlayer = 0;
//		this.board = board;
//		this.players = players;
//		board.update(this);
//        players[0].init(this,'b');
//        players[1].init(this,'r');
//        cPlayer = 0;
        bGameEnd = true;
//        players[cPlayer].hasTurn();
	}
	public void newGame() {
		
		clearGrid();
		cPlayer =0;
		nplay = 0;
        bGameEnd = false;
		board.update(this);
		players[cPlayer].hasTurn();

	}
	
	public void startGame(BoardPanel board) {
	
		this.board = board;
		this.players = new Player[2];
//		board.update(this);
        bGameEnd = false;
        players[0] = new HumanPlayer('b');
        players[1] = new CompPlayer('r');
        
        players[0].init(this);
        players[1].init(this);
                
        board.addMouseListener( this );
        newGame();

	}
	
	public void changeLevel(int num) {
		
		Player p = players[num];
		if (p instanceof CompPlayer) {
			int lv = ((CompPlayer) p).getLevel();
			lv = (lv + 2) % 6;
			((CompPlayer) p).setLevel(lv);
		}
	}
	
	public int getPlayerLevel(int num) {
		int lv = ((CompPlayer) players[num]).getLevel();
		return lv;
	}
	
	public void endGame(char color) {
		
		bGameEnd = true;
		String msg;
		if (color == ' ')
			msg = "Draw!\n";
		else
			msg = "Players"+(cPlayer+1)+" has won!\n";
		msg += "Please choose New Game or Quit";
		JOptionPane.showMessageDialog(board, msg , "Notofocation", JOptionPane.INFORMATION_MESSAGE);
	}	
	
	
    public void makeMove( int posX )
    {
    	int posY = drop(players[cPlayer].getColor(),posX);
        board.update( this );

        board.labelLoc[cPlayer].setText("Player"+(cPlayer+1)+": "+(posX+1)+","+(posY+1));
//		if (players[cPlayer] instanceof CompPlayer) {
//			board.labelLoc[cPlayer].setText ("Level :" + ((CompPlayer)players[cPlayer]).gameLevel);
			
//		}
		

        if ( posY == -1) {
        	board.labelLoc[cPlayer].setText("<html>The column is Full!<BR>Your turn</html>");
        	players[cPlayer].hasTurn();
        }

        else {
            moves[nplay] = posX;

            if (isWin(posX, posY)== players[cPlayer].getColor()) {
            	board.labelLoc[cPlayer].setText("Player"+(cPlayer+1)+" won!!");
            	endGame(players[cPlayer].getColor());
            }
            else if (nplay>=41)
            	endGame(' ');
            else {
                nplay++;
                hasMoved();
            }

        }
    }
    
    public void hasMoved()
    {
        cPlayer = ( cPlayer + 1 ) % 2;
        Player p = players[cPlayer];
        System.out.println (cPlayer);
        p.hasTurn();

    }
    
    public void moveBack()
    {
    	if ( nplay < 1)
    		return;
    	if (players[(cPlayer+1)%2] instanceof CompPlayer) {
    		nplay--;
    		undoDrop(moves[nplay]);
    	}
    	nplay--;
    	undoDrop(moves[nplay]);
    	board.update( this );

    }
    
    public Player getPlayer(int num) {
    	return players[num];
    }
    
    public void changePlayerType(int num) {
    	System.out.println ("call");

    	if ( players[num] instanceof HumanPlayer )
    		players[num] = new CompPlayer(players[num].getColor(), 1);
    	else {
    		int lv = ((CompPlayer) players[num]).getLevel();
    		if ( lv == 5)
    			players[num] = new HumanPlayer(players[num].getColor());
    		else
    			changeLevel(num);
    	}    		
    	
        players[num].init(this);
        

    		
    }
    public void changeColor() {
    	char temp = players[0].getColor();
    	players[0].setColor(players[1].getColor());
    	players[1].setColor(temp);
    	
    }
    
    public boolean isGameEnd () {
    	return bGameEnd;
    }
    
    public int getCurrentPlayer () {
    	
    	return cPlayer;
    }
    
    public void mouseReleased( MouseEvent e ) {
    	
    }

    public void mouseClicked( MouseEvent e )
    {
        if ( !(players[cPlayer] instanceof HumanPlayer) || bGameEnd )
            return;
        

        Location pos = board.getPos( e.getX(), e.getY() );
        int x = pos.getRow();
        int y = pos.getCol();
        if (x<=6 && x>=0 && y>=0 && y <= 5) {
            board.labelLoc[cPlayer].setText("Previous move was"+x+","+y);
            players[cPlayer].move(x);
        }

    }

    public void mousePressed( MouseEvent e )
    {
    }

    public void mouseEntered( MouseEvent e )
    {
    }

    public void mouseExited( MouseEvent e )
    {
    }

}
