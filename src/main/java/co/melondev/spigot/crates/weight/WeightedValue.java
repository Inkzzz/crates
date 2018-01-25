package co.melondev.spigot.crates.weight;

public final class WeightedValue<T> implements Weighted<WeightedValue<T>> {
	
	private final T value;
	private final int weight;
	
	public WeightedValue(T value, int weight) {
		this.value = value;
		this.weight = weight;
	}
	
	public T getValue() {
		return this.value;
	}
	
	@Override
	public int getWeight() {
		return this.weight;
	}
	
}
