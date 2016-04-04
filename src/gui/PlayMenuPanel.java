package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PlayMenuPanel extends JPanel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LevelsPanel levelsPanel;
	private JButton basicLevels;
	private JButton tracksCreated;
	private JButton back;
	private Dimension dimSizeLabel;
	private GridBagConstraints constraints;
	private Cursor cursorCustom;
	private SoundGame soundGame;
	
	private String levelsBasic="resource/levelsBasic/";
	private String levelsCreated="resource/levelSave/";
	
	public PlayMenuPanel(MenuFrame frame,MenuPanel menuPanel) {
		
		
		
		this.setFocusable(true);
		this.setBackground(Color.BLUE.brighter());
		this.setDoubleBuffered(true);
		back=new JButton(new ImageIcon(ImageProvider.getGoback()));
		back.setContentAreaFilled(false);
		back.setOpaque(false);
		back.setBorder(null);
		back.setRolloverIcon(new ImageIcon(ImageProvider.getGobackPressed()));
		
		basicLevels=new JButton(new ImageIcon(ImageProvider.getBasiclevel1()));
		
		basicLevels.setContentAreaFilled(false);
		basicLevels.setOpaque(false);
		basicLevels.setBorder(null);
		basicLevels.setRolloverIcon(new ImageIcon(ImageProvider.getBasiclevel2()));
		
		tracksCreated=new JButton(new ImageIcon(ImageProvider.getTrackscreated1()));
		tracksCreated.setContentAreaFilled(false);
		tracksCreated.setOpaque(false);
		tracksCreated.setBorder(null);
		tracksCreated.setRolloverIcon(new ImageIcon(ImageProvider.getTrackscreated2()));
		
		constraints=new GridBagConstraints();
		constraints.insets=new Insets(menuPanel.resizeX(100),menuPanel.resizeY(100), menuPanel.resizeX(100), menuPanel.resizeY(100));
		
		dimSizeLabel=new Dimension(200,200);
		cursorCustom = Toolkit.getDefaultToolkit().createCustomCursor(ImageProvider.getCursor(), 
				new Point(0,0),"cursorCustom");
		this.setCursor(cursorCustom);
		soundGame=new SoundGame();
		
		basicLevels.setPreferredSize(dimSizeLabel);
		basicLevels.setMinimumSize(dimSizeLabel);
		basicLevels.setMaximumSize(dimSizeLabel);
		basicLevels.setSize(dimSizeLabel);
		basicLevels.setLayout(null);
		
		
		tracksCreated.setPreferredSize(dimSizeLabel);
		tracksCreated.setMinimumSize(dimSizeLabel);
		tracksCreated.setMaximumSize(dimSizeLabel);
		tracksCreated.setSize(dimSizeLabel);
		tracksCreated.setLayout(null);
	
		basicLevels.setToolTipText("Play at basic levels");
		tracksCreated.setToolTipText("Play levels created");
		back.setToolTipText("Come back");
	
		JPanel panelNord=new JPanel();
		panelNord.setOpaque(false);
		panelNord.add(back);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		JPanel panelCenter=new JPanel();
		panelCenter.setOpaque(false);
		panelCenter.setLayout(new GridBagLayout());
		panelCenter.add(basicLevels,constraints);
		panelCenter.add(tracksCreated,constraints);
		
		this.add(panelCenter,BorderLayout.CENTER);
		this.add(panelNord,BorderLayout.PAGE_END);
		
		basicLevels.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				soundGame.soundPlay(soundGame.getSoundBottonClick());
				
				levelsPanel=new LevelsPanel(frame,PlayMenuPanel.this);
				
				levelsPanel.getCenterLevelsPanel().loadLevelSave(levelsBasic,frame);
				frame.setContentPane(levelsPanel);
				levelsPanel.updateUI();
				levelsPanel.getjScrollPane().requestFocus();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				//do nothing
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				//do nothing
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				soundGame.soundPlay(soundGame.getSoundBottonPop());
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//do nothing
			}
		});
		
		tracksCreated.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				soundGame.soundPlay(soundGame.getSoundBottonClick());
				

				levelsPanel=new LevelsPanel(frame,PlayMenuPanel.this);
				
				levelsPanel.getCenterLevelsPanel().loadLevelSave(levelsCreated,frame);
				frame.setContentPane(levelsPanel);
				levelsPanel.updateUI();
				levelsPanel.getjScrollPane().requestFocus();
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				//do nothing
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				//do nothing
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				soundGame.soundPlay(soundGame.getSoundBottonPop());
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//do nothing
			}
		});
		
		back.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
				soundGame.soundPlay(soundGame.getSoundBottonClick());

				frame.setJMenuBar(null);
				frame.setContentPane(menuPanel);
				menuPanel.updateUI();
				menuPanel.requestFocus();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				//do nothing
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				//do nothing
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				soundGame.soundPlay(soundGame.getSoundBottonPop());
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//do nothing
			}
		});
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ImageProvider.getBackground(),0,0,null);
	}
	public String getLevelsBasic() {
		return levelsBasic;
	}
	public String getLevelsCreated() {
		return levelsCreated;
	}
	public LevelsPanel getLevelsPanel() {
		return levelsPanel;
	}
	public JButton getBasicLevels() {
		return basicLevels;
	}
	public JButton getTracksCreated() {
		return tracksCreated;
	}
	public JButton getBack() {
		return back;
	}
	public Dimension getDimSizeLabel() {
		return dimSizeLabel;
	}
	public GridBagConstraints getConstraints() {
		return constraints;
	}
	public Cursor getCursorCustom() {
		return cursorCustom;
	}
	public SoundGame getSoundGame() {
		return soundGame;
	}
	
	
}
