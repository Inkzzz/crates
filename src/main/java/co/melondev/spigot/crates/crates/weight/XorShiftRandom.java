package co.melondev.spigot.crates.crates.weight;

public final class XorShiftRandom {
	
	private long last;
	
	public XorShiftRandom(long last) {
		this.last = last;
	}
	
	public XorShiftRandom() {
		this(System.currentTimeMillis());
	}
	
	public int nextInt(int max) {
		long last = this.last;
		last ^= (last << 21);
		last ^= (last >>> 35);
		last ^= (last << 4);
		
		int out = (int) last % (max + 1);
		
		this.last = last;
		
		return (out < 0) ? -out : out;
	}
	
}
