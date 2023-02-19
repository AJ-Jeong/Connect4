package connect4;

import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class Connect4 extends JFrame {
	
	private IntroPanel intro;
	private BoardPanel board;
	private GamePanel gPanel;
	private ControlPanel cPanel;
	private Game game;
    private HumanPlayer players[];
    private int currentPlayer;

	public Connect4() {
		
		setTitle("Connect4");
		setVisible(true);
		setSize(670,550);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		
		game = new Game();
		intro = new IntroPanel(this);
		gPanel = new GamePanel(game);

		intro.setVisible(true);

		c.add(intro, BorderLayout.CENTER);
		
	}
	
	public void changeToboard() {
		Container c = getContentPane();
		c.removeAll();
		c.add(gPanel, BorderLayout.CENTER );
        revalidate();
        repaint();
		
	}
	


	public static void main(String[] args) {
		
        Connect4 window = new Connect4();
        window.setVisible( true );
	}

}
