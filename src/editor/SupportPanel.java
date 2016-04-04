package editor;

import gui.MenuFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class SupportPanel extends JPanel {
	private RightPanel rightpanel;
	private OptionsPanel optionsPanel;
	
	private JPanel panelLaps;
	private JLabel labelLaps;
	private JButton buttonLapsMore;
	private JButton buttonLapsLess;
	private JTextArea textAreaLaps;

	public SupportPanel(EditorPanel editorPanel,MenuFrame frame) {
		
		this.setFocusable(true);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		rightpanel=new RightPanel(editorPanel);
		optionsPanel= new OptionsPanel(editorPanel,frame);
		this.add(rightpanel,BorderLayout.CENTER);
		this.add(optionsPanel,BorderLayout.EAST);
		
		panelLaps=new JPanel();
		labelLaps=new JLabel("Laps ");
		buttonLapsMore=new JButton("+");
		buttonLapsLess=new JButton("-");
		textAreaLaps=new JTextArea(Integer.toString (editorPanel.getTotalLaps()));
		
		Font font=new Font("ARIAL", 10, 25);
		
		panelLaps.setBackground(Color.ORANGE.brighter());
		labelLaps.setFont(font);
		buttonLapsLess.setFont(font);
		buttonLapsLess.setFocusPainted(false);
		buttonLapsLess.setBackground(Color.GRAY.brighter());
		buttonLapsMore.setFont(font);
		buttonLapsMore.setFocusPainted(false);
		buttonLapsMore.setBackground(Color.GRAY.brighter());
		textAreaLaps.setFont(font);
		textAreaLaps.setOpaque(false);
		textAreaLaps.setFocusable(false);
		
		panelLaps.add(labelLaps);
		panelLaps.add(buttonLapsLess);
		panelLaps.add(textAreaLaps);
		panelLaps.add(buttonLapsMore);
		
		this.add(panelLaps,BorderLayout.NORTH);
		
		buttonLapsLess.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(editorPanel.getTotalLaps()>1){
					editorPanel.setTotalLaps(editorPanel.getTotalLaps()-1);
					textAreaLaps.setText(Integer.toString(editorPanel.getTotalLaps()));
				}
			}
		});
		buttonLapsMore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(editorPanel.getTotalLaps()<10){
					editorPanel.setTotalLaps(editorPanel.getTotalLaps()+1);
					textAreaLaps.setText(Integer.toString(editorPanel.getTotalLaps()));
				}
			}
		});
	}
	public RightPanel getLeftpanel() {
		return rightpanel;
	}
}
