package connect4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardPanel extends JPanel
{
	private Game game;
	public JLabel[] labelLoc;
	public JButton[] buttonPlayer, buttonCoin;

    private final int WIDTH = 7, HEIGHT = 6; // board dimensions
    private final int CELLSIZE = 70;

    
	private Image backgroundImage = new ImageIcon("src/images/blueboard70.png").getImage();
	private ImageIcon[] coinImageIcon = new ImageIcon[2];
	private Image[]  coinImage = new Image[2]; 

	public BoardPanel(Game game) {

        setPreferredSize( new Dimension( WIDTH * CELLSIZE, HEIGHT * CELLSIZE ) );
        setBackground( Color.WHITE );
        this.game = game;
        game.startGame(this);
        
		setLayout( null );
		
		labelLoc = new JLabel[2];
		buttonPlayer = new JButton[2];
		buttonCoin = new JButton[2];
		
		coinImageIcon[0] = new ImageIcon("src/images/yellow_coin.png");
		coinImageIcon[1] = new ImageIcon("src/images//red_coin.png");
		
		for (int i=0 ; i < 2; i++) {
			
			buttonCoin[i] = new JButton( coinImageIcon[i]);
			buttonCoin[i].setBounds(i*245,425,55,50);
			buttonCoin[i].setBorderPainted(false);
			buttonCoin[i].addActionListener(new MyListener());
	        add(buttonCoin[i]);
	        
			String temp = game.getPlayer(i).getName();
			if (temp.equals("Comp"))
				temp = temp + " Level: "+game.getPlayerLevel(i);
	        buttonPlayer[i] = new JButton(temp);
	        buttonPlayer[i].setHorizontalAlignment(JButton.CENTER);
	        buttonPlayer[i].setFont(new Font("Arial", Font.PLAIN, 20));
	        buttonPlayer[i].setBounds(i*245+50,430,195,50);
	        buttonPlayer[i].addActionListener(new MyListener());
	        add(buttonPlayer[i]);
	        
			labelLoc[i] = new JLabel("Ready!");
			labelLoc[i].setHorizontalAlignment(JLabel.CENTER);
			labelLoc[i].setFont(new Font("Arial", Font.PLAIN, 20));
			labelLoc[i].setBounds(i*245,480,245,50);
	        add(labelLoc[i]);
	        
	        coinImage[i] = coinImageIcon[i].getImage();
			
		}
      
	}
	
    public void update( Game game )
    {
        this.game = game;
        repaint();
    }
	
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        for (int x=0 ; x < WIDTH ; x++)
        	for (int y=0 ; y < HEIGHT ; y++)
        		g.drawImage(backgroundImage, x*CELLSIZE , y*CELLSIZE , null);
		
		for (int i = 0 ; i < HEIGHT; i++) {
			for (int j = 0 ; j < WIDTH ; j ++) {
				if (game.grid[j][i] == game.getPlayer(0).getColor())
					g.drawImage(coinImage[0], 10+ j*CELLSIZE , 10+ i*CELLSIZE, null);
				else if (game.grid[j][i] == game.getPlayer(1).getColor())
					g.drawImage(coinImage[1], 10+ j*CELLSIZE , 10+ i*CELLSIZE, null);

			}
		}


    }

    public Location getPos( int x, int y )
    {
        return new Location( x / CELLSIZE, y / CELLSIZE );
    }
    
    class MyListener implements ActionListener {
    	
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		
    		for (int i = 0 ; i < 2 ; i++) {
        		if(e.getSource() == buttonCoin[i] && game.isGameEnd () == true) {
        			game.changeColor();
        			coinImage[0] = coinImageIcon[1].getImage();
        			coinImage[1] = coinImageIcon[0].getImage();
        			ImageIcon temp = coinImageIcon[0];
        			coinImageIcon[0] = coinImageIcon[1];
        			coinImageIcon[1] = temp;
        			buttonCoin[0].setIcon(coinImageIcon[0]);
        			buttonCoin[1].setIcon(coinImageIcon[1]);
        			repaint();
        		}
        			
        		else if (e.getSource() == buttonPlayer[i]) {
        			game.changePlayerType(i);
        			String temp = game.getPlayer(i).getName();
        			if (temp.equals("Comp"))
        				temp = temp + " Level: "+game.getPlayerLevel(i);
        			buttonPlayer[i].setText(temp);

        	        if (game.isGameEnd () == false )
        	        	game.getPlayer(game.getCurrentPlayer()).hasTurn();

        		}
        			
        		
    			
    		}


    	}
    }
}



