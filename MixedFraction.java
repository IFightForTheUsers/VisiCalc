//Created by Brett Wortzman (modified toString to only return improper fractions)
public class MixedFraction {
	public MixedFraction(int whole){
		numerator = whole;
		denominator = 1;
		reduce();
	}
	
	public MixedFraction(int num, int denom){
		numerator = num;
		denominator = denom;
		reduce();
	}
	
	public MixedFraction(int whole, int num, int denom){
		numerator = Math.abs(num);
		numerator += (denom * Math.abs(whole));
		denominator = denom;
		if (num < 0 || whole < 0) {
			numerator *= -1;
		}
		reduce();
	}
	
	public String toString()
	{
		if (numerator % denominator  == 0)
		{
			return (numerator / denominator) + "/" + "1";
		}
		else
		{
			return numerator + "/" + denominator;
		}
	}
	
	private void reduce()
	{
		int n = numerator, d = denominator;
		
		while (d != 0)
		{
			int temp = d;
			d = n % d;
			n = temp;
		}
		
		numerator = (numerator / n);
		denominator = (denominator / n);
		
		if (denominator < 0)
		{
			denominator *= -1;
			numerator *= -1;
		}
	}
	
	public MixedFraction plus(MixedFraction other)
	{
		return new MixedFraction((numerator * other.denominator) + (other.numerator * denominator),
								 (denominator * other.denominator));
	}

	public MixedFraction minus(MixedFraction other)
	{
		return new MixedFraction((numerator * other.denominator) - (other.numerator * denominator),
								 (denominator * other.denominator));
	}
	
	public MixedFraction times(MixedFraction other)
	{
		return new MixedFraction(numerator * other.numerator, denominator * other.denominator);
	}

	public MixedFraction dividedBy(MixedFraction other)
	{
		return new MixedFraction(numerator * other.denominator, denominator * other.numerator);
	}

	private int numerator;
	private int denominator;
}