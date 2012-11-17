/**
 * @author Steven Yang
 *
 */
public class ErrorValue extends Value{
	
	public boolean isError() { return true; }
	
	public String toString(){
		return "I AM ERROR";
	}
}
