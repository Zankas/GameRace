package gui;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JPanel;


public class JpanelDialog extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JpanelDialog() {
		super();
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
	
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(ImageProvider.getMenuDialog(), 0, 0, null);
	}
}
