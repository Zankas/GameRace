package editor;

import gui.ImageProvider;
import gui.MenuFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import core.World;

@SuppressWarnings("serial")
public class EditorPanel extends JPanel{
	
	public static final int DIMENSION_BLOCK =140;
	private int bordY=0;
	private int bordX=0;
	private int totalLaps;
	private int dimensionBlock=0;
	private final Dimension dim;
	private CenterPanel centerpanel;
	private SupportPanel supportPanel;
	private TrasparentPanel trasparentPanel;
	private Cursor cursorCustom;
	private JLayeredPane layeredPane;
	
	public EditorPanel(MenuFrame frame) {
		
		dim=frame.getDim();
		System.out.println(dim);
		dimensionBlock=resizeX(DIMENSION_BLOCK);
		bordX=5;
		bordY=(resizeY(768)-(dimensionBlock*World.X_MATRIX_STRING))/2;
		totalLaps=3;
		supportPanel=new SupportPanel(this,frame);
		centerpanel=new CenterPanel(frame,supportPanel.getLeftpanel(),this);
		trasparentPanel=new TrasparentPanel(centerpanel.getWorld(), supportPanel.getLeftpanel(),this,frame);
		layeredPane=new JLayeredPane();
		this.setPreferredSize(dim);
		this.setFocusable(true);
		
		this.setDoubleBuffered(true);
		this.setBounds(0, 0, dim.width, dim.height);
		this.setLayout(new BorderLayout());
		this.setBackground(Color.getHSBColor(0.1f, 1.0f, 1.0f));
		
		layeredPane.add(trasparentPanel);
		layeredPane.add(centerpanel);
	
		this.add(layeredPane,BorderLayout.CENTER);
		this.add(supportPanel, BorderLayout.EAST);
		
		  cursorCustom = Toolkit.getDefaultToolkit().createCustomCursor(ImageProvider.getCursor(), 
					new Point(0,0),"cursorCustom");
		this.setCursor(cursorCustom);

	}
	
	public int getTotalLaps() {
		return totalLaps;
	}

	public void setTotalLaps(int totalLaps) {
		this.totalLaps = totalLaps;
	}

	public int getBordY() {
		return bordY;
	}
	
	public int getBordX() {
		return bordX;
	}
	
	public int getDimensionBlock() {
		return dimensionBlock;
	}

	public SupportPanel getSupportPanel() {
		return supportPanel;
	}
	public CenterPanel getCenterPanel(){
		return centerpanel;
	}
	public int resizeX(int x){
		return ((int)dim.width*x)/1366;
	}
	public int resizeY(int y){
		return ((int)dim.height*y)/768;
	}

