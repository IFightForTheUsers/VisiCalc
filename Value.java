/**
 * @author Brett Wortzman
 *
 */
import java.util.StringTokenizer;

public abstract class Value {
	public abstract String toString();
	
	public boolean isText() { return false; }
	public boolean isError() { return false; }
	public boolean isNumber() { return false; }
	public boolean isRealNumber() { return false; }
	public boolean isFraction() { return false; }
	public boolean isCurrency() { return false; }
	
	public static Value parseValue(String val) {
		Value value = null;
		if (val.charAt(0) == '\"') {
			// String - need to remove enclosing quotes
			value = new TextValue(val.substring(1, val.length() - 1));
		} else if (val.charAt(0) == '$') {
			// Currency
			value = new CurrencyValue(Double.parseDouble(val.substring(1)));
		} else if (val.contains("/")) {
			// Fraction
			StringTokenizer fracvals = new StringTokenizer(val, "/");
			value = new FractionValue(Integer.parseInt(fracvals.nextToken()), 
					Integer.parseInt(fracvals.nextToken()));
		} else {
			// Real Number
			value = new RealNumberValue(Double.parseDouble(val));
		}
		return value;
	}
}
