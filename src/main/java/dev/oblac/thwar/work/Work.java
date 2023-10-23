package dev.oblac.thwar.work;

import java.util.function.Supplier;

/**
 * Work abstraction.
 */
public class Work implements Supplier<String> {

	public static String run() {
		return CalculatePi.calc10000();
	}

	public static void runAndSleep() {
		run();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ignored) {
		}
	}

	@Override
	public String get() {
		return run();
	}
}
