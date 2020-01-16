import java.io.IOException;
import java.lang.Math;

public abstract class StartMinesweeper {
	static int width = 519;
	static int height = 519;
	static int bombs = 100;  //   0<bombs<((sidelength/20)^2)  else bombs = ((sidelength)^2)/8
	
	 	public static void main(String[] args) throws IOException {
	 		MinesweeperGUI mines = new MinesweeperGUI(width,height,bombs);
		}
	
}