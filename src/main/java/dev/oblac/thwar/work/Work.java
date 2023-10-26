package dev.oblac.thwar.work;

import dev.oblac.thwar.util.Sleep;

import java.util.concurrent.Semaphore;
import java.util.function.Supplier;

import static dev.oblac.thwar.util.Threads.CPU_COUNT;

/**
 * Work abstraction.
 */
public class Work implements Supplier<String> {

	private static final Semaphore semaphore = new Semaphore(CPU_COUNT);

	public static String run() {
		return CalculatePi.calc3sec();
	}

	public static void infSleep() {
		Sleep.forever();
	}

	public static String runw() {
		return CalculatePi.calcWait(100, 500);
	}

	public static String runs() {
		return CalculatePi.calcSemaphore(100, 500, semaphore);
	}

	@Override
	public String get() {
		return run();
	}
}
