/**
 * @author Steven Yang
 * AP Computer Science Project
 */
import java.io.*;
public class VisiCalc {
	public static void main(String[] args) throws IOException{
		BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
		Spreadsheet spr = new Spreadsheet();
		String command = null;
		
		do { 
			try {
				System.out.println(spr);
				command = kb.readLine();
				
				if (command.toLowerCase().startsWith("help")){
					System.out.println("Valid Commands:\n<cell id>: displays the contents of the cell" +
							"\n<cell id> = <formula/value>: assign the formula or value to the cell" +
							"\nclear <cell id>: clear the value of the cell" +
							"\ndisplay: displays the full table" +
							"quit: exits the program" +
							"\n\nFormulas are enclosed by parentheses " +
							"and should have spaces around each element. \nExample of range: D1-F7");
				}
				
				else if (command.toLowerCase().startsWith("clear")){ //Also clears range (EXTRA CREDIT!!!)
					int col1 = ("" + command.charAt(6)).toLowerCase().charAt(0) - 97;
					
					if (command.contains("-")){
						String[] range = command.split("-");
						int row1 = Integer.parseInt(range[0].substring(7));
						int col2 = ("" + range[1].charAt(0)).toLowerCase()
						.charAt(0) - 97;
						int row2 = Integer.parseInt(
								range[1].substring(1));
						for (int i = row1; i <= row2; i++){
							for (int j = col1; j <= col2; j++){
								spr.clearCell(i,j);
							}
						}
					}
					else {
						int row1 = Integer.parseInt(command.substring(7));
						spr.clearCell(row1,col1);
					}
				}
				
				
				else if (!command.toLowerCase().equals("quit") 
						&& !command.toLowerCase().equals("display")){
					int col1 = ("" + command.charAt(0)).toLowerCase().charAt(0) - 97;
					
					if (command.contains("=")){ 
						String[] equation = command.split("=");
						equation[0] = equation[0].trim();
						equation[1] = equation[1].trim();
						if (equation[1].charAt(0) == '(' && 
								equation[1].charAt(equation[1].length() - 1) == ')'){//If formula
							if (equation[0].contains("-")){                //If formula range
								String[] range = equation[0].split("-");  
								int row1 = Integer.parseInt(range[0].substring(1));
								int col2 = ("" + range[1].charAt(0)).toLowerCase()
								.charAt(0) - 97;
								int row2 = Integer.parseInt(
										range[1].substring(1));
								for (int i = row1; i <= row2; i++){
									for (int j = col1; j <= col2; j++){
										spr.setCell(i, j, equation[1], spr);
									}
								}
							}
							else{                                        //Sets cell to formula
								int row1 = Integer.parseInt(equation[0].substring(1));
								spr.setCell(row1, col1, equation[1], spr);
							}
						}
						
						else if (equation[0].contains("-")){               //if value range
							String[] range = equation[0].split("-");
							int row1 = Integer.parseInt(range[0].substring(1));
							int col2 = ("" + range[1].charAt(0)).toLowerCase()
							.charAt(0) - 97;
							int row2 = Integer.parseInt(
									range[1].substring(1));
							Value value = Value.parseValue(
									equation[1]);
							for (int i = row1; i <= row2; i++){
								for (int j = col1; j <= col2; j++){
									spr.setCell(i, j, value);
								}
							}
						}
						else{                                     //Sets cell to value or reference
							int row1 = Integer.parseInt(equation[0].substring(1));
							try {
								Value value = Value.parseValue(
										equation[1]);
								spr.setCell(row1, col1, value);
							}
							catch (NumberFormatException e){
								spr.setCell(row1, col1, equation[1], spr);
							}
						}
					}
					
					else {                                        //Display cell 
						int row1 = Integer.parseInt(command.substring(1));
						if (spr.getSpreadsheet()[row1][col1].isFormula()){
							System.out.println( "( " + ((FormulaCell) spr.getSpreadsheet()[row1][col1])
							                                              .getFormula() + " )");
						}
						else System.out.println(spr.getSpreadsheet()[row1][col1]);
					}
				}
			} catch (Exception e) {
				System.out.println("Invalid input.");
			}
		} while (!command.toLowerCase().equals("quit"));
			
	}
}
