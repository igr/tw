package dev.oblac.thwar.work;

import dev.oblac.thwar.util.Sleep;

import java.util.function.Supplier;

/**
 * Work abstraction.
 */
public class Work implements Supplier<String> {

	public static String run() {
		return CalculatePi.calc3sec();
	}

	public static void infSleep() {
		Sleep.forever();
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
