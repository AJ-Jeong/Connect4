package connect4;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {

	private Game game;
	private ImageIcon[] btnNorIcon, btnHovIcon, btnDisIcon;
	private JButton[] buttonMenu;

	public ControlPanel(Game game) {

		setPreferredSize( new Dimension( 185 ,420 ) );
        setBackground( Color.WHITE );
        setLayout(null);
		this.game = game;
		setButton();
		
		String str = "<HTML> Author: Alex Jeong<BR>Lynbrook High School<BR>";
		str += "AP Computer Science<BR>";
		str += "Final Project : Connect4 </HTML>";
		str += "May 2021</HTML>";
		JLabel credit = new JLabel(str);
		credit.setBounds(20,330,165,100);
		credit.setFont(new Font("Arial", Font.PLAIN, 15));
        add(credit);
				
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent( g );

	}
	private void setButton() {
		
		btnNorIcon = new ImageIcon[3];
		btnHovIcon = new ImageIcon[3];
		btnDisIcon = new ImageIcon[3];
		buttonMenu = new JButton[3];
	
		for (int i=0 ; i < 3; i++) {
			
			btnNorIcon[i] = new ImageIcon("src/images/btnNor"+i+".png");
			btnHovIcon[i] = new ImageIcon("src/images/btnHov"+i+".png");
			btnDisIcon[i] = new ImageIcon("src/images/btnDis"+i+".png");
			buttonMenu[i] = new JButton( btnNorIcon[i]);
			buttonMenu[i].setBorderPainted(false);
	        buttonMenu[i].setBounds(20,100*i+30,165,100);
			buttonMenu[i].addMouseListener(new MyListener());
	        add(buttonMenu[i]);
		
		}
	}

    class MyListener implements MouseListener {
    	
		@Override
		public void mouseEntered(MouseEvent e) {
			
			for (int i=0; i<3 ; i++) {
				if(e.getSource() == buttonMenu[i]) {
					buttonMenu[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
					buttonMenu[i].setIcon(btnHovIcon[i]);
				}
			}

		}
		@Override
		public void mouseExited(MouseEvent e) {
			
			for (int i=0; i<3 ; i++) {
				if(e.getSource() == buttonMenu[i]) {
					buttonMenu[i].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					buttonMenu[i].setIcon(btnNorIcon[i]);
				}
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			if (e.getSource() == buttonMenu[0]) {
				game.newGame();
			}
			else if (e.getSource() == buttonMenu[1]) {
				game.moveBack();
			}
			else if (e.getSource() == buttonMenu[2]) {
				System.exit(0);
			}
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
    }
	
}
