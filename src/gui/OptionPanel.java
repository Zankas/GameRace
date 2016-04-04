package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class OptionPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel title;
	private JButton back;
	
	private JRadioButton sound;
	private JRadioButton wasd;
	private JPanel panelCenter;
	private JPanel panelSud;
	private SoundGame soundGame;
	private JRadioButton screen;
	
	private Font font;
	
	private GridBagConstraints constraints;
	
	public OptionPanel(MenuPanel menuPanel, MenuFrame frame) {
		
		this.setLayout(new BorderLayout());
		this.setFocusable(true);
		this.setBackground(Color.YELLOW.brighter());
		this.setDoubleBuffered(true);
		
		
        try {
            InputStream myStream = new BufferedInputStream(new FileInputStream("ARB 85 Poster Script JAN-39 FRE.ttf"));
             font =Font.createFont(Font.TRUETYPE_FONT, myStream).deriveFont(Font.PLAIN, 40);
        } catch (Exception e) {
            e.printStackTrace();
            font=new Font("ARIAL",10,40);
            System.err.println("Custom font not loaded.\nLoad default font.");
        }
        
		constraints=new GridBagConstraints();
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.insets.set(20, 20, 20, 20);
		
		panelCenter=new JPanel();
		panelCenter.setLayout(new GridBagLayout());
		panelSud=new JPanel();
		panelSud.setLayout(new GridBagLayout());

		
		title=new JLabel("Options");
		title.setFont(font);
		back=new JButton(new ImageIcon(ImageProvider.getGoback()));
		soundGame=new SoundGame();
		Cursor cursorCustom = Toolkit.getDefaultToolkit().createCustomCursor(ImageProvider.getCursor(), 
				new Point(0,0),"cursorCustom");
		this.setCursor(cursorCustom);

		back.setContentAreaFilled(false);
		back.setOpaque(false);
    	back.setBorder(null);
    	back.setFocusPainted(false);
    	back.setToolTipText("Come back");
    	back.setRolloverIcon(new ImageIcon(ImageProvider.getGobackPressed()));
		
		sound=new JRadioButton("Sound");
//		sound.setContentAreaFilled(false);
//    	sound.setOpaque(false);
    	sound.setBorder(null);
    	sound.setFocusPainted(false);
    	sound.setBackground(Color.ORANGE);
    	sound.setToolTipText("Select/Deselect sound");    
    	
    	
    	
		if(menuPanel.isSound())
			sound.setSelected(true);
		else
			sound.setSelected(false);
		
		wasd=new JRadioButton("WASD to drive your car");
//		wasd.setContentAreaFilled(false);
//    	wasd.setOpaque(false);
    	wasd.setBorder(null);
    	wasd.setFocusPainted(false);
    	wasd.setBackground(Color.ORANGE);
    	wasd.setToolTipText("You can use WASD to move the car or use the arrows by selecting/deselecting the button"); 
    	wasd.setSelected(menuPanel.isWASD());
		
		panelCenter.setOpaque(false);
		
		
		panelSud.setOpaque(false);
		
	
		constraints.gridy++;
		panelSud.add(title,constraints);
		
		
		sound.setFont(font);
		panelCenter.add(sound);
		
		screen=new JRadioButton("Fullscreen");
		
		screen.setBorder(null);
		screen.setFocusPainted(false);
		screen.setBackground(Color.ORANGE);
		screen.setToolTipText("Select/Deselect fullscreen");    	
		
		screen.setFont(font);
		
		if(frame.isFullScreen())
			screen.setSelected(true);
		else
			screen.setSelected(false);
		
		panelCenter.add(screen,constraints);
		
		constraints.gridy++;
		wasd.setFont(font);
		panelCenter.add(wasd,constraints);
		
		screen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.resizeFrame();
			}
		});

		this.add(panelCenter,BorderLayout.CENTER);
		this.add(panelSud,BorderLayout.NORTH);
		this.add(new JPanel().add(back),BorderLayout.PAGE_END);
		sound.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(sound.isSelected()){
					SoundProvider.getMusic().play();
					menuPanel.setSound(true);
				}
				else {
					SoundProvider.getMusic().pause();
					menuPanel.setSound(false);
				}
			}
		});
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				soundGame.soundPlay(soundGame.getSoundBottonClick());
				
				frame.setJMenuBar(null);
				frame.setContentPane(menuPanel);
				menuPanel.updateUI();
				menuPanel.requestFocus();
				
			}
		});
		
		wasd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(menuPanel.isWASD()){
					menuPanel.setWasd(false);
					wasd.setSelected(false);
				}
				else
				{
					menuPanel.setWasd(true);
					wasd.setSelected(true);
				}
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		g.drawImage(ImageProvider.getBackground(),0,0,null);
		
	}

}
