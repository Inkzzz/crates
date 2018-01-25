package co.melondev.spigot.crates.weight;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public final class WeightedTable<T> {
	
	public static <T> Builder<T> builder() {
		return new Builder<>();
	}
	
	public static final class Builder<T> {
		
		private final List<WeightedValue<T>> weightedValues = new ArrayList<>();
		
		private Builder() {
		
		}
		
		public WeightedTable<T> build() {
			return new WeightedTable<>(this.weightedValues);
		}
		
		public Builder<T> add(WeightedValue<T> weightedValue) {
			Objects.requireNonNull(weightedValue, "weightedValue");
			this.weightedValues.add(weightedValue);
			return this;
		}
		
		public Builder<T> addAll(Collection<WeightedValue<T>> weightedValues) {
			weightedValues.forEach(this::add);
			return this;
		}
		
	}
	
	private final XorShiftRandom xorShiftRandom = new XorShiftRandom();
	private final List<WeightedEntry> entries;
	
	private int total;
	
	private WeightedTable(List<WeightedValue<T>> weightedValues) {
		this.entries = this.calculateWeights(weightedValues);
	}
	
	private List<WeightedEntry> calculateWeights(List<WeightedValue<T>> weightedValues) {
		List<WeightedEntry> weightedEntries = new ArrayList<>(weightedValues.size());
		
		WeightedEntry last = null;
		
		for (WeightedValue<T> weightedValue : weightedValues) {
			int min;
			int max;
			
			if (last == null) {
				min = 0;
				max = weightedValue.getWeight();
			} else {
				min = last.max;
				max = min + weightedValue.getWeight();
			}
			
			last = new WeightedEntry(weightedValue.getValue(), min, max);
			weightedEntries.add(last);
		}
		
		this.total = last == null ? 0 : last.max;
		
		return weightedEntries;
	}
	
	public T nextValue() {
		int roll = this.xorShiftRandom.nextInt(this.total);
		
		return this.entries.stream()
				.filter(entry -> roll >= entry.min && roll <= entry.max)
				.map(entry -> entry.value)
				.findFirst()
				.orElse(null);
	}
	
	private final class WeightedEntry {
		
		private final T value;
		private final int min;
		private final int max;
		
		public WeightedEntry(T value, int min, int max) {
			this.value = value;
			this.min = min;
			this.max = max;
		}
		
	}
	
}
