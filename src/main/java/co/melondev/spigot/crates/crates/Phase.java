package co.melondev.spigot.crates.crates;

import java.util.List;
import java.util.ListIterator;

public class Phase {
	
	private final int phaseLength;
	private final int phaseRefreshInterval;
	private final ListIterator<PhaseExecution> phaseExecutionListIterator;
	
	private int executedTime;
	
	public Phase(int phaseLength, int phaseRefreshInterval, List<PhaseExecution> phaseExecutions) {
		this.phaseLength = phaseLength;
		this.phaseRefreshInterval = phaseRefreshInterval;
		this.phaseExecutionListIterator = phaseExecutions.listIterator();
	}
	
	public int getPhaseLength() {
		return this.phaseLength;
	}
	
	public int getPhaseRefreshInterval() {
		return this.phaseRefreshInterval;
	}
	
	public boolean isPhaseOver() {
		return this.executedTime > this.phaseLength;
	}
	
	public boolean shouldCallNextPhaseExecution() {
		return this.executedTime % this.phaseRefreshInterval == 0;
	}
	
	public void incrementExecutionTime() {
		this.executedTime++;
	}
	
	public PhaseExecution getNextPhaseExecution() {
		return this.phaseExecutionListIterator.hasNext() ? this.phaseExecutionListIterator.next() : null;
	}
	
}
