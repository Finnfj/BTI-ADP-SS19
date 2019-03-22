package types;

public class Elem {
	public int key;
	public Object data;
	
	public Elem(Object data) {
		this.data = data;
		this.key = this.hashCode();
	}
}