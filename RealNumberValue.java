/**
 * @author Steven Yang
 *
 */
public class RealNumberValue extends NumberValue{
	public RealNumberValue(double num){
		doubleVal = num;
	}
	
	public boolean isRealNumber() { return true; }
	
	public String toString(){
		return doubleVal + "";
	}
	
}
