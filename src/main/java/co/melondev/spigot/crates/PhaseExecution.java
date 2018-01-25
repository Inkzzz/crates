package co.melondev.spigot.crates;

@FunctionalInterface
public interface PhaseExecution {
	
	void execute(CrateContext context);
	
}
