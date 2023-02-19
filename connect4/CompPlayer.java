package connect4;

public class CompPlayer extends Player {
	
	public int gameLevel; 
    
    public CompPlayer(char c)
    {
    	super("Comp", c);
    	this.gameLevel = 5;

    }
    
    public CompPlayer(char c, int lv)
    {
    	super("Comp", c);
    	this.gameLevel = lv;

    }

    public void hasTurn()
    {
        myTurn = true;
        Strategy ai = new Strategy(game, color);

        int posX = ai.bestMoveForComputer(gameLevel);

        move(posX);
        
    }
	
    public void move(int x) {

    	game.makeMove(x);
        myTurn = false;
    }
    
    public void setLevel(int level) {
    	
    	gameLevel = level;
    }
    
    public int getLevel() {
    	
    	return gameLevel;
    }
    


}
