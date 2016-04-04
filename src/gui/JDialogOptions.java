package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class JDialogOptions extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton buttonResume;
	private JButton buttonSound;
	private JButton buttonCloseDialog;
	private JButton buttonRestart;
	private JButton buttonEsc;
	private JButton buttonMenuTrack;
	
	private JPanel panel; 
	
	private GridBagConstraints constraints;

	public JDialogOptions(MenuFrame menuFrame, GamePanel gamePanel) {
		super(menuFrame);
		this.setUndecorated(true);
		this.setBackground(new Color(255, 255, 255, 0));
		
		buttonResume=new JButton(new ImageIcon(ImageProvider.getResume()));
		buttonRestart=new JButton(new ImageIcon(ImageProvider.getRestart()));
		buttonMenuTrack=new JButton(new ImageIcon(ImageProvider.getTracks()));
		buttonSound=new JButton(new ImageIcon(ImageProvider.getSound()));
		buttonCloseDialog=new JButton(new ImageIcon(ImageProvider.getxEsc()));
		buttonEsc=new JButton(new ImageIcon(ImageProvider.getEsc()));
		
		buttonResume.setContentAreaFilled(false);
		buttonResume.setOpaque(false);
		buttonResume.setBorder(null);
		buttonResume.setRolloverIcon(new ImageIcon(ImageProvider.getResumePressed()));
		
		buttonRestart.setContentAreaFilled(false);
		buttonRestart.setOpaque(false);
		buttonRestart.setBorder(null);
		buttonRestart.setRolloverIcon(new ImageIcon(ImageProvider.getRestartPressed()));
		
		buttonMenuTrack.setContentAreaFilled(false);
		buttonMenuTrack.setOpaque(false);
		buttonMenuTrack.setBorder(null);
		buttonMenuTrack.setRolloverIcon(new ImageIcon(ImageProvider.getTracksPressed()));
		
		buttonSound.setContentAreaFilled(false);
		buttonSound.setOpaque(false);
		buttonSound.setBorder(null);
		buttonSound.setRolloverIcon(new ImageIcon(ImageProvider.getSoundPressed()));
		
		buttonCloseDialog.setContentAreaFilled(false);
		buttonCloseDialog.setOpaque(false);
		buttonCloseDialog.setBorder(null);
		buttonCloseDialog.setRolloverIcon(new ImageIcon(ImageProvider.getxEscPressed()));
		
		buttonEsc.setContentAreaFilled(false);
		buttonEsc.setOpaque(false);
		buttonEsc.setBorder(null);
		buttonEsc.setRolloverIcon(new ImageIcon(ImageProvider.getEscPressed()));
		
		panel=new JPanel (); 
		panel.setLayout(new GridBagLayout ());
		buttonResume.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				gamePanel.getGame().setUpdate(true);
				gamePanel.setPause(false);
				JDialogOptions.this.dispose();
			}
		});
		
		buttonMenuTrack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				menuFrame.setContentPane(menuFrame.getMenuPanel().getPlayMenuPanel().getLevelsPanel());
				menuFrame.getMenuPanel().getPlayMenuPanel().getLevelsPanel().updateUI();
				menuFrame.getMenuPanel().getPlayMenuPanel().getLevelsPanel().requestFocus();
				JDialogOptions.this.dispose();
				System.gc();
			}
		});
		
		buttonCloseDialog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				gamePanel.getGame().setUpdate(true);
				gamePanel.setPause(false);
				JDialogOptions.this.dispose();
			}
		});
		
		buttonRestart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gamePanel.getGame().setUpdate(true);
				gamePanel.setPause(false);
				
				gamePanel.restartGamePanel();
				
				JDialogOptions.this.dispose();
				System.gc();
			}
		});
		
		buttonEsc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				menuFrame.setContentPane(menuFrame.getMenuPanel());
				menuFrame.getMenuPanel().requestFocus();
				menuFrame.getMenuPanel().updateUI();
				JDialogOptions.this.dispose();
				System.gc();
				
			}
		});
		
		constraints=new GridBagConstraints();
		constraints.insets.set(5, 5, 5, 5);
		constraints.gridx=0;
		constraints.gridy=0;
		
		panel.add(buttonResume,constraints);
		constraints.gridy++;
		panel.add(buttonRestart,constraints);
		constraints.gridy++;
		panel.add(buttonMenuTrack,constraints);
		constraints.gridy++;
		panel.add(buttonSound,constraints);
		constraints.gridy++;
		panel.add(buttonCloseDialog,constraints);
		panel.add(buttonEsc,constraints);
		constraints.gridy++;
		
		panel.setOpaque(false);
		
		
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		
		JPanel xJPanel=new JPanel();
		xJPanel.setOpaque(false);
		xJPanel.setLayout(new BorderLayout());
		xJPanel.add(buttonCloseDialog,BorderLayout.EAST);
		JpanelDialog jpanelDialog=new JpanelDialog();
		
		jpanelDialog.add(xJPanel,BorderLayout.NORTH);
		jpanelDialog.add(panel,BorderLayout.CENTER);
		this.setLocationRelativeTo(menuFrame);
		this.setModal(true);
		this.add(jpanelDialog);
	}
	
}
