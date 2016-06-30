package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import editor.EditorPanel;

public class MenuPanel extends JPanel implements MouseMotionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Toolkit tk;
	private Dimension dim;
	private Dimension sizeTitle;
	
	private JPanel centerPanel;
	private OptionPanel optionPanel;
	private GridBagConstraints constraints;
	private PlayMenuPanel playMenuPanel;
	private InfoPanel infoPanel;
	
	private JLabel title;
	private JButton play;
	private JButton editor;
	private JButton options;
	private JButton esc;
	private JPanel panelDown;
	private JLabel labelDown;
	private JButton info;
	
	private EditorPanel editorPanel;
	
	private Cursor cursorCustom;
	
	private SoundGame soundGame;
	
	private boolean sound;
	private boolean wasd;
	
	
	public MenuPanel(MenuFrame frame) {
		
		this.setFocusable(true);
		this.setBackground(Color.BLUE.brighter());
		this.setDoubleBuffered(true);
		this.setLayout(new BorderLayout());
		
		wasd=false;
		
		tk=Toolkit.getDefaultToolkit();
		dim=tk.getScreenSize();
		sizeTitle=new Dimension(resizeX(740),resizeY(250));
		
		centerPanel=new JPanel();
		centerPanel.setLayout(new GridBagLayout());
		constraints=new GridBagConstraints();
		constraints.insets=new Insets(resizeX(20),resizeY(20), resizeX(20), resizeY(20));
		constraints.gridx=0;
		constraints.gridy=0;
		panelDown=new JPanel();
		title=new JLabel(new ImageIcon(ImageProvider.getTitle()));
		play=new JButton(new ImageIcon(ImageProvider.getPlay1()));
		editor=new JButton(new ImageIcon(ImageProvider.getEditor2()));
		options=new JButton(new ImageIcon(ImageProvider.getOptions2()));
		esc=new JButton(new ImageIcon(ImageProvider.getEsc2()));
		info=new JButton(new ImageIcon(ImageProvider.getInfo()));
		
		title.setPreferredSize(sizeTitle);
		title.setMinimumSize(sizeTitle);
		title.setMaximumSize(sizeTitle);
		title.setSize(sizeTitle);
		title.setLayout(null);
		
		play.setContentAreaFilled(false);
    	play.setOpaque(false);
    	play.setBorder(null);
    	play.setRolloverIcon(new ImageIcon(ImageProvider.getPlay2()));
    	play.setFocusPainted(false);
    	
    	editor.setContentAreaFilled(false);
    	editor.setOpaque(false);
    	editor.setBorder(null);
    	editor.setRolloverIcon(new ImageIcon(ImageProvider.getEditor1()));
		editor.setFocusable(false);
		
		options.setContentAreaFilled(false);
    	options.setOpaque(false);
    	options.setBorder(null);
    	options.setRolloverIcon(new ImageIcon(ImageProvider.getOptions1()));
		options.setFocusable(false);
		
		esc.setContentAreaFilled(false);
    	esc.setOpaque(false);
    	esc.setBorder(null);
    	esc.setRolloverIcon(new ImageIcon(ImageProvider.getEsc1()));
		esc.setFocusable(false);
		
		info.setContentAreaFilled(false);
    	info.setOpaque(false);
    	info.setBorder(null);
		info.setFocusable(false);
		
		labelDown=new JLabel(" Beta Version 6.0");
		
		cursorCustom = Toolkit.getDefaultToolkit().createCustomCursor(ImageProvider.getCursor(), 
				new Point(0,0),"cursorCustom");
		this.setCursor(cursorCustom);
		
		soundGame=new SoundGame();
		sound=true;
		
//		try {
//	        File file=new File("resource/fileSound/Trackmania United - Stadium - Tictac remix.wav");
//	         AudioInputStream audioIn = AudioSystem.getAudioInputStream(file); 
//	         
//	         clip = AudioSystem.getClip();
//	         clip.open(audioIn);
//	         clip.start();
//	         clip.loop(Clip.LOOP_CONTINUOUSLY);
//	      } catch (UnsupportedAudioFileException e) {
//	         e.printStackTrace();
//	      } catch (IOException e) {
//	         e.printStackTrace();
//	      } catch (LineUnavailableException e) {
//	         e.printStackTrace();
//	      }
		
		SoundProvider.getMusic().play();
		SoundProvider.getMusic().setRepeat(true);
		
		centerPanel.setOpaque(false);
		panelDown.setOpaque(false);
		
		play.setToolTipText("Click to play");
		editor.setToolTipText("Click to create a track");
		esc.setToolTipText("Click to exit");
		options.setToolTipText("Click to change settings");
		info.setToolTipText("Info");
		
		panelDown.add(labelDown);
		panelDown.add(info);
		labelDown.setForeground(Color.BLACK.darker().darker());
		labelDown.setFont(new Font("TimesRoman", Font.BOLD, 16));
		
		centerPanel.add(play,constraints);
		constraints.gridx++;
		centerPanel.add(editor,constraints);
		constraints.gridy++;
		constraints.gridx=0;
		centerPanel.add(options,constraints);
		constraints.gridx++;
		centerPanel.add(esc,constraints);

		this.add(title,BorderLayout.PAGE_START);
		this.add(centerPanel,BorderLayout.CENTER);
		
		
		this.add(panelDown,BorderLayout.PAGE_END);//labelDown
		
        centerPanel.addMouseMotionListener(this);
        title.addMouseMotionListener(this);
		play.addMouseMotionListener(this);
		editor.addMouseMotionListener(this);
		options.addMouseMotionListener(this);
		esc.addMouseMotionListener(this);
		
		labelDown.addMouseMotionListener(this);
		info.addMouseMotionListener(this);
		this.addMouseMotionListener(this);
		
		
		this.setVisible(true);
		
		play.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
				soundGame.soundPlay(soundGame.getSoundBottonClick());
				if(playMenuPanel==null)
					playMenuPanel=new PlayMenuPanel(frame, MenuPanel.this);
				frame.setContentPane(playMenuPanel);
				playMenuPanel.updateUI();
				playMenuPanel.requestFocus();
				
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
		
		editor.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				soundGame.soundPlay(soundGame.getSoundBottonClick());
			
				editorPanel=new EditorPanel(frame);

				frame.setContentPane(editorPanel);
				editorPanel.requestFocus();
				editorPanel.updateUI();
				
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
		
		options.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				soundGame.soundPlay(soundGame.getSoundBottonClick());
				createOptionPanel(frame);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// do nothing
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// do nothing
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				soundGame.soundPlay(soundGame.getSoundBottonPop());
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// do nothing
			}
		});
		
		esc.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
				soundGame.soundPlay(soundGame.getSoundBottonClick());
					
					int option = JOptionPane.showConfirmDialog(MenuPanel.this,
							"Do you really want to exit?", "Exit",
							JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						System.exit(0);
					}
					
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// do nothing
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// do nothing
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				soundGame.soundPlay(soundGame.getSoundBottonPop());
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// do nothing
			}
		});
		
		
		info.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
				soundGame.soundPlay(soundGame.getSoundBottonClick());
				
				infoPanel=new InfoPanel( MenuPanel.this,frame);
				frame.setContentPane(infoPanel);
				infoPanel.updateUI();
				infoPanel.requestFocus();
				
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

	private void createOptionPanel(MenuFrame frame)  {
		
		optionPanel=new OptionPanel(this,frame);
		frame.setContentPane(optionPanel);
		optionPanel.updateUI();
		optionPanel.requestFocus();
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// do nothing
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// do nothing
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		g.drawImage(ImageProvider.getBackground(),0,0,null);
		
	}
	
	public int resizeX(int x){
		return ((int)dim.width*x)/1366;
	}
	public int resizeY(int y){
		return ((int)dim.height*y)/768;
	}

	public Toolkit getTk() {
		return tk;
	}

	public Dimension getDim() {
		return dim;
	}

	public Dimension getSizeTitle() {
		return sizeTitle;
	}

	public JPanel getCenterPanel() {
		return centerPanel;
	}

	public OptionPanel getOptionPanel() {
		return optionPanel;
	}

	public GridBagConstraints getConstraints() {
		return constraints;
	}

	public PlayMenuPanel getPlayMenuPanel() {
		return playMenuPanel;
	}

	public JLabel getTitle() {
		return title;
	}

	public JButton getPlay() {
		return play;
	}

	public JButton getEditor() {
		return editor;
	}

	public JButton getOptions() {
		return options;
	}

	public JButton getEsc() {
		return esc;
	}

	public JPanel getPanelDown() {
		return panelDown;
	}

	public JLabel getLabelDown() {
		return labelDown;
	}

	public JButton getInfo() {
		return info;
	}

	public EditorPanel getEditorPanel() {
		return editorPanel;
	}

	public Cursor getCursorCustom() {
		return cursorCustom;
	}

	public SoundGame getSoundGame() {
		return soundGame;
	}

	public boolean isSound() {
		return sound;
	}

	public void setSound(boolean sound) {
		this.sound = sound;
	}
	public boolean isWASD() {
		return wasd;
	}

	public void setWasd(boolean wasd) {
		this.wasd = wasd;
	}
}