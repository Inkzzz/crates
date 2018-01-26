package co.melondev.spigot.crates;

import java.util.List;
import java.util.ListIterator;

public class Phase {
	
	private final long phaseLength; // max crate refresh times
	private final long phaseRefreshInterval; // crate refresh time interval between calling executions
	private final ListIterator<PhaseExecution> phaseExecutionListIterator;
	
	private int executedTime;
	private int executedPhaseTime;
	
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
		return this.executedTime == 0 || this.executedPhaseTime >= this.phaseRefreshInterval;
	}
	
	public void incrementExecutionTime() {
		this.executedTime++;
		this.executedPhaseTime++;
	}
	
	public PhaseExecution getNextPhaseExecution() {
		if (this.isPhaseOver()) {
			return null;
		}
		
		this.executedPhaseTime = 0;
		
		return this.phaseExecutionListIterator.hasNext() ? this.phaseExecutionListIterator.next() : null;
	}
	
}
