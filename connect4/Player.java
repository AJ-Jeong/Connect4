package connect4;

public class Player {
	
	public String name;
	public int score;
    public char color;
	public boolean myTurn;
	public Game game;
	
	public Player(String name, char c) {
		this.name = name;
		this.score = 0;
		this.myTurn = false;
		this.color = c;
	}
	
	public void init(Game g) {
    	this.game = g;	
	}
	

	public void hasTurn()
	{
		
	}
	
	public void makeMove() {
		
	}
    public void move(int col) {

    	myTurn = false;
    }
	
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}

    public void setColor(char c) {
    	this.color = c;
    }
    
	public char getColor() {
		return color;
	}



}
