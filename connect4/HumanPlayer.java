package connect4;

import java.awt.*;

public class HumanPlayer extends Player
{

    
    public HumanPlayer(char c)
    {
    	super("Human", c);

    }

    public void hasTurn()
    {
        myTurn = true;
    }
	
    // Called automatically when the mouse button is released
    public void move(int x) {
    	game.makeMove(x);
        myTurn = false;
    }


}
