/**
 * @author Steven Yang
 *
 */
public class CurrencyValue extends NumberValue{
	public CurrencyValue(double money){
		doubleVal = money;
	}

	public boolean isCurrency() { return true; }
	
	public String toString() {
		return "$" + doubleVal;
	}
}
