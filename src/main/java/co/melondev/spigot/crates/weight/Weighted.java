package co.melondev.spigot.crates.weight;

public interface Weighted<T extends Weighted<T>> extends Comparable<T> {
	
	int getWeight();
	
	@Override
	default int compareTo(T o) {
		return Integer.compare(this.getWeight(), o.getWeight());
	}
	
}
