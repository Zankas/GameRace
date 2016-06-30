package editor;
 
import gui.ImageProvider;
import gui.MenuFrame;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import core.World;
 
@SuppressWarnings("serial")
public class CenterPanel extends JPanel {
 
	private int DIMENSION_BLOCK =0;
	private int BORD_Y = 0;
	private int BORD_X = 0;

	private Dimension dim;
	private World world;
   
    public CenterPanel(MenuFrame frame,RightPanel leftpanel, EditorPanel editorPanel) {
    	
        dim =frame.getDim();
        world = new World();
        BORD_X=editorPanel.getBordX();
        BORD_Y=editorPanel.getBordY();    
        DIMENSION_BLOCK=editorPanel.getDimensionBlock();

        this.setPreferredSize(dim);
        this.setFocusable(true);
        this.setOpaque(false);
        this.setDoubleBuffered(true);
        this.setBounds(0, 0, dim.width, dim.height);
     
    }

	
    public World getWorld() {
        return world;
    }
 
    
 
    private void paintWorldImage(final Graphics g) {
        for (int i = 0; i < World.X_MATRIX_STRING; i++) {
            for (int j = 0; j < World.Y_MATRIX_STRING; j++) {
                if ("horizontal".equals(world.getMatrixString()[i][j])) {
                    g.drawImage(ImageProvider.getHorizontalEditor(), j * DIMENSION_BLOCK
                            + BORD_X, i * DIMENSION_BLOCK + BORD_Y, null);
                }
                if ("vertical".equals(world.getMatrixString()[i][j])) {
                    g.drawImage(ImageProvider.getVerticalEditor(), j * DIMENSION_BLOCK
                            + BORD_X, i * DIMENSION_BLOCK + BORD_Y, null);
                }
                if ("curveleftup".equals(world.getMatrixString()[i][j])) {
                    g.drawImage(ImageProvider.getUpLeftEditor(), j * DIMENSION_BLOCK
                            + BORD_X, i * DIMENSION_BLOCK + BORD_Y, null);
                }
                if ("curveleftdown".equals(world.getMatrixString()[i][j])) {
                    g.drawImage(ImageProvider.getDownLeftEditor(), j * DIMENSION_BLOCK
                            + BORD_X, i * DIMENSION_BLOCK + BORD_Y, null);
                }
                if ("curverightup".equals(world.getMatrixString()[i][j])) {
                    g.drawImage(ImageProvider.getUpRigthEditor(), j * DIMENSION_BLOCK
                            + BORD_X, i * DIMENSION_BLOCK + BORD_Y, null);
                }
                if ("curverightdown".equals(world.getMatrixString()[i][j])) {
                    g.drawImage(ImageProvider.getDownRightEditor(), j * DIMENSION_BLOCK
                            + BORD_X, i * DIMENSION_BLOCK + BORD_Y, null);
                }
                if ("grass".equals(world.getMatrixString()[i][j])) {
                    g.drawImage(ImageProvider.getGrassEditor(), j * DIMENSION_BLOCK
                            + BORD_X, i * DIMENSION_BLOCK + BORD_Y, null);
                }
                if ("start".equals(world.getMatrixString()[i][j])) {
                    g.drawImage(ImageProvider.getStarthorizontalrightEditor(), j * DIMENSION_BLOCK
                            + BORD_X, i * DIMENSION_BLOCK + BORD_Y, null);
                }
                if ("starthorizontalright"
                        .equals(world.getMatrixString()[i][j])) {
                    g.drawImage(ImageProvider.getStarthorizontalrightEditor(), j
                            * DIMENSION_BLOCK + BORD_X, i * DIMENSION_BLOCK
                            + BORD_Y, null);
                }
                if ("starthorizontalleft".equals(world.getMatrixString()[i][j])) {
                    g.drawImage(ImageProvider.getStarthorizontalleftEditor(), j
                            * DIMENSION_BLOCK + BORD_X, i * DIMENSION_BLOCK
                            + BORD_Y, null);
                }
                if ("startverticalup".equals(world.getMatrixString()[i][j])) {
                    g.drawImage(ImageProvider.getStartverticalupEditor(), j
                            * DIMENSION_BLOCK + BORD_X, i * DIMENSION_BLOCK
                            + BORD_Y, null);
                }
                if ("startverticaldown".equals(world.getMatrixString()[i][j])) {
                    g.drawImage(ImageProvider.getStartverticaldownEditor(), j
                            * DIMENSION_BLOCK + BORD_X, i * DIMENSION_BLOCK
                            + BORD_Y, null);
                }
            }
        }
    }
 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintWorldImage(g);
        try {
            Thread.sleep(30);
            repaint();
        } catch (InterruptedException e) {
 
            e.printStackTrace();
        }
    }
}