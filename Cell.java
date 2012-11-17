/**
 * @author Brett Wortzman
 *
 */
public class Cell {
	private Value val;
	
	public Cell() {
		this.val = null;
	}
	
	public Cell(Value val) {
		this.val = val;
	}
	
	public Value getValue() {
		return val;
	}

	public boolean isFormula() { return false; }
	
	public String toString() {
		Value val = getValue();
		return String.format("| %8.8s |", (val == null ? "" : val.toString()));
	}
}
