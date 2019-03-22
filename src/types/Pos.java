package types;


public class Pos<T> {
	public Boolean isValid;
	protected T pointer;
	
	public Pos() {
		this.isValid = false;
	}

	public T getPointer() {
		return pointer;
	}

	public void setPointer(T pointer) {
		this.pointer = pointer;
	}
}