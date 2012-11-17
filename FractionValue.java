/**
 * @author Steven Yang
 *
 */
public class FractionValue extends NumberValue{
	public FractionValue(int numer, int denom){
		frac = new MixedFraction(numer, denom);
		doubleVal = (double) numer / denom;
	}
	
	public boolean isFraction() { return true; }
	
	public String toString(){
		return frac.toString();
	}
	
	public MixedFraction getFraction(){
		return frac;
	}
	
	private MixedFraction frac;
}
