import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.Random;

public class MinesweeperGUI extends JFrame implements MouseListener, ActionListener{
    public JButton buttonAlgorithem;
    private MinesButton[][] field;
    private JPanel canvas;
    private int canvasX;
    private int canvasY;
    private int numOfBombs = 15;
    private Random rnd;

    MinesweeperGUI(int width, int height, int bombs) {
        super("Minesweeper");

        canvas = new JPanel();
        rnd = new Random();

        setSize(width, height);
        canvasX = width / 20;
        canvasY = height / 20;
        if (bombs > 0 && bombs < 60) {
            numOfBombs = bombs;
        }
        field = new MinesButton[canvasX][canvasY];

        this.addMouseListener(this);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        canvas.setLayout(new GridLayout(canvasX, canvasY));

        // placeBombs();
        placeComponents();
        placeNumbers();

        setVisible(true);
    }

    void placeNumbers() {
        JPanel canvas2 = new JPanel();
        canvas2.setLayout(new GridLayout(canvasX, canvasY));
        int a = 0;
        for (int j = 0; j < canvasY; j++) {
            for (int i = 0; i < canvasX; i++) {
                if (field[i][j].currentObject.equals("tile")) {
                    if (i > 0 && j > 0 && field[i - 1][j - 1].currentObject.equals("bomb")) { // oben links
                        a++;
                    }
                    if (j > 0 && field[i][j - 1].currentObject.equals("bomb")) { // oben mitte
                        a++;
                    }
                    if (i < canvasX - 1 && j > 0 && field[i + 1][j - 1].currentObject.equals("bomb")) { // oben rechts
                        a++;
                    }
                    if (i > 0 && j < canvasY - 1 && field[i - 1][j + 1].currentObject.equals("bomb")) { // unten links
                        a++;
                    }
                    if (j < canvasY - 1 && field[i][j + 1].currentObject.equals("bomb")) { // unten mitte
                        a++;
                    }
                    if (i < canvasX - 1 && j < canvasY - 1 && field[i + 1][j + 1].currentObject.equals("bomb")) { // unten
                        // rechts
                        a++;
                    }
                    if (i > 0 && field[i - 1][j].currentObject.equals("bomb")) { // mitte links
                        a++;
                    }
                    if (i < canvasX - 1 && field[i + 1][j].currentObject.equals("bomb")) { // mitte rechts
                        a++;
                    }

                }
                if (a > 0) {
                    field[i][j] = new MinesButton("tile", "num" + a,this,i,j);
                    canvas2.add(field[i][j]);
                    canvas.remove(field[i][j]);
                    a = 0;
                    // System.out.println(field[i][j].currentIcon + ", "
                    // +field[i][j].currentObject);
                } else if (!field[i][j].currentObject.equals("bomb")) {
                    field[i][j] = new MinesButton("tile", "tile",this,i,j);
                    canvas.remove(field[i][j]);
                    canvas2.add(field[i][j]);
                } else {
                    canvas.remove(field[i][j]);
                    canvas2.add(field[i][j]);
                    canvas2.add(field[i][j]);
                }
            }
        }

        canvas = canvas2;
        add(canvas);
        canvas.setSize(this.WIDTH + 20, this.HEIGHT + 20);
    }

    void placeComponents() {

        canvas.removeAll();
        for (int j = 0; j < canvasY; j++) {
            for (int i = 0; i < canvasX; i++) {
                if (field[i][j] == null) {
                    int a = rnd.nextInt((canvasX * canvasY) / numOfBombs);
                    if (a == 5) {
                        field[i][j] = new MinesButton("tile", "bomb",this,i,j);
                    } else {
                        field[i][j] = new MinesButton("tile", "tile",this,i,j);
                    }
                    canvas.add(field[i][j]);
                }
            }
        }
        add(canvas);

    }

    void placeBombs() {
        canvas.removeAll();
        int x = 0;
        int y = 0;
        for (int i = 0; i < numOfBombs; i++) {
            x = rnd.nextInt(canvasX);
            y = rnd.nextInt(canvasY);
            if (field[x][y] == null) {
                field[x][y] = new MinesButton("tile", "bomb",this,x,y);
                canvas.add(field[x][y]);
            } else {
                i = i - 1;
            }
        }
        add(canvas);

    }

    void minesweeperAlgorithem() {

    }

    void uncover(int x,int y) {
        canvas.removeAll();
        uncoverReal(x,y);
        
        System.out.println(x+" "+y);
        for(int i=0;i<canvasX;i++){
            for(int j=0;j<canvasY;j++){
                canvas.add(field[i][j]);
            }
        }
        add(canvas);
    }

    void uncoverReal(int x, int y) {

        if(!field[x][y].uncoverd){// && x>=0 && y>=0 && x<=canvasX && y<=canvasY){
            
            field[x][y] = new MinesButton("tile", "tile",this,x,y);
            field[x][y].uncoverd = true;
            try{
                if(field[x+1][y].currentObject.equals("tile")) {
                    uncoverReal(x+1, y);
                }
            }catch(Exception e){}
            try{
                if(field[x][y+1].currentObject.equals("tile")) {
                    uncoverReal(x, y+1);
                }
            }catch(Exception e){}
            try{
                if(field[x-1][y].currentObject.equals("tile")) {
                    uncoverReal(x-1, y);
                }
            }catch(Exception e){}
            try{
                if(field[x][y-1].currentObject.equals("tile")) {
                    uncoverReal(x, y-1);
                }
            }catch(Exception e){}
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        int x = getMousePosition().x/20;
        int y = getMousePosition().y/20;
        System.out.println(x);
        System.out.println(y);
        //if(field[x][y].currentObject.equals("tile")) {
        uncover(x, y);
         */
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        /*
        int x = e.getX()/20;
        int y = e.getY()/20;
        System.out.println(x);
        System.out.println(y);
        //if(field[x][y].currentObject.equals("tile")) {
        uncover(x, y);
         */
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
