package types;

import set.SetInterface;

public class Pos<T> {
	public Boolean isValid;
	protected T pointer;
	protected SetInterface set;
	private Integer previousIndex;
	private Integer nextIndex; 

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

	public void setPreviousIndex(int previousIndex) {
		this.previousIndex = previousIndex;
	}

	public void setNextIndex(int nextIndex) {
		this.nextIndex = nextIndex;
	}

	public int getNextIndex() {
		return nextIndex;
	}

	public SetInterface getSet() {
		return set;
	}

	@SuppressWarnings("unchecked")
	public void setPointer(Object pointer) {
		this.pointer = (T) pointer;
	}
}