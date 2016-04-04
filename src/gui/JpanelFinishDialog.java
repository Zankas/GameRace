package gui;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

public class JpanelFinishDialog extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JpanelFinishDialog() {
		super();
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
	
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(ImageProvider.getMenuFinishDialog(), 0, 0, null);
	}
}
