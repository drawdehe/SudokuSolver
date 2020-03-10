import java.util.Arrays;

/**
 * Class for solving a sudoku.
 * 
 * @author ed2576sj-s
 */
public class Sudoku {
	private int[][] grid = new int[9][9];
	/**
	 * Constructor for the class.
	 * 
	 * @param newGrid The sudoku grid that is solved.
	 */
	public Sudoku(int[][] newGrid) {
		this.grid = newGrid;
	}
	/**
	 * Sets the value in a specific row and column.
	 * 
	 * @param row The row of the number.
	 * @param col The column of the number.
	 * @param value The value of the number.
	 */
	public void setValue(int row, int col, int value) {
			grid[row][col] = value;
	}
	/**
	 * Gets the value from a specific row and column.
	 * 
	 * @param row The row of the nuumber.
	 * @param col The column of the number.
	 * @return The value of the number.
	 */
	public int getValue(int row, int col) {
		return grid[row][col];
	}
	//Checks if the number exists in the region
	private boolean checkRegion(int row, int col, int value) {
		int upperLeftRow = row - row % 3;
		int upperLeftCol = col - col % 3;
		for (int i = upperLeftRow; i < upperLeftRow + 3; i++) {
			for (int j = upperLeftCol; j < upperLeftCol + 3; j++) {
				if (grid[i][j] == value && i != row && j != col) {
					return true;
				}
			}
		}
		return false;
	}
	//Checks if the number exists in the row
	private boolean checkRow(int row, int col, int value) {
		for (int i = 0; i < 9; i++) {
			if (grid[row][i] == value && i != col) {
				return true;
			}
		}
		return false;
	}
	//Checks if the number exists in the column
	private boolean checkColumn(int row, int col, int value) {
		for (int i = 0; i < 9; i++) {
			if (grid[i][col] == value && i != row) {
				return true;
			}
		}
		return false;
	}
	//Checks if the number can be placed in the box according to all conditions
	private boolean checkAllReqs(int row, int col, int value) {
		if (value == 0) {
			return true;
		}
		if ((checkRegion(row, col, value) != true && checkRow(row, col, value) != true && checkColumn(row, col, value) != true)) {
			return true;
		}
		return false;
	}
	//Solves the sudoku with backtracking
	private boolean solveBackTrack(int row, int col) {
		if (row == 9) {
			return true;
		}
		int newCol = col + 1;
		int newRow = row;
		if (newCol == 9) {
			newRow++;
			newCol = 0;
		}
		if (getValue(row, col) == 0) {
			for (int value = 1; value < 10; value++) {
				if (checkAllReqs(row, col, value)) {
					setValue(row, col, value);
					if (solveBackTrack(newRow, newCol)) {
						return true;
					} else {
						setValue(row, col, 0);
					}
				}
			}
			return false;
		}
		return solveBackTrack(newRow, newCol);
	}
	/**
	 * Checks if the sudoku is filled in according to the rules and then solves the sudoku.
	 * 
	 * @return True if the sudoku is solvable.
	 */
	public boolean solve() {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (checkAllReqs(row, col, grid[row][col]) == false && grid[row][col] != 0) {
					return false;
				}
			}
		}
		return solveBackTrack(0, 0);
	}
}