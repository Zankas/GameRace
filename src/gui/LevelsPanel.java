package gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class LevelsPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CenterLevelsPanel centerLevelsPanel;
	private JScrollPane jScrollPane;
	private JPanel supportCenterPanel;
	private JPanel jPanelButtonBack;
	private JButton back;
	private Cursor cursorCustom;
	
	public LevelsPanel(MenuFrame frame,PlayMenuPanel playMenuPanel) {
		
		this.setLayout(new BorderLayout());
		cursorCustom = Toolkit.getDefaultToolkit().createCustomCursor(ImageProvider.getCursor(), new Point(0,0),"cursorCustom");
		this.setCursor(cursorCustom);
		centerLevelsPanel=new CenterLevelsPanel();
		
		jScrollPane=new JScrollPane(centerLevelsPanel);
		
		jScrollPane.setOpaque(false);
		jScrollPane.setBorder(null);
		jScrollPane.getViewport().setOpaque(false);
		jScrollPane.getViewport().setBorder(null);
		
		jScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI());
		jScrollPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI());
		
		jScrollPane.getVerticalScrollBar().setBorder(null);
		jScrollPane.getVerticalScrollBar().setUnitIncrement(20);
		jScrollPane.getVerticalScrollBar().setBlockIncrement(20);
		jScrollPane.getVerticalScrollBar().setOpaque(false);
		
		back=new JButton(new ImageIcon(ImageProvider.getGoback()));
		back.setContentAreaFilled(false);
    	back.setOpaque(false);
    	back.setBorder(null);
    	back.setFocusPainted(false);
    	back.setRolloverIcon(new ImageIcon(ImageProvider.getGobackPressed()));
		
		supportCenterPanel=new JPanel();
		
		jPanelButtonBack=new JPanel();
		
		jPanelButtonBack.setLayout(new GridBagLayout());
		jPanelButtonBack.setOpaque(false);
		jPanelButtonBack.add(back);
		
		supportCenterPanel.setLayout(new BorderLayout());
		supportCenterPanel.setOpaque(false);
		
		supportCenterPanel.add(jScrollPane,BorderLayout.CENTER);
		supportCenterPanel.add(jPanelButtonBack,BorderLayout.PAGE_END);
		
		this.add(supportCenterPanel);
		this.setVisible(true);
		
		back.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
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
				//do nothing
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//do nothing
			}
		});
	}
	
	
	public JScrollPane getjScrollPane() {
		return jScrollPane;
	}



	public JPanel getSupportCenterPanel() {
		return supportCenterPanel;
	}



	public JPanel getjPanelButtonBack() {
		return jPanelButtonBack;
	}



	public JButton getBack() {
		return back;
	}



	public Cursor getCursorCustom() {
		return cursorCustom;
	}



	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ImageProvider.getBackground(),0,0,null);
	}
	public CenterLevelsPanel getCenterLevelsPanel() {
		return centerLevelsPanel;
	}


}