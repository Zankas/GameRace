package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel title;
	private JButton back;
	private JPanel panelCenter;
	private JPanel panelSud;
	private SoundGame soundGame;
	private Font font;
	private GridBagConstraints constraints;
	private Dimension dimension;
	
	public InfoPanel(MenuPanel menuPanel, MenuFrame frame) {
		
		this.setLayout(new BorderLayout());
		this.setFocusable(true);
		this.setBackground(Color.YELLOW.brighter());
		this.setDoubleBuffered(true);
		this.dimension=frame.getDim();
		
        font=frame.getFont();
        
		constraints=new GridBagConstraints();
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.insets.set(20, 20, 20, 20);
		
		panelCenter=new JPanel();
		panelCenter.setLayout(new GridBagLayout());
		panelSud=new JPanel();
		panelSud.setLayout(new GridBagLayout());

		
		title=new JLabel("Info");
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
		
		panelCenter.setOpaque(false);
		
		panelSud.setOpaque(false);
		
		constraints.gridy++;
		panelSud.add(title,constraints);
		
		this.add(panelCenter,BorderLayout.CENTER);
		this.add(panelSud,BorderLayout.NORTH);
		this.add(new JPanel().add(back),BorderLayout.PAGE_END);
		
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
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		g.drawImage(ImageProvider.getBackground(),0,0,null);
		
		g.drawImage(ImageProvider.getPanelInfo(), (dimension.width/2)-(ImageProvider.getPanelInfo().getWidth(this)/2), (dimension.height-ImageProvider.getPanelInfo().getHeight(this))/2, null);
		
		g.drawImage(ImageProvider.getArrows(),(dimension.width/2)-(ImageProvider.getPanelInfo().getWidth(this)/2)+20, ((dimension.height-ImageProvider.getPanelInfo().getHeight(this))/2)+30, null);
	}
}
