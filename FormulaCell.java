/**
 * @author Steven Yang
 *
 */
import java.util.StringTokenizer;

public class FormulaCell extends Cell {

	// Constructs a FormulaCell.
	// @param formula The formula for this cell (NOT the result fo the formula).
	public FormulaCell(Spreadsheet spr, String formula) {
		this.spr = spr;
		if (formula.charAt(0) == '(')
		this.formula = formula.substring(2 , formula.length()-2);
		else 
			this.formula = formula;
	}
	
	public String getFormula() {
		return formula;
	}
	
	public Value getValue(){
		if (!formula.contains(" ")){
			int col = ("" + formula.charAt(0)).toLowerCase().charAt(0) - 97;
			int row = Integer.parseInt(formula.substring(1));
			return spr.getSpreadsheet()[row][col].getValue();
		}
		String[] parts = formula.split(" ");	
		if (parts[0].toLowerCase().equals("concat")){
			String[] range = parts[1].split("-");  
			int col1 = ("" + range[0].charAt(0)).toLowerCase().charAt(0) - 97;
			int row1 = Integer.parseInt(range[0].substring(1));
			int col2 = ("" + range[1].charAt(0)).toLowerCase()
			.charAt(0) - 97;
			int row2 = Integer.parseInt(range[1].substring(1));
			
			String result = "";
			
			for (int i = row1; i <= row2; i++){
				for (int j = col1; j <= col2; j++){
					
					try {
						if (!spr.getSpreadsheet()[i][j].getValue().isText()){
							return new ErrorValue();
						}
						else
							result = result + spr.getSpreadsheet()[i][j]
							                              .getValue().toString();
					}
					catch (NullPointerException e){
					}
					
				}
			}
			return Value.parseValue("\"" + result + "\"");
		}
		else if (parts[0].toLowerCase().equals("sum") 
				|| parts[0].toLowerCase().equals("avg")){
			String[] range = parts[1].split("-");  
			int col1 = ("" + range[0].charAt(0)).toLowerCase().charAt(0) - 97;
			int row1 = Integer.parseInt(range[0].substring(1));
			int col2 = ("" + range[1].charAt(0)).toLowerCase()
			.charAt(0) - 97;
			int row2 = Integer.parseInt(range[1].substring(1));
			
			Value result = Value.parseValue("0/1");
			int numOfCells = 0;
			
			for (int i = row1; i <= row2; i++){
				for (int j = col1; j <= col2; j++){
					
					try {
						if (!spr.getSpreadsheet()[i][j].getValue().isNumber()){
							return new ErrorValue();
						}
						else
							result = NumberValue.add((NumberValue) result,
									(NumberValue)spr.getSpreadsheet()[i][j].getValue());
						numOfCells++;
					}
					catch (NullPointerException e){
						result = NumberValue.add((NumberValue) result,
								(NumberValue) Value.parseValue(
										(result.isFraction() ? "0/1" : "0")));
					}
					
				}
			}
			if (numOfCells == 0)     //Deals with empty cells
				return Value.parseValue("0");
			if (parts[0].toLowerCase().equals("sum"))
				return result;
			else 
				return NumberValue.divide((NumberValue) result, 
						(NumberValue) Value.parseValue(numOfCells + "/1"));
		}
		else {
			Value result = null;
			boolean allEmpty = true;
			for (int i = 1; i < parts.length; i += 2){
				Value a, b;
				
				try {
					a = Value.parseValue(parts[i-1]);
				}
				catch (NumberFormatException e){
					int col1 = ("" + parts[i-1].charAt(0)).toLowerCase().charAt(0) - 97;
					int row1 = Integer.parseInt(parts[i-1].substring(1));
					a = spr.getSpreadsheet()[row1][col1].getValue();
				}
				
				
				try {
					b = Value.parseValue(parts[i+1]);;
				}
				catch (NumberFormatException e){
					int col1 = ("" + parts[i+1].charAt(0)).toLowerCase().charAt(0) - 97;
					int row1 = Integer.parseInt(parts[i+1].substring(1));
					b = spr.getSpreadsheet()[row1][col1].getValue();
				}
				
				if ((a == null || a.isNumber()) && (b == null || b.isNumber())){
					if (a == null){
						a = Value.parseValue("0/1");
					}
					if (b == null){
						b = Value.parseValue("0/1");
					}
					else 
						allEmpty = false;
					
					if (parts[i].equals("+"))
						result = NumberValue.add((NumberValue)a,(NumberValue)b);
					else if (parts[i].equals("-"))
						result = NumberValue.subtract((NumberValue)a,(NumberValue)b);
					else if (parts[i].equals("*"))
						result = NumberValue.multiply((NumberValue)a,(NumberValue)b);
					else if (parts[i].equals("/"))
						result = NumberValue.divide((NumberValue)a,(NumberValue)b);
					else return new ErrorValue();
				}

				else if ((a == null || a.isText()) && (b == null || b.isText())){
					if (a != null || b != null) 
						allEmpty = false;
					if (a == null){
						a = Value.parseValue("\"\"");
					}
					if (b == null){
						b = Value.parseValue("\"\"");
					}
					if (parts[i].equals("+"))
						result = Value.parseValue("\"" + a.toString() + b.toString() + "\"");
				}
				else return new ErrorValue();
				
				if (result.isText()){
					parts[i+1] = "\"" + result.toString() + "\"";
				}
				else 
					parts[i+1] = result.toString();
			}
			if (allEmpty)     //Deals with empty cells
				return Value.parseValue("0");
			return result;
		}
	}
	
	@Override
	public boolean isFormula() { return true; }
	
	private String formula;
	private Spreadsheet spr;
}
