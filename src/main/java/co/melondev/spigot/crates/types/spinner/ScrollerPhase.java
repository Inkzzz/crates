package co.melondev.spigot.crates.types.spinner;

import java.util.Collections;

import co.melondev.spigot.crates.Phase;
import co.melondev.spigot.crates.PhaseExecution;

class ScrollerPhase extends Phase {
	
	public ScrollerPhase(long phaseLength, long phaseRefreshInterval) {
		super(phaseLength, phaseRefreshInterval, Collections.emptyList());
	}
	
	@Override
	public PhaseExecution getNextPhaseExecution() {
		if (this.isPhaseOver()) {
			return null;
		}
		
		return new ScrollPhaseExecution();
	}
	
}
