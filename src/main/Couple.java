package main;


//classe pour ne pas s'embeter à carrément aller chercher des Map si on a qu'une entré
public class Couple <K,V>{
	
	private K key;
	private V value;
	
	public Couple(K key, V value)
	{
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

}