	public void load() {
		 
        File folder = new File("resource/levelSave/");
        String[] array = folder.list();
 
        for (int i = 0; i < array.length; i++)
            array[i] = array[i].substring(0, array[i].length() - 4);
 
        String s = (String) JOptionPane.showInputDialog(this,
                "Choose files to upload", "File upload",
                JOptionPane.PLAIN_MESSAGE, null, array, null);
 
        if ((s != null) && (s.length() > 0)) {
 
            String path = "resource/levelSave/" + s + ".lll";
 
            try {
                File file = new File(path);
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                for (int i = 0; i < World.X_MATRIX_STRING; i++) {
                    for (int j = 0; j < World.Y_MATRIX_STRING; j++) {
                        centerpanel.getWorld().getMatrixString()[i][j] = br.readLine();
                    }
                }
 
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
    private boolean isSaveStartBlock() {
        int cont = 0;
        for (int i = 0; i < World.X_MATRIX_STRING; i++) {
            for (int j = 0; j < World.Y_MATRIX_STRING; j++) {
            	if ("start".equals(centerpanel.getWorld().getMatrixString()[i][j])
                        || "starthorizontalright".equals(centerpanel.getWorld()
                                .getMatrixString()[i][j])
                        || "starthorizontalleft"
                                .equals(centerpanel.getWorld().getMatrixString()[i][j])
                        || "startverticalup"
                                .equals(centerpanel.getWorld().getMatrixString()[i][j])
                        || "startverticaldown"
                                .equals(centerpanel.getWorld().getMatrixString()[i][j]))
                    cont++;
            }
        }
        if (cont == 1) {
            return true;
        }
        return false;
    }
 
    private boolean yesRoad() {
        for (int i = 0; i < World.X_MATRIX_STRING; i++) {
            for (int j = 0; j < World.Y_MATRIX_STRING; j++) {
                if (!"grass".equals(centerpanel.getWorld().getMatrixString()[i][j])
                        && !"".equals(centerpanel.getWorld().getMatrixString()[i][j]))
                    return true;
            }
        }
        return false;
    }
 
    private boolean trackNoAllBlock() {
        boolean noblock = true;
        for (int i = 0; i < World.X_MATRIX_STRING; i++) {
            for (int j = 0; j < World.Y_MATRIX_STRING; j++) {
                if (!"".equals(centerpanel.getWorld().getMatrixString()[i][j]))
                    noblock = false;
            }
        }
        return noblock;
    }
 
    private boolean workingRoad() {
 
        for (int i = 0; i < World.X_MATRIX_STRING; i++) {
            for (int j = 0; j < World.Y_MATRIX_STRING; j++) {
 
                switch (centerpanel.getWorld().getMatrixString()[i][j]) {
 
                case "starthorizontalleft":
                    if (j == 0 || j == World.Y_MATRIX_STRING-1) {
                        return false;
                    }
                    if (j < World.Y_MATRIX_STRING-1) {
                        if (centerpanel.getWorld().getMatrixString()[i][j + 1] == "vertical"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "startverticalup"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "startverticaldown"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "grass"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "")
                            return false;
                    }
                    if (j > 0) {
                        if (centerpanel.getWorld().getMatrixString()[i][j - 1] == "vertical"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "startverticalup"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "startverticaldown"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "grass"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "")
                            return false;
                    }
                    break;
 
                case "starthorizontalright":
                    if (j == 0 || j == World.Y_MATRIX_STRING-1) {
                        return false;
                    }
                    if (j < World.Y_MATRIX_STRING-1) {
                        if (centerpanel.getWorld().getMatrixString()[i][j + 1] == "vertical"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "startverticalup"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "startverticaldown"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "grass"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "")
                            return false;
                    }
                    if (j > 0) {
                        if (centerpanel.getWorld().getMatrixString()[i][j - 1] == "vertical"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "startverticalup"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "startverticaldown"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "grass"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "")
                            return false;
                    }
                    break;
 
                case "horizontal":
                    if (j == 0 || j == World.Y_MATRIX_STRING-1) {
                        return false;
                    }
                    if (j < World.Y_MATRIX_STRING-1) {
                        if (centerpanel.getWorld().getMatrixString()[i][j + 1] == "vertical"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "startverticalup"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "startverticaldown"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "grass"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "")
                            return false;
                    }
                    if (j > 0) {
                        if (centerpanel.getWorld().getMatrixString()[i][j - 1] == "vertical"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "startverticalup"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "startverticaldown"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "grass"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "")
                            return false;
                    }
                    break;
 
                case "vertical":
                    if (i == 0 || i == World.X_MATRIX_STRING-1) {
                        return false;
                    }
                    if (i > 0) {
                        if (centerpanel.getWorld().getMatrixString()[i - 1][j] == "horizontal"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "starthorizontalright"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "starthorizontalleft"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "grass"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "")
                            return false;
                    }
                    if (i < World.X_MATRIX_STRING-1) {
                        if (centerpanel.getWorld().getMatrixString()[i + 1][j] == "horizontal"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "starthorizontalright"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "starthorizontalleft"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "grass"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "")
                            return false;
                    }
                    break;
 
                case "startverticalup":
                    if (i == 0 || i == World.X_MATRIX_STRING-1) {
                        return false;
                    }
                    if (i > 0) {
                        if (centerpanel.getWorld().getMatrixString()[i - 1][j] == "horizontal"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "starthorizontalright"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "starthorizontalleft"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "grass"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "")
                            return false;
                    }
                    if (i < World.X_MATRIX_STRING-1) {
                        if (centerpanel.getWorld().getMatrixString()[i + 1][j] == "horizontal"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "starthorizontalright"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "starthorizontalleft"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "grass"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "")
                            return false;
 
                    }
 
                    break;
 
                case "startverticaldown":
                    if (i == 0 || i == World.X_MATRIX_STRING-1) {
                        return false;
                    }
                    if (i > 0) {
                        if (centerpanel.getWorld().getMatrixString()[i - 1][j] == "horizontal"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "starthorizontalright"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "starthorizontalleft"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "grass"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "")
                            return false;
                    }
                    if (i < World.X_MATRIX_STRING-1) {
                        if (centerpanel.getWorld().getMatrixString()[i + 1][j] == "horizontal"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "starthorizontalright"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "starthorizontalleft"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "grass"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "")
                            return false;
                    }
 
                    break;
 
                case "curveleftup":
                    if (i == 0 || j == 0) {
                        return false;
                    }
                    if (i > 0) {
                        if (centerpanel.getWorld().getMatrixString()[i - 1][j] == "horizontal"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "starthorizontaright"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "starthorizontalleft"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "curveleftup"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "curverightup"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "grass"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "")
                            return false;
                    }
                    if (i < World.X_MATRIX_STRING-1) {
                        if (centerpanel.getWorld().getMatrixString()[i + 1][j] == "vertical"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "startverticalup"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "startverticaldown"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "curveleftup"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "curverightup")
                            return false;
                    }
                    if (j > 0) {
                        if (centerpanel.getWorld().getMatrixString()[i][j - 1] == "vertical"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "startverticalup"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "startverticaldown"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "curveleftup"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "curveleftdown"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "grass"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "")
                            return false;
                    }
                    if (j < World.Y_MATRIX_STRING-1) {
                        if (centerpanel.getWorld().getMatrixString()[i][j + 1] == "horizontal"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "starthorizontalleft"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "starthorizontalright"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "curveleftup"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "curveleftdown")
                            return false;
                    }
                    break;
 
                case "curveleftdown":
                    if (j == 0 || i == World.X_MATRIX_STRING-1)
                        return false;
 
                    if (i > 0) {
                        if (centerpanel.getWorld().getMatrixString()[i - 1][j] == "vertical"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "startverticalup"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "startverticaldown"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "curveleftdown"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "curverightdown")
                            return false;
                    }
                    if (i < World.X_MATRIX_STRING-1) {
                        if (centerpanel.getWorld().getMatrixString()[i + 1][j] == "horizontal"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "starthorizontalleft"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "starthorizontalright"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "curveleftdown"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "curverightdown"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "grass"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "")
                            return false;
                    }
                    if (j > 0) {
                        if (centerpanel.getWorld().getMatrixString()[i][j - 1] == "vertical"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "startverticalup"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "startverticaldown"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "curveleftup"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "curveleftdown"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "grass"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "")
                            return false;
                    }
                    if (j < World.Y_MATRIX_STRING-1) {
                        if (centerpanel.getWorld().getMatrixString()[i][j + 1] == "horizontal"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "starthorizontalleft"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "starthorizontalright"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "curvleftup"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "curvleftdown")
                            return false;
                    }
 
                    break;
 
                case "curverightup":
                    if (i == 0 || j == World.Y_MATRIX_STRING-1) {
                        return false;
                    }
 
                    if (i > 0) {
 
                        if (centerpanel.getWorld().getMatrixString()[i - 1][j] == "starthorizontalleft"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "starthorizontalright"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "horizontal"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "curveleftup"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "curverightup"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "grass"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "")
                            return false;
                    }
                    if (i < World.X_MATRIX_STRING-1) {
 
                        if (centerpanel.getWorld().getMatrixString()[i + 1][j] == "vertical"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "startverticalup"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "startverticaldown"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "curveleftup"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "curverightup")
                            return false;
                    }
                    if (j > 0) {
                        if (centerpanel.getWorld().getMatrixString()[i][j - 1] == "starthorizontalleft"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "starthorizontalright"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "horizontal"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "curverightdown"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "curverightup")
                            return false;
                    }
                    if (j < World.Y_MATRIX_STRING-1) {
 
                        if (centerpanel.getWorld().getMatrixString()[i][j + 1] == "vertical"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "startverticalup"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "startverticalupdown"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "curverightdown"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "curverightup"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "grass"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "")
                            return false;
                    }
                    break;
 
                case "curverightdown":
                    if (i == World.X_MATRIX_STRING-1 || j == World.Y_MATRIX_STRING-1) {
                        return false;
                    }
 
                    if (i > 0) {
                        if (centerpanel.getWorld().getMatrixString()[i - 1][j] == "curveleftdown"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "startverticalup"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "startverticaldown"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "vertical"
                                || centerpanel.getWorld().getMatrixString()[i - 1][j] == "curverightdown")
                            return false;
                    }
                    if (i < World.X_MATRIX_STRING-1) {
                        if (centerpanel.getWorld().getMatrixString()[i + 1][j] == "horizontal"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "starthorizontalleft"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "starthorizontalright"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "curverightdown"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "curveleftdown"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "grass"
                                || centerpanel.getWorld().getMatrixString()[i + 1][j] == "")
                            return false;
                    }
                    if (j > 0) {
                        if (centerpanel.getWorld().getMatrixString()[i][j - 1] == "horizontal"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "starthorizontalright"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "starthorizontalleft"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "curverightdown"
                                || centerpanel.getWorld().getMatrixString()[i][j - 1] == "curverightup")
                            return false;
                    }
                    if (j < World.Y_MATRIX_STRING-1) {
                        if (centerpanel.getWorld().getMatrixString()[i][j + 1] == "vertical"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "startverticalup"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "startverticaldown"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "curverightdown"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "curverightup"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "grass"
                                || centerpanel.getWorld().getMatrixString()[i][j + 1] == "")
                            return false;
                    }
                    break;
                }
            }
        }
 
        return true;
    }
 
    public void save() {
        if (trackNoAllBlock()) {
            JOptionPane.showMessageDialog(this, "Pista senza tutti i blocchi",
                    "Error save", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!yesRoad()) {
            JOptionPane.showMessageDialog(this, "Pista senza strada!",
                    "Error save", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isSaveStartBlock()) {
            JOptionPane.showMessageDialog(this,
                    "Devi inserire solo e almeno un blocco di partenza!",
                    "Error save", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!workingRoad()) {
            JOptionPane.showMessageDialog(this,
                            "Non ti sei accorto che non e' una pista valida?\nRiprova, sarai piu' fortunato."
                            + "\nLa pista deve essere chiusa su se stessa per essere valida.",
                            "Error save", JOptionPane.ERROR_MESSAGE);
            return;
        }
 
        String name = (String) JOptionPane.showInputDialog(this,
                "Enter the name of the track", "Save",
                JOptionPane.PLAIN_MESSAGE, null, null, "track name");
 
        if ((name != null) && (name.length() > 0)) {
            File folder = new File("resource/levelSave/");
 
            String[] array = folder.list();
 
            boolean itIsPresent = false;
 
            for (int a = 0; a < array.length; a++) {
 
                String tmp = name + ".lll";
 
                if (tmp.equals(array[a])) {
                    itIsPresent = true;
                }
            }
            if(name.length()>10){
            	 JOptionPane.showMessageDialog(this,
                         "The name length is > of 10 caracter", "Error save",
                         JOptionPane.ERROR_MESSAGE);
            	 save();
            	 return;
            }
            if (!itIsPresent) {
 
                if ("".equals(name)) {
                    JOptionPane.showMessageDialog(this,
                            "You must enter a name to save", "Error save",
                            JOptionPane.ERROR_MESSAGE);
                }
                try {
 
                    File file = new File("resource/levelSave/" + name + ".lll");
                    FileWriter fw = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(fw);
                    for (int i = 0; i < World.X_MATRIX_STRING; i++) {
                        for (int j = 0; j < World.Y_MATRIX_STRING; j++) {
                            bw.write(centerpanel.getWorld().getMatrixString()[i][j] + "\n");
                        }
                    }
                    bw.write(totalLaps+ "\n");
                    bw.flush();
                    bw.close();
                    JOptionPane.showMessageDialog(this, "Level Save", "Save",
                            JOptionPane.PLAIN_MESSAGE);
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "No save level",
                            "Error save", JOptionPane.ERROR_MESSAGE);
                }
            } else {
 
                JOptionPane.showMessageDialog(this,
                                "The file with the entered name already exists. Write a different name",
                                "Error save", JOptionPane.ERROR_MESSAGE);
                save();
            }
 
        } else if ("".equals(name)) {
            JOptionPane.showMessageDialog(this,
                    "You must enter a name to save", "Error save",
                    JOptionPane.ERROR_MESSAGE);
            save();
        }
 
    }
}
