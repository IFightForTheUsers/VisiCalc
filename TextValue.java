/**
 * @author Steven Yang
 *
 */
public class TextValue extends Value{
	public TextValue(String val){
		text = val;
	}
	
	public String toString() {
		return text;
	}
	public boolean isText() { return true; }
	
	private String text;
}
