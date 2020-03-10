import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

//Class to test the solving of a sudoku
class SudokuTest {
	private int[][] grid = new int[9][9];
	private Sudoku sudoku = new Sudoku(grid);
	
	//Test to solve an empty sudoku
	@Test
	void testSolvingEmptySudoku() {
		assertTrue("Succesfully solved an empty sudoku grid", sudoku.solve());
	}
	//Test to solve a sample sudoku
	@Test
	void testSolvingSampleSudoku() {
		sudoku.setValue(0, 2, 8);
		sudoku.setValue(0, 5, 9);
		sudoku.setValue(0, 7, 6);
		sudoku.setValue(0, 8, 2);
		sudoku.setValue(1, 8, 5);
		sudoku.setValue(2, 0, 1);
		sudoku.setValue(2, 2, 2);
		sudoku.setValue(2, 3, 5);
		sudoku.setValue(3, 3, 2);
		sudoku.setValue(3, 4, 1);
		sudoku.setValue(3, 7, 9);
		sudoku.setValue(4, 1, 5);
		sudoku.setValue(4, 6, 6);
		sudoku.setValue(5, 0, 6);
		sudoku.setValue(5, 7, 2);
		sudoku.setValue(5, 8, 8);
		sudoku.setValue(6, 0, 4);
		sudoku.setValue(6, 1, 1);
		sudoku.setValue(6, 3, 6);
		sudoku.setValue(6, 5, 8);
		sudoku.setValue(7, 0, 8);
		sudoku.setValue(7, 1, 6);
		sudoku.setValue(7, 4, 3);
		sudoku.setValue(7, 6, 1);
		sudoku.setValue(8, 6, 4);
		
		assertTrue("Succesfully solved a sample sudoku grid", sudoku.solve());
	}
	//Test to solve an unsolvable sudoku with two eights in the same region
	@Test
	void testSolvingFirstUnsolvableSudoku() {
		sudoku.setValue(0, 0, 8);
		sudoku.setValue(1, 1, 8);
		assertTrue(sudoku.solve() == false);
	}
	//Test to solve an unsolvable sudoku with two eights in the same row but different regions
	@Test
	void testSolvingSecondUnsolvableSudoku() {
		sudoku.setValue(0, 0, 8);
		sudoku.setValue(0, 6, 8);
		assertTrue(sudoku.solve() == false);
	}
	//Test to solve an unsolvable sudoku with two eights in the same column but different regions
	@Test
	void testSolvingThirdUnsolvableSudoku() {
		sudoku.setValue(2, 3, 8);
		sudoku.setValue(6, 3, 8);
		assertTrue(sudoku.solve() == false);
	}
}
