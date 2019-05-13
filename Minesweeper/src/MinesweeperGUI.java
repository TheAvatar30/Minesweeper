import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.Random;

public class MinesweeperGUI extends JFrame { // implements MouseListener, ActionListener {
	public JButton buttonAlgorithem;
	private MinesButton[][] field;
	private JPanel canvas;
	private int canvasX;
	private int canvasY;
	private int numOfBombs;
	private Random rnd;
	private JLabel flagCount;

	MinesweeperGUI(int width, int height, int bombs) {
		super("Minesweeper");

		canvas = new JPanel();
		rnd = new Random();
		flagCount = new JLabel();

		setSize(width, height);
		canvasX = width / 20;
		canvasY = height / 20;
		if (bombs > 0 && bombs <= (canvasX * canvasY)) {
			numOfBombs = bombs;
		} else {
			numOfBombs = (canvasX * canvasY) / 8;
		}
		field = new MinesButton[canvasX][canvasY];

		// this.addMouseListener(this);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		canvas.add(flagCount);
		canvas.setLayout(new GridLayout(canvasX, canvasY));

		placeComponents2();
		placeNumbers();
		//uncoverAll();
		easyStart();
		printGameInfo();

		setVisible(true);

	}

		//	easy 0.15
		//	medium 0.21111111
		//	hard 0.37222222222
	void printGameInfo() {
		System.out.println("You're playing with " + numOfBombs + " Bombs");
		double diff = (double)numOfBombs / ((double)canvasX * (double)canvasY);
		System.out.println(diff);
		if (0 <= diff && diff <= 0.15) {
			System.out.println("Difficulty: Easy");
		}else if (0.15 < diff && diff <= 0.2111) {
			System.out.println("Difficulty: Medium");
		}else if (0.2111 < diff && diff <= 0.37222) {
			System.out.println("Difficulty: Hard");
		}else if (0.37222 < diff) {
			System.out.println("Difficulty: Very Hard");
		}
	}

	void arrInCanvas(MinesButton[][] arr) {
		for (int i = 0; i < canvasX; i++) {
			for (int j = 0; j < canvasY; j++) {
				canvas.add(arr[i][j]);
			}
		}
	}

	void easyStart() {
		for (int i = 0; i < canvasX; i++) {
			for (int j = 0; j < canvasY; j++) {
				if (field[i][j].currentObject.equals("tile")) {
					uncover(i, j);
					return;
				}
			}
		}
	}

	void uncoverAll() {
		canvas.removeAll();
		for (int i = 0; i < canvasX; i++) {
			for (int j = 0; j < canvasY; j++) {
				if (field[i][j].currentObject.equals("tile")) {
					field[i][j] = new MinesButton("tileEmpty", "tileEmpty", this, i, j);
					field[i][j].uncoverd = true;
					canvas.add(field[i][j]);
				} else {
					field[i][j] = new MinesButton(field[i][j].currentObject, field[i][j].currentObject, this, i, j);
					field[i][j].uncoverd = true;
					canvas.add(field[i][j]);
				}
			}

		}
		this.add(canvas);
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
					field[i][j] = new MinesButton("tile", "num" + a, this, i, j);
					canvas2.add(field[i][j]);
					canvas.remove(field[i][j]);
					a = 0;
					// System.out.println(field[i][j].currentIcon + ", "
					// +field[i][j].currentObject);
				} else if (!field[i][j].currentObject.equals("bomb")) {
					field[i][j] = new MinesButton("tile", "tile", this, i, j);
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

	void placeComponents2() {
		canvas.removeAll();
		for (int i = 0; i < numOfBombs;) {
			int rndX = rnd.nextInt(canvasX);
			int rndY = rnd.nextInt(canvasY);
			if (field[rndX][rndY] == null) {
				field[rndX][rndY] = new MinesButton("tile", "bomb", this, rndX, rndY);
				i++;
			}
		}
		for (int i = 0; i < canvasX; i++) {
			for (int j = 0; j < canvasY; j++) {
				if (field[i][j] == null) {
					field[i][j] = new MinesButton("tile", "tile", this, i, j);
				}
			}
		}
		arrInCanvas(field);
		this.add(canvas);
	}

	void placeComponents() {

		canvas.removeAll();
		for (int j = 0; j < canvasY; j++) {
			for (int i = 0; i < canvasX; i++) {
				if (field[i][j] == null) {
					int a = rnd.nextInt((canvasX * canvasY) / numOfBombs);
					if (a == 1 || a == 1) {
						field[i][j] = new MinesButton("tile", "bomb", this, i, j);
					} else {
						field[i][j] = new MinesButton("tile", "tile", this, i, j);
					}
				}
			}
		}
		arrInCanvas(field);
		this.add(canvas);

	}

	void minesweeperAlgorithem() {

	}

	void uncover(int x, int y) {
		if (field[x][y].currentObject.equals("tile")) {
			canvas.removeAll();
			uncoverReal(x, y);
			for (int i = 0; i < canvasX; i++) {
				for (int j = 0; j < canvasY; j++) {
					canvas.add(field[i][j]);
				}
			}
			add(canvas);
		}
	}

	void uncoverReal(int x, int y) {
		try {
			if (x >= 0 && y >= 0 && x <= canvasX && y <= canvasY && field[x][y].currentObject.equals("tile")
					&& field[x][y].uncoverd == false) {
				field[x][y] = new MinesButton("tileEmpty", "tile", this, x, y);
				field[x][y].uncoverd = true;
				if (x < canvasX) {
					uncoverReal(x + 1, y);
				}
				if (x > 0) {
					uncoverReal(x - 1, y);
				}
				if (y < canvasY) {
					uncoverReal(x, y + 1);
				}
				if (y > 0) {
					uncoverReal(x, y - 1);
				}
				if (x < canvasX && y > 0) {
					uncoverReal(x + 1, y - 1);
				}
				if (x < canvasX && y < canvasY) {
					uncoverReal(x + 1, y + 1);
				}
				if (x > 0 && y > 0) {
					uncoverReal(x - 1, y - 1);
				}
				if (x > 0 && y < canvasY) {
					uncoverReal(x - 1, y + 1);
				}

			}
			if (!field[x][y].currentObject.equals("tile") && field[x][y].uncoverd == false) {
				field[x][y] = new MinesButton(field[x][y].currentObject, field[x][y].currentObject, this, x, y);
				field[x][y].uncoverd = true;
			}
		} catch (Exception e) {

		}
	}
}