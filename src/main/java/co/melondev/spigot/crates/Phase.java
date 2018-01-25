package co.melondev.spigot.crates;

import java.util.List;
import java.util.ListIterator;

public class Phase {
	
	private final long phaseLength;
	private final long phaseRefreshInterval;
	private final ListIterator<PhaseExecution> phaseExecutionListIterator;
	
	private int executedTime;
	
	public Phase(long phaseLength, long phaseRefreshInterval, List<PhaseExecution> phaseExecutions) {
		this.phaseLength = phaseLength;
		this.phaseRefreshInterval = phaseRefreshInterval;
		this.phaseExecutionListIterator = phaseExecutions.listIterator();
	}
	
	public long getPhaseLength() {
		return this.phaseLength;
	}
	
	public long getPhaseRefreshInterval() {
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
		if (this.isPhaseOver()) {
			return null;
		}
		
		return this.phaseExecutionListIterator.hasNext() ? this.phaseExecutionListIterator.next() : null;
	}
	
}
