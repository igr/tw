package dev.oblac.thwar.util;

import java.time.Duration;
import java.util.function.Supplier;

public class StopWatches {

	public static RunningStopwatch start() {
		return new RunningStopwatch();
	}

	public static MeasuredDuration stop(RunningStopwatch runningStopwatch) {
		return new MeasuredDuration(runningStopwatch);
	}

	public static class RunningStopwatch {
		private final long start = System.currentTimeMillis();
		public long elapsedMillis() {
			return System.currentTimeMillis() - start;
		}
	}
	public static class MeasuredDuration implements Supplier<Duration> {
		final Duration elapsed;
		MeasuredDuration(RunningStopwatch runningStopwatch) {
			this.elapsed = Duration.ofMillis(System.currentTimeMillis() - runningStopwatch.start);
		}

		public Duration get() {
			return elapsed;
		}
	}


	public static Duration run(Runnable runnable) {
		var sw = start();
		runnable.run();
		return stop(sw).get();
	}
}

