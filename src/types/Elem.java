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
	
	public Elem(int key) {
		this.key = key;
	}

	public void print() {
		// TODO Auto-generated method stub
		
	}
}