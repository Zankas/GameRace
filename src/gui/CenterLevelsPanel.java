package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class CenterLevelsPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<JButton> levelSave;
	private JPanel supportLevesPanel;
	private GamePanel gamePanel;
	private Dimension sizeBorder;
	private GridBagConstraints constraints;
	
	
public CenterLevelsPanel() {
		
	this.setLayout(new GridBagLayout());
	this.setOpaque(false);
	sizeBorder=new Dimension(200,200);
	
	levelSave=new ArrayList<JButton>();
	supportLevesPanel=new JPanel();
	supportLevesPanel.setOpaque(false);
	
	supportLevesPanel.setLayout(new GridBagLayout());
	
	constraints=new GridBagConstraints();
	constraints.insets=new Insets(20, 20, 20, 20);
	constraints.gridx=0;
    constraints.gridy=0;
    
	this.add(supportLevesPanel);
	this.setVisible(true);
	}

public void loadLevelSave(String pathFolderLevel,MenuFrame frame){
	
	File folder = new File(pathFolderLevel);
	
    String[] array = folder.list();
    
    
    for (int i = 0; i < array.length; i++) {

    	
    	JButton tmp=new JButton();
    	String tmpName=array[i].substring(0, array[i].length()-4);
    	String nameTrack=pathFolderLevel+array[i];
    	
    	tmp.setIcon(new ImageIcon(ImageProvider.getLevel1()));
    	tmp.setIconTextGap((int)-((sizeBorder.width/2)+((array[i].length()-4)*4.5)));
    	tmp.setContentAreaFilled(false);
    	tmp.setOpaque(false);
    	tmp.setBorder(null);
    	tmp.setRolloverIcon(new ImageIcon(ImageProvider.getLevel2()));
    	tmp.setFocusPainted(false);
    	tmp.setForeground(Color.GREEN);
    	
        Font customFont = null;
        try {
            InputStream myStream = new BufferedInputStream(new FileInputStream("Eras Bold ITC.ttf"));
             customFont =Font.createFont(Font.TRUETYPE_FONT, myStream).deriveFont(Font.PLAIN, 15);
             tmp.setFont(customFont);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Custom font not loaded.");
        }
    	
    	tmp.setText(array[i].substring(0, array[i].length() - 4));
    	tmp.setToolTipText("Track "+array[i].substring(0, array[i].length() - 4));
		tmp.setPreferredSize(sizeBorder);
		tmp.setMinimumSize(sizeBorder);
		tmp.setMaximumSize(sizeBorder);
		tmp.setSize(sizeBorder);
		tmp.setLayout(null);
		
	
    	levelSave.add(tmp);
    	
		if(constraints.gridx==4){
			constraints.gridx=0;
			constraints.gridy++;
		}
		constraints.gridx++;
		
    	supportLevesPanel.add(levelSave.get(i),constraints);
    	
    	tmp.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
				System.gc();
				gamePanel=new GamePanel(nameTrack,tmpName,frame.getMenuPanel().getPlayMenuPanel().getLevelsPanel(),frame);
				frame.setContentPane(gamePanel);
				gamePanel.requestFocus();
				gamePanel.updateUI();
				System.gc();
				
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
				// do nothing
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// do nothing
			}
		});
    }
}
	
}