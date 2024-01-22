
public class GameOfLife {

	public static void main(String[] args) {
		String fileName = args[0];
		//// Uncomment the test that you want to execute, and re-compile.
		//// (Run one test at a time).
		 test1(fileName);
		//// test2(fileName);
		//// test3(fileName, 3);
		//// play(fileName);
	}
	
	private static void test1(String fileName) {
		int[][] board = read(fileName);
		print(board);
	}

	private static void test2(String fileName) {
		int[][] board = read(fileName);
		
		for (int i = 1; i < board.length-1; i++){
			
			for (int j = 1; j < board[i].length-1; j++){

				System.out.print(count(board, i, j)+ " ");

			}
		}

		System.out.println();

		for (int i = 1; i < board.length-1; i++){
			
			for (int j = 1; j < board[i].length-1; j++){

				System.out.print(cellValue(board, i, j)+ " ");

			}
		}

	}
		
	private static void test3(String fileName, int Ngen) {
		int[][] board = read(fileName);
		for (int gen = 0; gen < Ngen; gen++) {
			System.out.println("Generation " + gen + ":");
			print(board);
			board = evolve(board);
		}
	}
		

	public static void play(String fileName) {
		int[][] board = read(fileName);
		while (true) {
			show(board);
			board = evolve(board);
		}
	}
	

	public static int[][] read(String fileName) {
		In in = new In(fileName); 
		int rows = Integer.parseInt(in.readLine());
		int cols = Integer.parseInt(in.readLine());
		int[][] board = new int[rows][cols];
		String str = "";
		
		while (!in.isEmpty()) {

			for (int j = 0; j < board.length; j++){
			
				str = in.readLine();

				for (int i = 0; i < str.length(); i++){

					if (str.charAt(i) == 'x')  board[j][i] = 1;

				}

			}
	
		}
	
		return board;
	}
	

	public static int[][] evolve(int[][] board) {

		int[][] newBoard = new int[board.length][board[0].length];

		for (int i = 1; i < board.length-1; i++){

			for (int j = 1; j < board[i].length-1; j++){

				newBoard[i][j] = cellValue(board, i, j);

			}

		}
		
		return newBoard;
	}


	public static int cellValue(int[][] board, int i, int j) {
		
		int nxtGen = 0;

		if(board[i][j] == 0 && count(board, i, j) == 3){

			nxtGen = 1;
			return nxtGen;


		}  
		
		if(board[i][j] == 1){

			if(count(board, i, j) < 2){

				nxtGen = 0;

			} else if(count(board, i, j) == 2 || count(board, i, j) == 3){

				nxtGen = 1;

			} else if(count(board, i, j) > 3){

				nxtGen = 0;

			}


		}
		
		return nxtGen;
	}
	
 
	public static int count(int[][] board, int i, int j) {

		int neighNum = 0;

		for (int l = i-1; l <= i+1; l++){

			for (int k = j-1; k <= j+1; k++){

					if (board[l][k] == 1)

						neighNum++;
					
				}
		
			}

		if (board[i][j] == 1){

			neighNum--;
		}

		return neighNum;
	}
	
    public static void print(int[][] arr) {


		for (int i = 0; i < arr.length; i++) {
		
			for(int j = 0; j < arr[i].length; j++){

				System.out.printf("%3s", arr[i][j]);

			}

		}


	}


		
    // Displays the board. Living and dead cells are represented by black and white squares, respectively.
    // We use a fixed-size canvas of 900 pixels by 900 pixels for displaying game boards of different sizes.
    // In order to handle any given board size, we scale the X and Y dimensions according to the board size.
    // This results in the following visual effect: The smaller the board, the larger the squares
	// representing cells.
	public static void show(int[][] board) {
		StdDraw.setCanvasSize(900, 900);
		int rows = board.length;
		int cols = board[0].length;
		StdDraw.setXscale(0, cols);
		StdDraw.setYscale(0, rows);

		// Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
		
		// For each cell (i,j), draws a filled square of size 1 by 1 (remember that the canvas was 
		// already scaled to the dimensions rows by cols, which were read from the data file). 
		// Uses i and j to calculate the (x,y) location of the square's center, i.e. where it
		// will be drawn in the overall canvas. If the cell contains 1, sets the square's color
		// to black; otherwise, sets it to white. In the RGB (Red-Green-Blue) color scheme used by
		// StdDraw, the RGB codes of black and white are, respetively, (0,0,0) and (255,255,255).
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int color = 255 * (1 - board[i][j]);
				StdDraw.setPenColor(color, color, color);
				StdDraw.filledRectangle(j + 0.5, rows - i - 0.5, 0.5, 0.5);
			}
		}
		StdDraw.show();
		StdDraw.pause(100); 
	}
}
