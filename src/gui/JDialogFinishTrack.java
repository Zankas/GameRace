package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JDialogFinishTrack extends JDialog{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton buttonNextTrack;
	private JButton buttonRestart;
	private JButton buttonEsc;
	private JButton buttonMenuTrack;
	private JLabel labelTime;
	private JLabel labelFinish;
		
	private JPanel panel; 
		
	private GridBagConstraints constraints;

		public JDialogFinishTrack(MenuFrame menuFrame, GamePanel gamePanel) {
			super(menuFrame);
			
			this.setUndecorated(true);
			
			this.setBackground(new Color(255, 255, 255, 0));
			
			Font font=new Font("ARIAL", 10, 30);
			
			buttonNextTrack=new JButton("NEXT");
			buttonRestart=new JButton(new ImageIcon(ImageProvider.getRestart()));
			buttonMenuTrack=new JButton(new ImageIcon(ImageProvider.getTracks()));
			buttonEsc=new JButton(new ImageIcon(ImageProvider.getEsc()));
			labelFinish=new JLabel("Track Finish");
			labelFinish.setFont(font);
			labelTime=new JLabel("Your Time: "+gamePanel.getActualCalendar().get(Calendar.MINUTE)+":"
					+gamePanel.getActualCalendar().get(Calendar.SECOND)+":"+gamePanel.getActualCalendar().get(Calendar.MILLISECOND));
			
			labelTime.setFont(font);
			
			buttonNextTrack.setContentAreaFilled(false);
			buttonNextTrack.setOpaque(false);
			buttonNextTrack.setBorder(null);
			buttonNextTrack.setRolloverIcon(new ImageIcon(ImageProvider.getResumePressed()));
			
			buttonRestart.setContentAreaFilled(false);
			buttonRestart.setOpaque(false);
			buttonRestart.setBorder(null);
			buttonRestart.setRolloverIcon(new ImageIcon(ImageProvider.getRestartPressed()));
			
			buttonMenuTrack.setContentAreaFilled(false);
			buttonMenuTrack.setOpaque(false);
			buttonMenuTrack.setBorder(null);
			buttonMenuTrack.setRolloverIcon(new ImageIcon(ImageProvider.getTracksPressed()));
			
			
			
			buttonEsc.setContentAreaFilled(false);
			buttonEsc.setOpaque(false);
			buttonEsc.setBorder(null);
			buttonEsc.setRolloverIcon(new ImageIcon(ImageProvider.getEscPressed()));
			
			panel=new JPanel (); 
			panel.setLayout(new GridBagLayout ());
			buttonNextTrack.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					
				}
			});
			
			buttonMenuTrack.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					menuFrame.setContentPane(menuFrame.getMenuPanel().getPlayMenuPanel().getLevelsPanel());
					menuFrame.getMenuPanel().getPlayMenuPanel().getLevelsPanel().updateUI();
					menuFrame.getMenuPanel().getPlayMenuPanel().getLevelsPanel().requestFocus();
					JDialogFinishTrack.this.dispose();
					System.gc();
				}
			});
			
		
			buttonRestart.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					gamePanel.getGame().setUpdate(true);
					gamePanel.setPause(false);
					
					gamePanel.restartGamePanel();
					
					JDialogFinishTrack.this.dispose();
					System.gc();
				}
			});
			
			buttonEsc.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				
					menuFrame.setContentPane(menuFrame.getMenuPanel());
					menuFrame.getMenuPanel().requestFocus();
					menuFrame.getMenuPanel().updateUI();
					JDialogFinishTrack.this.dispose();
					System.gc();
					
				}
			});
		
			constraints=new GridBagConstraints();
			constraints.insets.set(5, 5, 5, 5);
			constraints.gridx=0;
			constraints.gridy=0;
			
			panel.add(labelFinish,constraints);
			constraints.gridy++;
			panel.add(labelTime,constraints);
			constraints.gridy++;
			panel.add(buttonRestart,constraints);
			constraints.gridy++;
			panel.add(buttonMenuTrack,constraints);
			constraints.gridy++;
			panel.add(buttonEsc,constraints);
			constraints.gridy++;
			
			panel.setOpaque(false);
			
			
			this.setSize(400, 400);
			this.setLocationRelativeTo(null);
			
			
			JpanelFinishDialog jpanelFinishDialog=new JpanelFinishDialog();
			
			
			jpanelFinishDialog.add(panel,BorderLayout.CENTER);
			this.setModal(true);
			this.add(jpanelFinishDialog);
		}
}
