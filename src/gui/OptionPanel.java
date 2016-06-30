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
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OptionPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel title;
	private JButton back;
	
	private JCheckBox sound;
	private JCheckBox wasd;
	private JPanel panelCenter;
	private JPanel panelNord;
	private SoundGame soundGame;
	private JCheckBox screen;
	
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
		constraints.insets.set(15, 15, 15, 15);
		constraints.anchor = GridBagConstraints.WEST;
		
		panelCenter=new JPanel();
		panelCenter.setLayout(new GridBagLayout());
		panelCenter.setBackground(Color.ORANGE);
			
		panelNord=new JPanel();
		panelNord.setLayout(new GridBagLayout());
		panelNord.setOpaque(false);
		
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
		
		sound=new JCheckBox("Sound");
		sound.setContentAreaFilled(false);
    	sound.setOpaque(false);
    	sound.setBorder(null);
    	sound.setFocusPainted(false);
    	sound.setToolTipText("Select/Deselect sound");    
    	
    	
    	
		if(menuPanel.isSound()){
			sound.setSelected(true);
			sound.setIcon(new ImageIcon(ImageProvider.getSelectedCheckBox()));
		}
		else{
			sound.setSelected(false);
			sound.setIcon(new ImageIcon(ImageProvider.getDeselectedCheckBox()));
		}
		
		wasd=new JCheckBox("WASD keys");
		wasd.setContentAreaFilled(false);
    	wasd.setOpaque(false);
    	wasd.setBorder(null);
    	wasd.setFocusPainted(false);
    	wasd.setToolTipText("You can use WASD to move the car or use the arrows by selecting/deselecting the button"); 
    	wasd.setSelected(menuPanel.isWASD());
    	wasd.setFont(font);
		
	
		constraints.gridy++;
		panelNord.add(title,constraints);
		
		
		sound.setFont(font);
		
		
		screen=new JCheckBox("Fullscreen");
		
		screen.setBorder(null);
		screen.setFocusPainted(false);
		screen.setContentAreaFilled(false);
		screen.setOpaque(false);
		screen.setToolTipText("Select/Deselect fullscreen");    	
		
		screen.setFont(font);
		
		if(frame.isFullScreen()){
			screen.setSelected(true);
			screen.setIcon(new ImageIcon(ImageProvider.getSelectedCheckBox()));

		}
		else{
			screen.setSelected(false);
			screen.setIcon(new ImageIcon(ImageProvider.getDeselectedCheckBox()));

		}
		
		panelCenter.add(sound,constraints);
		constraints.gridy++;
		panelCenter.add(screen,constraints);
		constraints.gridy++;
		panelCenter.add(wasd,constraints);
		
		screen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.resizeFrame();
				if(frame.isFullScreen()){
					screen.setIcon(new ImageIcon(ImageProvider.getSelectedCheckBox()));

				}else{
					screen.setIcon(new ImageIcon(ImageProvider.getDeselectedCheckBox()));

				}
			}
		});

		JPanel panelSupport=new JPanel();
		panelSupport.setLayout(new GridBagLayout());
		panelSupport.setOpaque(false);
		panelSupport.add(panelCenter);
		this.add(panelSupport,BorderLayout.CENTER);
		this.add(panelNord,BorderLayout.NORTH);
		
		this.add(new JPanel().add(back),BorderLayout.PAGE_END);
		
		
		
		
		sound.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(sound.isSelected()){
					SoundProvider.getMusic().play();
					menuPanel.setSound(true);
					sound.setIcon(new ImageIcon(ImageProvider.getSelectedCheckBox()));
				}
				else {
					SoundProvider.getMusic().pause();
					menuPanel.setSound(false);
					sound.setIcon(new ImageIcon(ImageProvider.getDeselectedCheckBox()));
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
		
		if(menuPanel.isWASD()){
			wasd.setSelected(true);
			wasd.setIcon(new ImageIcon(ImageProvider.getSelectedCheckBox()));
		}
		else{
			wasd.setSelected(false);
			wasd.setIcon(new ImageIcon(ImageProvider.getDeselectedCheckBox()));
		}
		
		wasd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(menuPanel.isWASD()){
					menuPanel.setWasd(false);
					wasd.setSelected(false);
					wasd.setIcon(new ImageIcon(ImageProvider.getDeselectedCheckBox()));

				}
				else
				{
					menuPanel.setWasd(true);
					wasd.setSelected(true);
					wasd.setIcon(new ImageIcon(ImageProvider.getSelectedCheckBox()));

				}
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		g.drawImage(ImageProvider.getBackground(),0,0,null);
		
	}

}
