package types;

import set.SetInterface;

public class Pos<T> {
	public Boolean isValid;
	protected T pointer;
	protected SetInterface set;
	private int previousIndex;
	private int nextIndex; 

	public Pos(T pointer, SetInterface set) {
		this.isValid = false;
		this.pointer = pointer;
		this.set = set;
	}

	public Pos(T pointer, SetInterface set, int previousIndex, int nextIndex) {
		this.isValid = false;
		this.pointer = pointer;
		this.set = set;
		this.previousIndex = previousIndex;
		this.nextIndex = nextIndex;
	}

	public T getPointer() {
		return pointer;
	}

	public int getPreviousIndex() {
		return previousIndex;
	}

	public int getNextIndex() {
		return nextIndex;
	}

	public void setPointer(T pointer) {
		this.pointer = pointer;
	}

	public SetInterface getSet() {
		return set;
	}
}