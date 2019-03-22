package types;

import set.SetInterface;

public class Pos<T> {
	public Boolean isValid;
	protected T pointer;
	protected SetInterface set;

	public Pos(T pointer, SetInterface set) {
		this.isValid = false;
		this.pointer = pointer;
		this.set = set;
	}

	public T getPointer() {
		return pointer;
	}

	public void setPointer(T pointer) {
		this.pointer = pointer;
	}
}