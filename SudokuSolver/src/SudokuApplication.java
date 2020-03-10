import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Class for sudoku GUI
 * 
 * @author ed2576sj-s
 */
public class SudokuApplication extends Application {
	private OneLetterTextField[][] tf = new OneLetterTextField[9][9];
	private Button solveButton = new Button("Solve");
	private Button clearButton = new Button("Clear");
	private HBox buttonBox = new HBox(20);
	private TilePane grid = new TilePane();
	
	/**
	 * Launches the GUI
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}
	/**
	 * Builds the GUI
	 */
	@Override
	public void start(Stage stage) throws Exception {
		
		BorderPane root = new BorderPane();
		root.setBottom(buttonBox);
		root.setCenter(grid);
		
		stage.setScene(new Scene(root, 680, 700));
		stage.setTitle("Sudoku");
		stage.setResizable(false);
		
		buttonBox.setPadding(new Insets(0, 0, 40, 40));
		buttonBox.getChildren().addAll(solveButton, clearButton);
		grid.setPadding(new Insets(40, 40, 20, 40));
		grid.setVgap(1);
		grid.setHgap(1);
		solveButton.setOnAction(event -> clickSolveButton());
		clearButton.setOnAction(event -> clickClearButton());
		
		displayGrid();
		stage.show();
	}
	//Displays the sudoku in the GUI
	private void displayGrid() {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				OneLetterTextField box = new OneLetterTextField();
				box.setPrefSize(65, 65);
				box.setFont(Font.font("Righteous", FontWeight.BOLD, 33));
				if (row == 3 || row == 4 || row == 5 || col == 3 || col == 4 || col == 5) {
					box.setStyle("-fx-background-color: #8D8D92;");
					if (col == 3 && row == 3 || col == 4 && row == 3 || col == 5 && row == 3) {
						box.setStyle("-fx-background-color: #A9A988;");
					}
					if (col == 3 && row == 4 || col == 4 && row == 4 || col == 5 && row == 4) {
						box.setStyle("-fx-background-color: #A9A988;");
					}
					if (col == 3 && row == 5 || col == 4 && row == 5 || col == 5 && row == 5) {
						box.setStyle("-fx-background-color: #A9A988;");
					}
				} else {
					box.setStyle("-fx-background-color: #A9A988;");
				}
				tf[row][col] = box;
				grid.getChildren().add(box);
			}
		}
	}
	//Prints the sudoku in the GUI
	private void print(Sudoku sudoku) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				tf[row][col].setText(sudoku.getValue(row, col) + "");
			}
		}
	}
	//Solves the sudoku
	private void clickSolveButton() {
		int[][] newGrid = new int[9][9];
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (tf[row][col].getLength() != 0) {
					newGrid[row][col] = Integer.parseInt(tf[row][col].getText());
				} else {
					newGrid[row][col] = 0;
				}
			}
		}
		Sudoku sudoku = new Sudoku(newGrid);
		if (sudoku.solve()) {
			print(sudoku);
		} else {
			Alert alt = new Alert(Alert.AlertType.INFORMATION);
			alt.setHeaderText("The sudoku is unsolvable. Please try again with different numbers.");
			alt.show();
		}
	}
	//Clears the textfields of the grid
	private void clickClearButton() {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				tf[row][col].setText("");
			}
		}
	}
}