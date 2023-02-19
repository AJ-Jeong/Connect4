package connect4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//import dynamic_beat_13.Music;

public class IntroPanel extends JPanel {

	Image intro_image;
	private ImageIcon quitButtonEnteredImage, quitButtonBasicImage, startButtonEnteredImage, startButtonBasicImage;
	private JButton quitButton, startButton;
	private Connect4 frame;

	public IntroPanel(Connect4 frame) {
		setPreferredSize( new Dimension( 670,420 ) );
		intro_image = new ImageIcon("src/images/connect4.jpg").getImage();
		this.frame = frame;
		setLayout( null );
		setButton();

	}

	public void paintComponent(Graphics g) {
		super.paintComponent( g );
		//		g.drawImage(intro_image, 0, 0, null);
		//이미지를 그려준다. 이미지객체,시작x,시작y,가로,세로,보여줄 옵저버;
		g.drawImage(intro_image,0,0, 670,420,this);
		//g객체에 폰트스타일을 바꾸어준다. 이코드에서는 굵은글씨에 30포인트


	}
	private void setButton() {

		quitButtonEnteredImage = new ImageIcon("src/images/quit_hover.png");
		quitButtonBasicImage = new ImageIcon("src/images/quit_normal.png");
		startButtonEnteredImage = new ImageIcon("src/images/start_hover.png");
		startButtonBasicImage = new ImageIcon("src/images/start_normal.png");
		quitButton = new JButton(quitButtonBasicImage);
		startButton = new JButton(startButtonBasicImage);

		quitButton.setBounds(400, 400, 209, 96);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

			}
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {

				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				//				JOptionPane.showMessageDialog(this, "Bye!", "정보", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		});
		add(quitButton);


		startButton.setBounds(40, 400, 248, 96);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				//				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				//				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				frame.changeToboard();
			}
		});
		add(startButton);
	}


}



