/**
 * @author Steven Yang
 * 
 */
public abstract class NumberValue extends Value {

	public boolean isNumber() {
		return true;
	}

	public static Value add(NumberValue a, NumberValue b) {
		if (a.isError()) {
			return new ErrorValue();
		}
		if (a.isFraction() && b.isFraction()) {
			MixedFraction m = ((FractionValue) a).getFraction();
			MixedFraction n = ((FractionValue) b).getFraction();
			return Value.parseValue(m.plus(n).toString());
		}
		if (a.isCurrency() || b.isCurrency()) {
			return Value.parseValue("$" + (a.doubleVal + b.doubleVal));
		}
		return Value.parseValue("" + (a.doubleVal + b.doubleVal));
	}

	public static Value subtract(NumberValue a, NumberValue b) {
		if (a.isError()) {
			return new ErrorValue();
		}
		if (a.isFraction() && b.isFraction()) {
			MixedFraction m = ((FractionValue) a).getFraction();
			MixedFraction n = ((FractionValue) b).getFraction();
			return Value.parseValue(m.minus(n).toString());
		}
		if (a.isCurrency() || b.isCurrency()) {
			return Value.parseValue("$" + (a.doubleVal - b.doubleVal));
		}
		return Value.parseValue("" + (a.doubleVal - b.doubleVal));
	}
	
	public static Value multiply(NumberValue a, NumberValue b) {
		if (a.isError()) {
			return new ErrorValue();
		}
		if (a.isFraction() && b.isFraction()) {
			MixedFraction m = ((FractionValue) a).getFraction();
			MixedFraction n = ((FractionValue) b).getFraction();
			return Value.parseValue(m.times(n).toString());
		}
		if (a.isCurrency() || b.isCurrency()) {
			return Value.parseValue("$" + (a.doubleVal * b.doubleVal));
		}
		return Value.parseValue("" + (a.doubleVal * b.doubleVal));
	}
	
	public static Value divide(NumberValue a, NumberValue b) {
		if (a.isError()) {
			return new ErrorValue();
		}
		if (a.isFraction() && b.isFraction()) {
			MixedFraction m = ((FractionValue) a).getFraction();
			MixedFraction n = ((FractionValue) b).getFraction();
			return Value.parseValue(m.minus(n).toString());
		}
		if (a.isCurrency() || b.isCurrency()) {
			return Value.parseValue("$" + (a.doubleVal / b.doubleVal));
		}
		return Value.parseValue("" + (a.doubleVal / b.doubleVal));
	}

	protected double doubleVal;
}
