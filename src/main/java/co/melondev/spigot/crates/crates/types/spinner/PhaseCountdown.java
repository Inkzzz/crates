package co.melondev.spigot.crates.crates.types.spinner;

import java.util.Arrays;

import co.melondev.spigot.crates.crates.Phase;

class PhaseCountdown extends Phase {
	
	public PhaseCountdown() {
		super(3, 1, Arrays.asList(new PhaseExecutionCountdown3(),
				new PhaseExecutionCountdown2(), new PhaseExecutionCountdown1())
		);
	}
	
}
