package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoadFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoadFrame() {
		
		this.setTitle("Loading GameRace");
		this.setUndecorated(true);
		JPanel panel=new JPanel();
		panel.setBackground(Color.ORANGE.brighter());
		panel.setLayout(new GridBagLayout());
		JLabel label=new JLabel(new ImageIcon("resource/img/giphy.gif"));
		GridBagConstraints constraints=new GridBagConstraints();
		constraints.gridx=0;
		constraints.gridy=0;
		panel.add(label,constraints);
		constraints.gridy++;
		panel.add(new JLabel("Loading ..."),constraints);
		
		this.setContentPane(panel);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
