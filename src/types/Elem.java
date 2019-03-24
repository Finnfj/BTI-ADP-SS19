package types;

public class Elem<T> {
	public int key;
	public T data;
	
	public Elem(T data) {
		this.data = data;
		this.key = this.hashCode();
	}
	
	public Elem(int key, T data) {
		this.data = data;
		this.key = key;
	}

	@Override
	public String toString() {
		return "Key: " + key + " Data: " + data;
	}
}