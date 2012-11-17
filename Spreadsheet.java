/**
 * @author Steven Yang
 *
 */
public class Spreadsheet {
	public Spreadsheet(){
		
		for (int i = 0; i < ROWS; i++){
			for (int j = 0; j < COLUMNS; j++){
				spreadsheet[i][j] = new Cell();
			}
		}
	}
	public String toString(){
		String printForm = "__";
		char c = 'A';
		for (int i = 0; i < COLUMNS; i++){
			printForm += "_/**==" + c + "==**\\";
			c++;
		}
		printForm += "\n";
		for (int i = 0; i < spreadsheet.length; i++){
			printForm += (i < 10 ? "  ": " ") + i;
			for (int j = 0; j < spreadsheet[i].length; j++){
				printForm += spreadsheet[i][j].toString();
			}
			printForm += "\n";
		}
		return printForm;
	}
	
	public Cell[][] getSpreadsheet(){
		return spreadsheet;
	}
	
	public void clearCell(int row, int column){
		spreadsheet[row][column] = new Cell();
	}
	
	public void setCell(int row, int column, Value val){
		spreadsheet[row][column] = new Cell(val);
	}
	
	public void setCell(int row, int column, String formula, Spreadsheet spr){
		spreadsheet[row][column] = new FormulaCell(spr, formula);
	}
	
	public final int ROWS = 25;
	public final int COLUMNS = 13;
	private Cell[][] spreadsheet = new Cell[ROWS][COLUMNS];
}
