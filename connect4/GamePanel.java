package connect4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private Connect4 frame;
	private ControlPanel cPanel;
	private BoardPanel bPanel;
	
	private Game game;
//    private Player players[];
//    private int currentPlayer;
//    private int gameLevel;
		

	public GamePanel(Game game) {
        setPreferredSize( new Dimension( 595,420 ) );
		this.frame = frame;
		
//        HumanPlayer human = new HumanPlayer( 'r' );
//        CompPlayer comptuer = new CompPlayer( 'b' );
//        players = new Player[2];
//        players[0] = human;
//        players[1] = comptuer;
        
		this.bPanel = new BoardPanel(game);
//        game = new Game(bPanel, players);
		this.cPanel = new ControlPanel(game);
		
		setLayout( new BorderLayout());
		this.add(cPanel, BorderLayout.WEST);
		this.add(bPanel, BorderLayout.CENTER);

				
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent( g );


	}
}
